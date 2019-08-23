package com.promotion.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents Item List in memory
 */
class ItemsDataHolder implements Serializable {

    private static final long serialVersionUID = 8679597288984361764L;

    private List<Item> itemsList = generateDummyData();

    private List<Item> generateDummyData() {
        this.itemsList = new ArrayList<>(3);
        this.itemsList.add(new Item("001", "Travel Card Holder", 9.25));
        this.itemsList.add(new Item("002", "Personalised cufflinks", 45.00));
        this.itemsList.add(new Item("003", "Kids T-shirt", 19.95));
        return itemsList;
    }

    Optional<Item> findItems(String itemCode) {
        return itemsList.stream()
                .filter(item -> item.getItemCode().equalsIgnoreCase(itemCode)).findFirst();
    }

}
