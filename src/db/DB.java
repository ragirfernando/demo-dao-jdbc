package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private static Connection conn = null;

    /*private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/coursejdbc";
    private static final String user = "root";
    private static final String pass = "123456";*/

    public static Connection getConnection() {

        if (conn == null) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    throw new DbException(ex.getMessage());
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursejdbc?autoReconnect=true&useSSL=false", "root", "123456");
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeCt(){
        
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
        }
    }
    
    public static void closeSt(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
            
        }
    }
    
    public static void closeRt(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
            
        }
    }
}
