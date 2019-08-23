package com.promotion.domain;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents Promotion rule engine
 */
@AllArgsConstructor
public class PromotionRule implements Serializable {

    private static final long serialVersionUID = -4544389522315481863L;
    private String promotionDescription;

    private Predicate<Basket> basketMatchCondition;

    private Function<Basket, Double> discountBasket;

    void executeRule(Basket basket) {
        if (basketMatchCondition.test(basket)) {
            Double discount = discountBasket.apply(basket);
            basket.makeDiscount(discount);
        }
    }
}
