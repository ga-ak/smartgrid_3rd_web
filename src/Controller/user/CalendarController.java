package Controller.user;

import Model.user.CalendarDAO;
import Model.userGraph.GraphDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // HttpSession session = req.getSession();


        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        resp.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");
        //PrintWriter out = resp.getWriter();

        //선택 구간의 전력량
      String start_date =  req.getParameter("start_date");
      String end_date = req.getParameter("end_date");
     // String user_email =(String)session.getAttribute("user_mail");

   //   System.out.println(user_email);


        CalendarDAO cal_dao = new CalendarDAO();

        GraphDTO cal_data = cal_dao.elecUsage("gpalsl", start_date, end_date);


        // 데이터 값 보내주기

        Gson energy_data_gson = new Gson();
        String json = energy_data_gson.toJson(cal_data);
        System.out.println(json);


        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(json);



    }
}
