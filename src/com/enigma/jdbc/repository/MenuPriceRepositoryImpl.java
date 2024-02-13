package com.enigma.jdbc.repository;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.dto.MenuDetailRequest;
import com.enigma.jdbc.dto.MenuDetailResponse;
import com.enigma.jdbc.entity.Menu;

import java.sql.*;

public class MenuPriceRepositoryImpl implements MenuPriceRepository {
    @Override
    public int save(MenuDetailRequest request) {
        try (Connection connection = DBConnector.getConnection()) {
            connection.setAutoCommit(false);
            String sqlInsertMenu = "INSERT INTO m_menu (menu_name) VALUES (?)";
            PreparedStatement statementInsertMenu = connection.prepareStatement(sqlInsertMenu, Statement.RETURN_GENERATED_KEYS);
            statementInsertMenu.setString(1, request.getName());
            statementInsertMenu.executeUpdate();

            ResultSet generatedKeysMenu = statementInsertMenu.getGeneratedKeys();
            int menuId = 0;
            if (generatedKeysMenu.next()) {
                menuId = generatedKeysMenu.getInt("id");
            } else {
                connection.rollback();
            }

            String sqlInsertMenuPrice = "INSERT INTO m_menu_price (menu_id, price) VALUES (?, ?)";
            PreparedStatement statementInsertMenuPrice = connection.prepareStatement(sqlInsertMenuPrice);
            statementInsertMenuPrice.setInt(1, menuId);
            statementInsertMenuPrice.setFloat(2, request.getPrice());

            int result = statementInsertMenuPrice.executeUpdate();

            generatedKeysMenu.close();
            statementInsertMenu.close();
            statementInsertMenuPrice.close();

            connection.commit();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public MenuDetailResponse findByMenuId(Integer menuId) {
        try (Connection connection = DBConnector.getConnection()) {
            String sql = """
                    SELECT
                    m.id as menu_id,
                    mp.id as menu_price_id,
                    m.menu_name as name,
                    mp.price
                    FROM m_menu m JOIN m_menu_price mp ON m.id = mp.menu_id
                    WHERE m.id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, menuId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                MenuDetailResponse response = new MenuDetailResponse(
                        resultSet.getInt("menu_id"),
                        resultSet.getInt("menu_price_id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price")
                );
                resultSet.close();
                statement.close();
                return response;
            }

            resultSet.close();
            statement.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

