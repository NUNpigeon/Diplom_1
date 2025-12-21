import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;


public class IngredientTest {

    private static final double DELTA = 0.01;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient(IngredientType.SAUCE, "Mock Ingredient", 2.5f);
    }

    @Test
    public void getTypeShouldReturnCorrectType() {
        assertEquals("Тип ингредиента должен быть SAUCE", IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        assertEquals("Имя ингредиента должно быть 'Mock Ingredient'", "Mock Ingredient", ingredient.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        assertEquals("Цена ингредиента должна быть 2.5f", 2.5, ingredient.getPrice(), DELTA);
    }
}
