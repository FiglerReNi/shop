package hu.tmx.shop.shopmanager;

import static org.junit.jupiter.api.Assertions.*;

import hu.tmx.shop.model.impl.Flower;
import hu.tmx.shop.model.impl.Shoes;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopManagerTest {

    ShopManager shopManager;
    Flower flower;
    Shoes shoes;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        shopManager = new ShopManager();
        flower = new Flower("Liliom", 197);
        shoes = new Shoes( "Adidas", 44);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void displayPriceIsCorrect(){
        shopManager.addAll(Arrays.asList(flower, shoes));
        shopManager.displayProductsDetails();
        assertEquals("49 hónapos és 1 hetes Liliom - 1394"
                + System.lineSeparator() + "44.0 méretű Adidas Cipő - 15000", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown(){
        flower = null;
        shoes = null;
        System.setOut(standardOut);
    }

}
