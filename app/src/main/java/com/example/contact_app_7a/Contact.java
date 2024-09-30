package com.example.contact_app_7a;

// Model Class
public class Contact {
    String name, phone, address, gender;

    public Contact() {
    }

    public Contact(String name, String phone, String address, String gender) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
