package com.example.gamebazzar.model.DTO;

public class UpdateCartItemRequest {
    private int newQuantity;

    public int getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(int newQuantity) {
        this.newQuantity = newQuantity;
    }
}
