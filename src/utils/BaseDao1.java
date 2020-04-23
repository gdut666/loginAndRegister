package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BaseDao1 {
    private static DataSource ds;

    static {
        Properties pro = new Properties();
        try {
            pro.load(BaseDao1.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static boolean addUpdateDelete(String sql, Object[] arr) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = BaseDao1.getConnection();
            pstmt = conn.prepareStatement(sql);

            if (arr != null && arr.length !=0) {
                for (int i=0; i<arr.length; i++) {
                    pstmt.setObject(i+1,arr[i]);
                }
            }

            int count = pstmt.executeUpdate();
            if (count > 0) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao1.close(pstmt,conn);
        }
        return false;
    }
}
