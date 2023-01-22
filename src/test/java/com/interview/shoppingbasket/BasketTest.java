package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BasketTest {
    @Test
    void emptyBasket() {
        Basket basket = new Basket();
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 0);
    }

    @Test
    void createBasketFullConstructor() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 1);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
    }

    @Test
    void createBasketWithMultipleProducts() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode3", "myProduct3", 10);

        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(),3);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
        assertEquals(basketSize.get(1).getProductCode(), "productCode2");
        assertEquals(basketSize.get(1).getProductName(), "myProduct2");
        assertEquals(basketSize.get(1).getQuantity(), 10);
        assertEquals(basketSize.get(2).getProductCode(), "productCode3");
        assertEquals(basketSize.get(2).getProductName(), "myProduct3");
        assertEquals(basketSize.get(2).getQuantity(), 10);
    }

    @Test
    void consolidateBasketTest() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode2", "myProduct2", 20);
        basket.add("productCode2", "myProduct2", 21);
        basket.add("productCode2", "myProduct2", 22);
        basket.add("productCode3", "myProduct3", 30);
        basket.add("productCode3", "myProduct3", 31);

        basket.consolidateItems();

        assertEquals(3, basket.getItems().size());

        long actualCount = basket.getItems().stream().filter(i -> "productCode".equals(i.getProductCode())).count();
        assertEquals(1, actualCount);
        long actualQtt = basket.getItems().stream().filter(i -> "productCode".equals(i.getProductCode())).findFirst().map(BasketItem::getQuantity).orElse(0);
        assertEquals(10, actualQtt);

        actualCount = basket.getItems().stream().filter(i -> "productCode2".equals(i.getProductCode())).count();
        assertEquals(1, actualCount);
        actualQtt = basket.getItems().stream().filter(i -> "productCode2".equals(i.getProductCode())).findFirst().map(BasketItem::getQuantity).orElse(0);
        assertEquals(63, actualQtt);

        actualCount = basket.getItems().stream().filter(i -> "productCode3".equals(i.getProductCode())).count();
        assertEquals(1, actualCount);
        actualQtt = basket.getItems().stream().filter(i -> "productCode3".equals(i.getProductCode())).findFirst().map(BasketItem::getQuantity).orElse(0);
        assertEquals(61, actualQtt);
    }
}
