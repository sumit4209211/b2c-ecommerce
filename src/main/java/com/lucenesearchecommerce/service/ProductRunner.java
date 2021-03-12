package com.lucenesearchecommerce.service;


@Component
@Order(value = 1)
public class ProductRunner implements ApplicationRunner {
    
    @Autowired
    private ILuceneService service; 
    
    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        /**
         * After startup, the Product table is synchronized and the index is created
         */
        service.synProductCreatIndex();
    }

    @Override
    public void synProductCreatIndex() throws IOException {
        // Get all productLists
        List<Product> allProduct = mapper.getAllProduct();
        // Insert productList again
        luceneDao.createProductIndex(allProduct);
    }

}

