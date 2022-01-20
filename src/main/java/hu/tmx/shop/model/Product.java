package hu.tmx.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Product implements Sellable{

    private final String name;

}
