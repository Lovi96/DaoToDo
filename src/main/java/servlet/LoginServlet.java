package servlet;

import com.google.gson.Gson;


import dao.SqlDao;
import dao.TodoDao;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by lovi on 2017.05.11..
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        TodoDao DAO = SqlDao.INSTANCE;
        HttpSession session = req.getSession();
        PrintWriter pw = resp.getWriter();
        pw.print(new Gson().toJson(DAO.returnAll((String)session.getAttribute("user"))));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user",req.getParameter("username"));

        resp.sendRedirect("todos.jsp");
    }

}
