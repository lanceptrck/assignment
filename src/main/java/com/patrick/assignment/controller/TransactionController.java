package com.patrick.assignment.controller;

import com.patrick.assignment.dto.DataTransferObject;
import com.patrick.assignment.dto.SummaryDTO;
import com.patrick.assignment.exception.TransactionNotFoundException;
import com.patrick.assignment.model.Product;
import com.patrick.assignment.model.Transaction;
import com.patrick.assignment.service.TransactionRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transaction")
    List<Transaction> all() {
        return repository.findAll();
    }

    @GetMapping("/transaction/{transactionId}")
    public Transaction getTransactionById(@PathVariable(name = "transactionId") Long id) {
        Transaction transaction = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        return transaction;
    }

    @GetMapping("/transaction/byProductName/{name}")
    public List<Transaction> getTransactionsByProductName(@PathVariable("name") String productName) {
        List<Transaction> transactions = repository.findTransactionByProductName(productName);
        return transactions;
    }

    @GetMapping("/assignment/transactionSummaryByManufacturingCity/{days}")
    public DataTransferObject transactionSummaryByManufacturingCity(@PathVariable(name = "days") Integer days) {

        List<Transaction> result = repository.findTransactionsBetweenStartDateEndDate(minusDays(days), new Date());

        List<SummaryDTO> dtoList = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : aggregateByCity(result).entrySet()) {
            SummaryDTO dto = new SummaryDTO();
            dto.setCityName(entry.getKey());
            dto.setTotalAmount(entry.getValue());
            dtoList.add(dto);
        }

        return new DataTransferObject(dtoList);
    }


    @GetMapping("/assignment/transactionSummaryByProducts/{days}")
    public DataTransferObject getTransactionSummaryByProducts(@PathVariable(name = "days") Integer days) {

        List<Transaction> result = repository.findTransactionsBetweenStartDateEndDate(minusDays(days), new Date());

        List<SummaryDTO> dtoList = new ArrayList<>();
        for (Map.Entry<Product, BigDecimal> entry : aggregateByProductName(result).entrySet()) {
            SummaryDTO dto = new SummaryDTO();
            dto.setProductName(entry.getKey().getName());
            dto.setTotalAmount(entry.getValue());
            dtoList.add(dto);
        }

        return new DataTransferObject(dtoList);
    }

    private Map<Product, BigDecimal> aggregateByProductName(List<Transaction> transactions) {
        Map<Product, BigDecimal> productSummary = new HashMap<>();

        for (Transaction t : transactions) {
            if (!productSummary.containsKey(t.getProduct()))
                productSummary.put(t.getProduct(), t.getAmount());
            else
                productSummary.put(t.getProduct(), productSummary.get(t.getProduct()).add(t.getAmount()));
        }

        return productSummary;
    }

    private Map<String, BigDecimal> aggregateByCity(List<Transaction> transactions) {
        Map<String, BigDecimal> productSummary = new HashMap<>();

        for (Transaction t : transactions) {
            if (!productSummary.containsKey(t.getProduct().getManufacturingCity()))
                productSummary.put(t.getProduct().getManufacturingCity(), t.getAmount());
            else
                productSummary.put(t.getProduct().getManufacturingCity(), productSummary.get(t.getProduct().getManufacturingCity()).add(t.getAmount()));
        }

        return productSummary;
    }

    private Date minusDays(Integer days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -days);
        Date dateBefore = cal.getTime();

        return dateBefore;
    }
}
