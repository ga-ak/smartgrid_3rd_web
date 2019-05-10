package Model;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCP {
    private static Context context;
    private static BasicDataSource basicDataSource;

    static {
        try {
            context = new InitialContext();
            basicDataSource = (BasicDataSource) context.lookup("java:comp/env/jdbc/project3rdDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = basicDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    } // end of getConnection()

    public static void freeConnection(PreparedStatement psmt, ResultSet rs, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        basicDataSource.invalidateConnection(conn);
    } // end of freeConnection(Connection conn)
}