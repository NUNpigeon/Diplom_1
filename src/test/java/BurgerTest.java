
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {


    private static final float MOCK_BUN_PRICE = 100.0f;
    private static final float MOCK_SAUCE_PRICE = 100.0f;
    private static final float MOCK_BUN_PRICE_2 = 200.0f;
    private static final float MOCK_BUN_PRICE_3 = 50.0f;
    private static final float MOCK_SAUCE_PRICE_2 = 30.0f;


    private static final int INDEX_FIRST = 0;
    private static final int INDEX_SECOND = 1;
    private static final int INDEX_THIRD = 2;

    private static final int EXPECTED_SIZE_AFTER_REMOVAL = 1;

    private static final float PRICE_DELTA = 0.001f;

    @Test
    public void priceCalculationWithMocks() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE);
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(MOCK_SAUCE_PRICE);

        burger.addIngredient(i1);

        float expectedPrice = calculateExpectedPrice(bun, i1);
        assertEquals(expectedPrice, burger.getPrice(), PRICE_DELTA);
    }

    @Test
    public void moveIngredientShouldMoveThirdToSecondPosition() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_2);
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

        burger.moveIngredient(INDEX_THIRD, INDEX_SECOND);

        assertSame(i3, burger.ingredients.get(INDEX_SECOND));
    }

    @Test
    public void moveIngredientShouldKeepFirstIngredientInPlace() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_2);
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


        burger.moveIngredient(INDEX_THIRD, INDEX_SECOND);

        assertSame(i1, burger.ingredients.get(INDEX_FIRST));
    }

    @Test
    public void moveIngredientShouldPlaceSecondIngredientAtThirdPosition() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_2);
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

        burger.moveIngredient(INDEX_THIRD, INDEX_SECOND);

        assertSame(i2, burger.ingredients.get(INDEX_THIRD));
    }

    @Test
    public void removeIngredientShouldDecreaseSizeByOne() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_2);
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getName()).thenReturn("ing1");

        Ingredient i2 = mock(Ingredient.class);
        when(i2.getName()).thenReturn("ing2");

        burger.addIngredient(i1);
        burger.addIngredient(i2);

        burger.removeIngredient(INDEX_FIRST);

        assertEquals(EXPECTED_SIZE_AFTER_REMOVAL, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveFirstIngredient() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_2);
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getName()).thenReturn("ing1");

        Ingredient i2 = mock(Ingredient.class);
        when(i2.getName()).thenReturn("ing2");

        burger.addIngredient(i1);
        burger.addIngredient(i2);

        burger.removeIngredient(INDEX_FIRST);

        assertSame(i2, burger.ingredients.get(INDEX_FIRST));
    }

    @Test
    public void receiptShouldStartWithBunName() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_3);

        Burger burger = new Burger();
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(MOCK_SAUCE_PRICE_2);
        burger.addIngredient(i1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.startsWith("(==== mock bun ====)"));
    }

    @Test
    public void receiptShouldIncludeIngredientInfo() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_3);

        Burger burger = new Burger();
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(MOCK_SAUCE_PRICE_2);
        burger.addIngredient(i1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= " + IngredientType.SAUCE.toString().toLowerCase() + " mock sauce"));
    }

    @Test
    public void receiptPriceShouldMatchCalculatedPrice() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_3);

        Burger burger = new Burger();
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(MOCK_SAUCE_PRICE_2);
        burger.addIngredient(i1);

        float expectedPrice = calculateExpectedPrice(bun, i1);
        assertEquals(expectedPrice, burger.getPrice(), PRICE_DELTA);
    }

    @Test
    public void receiptShouldContainFormattedPriceLine() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("mock bun");
        when(bun.getPrice()).thenReturn(MOCK_BUN_PRICE_3);

        Burger burger = new Burger();
        burger.setBuns(bun);

        Ingredient i1 = mock(Ingredient.class);
        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("mock sauce");
        when(i1.getPrice()).thenReturn(MOCK_SAUCE_PRICE_2);
        burger.addIngredient(i1);

        String receipt = burger.getReceipt();

        float expectedPrice = calculateExpectedPrice(bun, i1);
        String expectedPriceLine = String.format(Locale.US, "Price: %.6f", expectedPrice);

        assertTrue("Чек должен содержать строку цены: " + expectedPriceLine,
                receipt.contains(expectedPriceLine));
    }


    private float calculateExpectedPrice(Bun bun, Ingredient ingredient) {
        return bun.getPrice() * 2f + ingredient.getPrice();
    }
}
