package com.mista.soft.domain;

import java.io.Serializable;

public class DissolveCoffee extends Coffee implements Serializable {

    private TypeOfCoffee typeOfCoffee;
    private PackageCoffee packageCoffee;


    public DissolveCoffee(String nameCoffee, double netWeight, double grossWeight, int quantities, double price, TypeOfCoffee typeOfCoffee, PackageCoffee packageCoffee) {
        super(nameCoffee, netWeight, grossWeight, quantities, price);
        this.typeOfCoffee = typeOfCoffee;
        this.packageCoffee = packageCoffee;

    }
//System.lineSeparator()


    @Override
    public String toString() {
        return System.lineSeparator() +
                "nameCoffee='" + nameCoffee + '\'' +
                ", netWeight=" + netWeight +
                ", grossWeight=" + grossWeight +
                ", quantities=" + quantities +
                ", price=" + price +
                ", priceForKg=" + priceForKg +
                ", typeOfCoffee=" + typeOfCoffee +
                ", packageCoffee=" + packageCoffee;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
