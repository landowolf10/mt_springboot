package com.example.mt.client;

import jakarta.persistence.*;

@Entity
public class Clients {
    public Clients(Integer clientId, String cardName, String city, String category, String premium, String image) {
        this.clientId = clientId;
        this.cardName = cardName;
        this.city = city;
        this.category = category;
        this.premium = premium;
        this.image = image;
    }

    public Clients() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid")
    private Integer clientId;
    @Column(name = "cardname")
    private String cardName;
    private String city;
    private String category;
    private String premium;
    private String image;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
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

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
