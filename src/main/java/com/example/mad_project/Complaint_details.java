package com.example.mad_project;

public class Complaint_details {
    public  String name,phone,aadhar,email,address,complaint,description;

    public Complaint_details() {

    }
    public Complaint_details(String name, String phone, String aadhar,String email, String address, String complaint, String description) {
        this.name = name;
        this.phone = phone;
        this.aadhar = aadhar;
        this.email = email;
        this.address = address;
        this.complaint = complaint;
        this.description = description;
    }
}
