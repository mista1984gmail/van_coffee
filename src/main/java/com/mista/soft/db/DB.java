package com.mista.soft.db;

import com.mista.soft.domain.Coffee;
import com.mista.soft.domain.Van;
import com.mista.soft.exeption.IdIsNotAllowedOnDbException;
import com.mista.soft.exeption.NotUniqueIdException;
import com.mista.soft.io.SerializationUtils;

import java.io.Serializable;
import java.util.*;

public final class DB implements Serializable, AutoCloseable {
    private Map<Integer, Van> VANS;
    private static final int MAX_ALLOWED_ID = 100;
    private static DB instance = new DB();
    private int currentId = 0;

    private DB() {
        VANS = new HashMap<>();}

    public static DB getInstance(){
        return instance;
    }
    public void executeSaveOperation(Van van)
            throws NotUniqueIdException {
        van.setId(currentId);
        if (VANS.containsKey(van.getId())) {
            throw new NotUniqueIdException(van);
        }
        VANS.put(currentId++, van);
    }

    public Optional<Van> executeGetOperation(int id) {
        if (id > MAX_ALLOWED_ID) {
            throw new IdIsNotAllowedOnDbException(id);
        }
        return Optional.ofNullable(VANS.get(id));
    }

    public void save() {
        SerializationUtils.serialize(this);
    }


    public void load() {
        Object deserialized = SerializationUtils.deserialize();
        if (deserialized instanceof DB) {
            instance = (DB) deserialized;
        }
    }
    public List<Van> executeGetAllOperation() {
        return new ArrayList<>(VANS.values());
    }

    @Override
    public void close() throws Exception {

    }
    public void executeDeleteOperation(int id){
        VANS.remove(id);
        DB.getInstance().save();
    }
    public List<Coffee> getCoffeeVan(int id){
        return VANS.get(id).getCoffeeList();
    }
}
