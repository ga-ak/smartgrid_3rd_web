package Model.userGraph;

import Model.DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GraphDAO {


    PreparedStatement psmt = null;
    ResultSet rs = null;
    GraphDTO graphData = null;

    // x 축
    ArrayList<String> categeries = new ArrayList<>();
    // y 축
    ArrayList<GraphSeries> series = new ArrayList<>();
    // y 축의 데이터
    ArrayList<Integer> seriesData = new ArrayList<>();

    String x_date ="";
    int y_energy ;

    //관리자 입장 : 전체 사용자 전력 사용량
    public GraphDTO getTotalData(String sql){

        Connection conn = DBCP.getConnection();

        try {
            psmt = conn.prepareStatement(sql);
           rs =  psmt.executeQuery();

            while(rs.next()){
                x_date = rs.getString(1);
                y_energy = rs.getInt(2);

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
        }finally {
            DBCP.freeConnection(psmt, rs, conn);
        }

        return graphData;
    }

    //생각 필요함
    //관리자 입장 : 특정 사용자의 전력 사용량
   /* public void getSelectUser(String user_id, String sql){
        Connection conn = DBCP.getConnection();
        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while(rs.next()){
                x_date= rs.getString(1);
                y_energy =rs.getInt(2);

                categeries.add(x_date);
                seriesData.add(y_energy);


            }
            GraphSeries tempSeries = new GraphSeries(user_id, seriesData);

            series.add(tempSeries);
            // --------------------------------------------------------------------
            graphData = new GraphDTO(categeries, series);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBCP.freeConnection(psmt, rs, conn);
        }


    }*/

   //사용자 입장



}

