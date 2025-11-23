import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;


import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class BurgerPriceTest {


    private final Bun bun;
    private final Ingredient[] ingredients;
    private final float expectedPrice;
    private static final float DELTA = 0.01f;

    public BurgerPriceTest(Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Bun cheapBun = new Bun("cheap bun", 100f);
        Bun expensiveBun = new Bun("expensive bun", 200f);

        Ingredient salad = new Ingredient(IngredientType.FILLING, "salad", 30f);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        Ingredient cheese = new Ingredient(IngredientType.FILLING, "cheese", 80f);
        Ingredient cutlet = new Ingredient(IngredientType.FILLING, "cutlet", 100f);


        return Arrays.asList(new Object[][]{{cheapBun, new Ingredient[]{}, 200f}, {cheapBun, new Ingredient[]{sauce}, 250f}, {cheapBun, new Ingredient[]{cutlet, cheese}, 380f}, {expensiveBun, new Ingredient[]{}, 400f}, {cheapBun, new Ingredient[]{sauce, cutlet, cheese, salad}, 460f}, {expensiveBun, new Ingredient[]{cutlet, cheese}, 580f}});
    }

    @Test
    public void testGetPriceWithRealObjects() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ing : ingredients) {
            burger.addIngredient(ing);
        }
        assertEquals("Цена бургера должна соответствовать ожидаемой", expectedPrice, burger.getPrice(), DELTA);
    }
}