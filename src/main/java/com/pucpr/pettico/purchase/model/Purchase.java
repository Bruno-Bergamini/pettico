package com.pucpr.pettico.purchase.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Purchase(Integer userId, Date date) {
        this.userId = userId;
        this.date = date;
    }

    public Purchase() {}
}
