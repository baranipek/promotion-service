package com.promotion.domain;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasketUTest {

    private Basket basket;

    @Before
    public void setUp(){basket = new Basket();}

    @Test
    public void itemIsInTheBasket_FindItemsCount_ItemsCountIsReturned() {
        //given && when
        basket.addToBasket("001");
        //then
        Integer actual = basket.getBasketItems().getOrDefault(new Item("001"),0);
        assertEquals("failure - item is not in the basket", Integer.valueOf(1), actual);
    }


    @Test
    public void itemsAreAddedToBasket_GetTotalAmount_BasketTotalIsReturned() {
        //given
        basket.addToBasket("001");
        basket.addToBasket("002");
        basket.addToBasket("003");
        //when
        Double actual = basket.getTotalAmount();
        //then
        assertEquals("failure - basket total amount does not match", Double.valueOf(74.2), actual);
    }

    @Test
    public void itemsAreAddedToBasket_MakeDiscount_BasketDiscountIsMade() {
        //given
        basket.addToBasket("001");
        basket.addToBasket("002");
        basket.addToBasket("003");
        //when
        basket.makeDiscount(14.2);
        //then
        Double actual = basket.getTotalAmount();
        assertEquals("failure - basket discount calculation is wrong", Double.valueOf(60.0), actual);
    }
}
