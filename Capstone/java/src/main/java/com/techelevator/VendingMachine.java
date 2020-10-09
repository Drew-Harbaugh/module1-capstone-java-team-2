package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine extends VendingMachineBalance {

    VendingMachineBalance vmBalance = new VendingMachineBalance();

    public VendingMachine() {
        try {
            createProductList();
        } catch (IOException e) {
            System.out.println("Couldn't find inventory file.");
        }
        vmBalance.getBalance();
    }

    private List<Product> products = new ArrayList<>();

    public List<Product> createProductList() throws IOException {
        Path fileName = Paths.get("inventory.txt");
        try (Scanner inventoryList = new Scanner(fileName)) {
            while (inventoryList.hasNextLine()) {
                String[] lineSeparated = inventoryList.nextLine().split("\\|");
                products.add(new Product(lineSeparated[0], lineSeparated[1],
                        BigDecimal.valueOf(Double.parseDouble(lineSeparated[2])), lineSeparated[3]));
            }
        } catch (IOException e) {
            System.out.println("Couldn't read inventory file.");
        }
        return products;
    }

    public String displayProducts() {
        String result = "";
        for (Product product : products) {
            if (!product.isAvailable()) {
                product.setName("SOLD OUT");
            }
            result += product.getLocation() + " | " + product.getName() + " | " + product.getPrice() + "\n";
        }
        return result;
    }

    public String chooseProducts(String input) {
        String result = "";
        for (Product product : products) {
            if (product.getLocation().contains(input)) {
                result = vmBalance.subtractMoney(product.getPrice());
            }
        }
        return result;
    }
    //feed money



    //put account balance


    //return change

}

