package com.enigma.jdbc.repository;

public interface CustomerRepository {
    int save(String name, String phone, boolean member);
}
