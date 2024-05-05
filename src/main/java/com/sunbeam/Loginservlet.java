package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Loginservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("passwd");
        User user = null;
        try(UserDao Dao = new UserDao()) {
            user = Dao.findbyemailAndPassword(email,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        // out.println(" <img src='3.png' alt='Blog Logo' width: 200px; height: 40%; />");
        if(user != null) {

           // resp.sendRedirect("Bloglists");
           HttpSession session = req.getSession();
           session.setAttribute("curusr", user);

           resp.sendRedirect("Bloglists");
        }
        else {
            out.println("<h3>Invalid email or password.</h3>");
            out.println("<a href='index.html'>Try again</a>");
        }
        out.println("</body>");
        out.println("</html>");

    }

}
