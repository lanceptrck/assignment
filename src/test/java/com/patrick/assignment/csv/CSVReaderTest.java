package com.patrick.assignment.csv;

import com.patrick.assignment.model.Product;
import com.patrick.assignment.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class CSVReaderTest {

    private CSVReader csvReader;

    @Before
    public void init() {
        csvReader = new CSVReader();
    }

    @Test
    public void test_product_init() {
        try {
            List<Product> products = csvReader.readProductsFromCSV();

            for(Product p : products){
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_transaction_init() {
        try {
            List<Transaction> transactions = csvReader.readTransactionsFromCSV();

            for(Transaction t : transactions){
                System.out.println(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get_all_transaction_csv(){
        String directoryPath = "src/main/resources/transactions";
        File[] filesInDirectory = new File(directoryPath).listFiles();
        for(File f : filesInDirectory){
            String filePath = f.getPath();
            String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
            if("csv".equals(fileExtenstion)){
                System.out.println("CSV file found -> " + filePath);
                // Call the method checkForCobalt(filePath);
            }
        }
    }
}
