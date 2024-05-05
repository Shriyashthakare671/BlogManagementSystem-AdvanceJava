package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/newblog")
public class Newblogservlet  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("curusr");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>New Blog</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("Hello, " + user.getName() + "<hr/>");

        out.println("<form method='get' action='Bloglists'>");
        out.println("<h3></h3>");
        out.println("<center> <img src='3.png' alt='Blog Logo' /> </center>");
        out.println("<a href='categories.html' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;' > Create category</a>");
        out.println("<a href='catlist' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Show categories </a>");

        out.println(
                "<a href='newblog' style='background-color:darkgray; padding: 14px 20px; text-align: center; display: inline-block;'>New Blog</a>");
        out.println(
                "<a href='searchblog' style='background-color:darkgray; padding: 14px 20px; text-align: center; display: inline-block;'>Find Blog</a>");
        out.println(
                "<a href='Bloglists' style='background-color:darkgray; padding: 14px 20px; text-align: center; display: inline-block;'>All Blog</a>");
        out.printf(
                "<a href='Bloglists?userid=%d' style='background-color:darkgray; padding: 14px 20px; text-align: center; display: inline-block;'>My Blog</a>",
                user.getId());
        out.println(
                "<a href='logout' style='background-color:darkgray; padding: 14px 20px; text-align: center; display: inline-block;'>Sign Out</a>");
        out.println("<br/><br/><br/>");
        out.println("</form>");
        out.println("<form method='post' action='saveblog'>");
        out.println("Title: <input type='text' name='title'/><br/>");
        try(CategoryDao categoryDao = new CategoryDao()) {
            out.println("Category: <select name='categoryId'>");
            List<Category> categoryList = categoryDao.findAll();
            for(Category c:categoryList)
                out.printf("<option value='%d'>%s</option>\n", c.getId(), c.getTitle());
            out.println("</select><br/>");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        out.println("Contents: <textarea rows='10' cols='60' name='contents'></textarea><br/>");
        // out.printf("Current User Id: <input type='text' name='userId' value='%d' readonly/><br/>\n", user.getId());
        out.printf("<input type='hidden' name='userId' value='%d'/><br/> \n", user.getId());
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");

    }

    

}
