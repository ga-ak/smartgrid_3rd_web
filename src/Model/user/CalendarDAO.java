package Model.user;

import Model.DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalendarDAO {
    Connection conn = null;
    PreparedStatement psmt =null;
    ResultSet rs =null;
    String sql ="";
    CalendarDTO calendar_data =null;

    //x축 : date
    ArrayList<String> dates = new ArrayList<>();

    //y축
    ArrayList<EnergySeries>  energy = new ArrayList<>();
    //y축 값
    ArrayList<Integer> datas = new ArrayList<>();

    String x_data = "";
    int y_data;

    //달력에서 구간 선택했을 때
    //일별 전력량 뽑아주기
    public CalendarDTO elecUsage(String user_email, String start_date, String end_date){
        conn = DBCP.getConnection();
        sql = "select  date_format(elec.date, '%Y-%m-%d'), sum(elec.electric_energy)\n" +
                "from elec_power_usage elec, amr amr\n" +
                "where amr.amr_id=elec.amr_id\n" +
                "and amr.user_id = ?\n" +
                "and date_format(elec.date, '%Y-%m-%d') between ? and ?\n" +
                "group by date_format(elec.date, '%Y-%m-%d')";

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user_email);
            psmt.setString(2, start_date);
            psmt.setString(3,end_date);
            rs = psmt.executeQuery();

            getEnergyData(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBCP.freeConnection(psmt, rs, conn);

        }
        return  calendar_data;
    }

    private void getEnergyData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            x_data = rs.getString(1);
            y_data = rs.getInt(2);

            dates.add(x_data);
            datas.add(y_data);

        }
        EnergySeries energys = new EnergySeries("power", datas);

        energy.add(energys);

        calendar_data = new CalendarDTO(dates, energy);
    }


    //달력에서 "오늘"선택했을 때
    //시간별 전력량 뽑아주기

    public CalendarDTO todayUsage(String user_email, String today_date){

        conn = DBCP.getConnection();

        sql = "select  time_format(elec.date, '%H:%m'), sum(elec.electric_energy)\n" +
                "from elec_power_usage elec, amr amr\n" +
                "where amr.amr_id=elec.amr_id\n" +
                "  and amr.user_id = ?\n" +
                "  and date_format(elec.date, '%Y-%m-%d') =?\n" +
                "group by time_format(elec.date, '%H:%m')";

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user_email);
            psmt.setString(2,today_date);

            rs =psmt.executeQuery();

            getEnergyData(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBCP.freeConnection(psmt, rs, conn);
        }

        return calendar_data;
    }


   //요금 계산
}
