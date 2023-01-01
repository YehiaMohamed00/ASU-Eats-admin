package com.example.restaurantsadmin;

import java.io.Serializable;

public class Dish implements Serializable {
    String dishImg;
    String dishName;
    String dishDescription;
    double dishPrice;
    String dishAvailability;

    public Dish(String dishImg, String dishName, String dishDescription, double dishPrice, String dishAvailability) {
        this.dishImg = dishImg;
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.dishPrice = dishPrice;
        this.dishAvailability = dishAvailability;
    }

    public Dish(Dish dish){
        this.dishImg = dish.dishImg;
        this.dishName = dish.dishName;
        this.dishDescription = dish.dishDescription;
        this.dishPrice = dish.dishPrice;
        this.dishAvailability = dish.dishAvailability;
    }

    public String getDishImg() {
        return dishImg;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public String getDishAvailability() {
        return dishAvailability;
    }

    public void setDishAvailability(String dishAvailability) {
        this.dishAvailability = dishAvailability;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishImg=" + dishImg +
                ", dishName='" + dishName + '\'' +
                ", dishDescription='" + dishDescription + '\'' +
                ", dishPrice=" + dishPrice +
                ", dishAvailability=" + dishAvailability +
                '}';
    }
}
