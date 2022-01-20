package hu.tmx.shop.model;

import java.time.LocalDate;

public interface Refundable {

    double getPriceOfRefunds(LocalDate sellDate);

}
