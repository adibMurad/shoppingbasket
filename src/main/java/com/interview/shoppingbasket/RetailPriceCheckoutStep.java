package com.interview.shoppingbasket;

import java.util.List;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private final PricingService pricingService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        List<Promotion> promotions = checkoutContext.getPromotions();
        retailTotal = 0.0;

        for (BasketItem basketItem : basket.getItems()) {
             double price = pricingService.getPrice(basketItem.getProductCode());

            // Problem 2 says: "Assume that each promotion is tied to a specific productCode"
            // That doesn't mean a productCode is tied to just one promotion. So, I should do:
            // List<Promotion> itemPromotion = promotions.stream().filter(p -> p.getProductCode().equals(basketItem.getProductCode())).collect(Collectors.toList());
            // However, applyPromotion() signature implies that yes, I have just one promotion for each productCode (a 1:1 relationship).
            // So the first promotion found for that productCode on the list is the one (or there is none):
            Promotion itemPromotion =
                    promotions.stream().filter(p -> p.getProductCode().equals(basketItem.getProductCode())).findFirst().orElse(Promotion.NONE);

            basketItem.setProductRetailPrice(price);

            retailTotal = applyPromotion(itemPromotion, basketItem);
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    private double applyPromotion(Promotion promotion, BasketItem item) {
        switch (promotion) {
            case OFF_10:
                retailTotal += item.getQuantity() * item.getProductRetailPrice() * 0.9;
                break;
            case OFF_50:
                retailTotal += item.getQuantity() * item.getProductRetailPrice() * 0.5;
                break;
            case TWO_FOR_ONE:
                int half = item.getQuantity() / 2;
                retailTotal += (half + (item.getQuantity() % 2)) * item.getProductRetailPrice();
                break;
            default:
                retailTotal += item.getQuantity() * item.getProductRetailPrice();
        }
        return retailTotal;
    }

}
