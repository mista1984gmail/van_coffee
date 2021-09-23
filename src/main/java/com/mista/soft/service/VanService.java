package com.mista.soft.service;

import com.mista.soft.domain.Coffee;
import com.mista.soft.domain.Van;

import javax.validation.constraints.NotNull;

public interface VanService {
    void saveVan(@NotNull Van van) throws Exception;
    Van getVan(int id) throws Exception;
    void showInfo() throws Exception;
    void deleteVan (int id) throws Exception;
    void showCoffeeInfo(int id) throws Exception;
    Coffee addCoffee() throws Exception;
    void sortCoffeeByPriceForKg(int id) throws Exception;

}
