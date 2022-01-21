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
        if (this.lastMaintenanceDate == null) {
            return (this.ageInWeeks >= this.numberOfWeeksInYear * this.frequencyOfMaintenanceInYears);
        }
        int dateDiffForOtherMaintain = (int) ChronoUnit.WEEKS.between(this.lastMaintenanceDate, LocalDate.now());
        return (dateDiffForOtherMaintain >= this.numberOfWeeksInYear * this.frequencyOfMaintenanceInYears);
    }

    @Override
    public String maintain() {
        if(needMaintenance()){
            this.lastMaintenanceDate = LocalDate.now();
            return this.maintainType;
        }
        return null;
    }

    private String display() {
        String ageString;
        if (this.ageInWeeks < this.numberOfWeeksInMonth) {
            ageString = this.ageInWeeks + " hetes";
        } else if (this.ageInWeeks % this.numberOfWeeksInMonth == 0) {
            ageString = this.ageInWeeks / this.numberOfWeeksInMonth + " hónapos";
        } else {
            ageString = this.ageInWeeks / this.numberOfWeeksInMonth + " hónapos és " + this.ageInWeeks % this.numberOfWeeksInMonth + " hetes";
        }
        return ageString + " " + getName() + " - " + getPrice();
    }

    @Override
    public String toString() {
        return display();
    }
}
