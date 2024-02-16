package com.demo.bookmyshow.model;

public class DebitCardPayment implements Payment {
    String id;
    @Override
    public boolean doPayment(double amount) {
        //Simulate successful payment through payment gateway
        return true;
    }

    public String getPaymentId() {
        return id;
    }
}
