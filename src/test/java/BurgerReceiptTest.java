import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerReceiptTest {


    @Test
    public void receiptContainsInfoFromMocks() {
        // Мок булочки
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(50f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        // Мок ингредиента
        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(30f);

        burger.addIngredient(i1);

        String receipt = burger.getReceipt();

        // Заголовок чека
        assertTrue(receipt.startsWith("(==== mock bun ====)"));

        // Ингредиент в чеке
        assertTrue(receipt.contains("= " + IngredientType.SAUCE.toString().toLowerCase() + " mock sauce"));

        // Нижняя часть чека с той же булочкой
        assertTrue(receipt.contains("(==== mock bun ====)"));

        // Цена чека и общая цена
        float expectedPrice = 50f * 2f + 30f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);

        String expectedPriceLine = String.format(Locale.US, "Price: %f", expectedPrice);
        assertTrue(receipt.contains(expectedPriceLine));
    }
}