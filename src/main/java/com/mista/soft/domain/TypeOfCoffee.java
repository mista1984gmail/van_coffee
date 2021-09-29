package com.mista.soft.domain;

import java.io.Serializable;

public enum TypeOfCoffee implements Serializable {
    BEAN_COFFEE(1,"bean_coffee"),
    DISSOLVE_COFFEE(2,"dissolve_coffee_in_cans"),
    GROUND_COFFEE(3,"ground_coffee");


    /*BEAN_COFFEE, // кофе в зернах
    DISSOLVE_COFFEE,//растворимый кофе в банках
    GROUND_COFFEE,//молотый кофе*/


    public int i;
    public String name;

    TypeOfCoffee(int i) {
        this.i = i;
    }

    TypeOfCoffee(int i, String name) {
        this.i = i;
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
