package com.interview.shoppingbasket;

public class PromotionsStep implements CheckoutStep {
    private final PromotionsService promotionsService;

    public PromotionsStep(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        checkoutContext.setPromotions(promotionsService.getPromotions(basket));
    }
}
