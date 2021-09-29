package com.mista.soft.domain;

import java.io.Serializable;

public class DissolveCoffee extends Coffee implements Serializable {

    private TypeOfCoffee typeOfCoffee;
    private PackageCoffee packageCoffee;


    public DissolveCoffee(String nameCoffee, double netWeight, double grossWeight, int quantities, double price, PackageCoffee packageCoffee) {
        super(nameCoffee, netWeight, grossWeight, quantities, price);
        this.typeOfCoffee = TypeOfCoffee.DISSOLVE_COFFEE;
        this.packageCoffee = packageCoffee;

    }

    //System.lineSeparator()


    @Override
    public String toString() {
        return System.lineSeparator() +
                " TypeOfCoffee=" + typeOfCoffee +
                ", nameCoffee='" + nameCoffee + "'"+
                ", packageCoffee=" + packageCoffee+
                ", netWeight=" + netWeight + " kg "+
                ", grossWeight=" + grossWeight + " kg "+
                ", unit price=" + price + " $ "+
                ", price per kg=" + priceForKg + " $ "+
                ", quantities=" + quantities;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public TypeOfCoffee getTypeOfCoffee() {
        return typeOfCoffee;
    }

    public void setTypeOfCoffee(TypeOfCoffee typeOfCoffee) {
        this.typeOfCoffee = typeOfCoffee;
    }

    public PackageCoffee getPackageCoffee() {
        return packageCoffee;
    }

    public void setPackageCoffee(PackageCoffee packageCoffee) {
        this.packageCoffee = packageCoffee;
    }
}
