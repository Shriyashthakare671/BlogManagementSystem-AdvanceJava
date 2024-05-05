package com.sunbeam;

import java.io.IOException;
// import java.sql.Connection;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
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

    
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<center>");
            out.println("<title>Registration</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='post' action='saveuser'>");
            out.println("<center> <img src='3.png' alt='Blog Logo' /> </center>");
            out.println("<h2 style='color:rgb(199, 25, 25);'>Registration Form.!</h2> <br/> <br/>");
            out.println("Full Name: <input type='text' name='name' required /> <br /><br />");
            out.println("Email: <input type='text' name='email' required /> <br /><br />");
            out.println("Password: <input type='password' name='passwd' required /><br /><br />");
            out.println("Mobile No: <input type='tel' name='phoneno' required />");
            out.println("<br /><br />");
            out.println("<input type='submit' value='save' \n />   <Button> <a href='index.html'>Login In</a> </Button>" );
            
            out.println("</form>");
        

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        
    }

}
