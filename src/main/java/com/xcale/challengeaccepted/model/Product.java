package com.xcale.challengeaccepted.model;

public class Product {

    private int id;
    private String description;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", description=" + description + ", amount=" + amount + "]";
    }
}
