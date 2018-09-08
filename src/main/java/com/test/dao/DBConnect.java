package com.test.dao;



import java.sql.*;
import java.util.List;

public class DBConnect {
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/qd?useSSL=false";
    public static final String username = "root";
    public static final String password = "123456";
    public Connection conn = null;
    public PreparedStatement pstmt = null;
    public ResultSet rs = null;

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setAccount(int account, int id) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE server SET account=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, account + 1);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void login(String user, String pwd) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            String sql = "insert into useraccount(username,password) values(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pwd);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean check(String user, String pwd) {
        boolean b=false;
        try {
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select * from useraccount where username=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                b = true;
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public void setURL(String date, List<String> list) {
        for (String str:list) {
            try {
                conn = DriverManager.getConnection(url, username, password);
                String sql = ("insert into urlpath(date,urlpath) values(?,?)");
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, date);
                pstmt.setString(2, str);
                pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkUsername(String uname) {
        boolean b = false;
        try {
            conn = DriverManager.getConnection(url, username, password);
            String sql = "select username from useraccount where username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uname);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                b = true;
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
}
