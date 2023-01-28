package com.arcreations.pdfpencil;

public class RegistrationModal {

//this java class is used for storing my data


    private String subject;
    private String message;
    private String signature;
    private int id;


    // creating getter and setter methods


    public String getSubject() {
        return subject;
    }

    public void setSub(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //constructor
    public RegistrationModal(String subject, String message, String signature) {
        this.subject = subject;
        this.message = message;
        this.signature = signature;
    }
}
