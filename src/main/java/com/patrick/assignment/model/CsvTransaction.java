package com.patrick.assignment.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class CsvTransaction {

    @CsvBindByName(column = "transactionId")
    Long id;

    @CsvBindByName(column = "productId")
    private Long productId;

    @CsvBindByName(column = "transactionAmount")
    private BigDecimal amount;

    @CsvBindByName(column = "transactionDateTime")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public CsvTransaction(Long id, Long productId, BigDecimal amount, Date date) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.date = date;
    }

    public CsvTransaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
