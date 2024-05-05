package com.sunbeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class UserDao implements AutoCloseable {

    private Connection con;

    public UserDao() throws Exception {
        con = DbUtil.getConnection();
    }

    @Override
    public void close() throws Exception {

        if (con != null)
            con.close();
    }

    // Registering New User
    public int save(User u) throws Exception {
        String sql = "insert into users(name, email, password, phone) values(?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPAssword());
            stmt.setString(4, u.getPhone());
            int count = stmt.executeUpdate();
            return count;
        }
    }

    // public int addUser(int id, String name, String email, String password, String
    // phone) {
    // String sql = "insert into users(id, name, email, password, phone)
    // values(?,?,?,?,?)";
    // try (PreparedStatement stmt = con.prepareStatement(sql)) {
    // stmt.setInt(1, id);
    // stmt.setString(2, name);
    // stmt.setString(3, email);
    // stmt.setString(4, password);
    // stmt.setString(5, phone);
    // int count = stmt.executeUpdate();
    // return count;
    // }
    // }


    // search user by id
    public User findById(int userId) throws Exception {
        String sql = "Select * from users where id=?";
        System.out.println("UserID::" + userId);
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    System.out.println(id);
                    String name = rs.getString("name");
                    System.out.println(name);
                    String email = rs.getString("email");
                    System.out.println(email);
                    String password = rs.getString("Password");
                    System.out.println(password);
                    String phone = rs.getString("Phone");
                    System.out.println(phone);
                    Timestamp created = rs.getTimestamp("Created_on");
                    User u = new User(id, name, email, password, phone, created);
                    return u;
                }
            } // rs.close();
        } // stmt.close();
        return null;
    }

    // Search user by emailAnd Password
    public User findbyemailAndPassword(String email, String Password) throws Exception {
        System.out.println("in method");
        String sql = "Select * from users where email=? And password=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, Password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    System.out.println(id);
                    String name = rs.getString("name");
                    System.out.println(name);
                    email = rs.getString("email");
                    System.out.println(email);
                    String password = rs.getString("Password");
                    System.out.println(password);
                    String phone = rs.getString("Phone");
                    System.out.println(phone);
                    Timestamp created = rs.getTimestamp("Created_on");

                    User u = new User(id, name, email, password, phone, created);
                    return u;
                }
            } // rs.close();
        } // stmt.close();
        return null;

    }

    

}
