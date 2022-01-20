package hu.tmx.shop;

import hu.tmx.shop.model.impl.Flower;
import hu.tmx.shop.model.impl.Shoes;
import hu.tmx.shop.shopmanager.ShopManager;
import java.util.Arrays;

public class ShopApplication
{
    public static void main( String[] args )
    {
        ShopManager shopManager = new ShopManager();

        shopManager.addAll(Arrays.asList(
                new Shoes( "Adidas", 44),
                new Shoes( "Reebok", 40.5),
                new Shoes("Nike", 38),
                new Flower("Liliom", 8),
                new Flower("Jácint", 1)
        ));

        shopManager.displayProductsDetails();
    }
}
