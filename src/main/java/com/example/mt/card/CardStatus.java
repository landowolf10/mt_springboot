package com.example.mt.card;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CardStatus {
    public CardStatus(Integer id, Integer clientId, String status, String city, LocalDate date) {
        this.id = id;
        this.clientId = clientId;
        this.status = status;
        this.city = city;
        this.date = date;
    }

    public CardStatus() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "clientid")
    private Integer clientId;
    private String status;
    private String city;
    private LocalDate date;

    @PrePersist
    private void onCreate() {
        date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
