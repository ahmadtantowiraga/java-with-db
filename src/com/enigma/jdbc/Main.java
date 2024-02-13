package com.enigma.jdbc;

import com.enigma.jdbc.entity.Menu;
import com.enigma.jdbc.repository.CustomerRepository;
import com.enigma.jdbc.repository.CustomerRepositoryImpl;
import com.enigma.jdbc.repository.MenuRepository;
import com.enigma.jdbc.repository.MenuRepositoryImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MenuRepository menuRepository=new MenuRepositoryImpl();
//        Menu menu = menuRepository.findById(1);
//        System.out.println(menu.getId());
//        System.out.println(menu.getName());

//        List<Menu> menuList=menuRepository.findAll();
//        System.out.printf("%-7s %-20s\n","ID","Name");
//        for (int i = 0; i < menuList.size(); i++) {
//            System.out.printf("%-7d %-20s\n",menuList.get(i).getId(),menuList.get(i).getName());
//        }

//        Menu menu=new Menu(1,"Nasi kotak");
//        int update =menuRepository.update(menu);
//        System.out.println(update);


//       int delete= menuRepository.deleteById(24);
//        System.out.println(delete);


//        Menu menu2 = menuRepository.findByName("%Batagor%");
//        System.out.println(menu2.getId());
//        System.out.println(menu2.getName());
        CustomerRepository cust=new CustomerRepositoryImpl();
        int save=cust.save("uciha1","09876543211",true);



    }
}
