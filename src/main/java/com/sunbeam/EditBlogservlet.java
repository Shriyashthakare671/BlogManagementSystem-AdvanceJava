package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editblog")
public class EditBlogservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Blog b = new Blog();
        try (BlogDao blogDao = new BlogDao()) {
            int blogId = Integer.parseInt(req.getParameter("id"));
            b = blogDao.findById(blogId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("curusr");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>New Blog</title>");
        out.println("</head>");
        out.println("<body>");
        String userName = "";
        Cookie[] arr = req.getCookies();
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("uname")) {
                    userName = c.getValue();
                    break;
                }
            }
        }

        out.println("Hello, " + userName + "<hr/>");
        out.println("<form method='post' action='editblog'>");
        out.println("<center> <img src='3.png' alt='Blog Logo' /> </center>");
        out.printf("Title: <input type='text' name='title' value='%s'/> <br/> \n", b.getTitle());
        try (CategoryDao categoryDao = new CategoryDao()) {
            out.printf("Category: <select name='categoryId'>\n", b.getCategoryId());
            List<Category> categoryList = categoryDao.findAll();

            for (Category c : categoryList) {
                if (c.getId() == b.getCategoryId())
                    out.printf("<option value='%d Selected>%s</option> \n ", c.getId(), c.getTitle());
                else
                    out.printf("<option value='%d'>%s</option>\n", c.getId(), c.getTitle());
            }
            out.println("</select>");
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.printf("Contents: <textarea rows='10' cols='60' name='contents'> %s </textarea> <br/> \n", b.getContents());
        out.printf("<input type='hidden' name='userId' value='%d'/><br/>\n", b.getUserId());
        out.printf("<input type='hidden' name='id' value='%d'/><br/>\n", b.getId());
        out.println("<input type='submit' value='Update Blog'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    // @SuppressWarnings("unused")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        Blog b = new Blog(id, title, contents, userId, categoryId, null);

        try (BlogDao blogDao = new BlogDao()) {
            int count = blogDao.update(b);
            String message = "Blog " + count + " Updated Successfully.";
            req.setAttribute("message", message);
        } catch (Exception e) {
            String message = "Blog Update Failed.";
            req.setAttribute("message", message);
            e.printStackTrace();
        }
        // resp.sendRedirect("bloglist"); // all blogs
        RequestDispatcher rd = req.getRequestDispatcher("Bloglists");
        rd.forward(req, resp);
    }

}
