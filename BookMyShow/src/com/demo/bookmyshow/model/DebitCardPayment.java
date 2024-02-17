package com.demo.bookmyshow.model;

public class DebitCardPayment implements Payment {
    String id;
    String referenceNumber;
    @Override
    public boolean doPayment(double amount) {
        //Simulate successful payment through payment gateway
        return true;
    }

    public String getPaymentId() {
        return id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
