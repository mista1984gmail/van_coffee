package com.mista.soft.repository;

import com.mista.soft.db.DB;
import com.mista.soft.domain.Coffee;
import com.mista.soft.domain.Van;
import com.mista.soft.exeption.NotUniqueIdException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class VanRepositoryImp implements VanRepository {
    @Override
    public boolean saveVan(@NonNull Van van) throws Exception {
        try (DB db = DB.getInstance()) {
            db.executeSaveOperation(van);
            return true;
        } catch (NotUniqueIdException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Van> getVan(int id) throws Exception {
        try (DB db = DB.getInstance()) {
            return db.executeGetOperation(id);
        }
    }

    @Override
    public List<Van> getVans() throws Exception {
        try (DB db = DB.getInstance()) {
            return db.executeGetAllOperation();
        }
    }

    @Override
    public void deleteVan(int id) throws Exception {
        try (DB db = DB.getInstance()) {
            db.executeDeleteOperation(id);
        } catch (NotUniqueIdException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Coffee> coffees(int id) throws Exception {
        try (DB db = DB.getInstance()) {
            return db.getCoffeeVan(id);
        }
    }

}
