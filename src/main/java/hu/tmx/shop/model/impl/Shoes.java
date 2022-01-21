package hu.tmx.shop.model.impl;

import hu.tmx.shop.model.Product;
import hu.tmx.shop.model.Refundable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shoes extends Product implements Refundable {

    private static final String NAME = "Cipő";

    private final int smallShoesPrice = 14000;
    private final int largeShoesPrice = 15000;
    private final int limitOfRefundsInDays = 50;
    private final double baseSize = 40;

    private double size;
    private String brandName;

    public Shoes(String brandName, double size) {
        super(NAME);
        this.size = size;
        this.brandName = brandName;
    }

    @Override
    public double getPrice() {
        return this.size > this.baseSize ? this.largeShoesPrice : this.smallShoesPrice;
    }

    @Override
    public double getPriceOfRefunds(LocalDate sellDate) {
        long daysSincePurchase = ChronoUnit.DAYS.between(sellDate, LocalDate.now());
        if (daysSincePurchase == 0) {
            return getPrice();
        }
        if (daysSincePurchase < this.limitOfRefundsInDays) {
            return getPrice() / 2;
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.size + " méretű " + getBrandName() + " " + getName() + " - " + getPrice();
    }
}
