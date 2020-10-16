package com.patrick.assignment.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.patrick.assignment.model.CsvTransaction;
import com.patrick.assignment.model.Product;
import com.patrick.assignment.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReader {

    private List<Product> productList;

    public CSVReader() {
        productList = new ArrayList<>();
    }

    public List<Product> readProductsFromCSV() throws IOException {

        List<String> productCSVPathList = getAllCSVFromDirectoryPath("src/main/resources/product");

        for (String path : productCSVPathList) {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(path));
            ) {
                CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Product.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<Product> csvProductIterator = csvToBean.iterator();

                while (csvProductIterator.hasNext()) {
                    Product product = csvProductIterator.next();
                    productList.add(product);
                }
            }
        }


        return productList;
    }

    public List<Transaction> readTransactionsFromCSV() throws IOException {

        List<Transaction> transactions = new ArrayList<>();
        List<String> transactionCSVPathList = getAllCSVFromDirectoryPath("src/main/resources/transactions");

        if (transactionCSVPathList.isEmpty()) {
            throw new RuntimeException("No transaction csv files found");
        }

        for (String path : transactionCSVPathList) {
            try (
                    Reader reader = Files.newBufferedReader(Paths.get(path));
            ) {
                CsvToBean<CsvTransaction> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(CsvTransaction.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<CsvTransaction> csvTransactionIterator = csvToBean.iterator();

                while (csvTransactionIterator.hasNext()) {
                    CsvTransaction csvTransaction = csvTransactionIterator.next();

                    Transaction transaction = new Transaction();
                    transaction.setId(csvTransaction.getId());
                    transaction.setAmount(csvTransaction.getAmount());
                    transaction.setDate(csvTransaction.getDate());
                    transaction.setProduct(getProductInListById(csvTransaction.getProductId()));

                    transactions.add(transaction);
                }
            }
        }


        return transactions;
    }

    private Product getProductInListById(Long id){
        for(Product p : productList){
            if(p.getId().equals(id)){
                return p;
            }
        }

        return null;
    }

    private List<String> getAllCSVFromDirectoryPath(String directoryPath) {
        List<String> pathList = new ArrayList<>();

        File[] filesInDirectory = new File(directoryPath).listFiles();
        for (File f : filesInDirectory) {
            String filePath = f.getPath();
            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            if ("csv".equals(fileExtenstion)) {
                pathList.add(filePath);
            }
        }

        return pathList;
    }

}
