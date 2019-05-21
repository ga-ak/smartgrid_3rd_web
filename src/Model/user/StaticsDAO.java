package Model.user;

import Model.DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaticsDAO {


    String categories = "";
    int energy;
    int sale_rate;
    int pay;

    PreparedStatement psmt = null;
    ResultSet rs = null;
    StaticsDTO static_data = null;


    ArrayList<String> static_categories = new ArrayList<>();

    ArrayList<StaticsSeries> series = new ArrayList<>();
    ArrayList<Integer> static_energy = new ArrayList<>();
    ArrayList<Integer> static_sale = new ArrayList<>();

    ArrayList<Integer> static_pay = new ArrayList<>();


    public StaticsDTO getStatics(String user_mail) {


        Connection conn = DBCP.getConnection();
        String sql = "select date_format(elec.date, '%m'), sum(elec.electric_energy), sum(elec.sales_rate)\n" +
                "from elec_power_usage elec, amr amr\n" +
                "where amr.amr_id=elec.amr_id\n" +
                "  and amr.user_id = ?\n" +
                "group by date_format(elec.date, '%m')";
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user_mail);
            rs = psmt.executeQuery();


            while (rs.next()) {
                categories = rs.getString(1);
                energy = rs.getInt(2);
                sale_rate = rs.getInt(3);

                pay = (energy-sale_rate)*10;

                static_categories.add(categories);
                static_sale.add(sale_rate);
                static_energy.add(energy);
                static_pay.add(pay);

            }

            StaticsSeries energys = new StaticsSeries("사용량", static_energy);
            StaticsSeries sales = new StaticsSeries("판매량", static_sale);

            series.add(energys);
            series.add(sales);

            static_data = new StaticsDTO(static_categories, series, static_pay);



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBCP.freeConnection(psmt, rs, conn);
        }
        return static_data;

    }
}
