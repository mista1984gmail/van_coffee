package com.mista.soft.service;

import com.mista.soft.domain.*;
import com.mista.soft.repository.VanRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Consumer;

@Slf4j
public class VanServiceImp implements VanService {
    private VanRepository repository;
    public static final Consumer<Van> LOG_ACTION = van ->
            log.info(van.getNameVan() + ", id: " + van.getId());
    public VanServiceImp(VanRepository repository) {
        this.repository = repository;
    }
    @Override
    public void saveVan(@NonNull Van van) throws Exception {
        log.info("Trying to save van: {}", van);
        boolean isUserSaved = repository.saveVan(van);
        String success = isUserSaved ? "" : "not ";
        log.info("Van was {}saved: {}", success, van);
    }

    @Override
    public Van getVan(int id) throws Exception {
        log.info("Trying to get van with id = '{}'", id);
        Optional<Van> van = repository.getVan(id);
        if (van.isPresent()) {
            log.info("{} is gotten", van.get());
            return van.get();
        } else {
            log.info("Creating new van because no van with id");
            return Van.builder().id(id).build();
        }
    }

    @Override
    public void showInfo() throws Exception {
        log.info("Showing info about vans");
        repository.getVans().forEach(LOG_ACTION);
    }

    @Override
    public void deleteVan(int id) throws Exception {
        log.info("Trying to delete van with id= '{}'", id);
        repository.deleteVan(id);
    }

    @Override
    public void showCoffeeInfo(int id) throws Exception {
        log.info("Showing coffee in vans");
        log.info(String.valueOf(repository.coffees(id)));
    }

    @Override
    public Coffee addCoffee() throws Exception {
        Scanner scanner = new Scanner(System.in);
        log.info("Name Coffee: ");
        String nameCoffee=scanner.nextLine();
        log.info("Net weight: ");
        double netWeight=scanner.nextDouble();
        log.info("Gross weight: ");
        double grossWeight=scanner.nextDouble();
        log.info("Quantities: ");
        int quantities=scanner.nextInt();
        log.info("Price: ");
        double price=scanner.nextDouble();
        log.info("Type of coffee 1 - BEAN_COFFEE; 2 - DISSOLVE_COFFEE_IN_CANS; 3 - DISSOLVE_COFFEE_BAGS; 4 - GROUND_COFFEE");
        int i = scanner.nextInt();
        TypeOfCoffee typeOfCoffee=null;
        switch (i){
            case 1:
                typeOfCoffee = TypeOfCoffee.BEAN_COFFEE;
                break;
            case 2:
                typeOfCoffee = TypeOfCoffee.DISSOLVE_COFFEE_IN_CANS;
                break;
            case 3:
                typeOfCoffee = TypeOfCoffee.DISSOLVE_COFFEE_BAGS;
                break;
            case 4:
                typeOfCoffee = TypeOfCoffee.GROUND_COFFEE;
                break;
            default:
                log.info("There is no such option, please choose another option.");
        }
        log.info("Package 1 - CANS; 2 - BAGS");
        int j = scanner.nextInt();
        PackageCoffee packageCoffee = null;
        switch (j){
            case 1:
                packageCoffee = PackageCoffee.CANS;
                break;
            case 2:
                packageCoffee = PackageCoffee.BAGS;
            default:
                log.info("There is no such option, please choose another option.");
        }
        Coffee coffee = new DissolveCoffee(nameCoffee,netWeight,grossWeight,quantities,price,typeOfCoffee,packageCoffee);
        return coffee;
    }
    @Override
    public void sortCoffeeByPriceForKg(int id) throws Exception {
        log.info("Showing coffee van");
        List<Coffee> coffeeList=repository.coffees(id);
        Collections.sort(coffeeList, new Comparator<Coffee>() {
            @Override
            public int compare(Coffee o1, Coffee o2) {
                if (o1.getPriceForKg() > o2.getPriceForKg()){
                    return 1;
                } else if (o1.getPriceForKg() < o2.getPriceForKg()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (Coffee coffee:coffeeList) {
            System.out.println(coffee);
        }
    }
}
