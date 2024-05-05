package com.sunbeam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delblog")
public class DelectBlogservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @SuppressWarnings("unused")
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int blogId = Integer.parseInt(req.getParameter("id"));
        try (BlogDao blogDao = new BlogDao()) {
            int count = blogDao.deleteById(blogId);
            String message = "Blog " + count + "Deleted Sucessfully....";
            req.setAttribute("message", message);

        } catch (Exception e) {
            String message = "Blog Delete Failed....";
            req.setAttribute("message", e.getMessage());
            e.printStackTrace();
        }

        // resp.sendRedirect("Bloglists");

        RequestDispatcher rd = req.getRequestDispatcher("Bloglists");
        rd.forward(req, resp);
    }

}
