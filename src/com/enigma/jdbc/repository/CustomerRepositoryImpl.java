package com.enigma.jdbc.repository;

import com.enigma.jdbc.config.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository{

    @Override
    public int save(String name, String phone, Boolean member) {
        try (Connection con = DBConnector.getConnection()) {
            String sql1="select id from m_customer order by id desc limit 1";
            PreparedStatement statement1=con.prepareStatement((sql1));
            ResultSet resultSet1=statement1.executeQuery();
            long seq=0;
            while(resultSet1.next()){
                seq=resultSet1.getLong("id");
            }
            System.out.println(seq);
            statement1.close();

            String sql2="Alter sequence m_customer_seq restart with "+(seq+1);
            PreparedStatement statement2=con.prepareStatement((sql2));
//            statement2.setLong(1, (seq+1));
            System.out.println(statement2);
            statement2.executeUpdate();
            statement2.close();

            String sql="insert into m_customer (customer_name, mobile_phone_no, is_member) values (?, ?, ?)";
            PreparedStatement statement=con.prepareStatement((sql));
            statement.setString(1,name);
            statement.setString(2,phone);
            statement.setBoolean(3,member);
            int result =statement.executeUpdate();
            statement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
