package hu.tmx.shop.model.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlowerTest {

    Flower flower;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        flower = new Flower("Liliom", 197);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void GetPrice(){
        assertEquals(1394, flower.getPrice());
    }

    @Test
    public void FirstMaintenanceNeedIsTrueIfFlowerOlderThan3Years(){
        assertTrue(flower.needMaintenance());
    }

    @Test
    public void FirstMaintenanceNeedIsTrueIfFlower3Years(){
        flower.setAgeInWeeks(156);
        assertTrue(flower.needMaintenance());
    }

    @Test
    public void SecondMaintenanceNeedIsTrueIfPreviousMaintenanceOlderThan3Years(){
        flower.setLastMaintenanceDate(LocalDate.now().minusWeeks(200));
        assertTrue(flower.needMaintenance());
    }

    @Test
    public void FirstMaintenanceNeedIsFalseIfFlowerYoungerThan3Years(){
        flower.setAgeInWeeks(10);
        assertFalse(flower.needMaintenance());
    }

    @Test
    public void SecondMaintenanceNeedIsFalseIfPreviousMaintenanceYoungerThan3Years(){
        flower.setLastMaintenanceDate(LocalDate.now().minusWeeks(52));
        assertFalse(flower.needMaintenance());
    }

    @Test
    public void MaintenanceTypeIsCorrectIfFlowerNeedsMaintenance(){
        assertEquals("Öntözés", flower.maintain());
        assertEquals(LocalDate.now(), flower.getLastMaintenanceDate());
    }

    @Test
    public void MaintenanceTypeIsNullIfFlowerNoNeedMaintenance(){
        flower.setAgeInWeeks(10);
        assertNull(flower.maintain());
        assertNull(flower.getLastMaintenanceDate());
    }

    @Test
    public void ToStringCorrect(){
        System.out.println(flower);
        assertEquals("49 hónapos és 1 hetes Liliom - 1394.0", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown(){
        flower = null;
        System.setOut(standardOut);
    }

}
