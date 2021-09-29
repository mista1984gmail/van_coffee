package com.mista.soft.service;

import com.mista.soft.domain.Coffee;
import com.mista.soft.domain.Van;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface VanService {
    void saveVan(@NotNull Van van) throws Exception;
    Van getVan(int id) throws Exception;
    void showInfo() throws Exception;
    void deleteVan (int id) throws Exception;
    void showCoffeeInfo(int id) throws Exception;
    Coffee addCoffee() throws Exception;
    void sortCoffeeByPriceForKg(int id) throws Exception;
    void chooseCoffeePriceRangePerKg(int id, double start, double end) throws Exception;
    void totalCalculation(int id) throws Exception;
    void totalCalculation(List<Coffee>coffeeList) throws Exception;
    void sortingByTypeOfCoffee(int id, int type) throws Exception;


}
