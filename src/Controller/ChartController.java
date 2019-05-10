package Controller;


import Model.DBCP;
import Model.GraphDTO;
import Model.GraphSeries;
import com.google.gson.Gson;
import javafx.scene.chart.XYChart;
import test.SelectTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ChartController")
public class ChartController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        //DB에서 값 가져오기
        Connection conn = DBCP.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "select date_format(date, '%Y-%m-%d'), sum(electric_energy)from elec_power_usage group by date_format(date, '%Y-%m-%d')";

        GraphDTO graphData = null;

        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();


            // x 축
            ArrayList<String> categeries = new ArrayList<>();
            // y 축
            ArrayList<GraphSeries> series = new ArrayList<>();
            // y 축의 데이터
            ArrayList<Integer> seriesData = new ArrayList<>();

            while (rs.next()) {
                String x_date = rs.getString(1);
                int y_energy = rs.getInt(2);

                categeries.add(x_date);
                seriesData.add(y_energy);
            }

            // 만약 시리즈가 여러개라면 밑줄 부분까지를 for문 으로 돌려주면 된다.
            GraphSeries tempSeries = new GraphSeries("혜민", seriesData);

            series.add(tempSeries);
            // --------------------------------------------------------------------
            graphData = new GraphDTO(categeries, series);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCP.freeConnection(psmt, rs, conn);
        }


        //lineChart js에 값 보내주기
        Gson energy_data_gson = new Gson();
        String json = energy_data_gson.toJson(graphData);
        System.out.println(json);

           resp.setContentType("application/json; charset=UTF-8");
           resp.getWriter().println(json);

    }
}
