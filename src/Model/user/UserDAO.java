package Model.user;

import Model.DBCP;
import Model.user.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String sql = null;

    String user_mail;

    public void getClose() {

        try {

            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    //로그인
    public String login(String user_id, String user_pw) {
        conn = DBCP.getConnection();
        sql = "select user_email from user where user_email=?and user_pw=?";

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user_id);
            psmt.setString(2, user_pw);
            rs = psmt.executeQuery();

            if (rs.next()) {
                user_mail = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
            DBCP.freeConnection(psmt, rs, conn);
        }


        return user_mail;
    }

    //회원 가입

    public void join(UserDTO user_info) {
        conn = DBCP.getConnection();
        sql = "insert into user values (?, ?, ?, ?, ?)";

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user_info.getUser_email());
            psmt.setString(2, user_info.getUser_pw());
            psmt.setBoolean(3, user_info.isAutopay());
            psmt.setString(4, user_info.getPaynumber());
            psmt.setString(5, user_info.getUser_addr());
            psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getClose();
            DBCP.freeConnection(psmt, rs, conn);
        }


    }
}
