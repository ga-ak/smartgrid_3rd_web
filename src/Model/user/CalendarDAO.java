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
    public CalendarDTO elecUsage(String start_date, String end_date){
        conn = DBCP.getConnection();
        sql = "select date_format(date, '%Y-%m-%d'), sum(electric_energy) from elec_power_usage where date_format(date, '%Y-%m-%d') between ? and ? " +
                "group by date_format(date, '%Y-%m-%d')";
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, start_date);
            psmt.setString(2, end_date);
            rs = psmt.executeQuery();

            while (rs.next()){
                x_data = rs.getString(1);
                y_data = rs.getInt(2);

                dates.add(x_data);
                datas.add(y_data);

            }

            EnergySeries energys = new EnergySeries("전력", datas);

            energy.add(energys);

            calendar_data = new CalendarDTO(dates, energy);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBCP.freeConnection(psmt, rs, conn);

        }
        return  calendar_data;
    }


    //달력에서 "오늘"선택했을 때
    //시간별 전력량 뽑아주기


    //요금 계산
}
