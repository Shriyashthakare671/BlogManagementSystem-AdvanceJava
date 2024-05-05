package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveuser")
public class SaveUser extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    { ProcessRequest(req, resp);}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProcessRequest(req, resp);
    }
    protected void ProcessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter out = resp.getWriter();
        
        String name = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("passwd");
        String phone = req.getParameter("phone");
        User u = new  User(0, name, email, password, phone, null);
        try(UserDao ud = new UserDao())
        {
            int count = ud.save(u);
            if(count==1)
            {
                out.println("Congrat's user registered");
            }
            else{
                    out.println("user not registered");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // resp.sendRedirect("login");
        
    }
}