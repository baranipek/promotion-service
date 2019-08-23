package com.promotion.domain;

import com.promotion.exception.BasketIsEmptyException;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Represents a checkout in session with promotion rules and basket
 */
@Data
@RequiredArgsConstructor
class Checkout implements Serializable {

    private static final long serialVersionUID = 1743721017911646703L;

    private static final double MIN_BASKET_AMOUNT = 0;

    private final List<PromotionRule> promotionRules;

    private Basket basket = new Basket();

    void scan(String item) {
        basket.addToBasket(item);
    }

    double total() {
        promotionRules.forEach(rule -> rule.executeRule(basket));
        double checkoutTotal = basket.getTotalAmount();

        if (MIN_BASKET_AMOUNT >= checkoutTotal) {
            throw new BasketIsEmptyException("Basket Is Empty you can not checkout");
        }
        return BigDecimal.valueOf(checkoutTotal).setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
