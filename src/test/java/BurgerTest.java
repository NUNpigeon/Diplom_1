import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BurgerTest {


    @Test
    public void priceCalculationWithMocks() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(100f);

        burger.addIngredient(i1);

        float expected = bun.getPrice() * 2f + i1.getPrice();
        assertEquals(expected, burger.getPrice(), 0.0001f);
    }

    @Test
    public void moveIngredientReordersListWithMocks() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(200f);
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.FILLING);

        Ingredient i2 = mock(Ingredient.class);
        when(i2.getType()).thenReturn(IngredientType.SAUCE);

        Ingredient i3 = mock(Ingredient.class);
        when(i3.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(i1);
        burger.addIngredient(i2);
        burger.addIngredient(i3);

        // Переместим слой с ингредиентом: индекс 2 -> 1
        burger.moveIngredient(2, 1);

        // Ожидаемый порядок: i1, i3, i2
        assertSame(i1, burger.ingredients.get(0));
        assertSame(i3, burger.ingredients.get(1));
        assertSame(i2, burger.ingredients.get(2));
    }

    @Test
    public void removeIngredientRemovesByIndexWithMocks() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(200f);
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getName()).thenReturn("ing1");

        Ingredient i2 = mock(Ingredient.class);
        when(i2.getName()).thenReturn("ing2");

        burger.addIngredient(i1);
        burger.addIngredient(i2);

        // Удаляем ингредиент по индексу 0
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(i2, burger.ingredients.get(0));
    }

    @Test
    public void receiptContainsInfoFromMocks() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(50f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(30f);

        burger.addIngredient(i1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.startsWith("(==== mock bun ====)"));
        assertTrue(receipt.contains("= " + IngredientType.SAUCE.toString().toLowerCase() + " mock sauce"));

        float expectedPrice = 50f * 2f + 30f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);

        String expectedPriceLine = String.format("Price: %f", expectedPrice);
        assertTrue(receipt.contains(expectedPriceLine));
    }
}