package com.mista.soft.app;

import com.mista.soft.db.DB;
import com.mista.soft.domain.Coffee;
import com.mista.soft.domain.Van;
import com.mista.soft.repository.VanRepositoryImp;
import com.mista.soft.service.VanServiceImp;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Slf4j
public class App 
{
    public static final VanRepositoryImp VAN_REPOSITORY = new VanRepositoryImp();
    public static final VanServiceImp VAN_SERVICE = new VanServiceImp(VAN_REPOSITORY);
    public static void main( String[] args ) throws Exception {
        DB.getInstance().load();
        int userInput = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            // instructions
            log.info("Enter 0 to exit the application");//выход из приложения
            log.info("Enter 1 to show all vans");//показ информации о фургонах
            log.info("Enter 2 to delete van");//удаление фургоне
            log.info("Enter 3 to show coffee in the van");//показать кофе в фургоне
            log.info("Enter 4 to add van");//добавить фургон
            log.info("Enter 5 to load the coffee van");//добавить кофе в фургон
            log.info("Enter 6 to sort coffee by price per kilogram");//сортировка кофе по цене за кг
            log.info("Enter 7 to find coffee in a given price range");//найти кофе в диапазоне от до (по цене за кг)
            log.info("Enter 8 to choose the type of coffee in van");//выбрать кофе по типу
            System.out.println("_______________________________________________________________________");
            // reading input
            userInput = scanner.nextInt();

            // choosing option
            switch (userInput) {
                case 0:
                    log.info("Goodbye!");
                    break;
                case 1:
                    VAN_SERVICE.showInfo();
                    break;
                case 2:
                    deleteVan();
                    break;
                case 3:
                    showCoffeesVan();
                    break;
                case 4:
                    addVan();
                    break;
                case 5:
                    addVanCoffee();
                    break;
                case 6:
                    sortCoffeeByPricePerKilogram();
                    break;
                case 7:
                    chooseCoffeePriceRangePerKilogramm();
                    break;
                case 8:
                    sortingByTypeOfCoffees();
                    break;
                default:
                    log.info("There is no such option, please choose another option.");
            }

        } while (userInput != 0);

        DB.getInstance().save();
    }
    private static void sortingByTypeOfCoffees() throws Exception {
        int id;
        int type;
        log.info("Input id Van for sorting by type of coffee");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        log.info("Input type of coffee 1 - BEAN_COFFEE; 2 - DISSOLVE_COFFEE; 3 - GROUND_COFFEE");
        type = scanner.nextInt();
        VAN_SERVICE.sortingByTypeOfCoffee(id, type);
    }

    private static void chooseCoffeePriceRangePerKilogramm() throws Exception {
        int id;
        double start;
        double end;
        log.info("Input id Van for sort coffee by price per kilogram");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        log.info("Input the minimum sample value for coffee by price per kilogram");
        start = scanner.nextDouble();
        log.info("Input the maximum sample value for coffee by price per kilogram");
        end = scanner.nextDouble();
        VAN_SERVICE.chooseCoffeePriceRangePerKg(id,start,end);
    }

    private static void sortCoffeeByPricePerKilogram() throws Exception {
        int id;
        log.info("Input id Van for sort coffee by price per kilogram");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        VAN_SERVICE.sortCoffeeByPriceForKg(id);
    }

    private static void showCoffeesVan() throws Exception {
        int id;
        log.info("Input id Van for show coffees");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        VAN_SERVICE.showCoffeeInfo(id);
    }


    private static void deleteVan() throws Exception {
        int idForDelete;
        log.info("Input id Van for delete");
        Scanner scanner = new Scanner(System.in);
        idForDelete = scanner.nextInt();
        VAN_SERVICE.deleteVan(idForDelete);
    }



    private static void addVan() throws Exception {
        Van.VanBuilder builder = Van.builder();

        log.info("Input information about Van");
        Scanner scanner = new Scanner(System.in);

        log.info("Name: ");
        builder.nameVan(scanner.nextLine());
        builder.coffeeList(new ArrayList<>());
        Van van = builder.build();

        VAN_SERVICE.saveVan(van);
    }
    private static void addVanCoffee() throws Exception {
        log.info("Input the Van id to load coffee");
        Scanner scanner = new Scanner(System.in);
        int idVan=scanner.nextInt();
        Van van = VAN_SERVICE.getVan(idVan);
        List<Coffee> coffeeList= van.getCoffeeList();
        coffeeList.add(VAN_SERVICE.addCoffee());
    }
    }

