package servlet;

import com.google.gson.Gson;
import dao.MemoryDao;

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
        MemoryDao memoryDao = MemoryDao.INSTANCE;
        resp.setContentType("application/json");
        HttpSession session = req.getSession();
        PrintWriter pw = resp.getWriter();
        if(req.getParameterMap().containsKey("input")) {
            memoryDao.addTask(req.getParameter("input"), (String) session.getAttribute("user"));
        }
        if(req.getParameterMap().containsKey("toggle")) {
            memoryDao.toggleStatus(Integer.parseInt(req.getParameter("toggle")));
        }
        if(req.getParameterMap().containsKey("getDone")) {
            pw.print(new Gson().toJson(memoryDao.returnDone((String) session.getAttribute("user"))));
        }
        if(req.getParameterMap().containsKey("getInProgress")) {
            pw.print(new Gson().toJson(memoryDao.returnInProgress((String) session.getAttribute("user"))));
        } else {
            pw.print(new Gson().toJson(memoryDao.returnAll((String) session.getAttribute("user"))));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemoryDao memoryDao = MemoryDao.INSTANCE;
        Integer idHelper = Integer.valueOf(req.getRequestURI().lastIndexOf("/"));
        Integer id = Integer.valueOf(req.getRequestURI().lastIndexOf(idHelper + 1));
        memoryDao.deleteTask(id);
    }
}
