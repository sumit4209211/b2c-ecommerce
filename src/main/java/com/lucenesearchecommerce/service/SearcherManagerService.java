package com.lucenesearchecommerce.service;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import javax.persistence.EntityManagerFactory;

public class SearcherManagerService {


    @Autowired
    private Analyzer analyzer;

    @Autowired
    private SearcherManager searcherManager;
    
    @Override
    public PageQuery<Product> searchProduct(PageQuery<Product> pageQuery) throws IOException, ParseException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();
        Product params = pageQuery.getParams();
        Map<String, String> queryParam = pageQuery.getQueryParam();
        Builder builder = new BooleanQuery.Builder();
        Sort sort = new Sort();
        // Sorting Rules
        com.infinova.yimall.entity.Sort sort1 = pageQuery.getSort();
        if (sort1 != null && sort1.getOrder() != null) {
            if ("ASC".equals((sort1.getOrder()).toUpperCase())) {
                sort.setSort(new SortField(sort1.getField(), SortField.Type.FLOAT, false));
            } else if ("DESC".equals((sort1.getOrder()).toUpperCase())) {
                sort.setSort(new SortField(sort1.getField(), SortField.Type.FLOAT, true));
            }
        }

        // Fuzzy Matching, Matching Words
        String keyStr = queryParam.get("searchKeyStr");
        if (keyStr != null) {
            // Enter spaces without ambiguous queries
            if (!"".equals(keyStr.replaceAll(" ", ""))) {
                builder.add(new QueryParser("name", analyzer).parse(keyStr), Occur.MUST);
            }
        }

        // Accurate query
        if (params.getCategory() != null) {
            builder.add(new TermQuery(new Term("category", params.getCategory())), Occur.MUST);
        }
        if (queryParam.get("lowerPrice") != null && queryParam.get("upperPrice") != null) {
            // Price Range Query
            builder.add(FloatPoint.newRangeQuery("price", Float.parseFloat(queryParam.get("lowerPrice")),
                    Float.parseFloat(queryParam.get("upperPrice"))), Occur.MUST);
        }
        PageInfo pageInfo = pageQuery.getPageInfo();
        TopDocs topDocs = indexSearcher.search(builder.build(), pageInfo.getPageNum() * pageInfo.getPageSize(), sort);

        pageInfo.setTotal(topDocs.totalHits);
        ScoreDoc[] hits = topDocs.scoreDocs;
        List<Product> pList = new ArrayList<Product>();
        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);
            System.out.println(doc.toString());
            Product product = new Product();
            product.setId(Integer.parseInt(doc.get("id")));
            product.setName(doc.get("name"));
            product.setCategory(doc.get("category"));
            product.setPlace(doc.get("place"));
            product.setPrice(Float.parseFloat(doc.get("price")));
            product.setCode(doc.get("code"));
            pList.add(product);
        }
        pageQuery.setResults(pList);
        return pageQuery;
    }


    @Override
    public void deleteProductIndexById(String id) throws IOException {
        indexWriter.deleteDocuments(new Term("id",id));
        indexWriter.commit();
    }
    
}
