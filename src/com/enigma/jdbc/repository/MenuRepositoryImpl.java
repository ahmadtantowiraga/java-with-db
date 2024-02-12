package com.enigma.jdbc.repository;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.entity.Menu;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MenuRepositoryImpl implements MenuRepository {
    @Override
    public int save(String name) {
        try (Connection con = DBConnector.getConnection()) {
            String sql="insert into m_menu (menu_name) values (?)";
            PreparedStatement statement=con.prepareStatement((sql));
            statement.setString(1,name);
            int result =statement.executeUpdate();
            statement.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Menu findById(Integer id) {
        try (Connection con = DBConnector.getConnection()) {
            String sql="select id, menu_name as name from m_menu where id=?";
            PreparedStatement statement=con.prepareStatement((sql));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Menu menu =new Menu(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                resultSet.close();
                statement.close();
                return menu;
            }else{
                resultSet.close();
                statement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Menu> findAll() {
        try (Connection con = DBConnector.getConnection()) {
            String sql="select id, menu_name as name from m_menu";
            PreparedStatement statement=con.prepareStatement((sql));
            ResultSet resultSet = statement.executeQuery();
            List<Menu> menuList=new ArrayList<>();
            while(resultSet.next()){
                Menu menu=new Menu(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                menuList.add(menu);
            }
            resultSet.close();
            statement.close();
            return menuList;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int update(Menu menu) {
        try (Connection con = DBConnector.getConnection()) {
            String sql="Update m_menu set menu_name=? where id=?";
            PreparedStatement statement=con.prepareStatement((sql));
            statement.setString(1, menu.getName());
            statement.setInt(2,menu.getId());
            int result=statement.executeUpdate();
            statement.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int deleteById(int id){
        try (Connection con = DBConnector.getConnection()) {
            String sql="DELETE FROM m_menu WHERE id = ?";
            PreparedStatement statement=con.prepareStatement((sql));
            statement.setInt(1,id);
            int result=statement.executeUpdate();
            statement.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Menu findByName(String name) {
        try (Connection con = DBConnector.getConnection()) {
            String sql="SELECT id, menu_name FROM m_menu WHERE menu_name = ?";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                Menu menu =new Menu(
                        resultSet.getInt("id"),
                        resultSet.getString("menu_name")
                );
                resultSet.close();
                statement.close();
                return menu;
            }else{
                resultSet.close();
                statement.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}

