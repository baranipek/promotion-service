package com.promotion.helper;


import com.promotion.domain.Basket;
import com.promotion.domain.Item;
import com.promotion.domain.PromotionRule;

import java.util.function.Function;
import java.util.function.Predicate;

public class PromotionRuleHelper {

    public static PromotionRule getItemPromotionRule(Item item, double discountPercentage, int itemCountDiscount) {
        Predicate<Basket> multipleTravelCard = basket -> basket.getBasketItems().getOrDefault(item, 0) >= itemCountDiscount;

        Function<Basket, Double> travelCardBasketDiscount = basket -> basket.getBasketItems().
                getOrDefault(item, 0) * discountPercentage;

        return new PromotionRule("Multiple travel car promotion", multipleTravelCard, travelCardBasketDiscount);
    }


    public static PromotionRule getBasketDiscountPromotionRule(double totalBasketAmount, double discountPercentage) {
        Predicate<Basket> totalBasketAmountRule = Basket -> Basket.getTotalAmount() > totalBasketAmount;
        Function<Basket, Double> discountPercent = Basket -> Basket.getTotalAmount() * discountPercentage;

        return new PromotionRule(discountPercentage + " percent basket promotions over " + totalBasketAmount,
                totalBasketAmountRule, discountPercent);
    }
}
