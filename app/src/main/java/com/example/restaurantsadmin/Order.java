package com.example.restaurantsadmin;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    public String orderID;
    public String userID;
    public String orderDate;
    public String orderStatus;
    public String orderGate;
    public String orderTimePeriod;
    public Double orderPrice;
    public List<Dish> orderedDishes;

    public Order(String orderID, String userID, String orderDate, String orderStatus, String orderGate, String orderTimePeriod, Double orderPrice, List<Dish> orderedDishes) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderGate = orderGate;
        this.orderTimePeriod = orderTimePeriod;
        this.orderPrice = orderPrice;
        this.orderedDishes = orderedDishes;
    }

    public Order(String orderID, String userID, String orderDate, String orderStatus, String orderGate, String orderTimePeriod) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderGate = orderGate;
        this.orderTimePeriod = orderTimePeriod;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderGate() {
        return orderGate;
    }

    public void setOrderGate(String orderGate) {
        this.orderGate = orderGate;
    }

    public String getOrderTimePeriod() {
        return orderTimePeriod;
    }

    public void setOrderTimePeriod(String orderTimePeriod) {
        this.orderTimePeriod = orderTimePeriod;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<Dish> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<Dish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", userID='" + userID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderGate='" + orderGate + '\'' +
                ", orderTimePeriod='" + orderTimePeriod + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderedDishes=" + orderedDishes +
                '}';
    }
}
