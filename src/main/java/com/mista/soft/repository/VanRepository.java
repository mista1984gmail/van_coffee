package com.mista.soft.repository;

import com.mista.soft.domain.Coffee;
import com.mista.soft.domain.Van;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface VanRepository {
    boolean saveVan(@NotNull Van van) throws Exception;

    Optional<Van> getVan(int id) throws Exception;

    List<Van> getVans() throws Exception;
    void deleteVan(int id) throws Exception;
    List<Coffee>coffees (int id) throws Exception;

}
