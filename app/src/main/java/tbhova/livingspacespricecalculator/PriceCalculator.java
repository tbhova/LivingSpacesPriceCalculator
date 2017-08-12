package tbhova.livingspacespricecalculator;

/**
 * Created by Tyler on 8/10/2017.
 */

public class PriceCalculator {
    final static int DISCOUNT = 10;

    private PriceCalculator() {}

    public static int calculatePrice(final int priceInCents, final int numberOfWeeks) {
        if (priceInCents < 0 || numberOfWeeks < 0) {
            return 0;
        }
        int finalPriceInCents = priceInCents;

        for (int i = 0; i < numberOfWeeks; i++) {
            finalPriceInCents -= finalPriceInCents/DISCOUNT;
        }
        return finalPriceInCents;
    }
}
