package com.mista.soft.domain;

import java.io.Serializable;

public enum PackageCoffee implements Serializable {
    CANS(1,"cans"), BAGS(2,"bags");//cans - банки, bags - мешки, пакеты

    public int id;
    public String name;

    PackageCoffee(int id) {
        this.id = id;
    }

    PackageCoffee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
