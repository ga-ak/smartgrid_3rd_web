package Controller.admin;


import Model.userGraph.GraphDAO;
import Model.userGraph.GraphDTO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ChartController")
public class ChartController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();


        String sql = "select date_format(date, '%Y-%m-%d'), sum(electric_energy)from elec_power_usage group by date_format(date, '%Y-%m-%d')";

        //GraphDTO graphData = null;
        GraphDAO getData = new GraphDAO();

        GraphDTO graphData = getData.getTotalData(sql);


        //lineChart js에 값 보내주기
        Gson energy_data_gson = new Gson();
        String json = energy_data_gson.toJson(graphData);
        System.out.println(json);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(json);

    }
}
