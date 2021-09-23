package com.mista.soft.domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class Coffee implements Comparable, Serializable {
    String nameCoffee;
    double netWeight;
    double grossWeight;
    int quantities;
    double price;
    double priceForKg;

    public Coffee(String nameCoffee, double netWeight, double grossWeight, int quantities, double price) {
        this.nameCoffee = nameCoffee;
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.quantities = quantities;
        this.price = price;
        this.priceForKg=price/netWeight;
    }

    public String getNameCoffee() {
        return nameCoffee;
    }

    public void setNameCoffee(String nameCoffee) {
        this.nameCoffee = nameCoffee;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceForKg() {
        return priceForKg;
    }

    public void setPriceForKg(double priceForKg) {
        this.priceForKg = priceForKg;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "nameCoffee='" + nameCoffee + '\'' +
                ", netWeight=" + netWeight +
                ", grossWeight=" + grossWeight +
                ", quantities=" + quantities +
                ", price=" + price +
                ", priceForKg=" + priceForKg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Double.compare(coffee.netWeight, netWeight) == 0 && Double.compare(coffee.grossWeight, grossWeight) == 0 && quantities == coffee.quantities && Double.compare(coffee.price, price) == 0 && nameCoffee.equals(coffee.nameCoffee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCoffee, netWeight, grossWeight, quantities, price);
    }
}
