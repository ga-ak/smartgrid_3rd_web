package Controller.user;

import Model.user.CalendarDAO;
import Model.user.CalendarDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Calendar")
public class CalendarController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

      String start_date =  req.getParameter("start_date");
      String end_date = req.getParameter("end_date");

        CalendarDAO cal_dao = new CalendarDAO();

        CalendarDTO cal_data = cal_dao.elecUsage(start_date, end_date);




        //데이터 값 보내주기

        Gson energy_data_gson = new Gson();
        String json = energy_data_gson.toJson(cal_data);
        System.out.println(json);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(json);


    }
}
