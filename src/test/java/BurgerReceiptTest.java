import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.assertj.core.api.Assertions;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerReceiptTest {

    @Test
    public void receiptContainsAllExpectedData() {

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(50f);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("mock sauce");
        when(ingredient.getPrice()).thenReturn(30f);


        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);


        float expectedPrice = 50f * 2f + 30f; // 2 булки + соус


        String expectedPriceLine = String.format(Locale.US, "Price: %.6f", expectedPrice);
        String expectedReceipt = String.format(Locale.US,
                "(==== mock bun ====)%n" +
                        "= sauce mock sauce =%n" +
                        "(==== mock bun ====)%n" +
                        "%s%n",
                expectedPriceLine
        );


        String actualReceipt = burger.getReceipt();


        System.out.println("--- ACTUAL RECEIPT ---");
        System.out.println(actualReceipt);
        System.out.println("--- EXPECTED RECEIPT ---");
        System.out.println(expectedReceipt);


        Assertions.assertThat(actualReceipt)
                .as("Чек должен точно соответствовать ожидаемому формату")
                .isEqualTo(expectedReceipt);
    }
}
