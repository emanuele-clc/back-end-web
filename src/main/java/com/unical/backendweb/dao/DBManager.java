package com.unical.backendweb.dao;

import java.sql.*;

public class DBManager {
    private static DBManager instance = null;
    private UserDAO userDAO = null;

    private static final String URL = "jdbc:postgresql://localhost:5432/progetto";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yourpass"; //provapush

    static Connection con = null;
    public static Connection getConnection(){
        if (con == null){
            try {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
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
}
