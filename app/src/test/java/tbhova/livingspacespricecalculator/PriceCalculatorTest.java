package tbhova.livingspacespricecalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class PriceCalculatorTest {
    @Test
    public void getPriceReturnsPriceWhenWeeksIsZero() {
        assertThat(PriceCalculator.calculatePrice(500, 0)).isEqualTo(500);
        assertThat(PriceCalculator.calculatePrice(200, 0)).isEqualTo(200);
        assertThat(PriceCalculator.calculatePrice(0, 0)).isEqualTo(0);
    }

    @Test
    public void getPriceReturnsZeroWhenNegativeInput() {
        assertThat(PriceCalculator.calculatePrice(2000, -1)).isEqualTo(0);
        assertThat(PriceCalculator.calculatePrice(-2000, 10)).isEqualTo(0);
    }

    @Test
    public void getPriceReturnsCorrectPriceForPositiveWeeks() {
        assertThat(PriceCalculator.calculatePrice(0, 10)).isEqualTo(0);
        assertThat(PriceCalculator.calculatePrice(500, 1)).isEqualTo(450);
    }

}
