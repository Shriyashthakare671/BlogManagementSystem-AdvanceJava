package com.sunbeam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/savecat")
public class SaveCategoryServlet extends HttpServlet
    {
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
            String catTitle=req.getParameter("catTitle");
            String catDesc = req.getParameter("catDesc");
            Category c = new Category(0, catTitle, catDesc);
            try(CategoryDao categoryDao= new CategoryDao())
            {
                int count =categoryDao.save(c);
                String message = "Category added succesfully";
                req.setAttribute("message", message);
            }
            catch (Exception e){
                e.printStackTrace();
                String message = "category not added";
                req.setAttribute("message", message);
            }

            RequestDispatcher rd = req.getRequestDispatcher("Bloglists");
            rd.forward(req, resp);
        }
    }
