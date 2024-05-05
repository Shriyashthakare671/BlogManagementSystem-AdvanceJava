package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/searchblog")
public class SearchBlogServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    processRequest(req, resp);
    }
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    processRequest(req, resp);
}

protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
    List<Blog> list = new ArrayList<>();
        String word= req.getParameter("word");
        try(BlogDao b = new BlogDao())
        {
            list = b.fingbyname(word);
            
        }
        catch(Exception e)
        {
            
            e.printStackTrace();
        }
            resp.setContentType("text/html;");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<title>Blogs Searched by word</title>");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("curusr");
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
        out.println("<br/><br/>");
        out.println("<form action='searchblog' method='post'>" + "Enter any term to searchblog: " +
        "<input type= 'text' name='word' /><input type='submit' " +
        "value= 'search' /></form>");
        out.println("<br/><br/>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Title</th>");
        out.println("<th>Category</th>");
        out.println("<th>User</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");
        try(CategoryDao categoryDao = new CategoryDao())
        {
            for(Blog b:list){
                out.println("<tr>");
                out.printf("<td>%d</td>",b.getId());
                out.printf("<td>%s</td>",b.getTitle());
                Category c = categoryDao.findById(b.getCategoryId());
                out.printf("<td>%s</td>",c.getTitle());

                try(UserDao userDao = new UserDao()) {
                    User u = userDao.findById(b.getUserId());
                    out.printf("<td>%s</td>",b.getTitle());

                }
                out.printf("<td></td>");
                out.println("</tr>");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        out.println("</table>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }
}
