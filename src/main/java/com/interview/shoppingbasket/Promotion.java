package com.interview.shoppingbasket;

import lombok.Getter;

public enum Promotion {
    NONE(null),
    TWO_FOR_ONE("12"),
    OFF_50("34"),
    OFF_10("12");

    @Getter
    private final String productCode;

    Promotion(String productCode) {
        this.productCode = productCode;
    }
}
