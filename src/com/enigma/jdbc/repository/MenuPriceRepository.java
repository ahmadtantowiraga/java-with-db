package com.enigma.jdbc.repository;

import com.enigma.jdbc.dto.MenuDetailRequest;
import com.enigma.jdbc.dto.MenuDetailResponse;

public interface MenuPriceRepository {
    int save(MenuDetailRequest request);
    MenuDetailResponse findByMenuId(Integer menuId);
}

