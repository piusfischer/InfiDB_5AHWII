package main.java.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

    private static DBManager instance;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String DBuser;
    private String DBdriver;
    private String url;
    private String pwd;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DataSource dataSource = null;
    public void init(DataSource dataSource){
        this.dataSource=dataSource;
    }
    public Connection getConnection()throws SQLException{
        Connection con = dataSource.getConnection();
        return con;
    }
    /*public void releaseConnection(Connection con){
        try{
            if(con!=null){
                con.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

     */

   /* public void init(String DBdriver, String DBuser, String url, String pwd) throws ClassNotFoundException {
        this.DBdriver = DBdriver;
        this.DBuser = DBuser;
        this.url = url;
        this.pwd = pwd;
        Class.forName(this.DBdriver);
    }

    public Connection getConnection() throws SQLException {
        System.out.println("url = " + url);
        System.out.println("DBuser = " + DBuser);
        System.out.println("pwd = " + pwd);
        Connection con = DriverManager.getConnection(url, DBuser, pwd);
        return con;
    }
    */


    public void dropConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void addUser(Connection con, String user_name, String pwd)
            throws SQLException, ClassNotFoundException {
        String string = "INSERT INTO users(username, pwd) values(?,?)";

        PreparedStatement ps = con.prepareStatement(string);


        try {
            ps.setString(1, user_name);
            ps.setString(2, pwd);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }

    public boolean checkUser(Connection con, String user_name, String password)
            throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ps.setString(1, user_name);


        while (rs.next()) {
            int id = rs.getInt("user_id");
            String username = rs.getString("username");
            String pwd = rs.getString("pwd");

            if (username.contentEquals(user_name) && encoder.matches(password, pwd)) {
                return true;
            } else {
                return false;
            }
        }
        rs.close();
        ps.close();
        return false;
    }




}