package com.example.mt.card;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Card {
    public Card(Integer cardId, Integer clientId, String cardname, String city, String category, String status,
                LocalDate date) {
        this.cardId = cardId;
        this.clientId = clientId;
        this.cardname = cardname;
        this.city = city;
        this.category = category;
        this.status = status;
        this.date = date;
    }

    public Card() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardid")
    private Integer cardId;
    @Column(name = "clientid")
    private Integer clientId;
    private String cardname;
    private String city;
    private String category;
    private String status;
    private LocalDate date;

    @PrePersist
    private void onCreate() {
        date = LocalDate.now();
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
