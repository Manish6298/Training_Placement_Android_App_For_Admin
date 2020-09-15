package com.mind.loginregisterapps;

public class AdminData {

    String name,id,email,mobileno;

    public AdminData() {
    }

    public AdminData(String name, String id, String email, String mobileno) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.mobileno = mobileno;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

}
