package Model.user;

import Model.DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaticsDAO {


    String month = "";
    int energy;
    int month_pay;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    StaticsDTO static_data = null;
    ArrayList<String> static_month = new ArrayList<>();
    ArrayList<Integer> static_pay= new ArrayList<>();
    ArrayList<Integer> static_energy= new ArrayList<>();

    public StaticsDTO getStatics(String user_mail) {


        Connection conn = DBCP.getConnection();
        String sql = "select date_format(elec.date, '%m') Month, sum(elec.electric_energy) Energy, sum(elec.electric_energy)*10 Pay\n" +
                "from elec_power_usage elec, amr amr\n" +
                "where amr.amr_id=elec.amr_id\n" +
                "  and amr.user_id = ?\n" +
                "group by date_format(elec.date, '%m')";
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user_mail);
            rs = psmt.executeQuery();

            while (rs.next()) {
                month = rs.getString(1);
                energy = rs.getInt(2);
                month_pay = rs.getInt(3);

                static_month.add(month);
                static_pay.add(month_pay);
                static_energy.add(energy);

            }

            static_data = new StaticsDTO(static_month, static_energy, static_pay);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCP.freeConnection(psmt, rs, conn);
        }
        return static_data;

    }
}
