package hu.tmx.shop.model.impl;

import hu.tmx.shop.model.Maintainable;
import hu.tmx.shop.model.Product;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flower extends Product implements Maintainable {

    private final double basePrice = 1000;
    private final String maintainType = "Öntözés";
    private final int frequencyOfMaintenanceInYears = 3;
    private final int numberOfWeeksInMonth = 4;
    private final int numberOfWeeksInYear = 52;
    private final int priceMultiplier = 2;

    private int ageInWeeks;
    private LocalDate lastMaintenanceDate;

    public Flower(String name, int ageInWeeks) {
        super(name);
        this.ageInWeeks = ageInWeeks;
    }

    @Override
    public double getPrice() {
        return this.basePrice + this.ageInWeeks * this.priceMultiplier;
    }

    @Override
    public boolean needMaintenance() {
        if (this.lastMaintenanceDate == null && this.ageInWeeks >= numberOfWeeksInYear * frequencyOfMaintenanceInYears) {
            return true;
        }
        if (this.lastMaintenanceDate != null) {
            int dateDiffForOtherMaintain = (int) ChronoUnit.WEEKS.between(this.lastMaintenanceDate, LocalDate.now());
            return (dateDiffForOtherMaintain >= numberOfWeeksInYear * frequencyOfMaintenanceInYears);
        }
        return false;
    }

    @Override
    public String maintain() {
        if(needMaintenance()){
            lastMaintenanceDate = LocalDate.now();
            return this.maintainType;
        }
        return null;
    }

    public String display() {
        String ageString = "";
        if (this.ageInWeeks < this.numberOfWeeksInMonth) {
            ageString = this.ageInWeeks + " hetes";
        } else if (this.ageInWeeks % this.numberOfWeeksInMonth == 0) {
            ageString = this.ageInWeeks / this.numberOfWeeksInMonth + " hónapos";
        } else {
            ageString = this.ageInWeeks / this.numberOfWeeksInMonth + " hónapos és " + this.ageInWeeks % this.numberOfWeeksInMonth + " hetes";
        }
        return ageString + " " + this.getName() + " - " + getPrice();
    }

    @Override
    public String toString() {
        return "Flower {" +
                "name=" + getName() +
                ", ageInWeeks=" + getAgeInWeeks() +
                ", price=" + getPrice() +
                '}';
    }
}