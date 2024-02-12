package com.enigma.jdbc.repository;

import com.enigma.jdbc.entity.Menu;

import java.util.List;

public interface MenuRepository {
    int save(String name);
    Menu findById(Integer id);
    List<Menu> findAll();
    int update(Menu menu);
    int deleteById(int id);
    Menu findByName(String name);
}