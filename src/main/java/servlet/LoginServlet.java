package servlet;

import com.google.gson.Gson;
import dao.MemoryDao;
import util.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by lovi on 2017.05.11..
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        MemoryDao memoryDao = MemoryDao.INSTANCE;
        List<Task> database = memoryDao.getDatabase();
        PrintWriter pw = resp.getWriter();
        pw.print(new Gson().toJson(database));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user",req.getParameter("username"));

        resp.sendRedirect("todos.jsp");
    }

}
