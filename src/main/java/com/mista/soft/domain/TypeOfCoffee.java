package com.mista.soft.domain;

import java.io.Serializable;

public enum TypeOfCoffee implements Serializable {
    BEAN_COFFEE(1,"bean_coffee"),
    DISSOLVE_COFFEE_IN_CANS(2,"dissolve_coffee_in_cans"),
    DISSOLVE_COFFEE_BAGS(3,"dissolve_coffee_bags"),
    GROUND_COFFEE(4,"ground_coffee");


    /*BEAN_COFFEE, // кофе в зернах
    DISSOLVE_COFFEE_IN_CANS,//растворимый кофе в банках
    DISSOLVE_COFFEE_BAGS, //растворимый кофе в бпакетах
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
