package com.mista.soft.service;

import com.mista.soft.domain.*;
import com.mista.soft.exeption.IdIsNotAllowedOnDbException;
import com.mista.soft.repository.VanRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        boolean isVanSaved = repository.saveVan(van);
        String success = isVanSaved ? "" : "not ";
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
        try{
        repository.getVans().forEach(LOG_ACTION);}
        catch (IdIsNotAllowedOnDbException e){
            e.getMessage();
        }
    }

    @Override
    public void deleteVan(int id) throws Exception {
        log.info("Trying to delete van with id= '{}'", id);
        log.info("Van with id= '{}' delete", id);
        repository.deleteVan(id);

    }

    @Override
    public void showCoffeeInfo(int id) throws Exception {
        log.info("Showing coffee in vans");
        log.info(String.valueOf(repository.coffees(id)));
        totalCalculation(id);
    }

    @Override
    public Coffee addCoffee() throws Exception {
        Scanner scanner = new Scanner(System.in);
        log.info("Type of coffee 1 - BEAN_COFFEE; 2 - DISSOLVE_COFFEE; 3 - GROUND_COFFEE");
        int i = scanner.nextInt();
        log.info("Package 1 - CANS; 2 - BAGS");
        int j = scanner.nextInt();
        PackageCoffee packageCoffee = null;
        switch (j){
            case 1:
                packageCoffee = PackageCoffee.CANS;
                break;
            case 2:
                packageCoffee = PackageCoffee.BAGS;
                break;
            default:
                log.info("There is no such option, please choose another option.");
        }
        log.info("Name Coffee: ");
        String name = scanner.next();
        log.info("Net weight: ");
        double netWeight=scanner.nextDouble();
        log.info("Gross weight: ");
        double grossWeight=scanner.nextDouble();
        log.info("Quantities: ");
        int quantities=scanner.nextInt();
        log.info("Price: ");
        double price=scanner.nextDouble();


        switch (i){
            case 1:
                Coffee beanCoffee = new BeanCoffee(name,netWeight,grossWeight,quantities,price,packageCoffee);
                return beanCoffee;
            case 2:
                Coffee dissolveCoffee = new DissolveCoffee(name,netWeight,grossWeight,quantities,price,packageCoffee);
                return dissolveCoffee;
            case 3:
                Coffee groundCoffee = new GroundCoffee(name,netWeight,grossWeight,quantities,price,packageCoffee);
                return groundCoffee;
            default:
                log.info("There is no such option, please choose another option.");
        }
        return null;
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
            System.out.print(coffee);
        }
        System.out.println();
        totalCalculation(coffeeList);
    }

    @Override
    public void chooseCoffeePriceRangePerKg(int id, double start, double end) throws Exception {
        log.info("Showing coffee van");
        List<Coffee> coffeeList=repository.coffees(id);
        List<Coffee> listChoose = coffeeList.stream()
                .filter(s ->(s.getPriceForKg()  >= start && s.getPriceForKg() <= end))
                .collect(Collectors.toList());
        for (Coffee coffee:listChoose) {
            System.out.print(coffee);
        }
        System.out.println();
        totalCalculation(listChoose);
    }

    @Override
    public void totalCalculation(int id) throws Exception {
        double totalNetWeight=0;
        double totalGrossWeight=0;
        double totalCost=0;
        List<Coffee> coffeeList=repository.coffees(id);
        for (Coffee coffee:coffeeList) {
            totalNetWeight = totalNetWeight + coffee.getNetWeight()*coffee.getQuantities();
            totalGrossWeight = totalGrossWeight + coffee.getGrossWeight()*coffee.getQuantities();
            totalCost = totalCost + coffee.getPrice()*coffee.getQuantities();
        }
        System.out.println(("TOTAL NET WEIGHT: " + totalNetWeight + " KG;"+ "TOTAL GROSS WEIGHT: "
                + totalGrossWeight + " KG;" + " TOTAL COST: " + totalCost + " $"));

    }

    @Override
    public void totalCalculation(List<Coffee> coffeeList) throws Exception {
        double totalNetWeight=0;
        double totalGrossWeight=0;
        double totalCost=0;
        for (Coffee coffee:coffeeList) {
            totalNetWeight = totalNetWeight + coffee.getNetWeight()*coffee.getQuantities();
            totalGrossWeight = totalGrossWeight + coffee.getGrossWeight()*coffee.getQuantities();
            totalCost = totalCost + coffee.getPrice()*coffee.getQuantities();
        }
        System.out.println(("TOTAL NET WEIGHT: " + totalNetWeight + " KG;"+ "TOTAL GROSS WEIGHT: "
                + totalGrossWeight + " KG;" + " TOTAL COST: " + totalCost + " $"));
    }

    @Override
    public void sortingByTypeOfCoffee(int id, int type) throws Exception {
        log.info("Showing coffee van");
        List<Coffee> coffeeList=repository.coffees(id);
        Class clazz=null;
        switch (type){
        case 1:
            clazz = BeanCoffee.class;
            break;
        case 2:
            clazz = DissolveCoffee.class;
            break;
        case 3:
            clazz = GroundCoffee.class;
            break;
        default:
        log.info("There is no such option, please choose another option.");
    }
        //TypeOfCoffee typeOfCoffee = TypeOfCoffee.values()[type];
        Class finalClazz = clazz;
        List<Coffee> listChoose = coffeeList.stream()
                .filter(s ->s.getClass().equals(finalClazz))
                .collect(Collectors.toList());
        for (Coffee coffee:listChoose) {
            System.out.print(coffee);
        }
        System.out.println();
        totalCalculation(listChoose);
    }
}
