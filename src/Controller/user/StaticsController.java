package Controller.user;


import Model.user.StaticsDAO;
import Model.user.StaticsDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/StaticsController")
public class StaticsController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  HttpSession session = req.getSession();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        resp.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");

      //  String user_mail = (String)session.getAttribute("user_mail");

        StaticsDAO static_dao = new StaticsDAO();
        StaticsDTO data = static_dao.getStatics("gpalsl");

        Gson static_gson = new Gson();
        String static_json = static_gson.toJson(data);
        System.out.println(static_json);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(static_json);



    }
}
