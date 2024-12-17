package com.unical.backendweb.dao;

import java.sql.*;

public class DBManager {
    private static DBManager instance = null;
    private UserDAO userDAO = null;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0603";

    static Connection con = null;
    public static Connection getConnection(){
        if (con == null){
            try {
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "0603");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    public static DBManager getInstance(){
        if (instance == null){
            instance = new DBManager();
        }
        return instance;
    }
    public UserDAO getUSerDao(){
        if (userDAO == null) {
            userDAO = new UserDAOImpl(getConnection());
        }
        return  userDAO;
    }

    public static void main(String[] args) {
        Connection con = DBManager.getInstance().getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            if (rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
