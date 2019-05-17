package Model.user;

import Model.DBCP;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComparisonDAO {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs =null;
    String sql="";

    int useEnergy;
    int saleEnergy;
    ComparisonDTO comp;


  //선택한 구간에 대한 사용량과 판매량
    public ComparisonDTO Comparison(String start_date, String end_date){
        conn= DBCP.getConnection();
        sql = "select sum(date_energy.사용량), sum(date_energy.판매량)\n" +
                "    from (\n" +
                "            select sum(elec.electric_energy) 사용량, sum(elec.sales_rate) 판매량\n" +
                "             from elec_power_usage elec, amr amr\n" +
                "             where amr.amr_id=elec.amr_id\n" +
                "               and amr.user_id = 'gpalsl'\n" +
                "               and date_format(elec.date, '%Y-%m-%d') between  ? and ?\n" +
                "             group by date_format(elec.date, '%Y-%m-%d')) date_energy";
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setString(1, start_date);
            psmt.setString(2,end_date);

            rs = psmt.executeQuery();

            if(rs.next()){
                useEnergy = rs.getInt(1);
                saleEnergy = rs.getInt(2);

            }
           comp = new ComparisonDTO(useEnergy, saleEnergy);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBCP.freeConnection(psmt, rs, conn);
        }


        return  comp;
    }

    //오늘에 대한 사용량과 판매량
    public ComparisonDTO TodayComparison(String today_date){
        conn= DBCP.getConnection();
        sql = "select sum(today_energy.사용량), sum(today_energy.판매량)\n" +
                "    from (\n" +
                "             select sum(elec.electric_energy) 사용량, sum(elec.sales_rate) 판매량\n" +
                "             from elec_power_usage elec, amr amr\n" +
                "             where amr.amr_id=elec.amr_id\n" +
                "               and amr.user_id = 'gpalsl'\n" +
                "               and date_format(elec.date, '%Y-%m-%d') =?\n" +
                "             group by time_format(elec.date, '%H:%m')) today_energy";
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setString(1, today_date);


            rs = psmt.executeQuery();

            if(rs.next()){
                useEnergy = rs.getInt(1);
                saleEnergy = rs.getInt(2);

            }
            comp = new ComparisonDTO(useEnergy, saleEnergy);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBCP.freeConnection(psmt, rs, conn);
        }

        return comp;
    }


}
