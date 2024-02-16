package com.demo.bookmyshow.utils;

public class PaymentSettler {
    public static PaymentSettler INSTANCE = new PaymentSettler();
    private PaymentSettler(){

    }
    public boolean refundPayment(String paymentId){
        //Simulating payment refund request received scenario
        return true;
    }
}
