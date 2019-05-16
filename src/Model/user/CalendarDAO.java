package Model.user;

import Model.DBCP;
import Model.userGraph.GraphDTO;
import Model.userGraph.GraphSeries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//GraphDAO, GraphDTO 이용
//CalendarDAO, CalendarDTO 삭제함
public class CalendarDAO {
    Connection conn = null;
    PreparedStatement psmt =null;
    ResultSet rs =null;
    String sql ="";
    GraphDTO graphData = null;

    // x 축
    ArrayList<String> categories = new ArrayList<>();
    // y 축
    ArrayList<GraphSeries> series = new ArrayList<>();
    // y 축의 데이터
    ArrayList<Integer> seriesData = new ArrayList<>();

    int payment=0;


    String x_data = "";
    int y_data;

    //달력에서 구간 선택했을 때
    //일별 전력량 뽑아주기
    public GraphDTO elecUsage(String user_email, String start_date, String end_date){
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
        return  graphData;
    }

    private void getEnergyData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            x_data = rs.getString(1);
            y_data = rs.getInt(2);


            categories.add(x_data);
            seriesData.add(y_data);

        }


        for(int i=0;i<seriesData.size();i++){
            payment+= seriesData.get(i)*10;

        }

        GraphSeries energys = new GraphSeries("power", seriesData);

        series.add(energys);

        graphData = new GraphDTO(categories, series, payment);
    }


    //달력에서 "오늘"선택했을 때
    //시간별 전력량 뽑아주기

    public GraphDTO todayUsage(String user_email, String today_date){

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

        return graphData;
    }


   //요금 계산
}
