package com.mista.soft.domain;

import java.io.Serializable;

public class GroundCoffee extends Coffee implements Serializable {

    private TypeOfCoffee typeOfCoffee;
    private PackageCoffee packageCoffee;

    public GroundCoffee(String nameCoffee, double netWeight, double grossWeight, int quantities, double price, TypeOfCoffee typeOfCoffee, PackageCoffee packageCoffee) {
        super(nameCoffee, netWeight, grossWeight, quantities, price);
        this.typeOfCoffee = TypeOfCoffee.GROUND_COFFEE;
        this.packageCoffee = packageCoffee;
    }

    @Override
    public String toString() {
        return "GroundCoffee{" +
                "typeOfCoffee=" + typeOfCoffee +
                ", packageCoffee=" + packageCoffee +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
