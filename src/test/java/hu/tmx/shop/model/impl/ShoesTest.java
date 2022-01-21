package hu.tmx.shop.model.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoesTest {
    Shoes shoes;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        shoes = new Shoes("Adidas", 44);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void GetLargePriceIfSizeGreaterThan40(){
        assertEquals(15000, shoes.getPrice());
    }

    @Test
    public void GetSmallPriceIfSizeSmallerThan40(){
        shoes.setSize(38);
        assertEquals(14000, shoes.getPrice());
    }

    @Test
    public void GetSmallPriceIfSizeEqual40(){
        shoes.setSize(40);
        assertEquals(14000, shoes.getPrice());
    }

    @Test
    public void GetRefundablePriceOfFirstDay(){
        assertEquals(15000, shoes.getPriceOfRefunds(LocalDate.now()));
    }

    @Test
    public void GetRefundablePriceOfSecondDay(){
        assertEquals(7500, shoes.getPriceOfRefunds(LocalDate.now().minusDays(1)));
    }

    @Test
    public void NoRefundablePriceIfPassedDaysEqual50(){
        assertEquals(0, shoes.getPriceOfRefunds(LocalDate.now().minusDays(50)));
    }

    @Test
    public void NoRefundablePriceAfter50Days(){
        assertEquals(0, shoes.getPriceOfRefunds(LocalDate.now().minusDays(51)));
    }

    @Test
    public void ToStringCorrect(){
        System.out.println(shoes);
        assertEquals("44.0 méretű Adidas Cipő - 15000.0", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown(){
        shoes = null;
        System.setOut(standardOut);
    }

}
