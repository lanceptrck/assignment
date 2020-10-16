package com.patrick.assignment.service;


import com.patrick.assignment.csv.CSVReader;
import com.patrick.assignment.model.Product;
import com.patrick.assignment.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final CSVReader reader = new CSVReader();

    @Bean
    CommandLineRunner initProductDatabase(ProductRepository repository) {
        return args -> {
            for (Product p : reader.readProductsFromCSV()) {
                log.info("Preloading " + repository.save(p));
            }
        };
    }

    @Bean
    CommandLineRunner initTransactionDatabase(TransactionRepository repository) {
        return args -> {
            for (Transaction t : reader.readTransactionsFromCSV()) {
                log.info("Preloading " + repository.save(t));
            }
        };
    }


}
