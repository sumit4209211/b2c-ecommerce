package com.lucenesearchecommerce.config;

import com.nullbeans.accounting.services.LuceneIndexServiceBean;
import org.springframework.context.annotation.Bean;
import javax.persistence.EntityManagerFactory;

@Configuration
public class LuceneConfig {
    
    @Bean
    public Analyzer analyzer() {
        return new SmartChineseAnalyzer();
    }

    @Bean
    public Directory directory() throws IOException {
        
        Path path = Paths.get(LUCENEINDEXPATH);
        File file = path.toFile();
        if(!file.exists()) {
            // If the folder does not exist, create
            file.mkdirs();
        }
        return FSDirectory.open(path);
    }
    
    
    @Bean
    public IndexWriter indexWriter(Directory directory, Analyzer analyzer) throws IOException {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        // Clear index
        indexWriter.deleteAll();
        indexWriter.commit();
        return indexWriter;
    }

    @Bean
    public SearcherManagerService searcherManager(Directory directory, IndexWriter indexWriter) throws IOException {
        SearcherManagerService searcherManager = new SearcherManagerService(indexWriter, false, false, new SearcherFactory());
        ControlledRealTimeReopenThread cRTReopenThead = new ControlledRealTimeReopenThread(indexWriter, searcherManager,
                5.0, 0.025);
        cRTReopenThead.setDaemon(true);
        // Thread name
        CRTReopenThead. setName ("Update IndexReader Thread");
        // Open threads
        cRTReopenThead.start();
        return searcherManager;
    }
}