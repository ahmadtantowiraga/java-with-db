package com.enigma.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection(){
        try{
            String dbHost=System.getenv("DB_HOST");
            String dbPort=System.getenv("DB_PORT");
            String dbName=System.getenv("DB_NAME");
            String url=String.format("jdbc:postgresql://%s:%s/%s",dbHost,dbPort,dbName);
            String username=System.getenv("DB_USERNAME");
            String password=System.getenv("DB_PASSWORD");
            return DriverManager.getConnection(url, username,password);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}