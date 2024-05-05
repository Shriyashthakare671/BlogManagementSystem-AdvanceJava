package com.sunbeam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveblog")
public class Saveblogservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        Blog b = new Blog(userId, title, contents, userId, categoryId, null);

        System.out.println("Saving: " + b);
        try (BlogDao blogDao = new BlogDao()) {
            int count = blogDao.save(b);
            String message = "Blog " + count + " Saved Successfully.";
            req.setAttribute("message", message);
        } catch (Exception e) {
            String message = "Blog Save Failed.";
            req.setAttribute("message", message);
            e.printStackTrace();
        }
        //resp.sendRedirect("Bloglists");

       // ServletContext ctx = this.getServletContext();
        RequestDispatcher rd = req.getRequestDispatcher("Bloglists");
        // RequestDispatcher rd = req.getRequestDispatcher("bloglist");
        rd.forward(req, resp);

    }

}
