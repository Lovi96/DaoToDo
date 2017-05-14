package servlet;

import com.google.gson.Gson;
import dao.SqlDao;
import dao.TodoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lovi on 2017.05.11..
 */
@WebServlet(urlPatterns = {"/todosServlet", "/todosServlet/*"})
public class todosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDao DAO = SqlDao.INSTANCE;
        resp.setContentType("application/json");
        HttpSession session = req.getSession();
        PrintWriter pw = resp.getWriter();
        if(req.getParameterMap().containsKey("input")) {
            DAO.addTask(req.getParameter("input"), (String) session.getAttribute("user"));
        }
        if(req.getParameterMap().containsKey("toggle")) {
            DAO.toggleStatus(Integer.parseInt(req.getParameter("toggle")));
        }
        if(req.getParameterMap().containsKey("getDoneTasks")) {
            pw.print(new Gson().toJson(DAO.returnDoneTasks((String) session.getAttribute("user"))));
        }
        if(req.getParameterMap().containsKey("getInProgress")) {
            pw.print(new Gson().toJson(DAO.returnInProgress((String) session.getAttribute("user"))));
        }
        if(req.getParameterMap().containsKey("input") || req.getParameterMap().containsKey("toggle")||
            req.getParameterMap().containsKey("showAll")) {
            pw.print(new Gson().toJson(DAO.returnAll((String) session.getAttribute("user"))));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDao DAO = SqlDao.INSTANCE;
        Integer idHelper = req.getRequestURI().lastIndexOf("/");
        System.out.println(idHelper);
        Integer id = Integer.valueOf(req.getRequestURI().substring(idHelper + 1));
        System.out.println(id);
        DAO.deleteTask(id);
    }
}
