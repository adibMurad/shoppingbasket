package com.interview.shoppingbasket;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

class CheckoutContext {
    @Getter
    private final Basket basket;
    @Setter
    private double retailPriceTotal = 0.0;
    @Getter
    @Setter
    private List<Promotion> promotions;

    CheckoutContext(Basket basket) {
        this.basket = basket;
    }

    public PaymentSummary paymentSummary() {
        return new PaymentSummary(retailPriceTotal);
    }
}
