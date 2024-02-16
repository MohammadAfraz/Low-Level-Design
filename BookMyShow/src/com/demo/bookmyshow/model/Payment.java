package com.demo.bookmyshow.model;

public interface Payment {
    boolean doPayment(double amount);
    String getPaymentId();
}
