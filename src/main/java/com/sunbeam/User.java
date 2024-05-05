package com.sunbeam;
import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private String email;
    private String PAssword;
    private String phone;
    private Timestamp createdOn;

    public User(int id2, String name2, String email2, String password2, String phone2){

    }
   
    public User(int id, String name, String email, String pAssword, String phone,Timestamp createdOn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.PAssword = pAssword;
        this.phone = phone;
        this.createdOn = createdOn;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPAssword() {
        return PAssword;
    }

    public void setPAssword(String pAssword) {
        PAssword = pAssword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", PAssword=" + PAssword + ", phone=" + phone
                + ", createdOn=" + createdOn + "]";
    }

    

}
