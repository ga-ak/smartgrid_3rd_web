package Controller.user;


import Model.user.CalendarDAO;
import Model.user.GraphDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//오늘 사용한 전력량
@WebServlet("/TodayEnergyController")
public class TodayEnergyController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  HttpSession session = req.getSession();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        resp.addHeader("Access-Control-Allow-Origin", "*");

        resp.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");


       String today_date = req.getParameter("today_date");
    //   String user_email = (String)session.getAttribute("user_mail");


        CalendarDAO today_dao = new CalendarDAO();
        GraphDTO today_data = today_dao.todayUsage("gpalsl", today_date);

        Gson energy_data_gson = new Gson();
        String today_json = energy_data_gson.toJson(today_data);
        System.out.println(today_json);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(today_json);



    }
}
