package test;

import Model.DBCP;
import Model.LocationDTO;
import com.google.gson.Gson;

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

@WebServlet("/SelectTest")
public class SelectTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBCP.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = "select * from test_DCU";

        ArrayList<LocationDTO> dto = new ArrayList<>();
        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                double tempLat = Double.parseDouble(rs.getString(2));
                double tempLng = Double.parseDouble(rs.getString(3));

                dto.add(new LocationDTO(tempLat, tempLng));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCP.freeConnection(psmt, rs, conn);
        }

        PrintWriter out = resp.getWriter();


        Gson gson = new Gson();
        String json = gson.toJson(dto);
        System.out.println(json);
        out.println(json);
    }


}
