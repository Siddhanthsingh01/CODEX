package com.example.codex.Logins;

public class UserHelperClass {
    String name, email, phone, prov, prov_uid;

    public UserHelperClass() {
    }


    public UserHelperClass(String name, String email, String prov, String prov_uid) {
        this.name = name;
        this.email = email;
        this.prov = prov;
        this.prov_uid = prov_uid;
    }

    public UserHelperClass(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }


    public String getProv_uid() {
        return prov_uid;
    }

    public void setProv_uid(String prov_uid) {
        this.prov_uid = prov_uid;
    }

}
