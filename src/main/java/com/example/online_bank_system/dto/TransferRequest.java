package com.example.online_bank_system.dto;

public class TransferRequest {
    
    private int senderAccountNo;        // sender user
    private int receiverAccountNo;     // receiver account number
    private String receiverName;
    private String reason;
    private double amount;
    private String specialCode;

    public TransferRequest () {

    }


    public int getSenderAccountNo() {
        return senderAccountNo;
    }


    public void setSenderAccountNo(int senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }


    public int getReceiverAccountNo() {
        return receiverAccountNo;
    }


    public void setReceiverAccountNo(int receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }


    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }

}
