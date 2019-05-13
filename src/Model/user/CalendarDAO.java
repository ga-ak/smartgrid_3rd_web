package Model.user;

import Model.DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CalendarDAO {
    Connection conn = DBCP.getConnection();
    PreparedStatement psmt =null;
    ResultSet rs =null;
    String sql ="";


    //달력에서 구간 선택했을 때
    //일별 전력량 뽑아주기


    //달력에서 "오늘"선택했을 때
    //시간별 전력량 뽑아주기


    //요금 계산
}
