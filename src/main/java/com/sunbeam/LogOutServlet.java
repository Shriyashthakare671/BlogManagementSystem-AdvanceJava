package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
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

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Logout</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<center> <img src='3.png' alt='Blog Logo' /> </center>");
        out.println("<h2 style='colour:rgb(199, 25, 25);'>Thank you for visiting our Blogs....</h2>");
        out.println("<a href='index.html'>Login Again</a>");
        out.println("</center>");
        // out.println("<link href='https://github.com/settings/profile'><link/>");

        out.println("</body>");
        out.println("</html>");
    }

}
