package com.sunbeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao  implements AutoCloseable{
    private Connection con;
    public CategoryDao() throws Exception{
        con = DbUtil.getConnection();
    }
    public void close() throws Exception{
        if(con != null)
        con.close();
    }
    // add category
    public int save(Category c) throws Exception{
        System.out.println(""+c);
        System.out.println("Title :" + c.getTitle());
        System.out.println("Description :"+ c.getDescription());

        String sql = "insert into categories(title,description) values(?,?)";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,c.getTitle());
            stmt.setString(2,c.getDescription());
            int count = stmt.executeUpdate();
            return count;
        }

    }

//get all categories
    public List<Category> findAll() throws Exception{
        List<Category> list = new ArrayList<>();
        String sql = "Select * from categories";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt("Id");
                    String title = rs.getString("title");
                    String desc = rs.getString("description");
                    Category c = new Category(id,title,desc);
                    list.add(c);
                }
            }
        }
        return list;

    }

    // get category by id
    public Category findById(int categoryId) throws Exception {
       String sql = "Select * from categories where id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
        stmt.setInt(1, categoryId);
        try(ResultSet rs = stmt.executeQuery()) {
            if(rs.next()) {
                int id = rs.getInt("Id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Category c= new Category(id, title, description);
                return c;
            }
        } // rs.close();
        }//stmt.close();
        return null;
    }



}
