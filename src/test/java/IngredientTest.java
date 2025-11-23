import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import praktikum.Ingredient;
import praktikum.IngredientType;

public class IngredientTest {

    private static final float DELTA = 0.01f;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        // конструктор в Ingredient: Ingredient(IngredientType type, String name, float price)
        ingredient = new Ingredient(IngredientType.SAUCE, "Mock Ingredient", 2.5f);
    }

    @Test
    public void gettersShouldReturnInitialValues() {
        assertEquals("getType() should return the correct type", IngredientType.SAUCE, ingredient.getType());
        assertEquals("getName() should return the correct name", "Mock Ingredient", ingredient.getName());
        assertEquals("getPrice() should return the correct price", 2.5f, ingredient.getPrice(), DELTA);
    }
}