package com.promotion.domain;

import com.promotion.exception.BasketIsEmptyException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.promotion.helper.PromotionRuleHelper.getBasketDiscountPromotionRule;
import static com.promotion.helper.PromotionRuleHelper.getItemPromotionRule;
import static org.junit.Assert.assertEquals;

public class CheckoutUTest {

    private Checkout checkout;
    private Item travelCardItem;

    @Before
    public void setUp() {
        travelCardItem = new Item("001");
    }

    @Test
    public void basketTotalAmountIsMoreThan60_CheckoutBasket_TenPercentDiscountRuleIsExecuted() {
        //given
        PromotionRule basketTotalAmountDiscount = getBasketDiscountPromotionRule(60, 0.1);
        checkout = new Checkout(Collections.singletonList(basketTotalAmountDiscount));

        checkout.scan("001");
        checkout.scan("002");
        checkout.scan("003");
        //when
        Double actual = checkout.total();
        //then
        Double expected = 66.78;
        assertEquals("failure - basket total amounts are not equal", expected, actual);
    }

    @Test
    public void multipleTravelCardInTheBasketDiscount_CheckoutBasket_ItemPercentageDiscountRuleExecuted() {
        //given
        PromotionRule multipleTravelCard = getItemPromotionRule(travelCardItem, 0.75,2);
        checkout = new Checkout(Collections.singletonList(multipleTravelCard));
        checkout.scan("001");
        checkout.scan("003");
        checkout.scan("001");
        //when
        Double actual = checkout.total();
        //then
        Double expected = 36.95;
        assertEquals("failure - basket total amounts are not equal", expected, actual);

    }

    @Test
    public void multipleTravelCardInTheBasketDiscountTotalIsMoreThan60_CheckoutBasket_ItemPercentageAndBasketDiscountRulesExecuted() {
        //given
        PromotionRule multipleTravelCard = getItemPromotionRule(travelCardItem, 0.75,2);
        PromotionRule basketTotalAmountDiscount = getBasketDiscountPromotionRule(60, 0.1);
        checkout = new Checkout(Arrays.asList(multipleTravelCard, basketTotalAmountDiscount));
        checkout.scan("001");
        checkout.scan("002");
        checkout.scan("001");
        checkout.scan("003");
        //when
        Double actual = checkout.total();
        //then
        Double expected = 73.76;
        assertEquals("failure - basket total amounts are not equal", expected, actual);
    }

    @Test
    public void noDiscountItemInTheBasket_CheckoutBasket_NoMatchWithPromotionRulesNoDiscount() {
        //given
        PromotionRule multipleTravelCard = getItemPromotionRule(travelCardItem, 0.75,2);
        PromotionRule basketTotalAmountDiscount = getBasketDiscountPromotionRule(60, 0.1);
        checkout = new Checkout(Arrays.asList(multipleTravelCard, basketTotalAmountDiscount));
        checkout.scan("003");
        checkout.scan("003");
        //when
        Double actual = checkout.total();
        //then
        Double expected = 39.90;
        assertEquals("failure - basket total amounts are not equal", expected, actual);
    }

    @Test
    public void noItemsInTheBasket_CheckoutBasket_ThrowBasketIsEmptyException() {
        PromotionRule ruleMultipleTravel = getItemPromotionRule(travelCardItem, 0.75,2);
        checkout = new Checkout(Collections.singletonList(ruleMultipleTravel));
        Assertions.assertThrows(BasketIsEmptyException.class, () -> checkout.total());
    }

    @Test
    public void noPromotionsInTheBasket_CheckoutBasket_NoDiscountIsAppliedToTheBasket() {
        //given
        checkout = new Checkout(new ArrayList<>());
        checkout.scan("001");
        checkout.scan("002");
        checkout.scan("001");
        checkout.scan("003");
        //when
        Double actual = checkout.total();
        //then
        Double expected = 83.45;
        assertEquals("failure - basket total amounts are not equal", expected, actual);
    }
}
