package hu.tmx.shop.shopmanager;

import hu.tmx.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ShopManager {

    private ArrayList<Product> products = new ArrayList<>();

    public void addAll(List<Product> products){
        this.products.addAll(products);
    }

    public void displayProductsDetails() {
        this.products.forEach(System.out::println);
    }
}
