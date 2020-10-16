package com.patrick.assignment.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import org.hibernate.annotations.Cascade;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Transaction {

    @CsvBindByName(column = "transactionId")
    private @Id
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    @CsvBindByName(column = "transactionAmount")
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @CsvBindByName(column = "transactionDateTime")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Transaction() {

    }

    public Transaction(Long id, Long productId, BigDecimal amount, Date date) {
        this.id = id;
        this.product = new Product(productId);
        this.amount = amount;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
