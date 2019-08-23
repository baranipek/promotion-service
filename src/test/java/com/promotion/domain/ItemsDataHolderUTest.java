package com.promotion.domain;

import org.junit.Test;


import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ItemsDataHolderUTest {

    @Test
    public void productIsCodeInTheList_FindItems_ItemsDetailsAreReturned() {
        //given && when
        ItemsDataHolder itemsDataHolder = new ItemsDataHolder();
        Optional<Item> actual = itemsDataHolder.findItems("001");

        Optional<Item> expected = Optional.of(new Item("001", "Travel Card Holder", 9.25));
        assertNotEquals(actual, Optional.empty());
        assertEquals("failure - basket total amounts are not equal", expected, actual);
    }

    @Test
    public void productIsCodeInTheList_FindItems_ItemsDetailsAreReturned1() {
        //given && when
        ItemsDataHolder itemsDataHolder = new ItemsDataHolder();
        Optional<Item> actual = itemsDataHolder.findItems("001234");

        assertEquals(actual, Optional.empty());
    }

}
