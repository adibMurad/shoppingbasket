package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        items = items.stream()
                .collect(Collectors
                        .collectingAndThen(
                                Collectors.toMap(BasketItem::getProductCode, Function.identity(), (item1, item2) -> {
                                    item1.setQuantity(item1.getQuantity() + item2.getQuantity());
                                    return item1;
                                }), m -> new ArrayList<>(m.values())));
    }
}
