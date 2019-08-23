package com.promotion.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
public class Basket implements Serializable {

    private static final long serialVersionUID = -8941875507658229292L;

    private ItemsDataHolder itemsDataHolder = new ItemsDataHolder();

    private Map<Item, Integer> basketItems = new HashMap<>();

    private Double totalAmount;

    private double getInitialTotal() {
        return basketItems.keySet().stream()
                .mapToDouble(item -> item.getUnitPrice() * basketItems.getOrDefault(item, 1)).sum();
    }

    void addToBasket(String productCode) {
        itemsDataHolder
                .findItems(productCode)
                .ifPresent(
                        item -> basketItems.merge(item, basketItems.getOrDefault(item, 1), Integer::sum));
    }

    void makeDiscount(double discount) {
        this.getInitialTotalAmount();
        this.totalAmount -= discount;
    }

    private void getInitialTotalAmount() {
        if (this.totalAmount == null) {
            this.totalAmount = getInitialTotal();
        }
    }

    public double getTotalAmount() {
        this.getInitialTotalAmount();
        return totalAmount;
    }
}
