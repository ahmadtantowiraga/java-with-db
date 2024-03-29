package com.enigma.jdbc.entity;

public class customer {
    private Integer id;
    private String name;
    private String phone;
    private Boolean member;

    public customer(Integer id, String name, String phone, Boolean member) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.member = member;
    }

    public customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }
}
