import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import org.mockito.Mockito;
import org.assertj.core.api.Assertions;


import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.when;

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

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}, ожидаемая цена: {2}")
    public static Collection<Object[]> data() {
        // Моки булочек
        Bun cheapBun = Mockito.mock(Bun.class);
        when(cheapBun.getPrice()).thenReturn(100f);
        when(cheapBun.getName()).thenReturn("cheap bun");

        Bun expensiveBun = Mockito.mock(Bun.class);
        when(expensiveBun.getPrice()).thenReturn(200f);
        when(expensiveBun.getName()).thenReturn("expensive bun");

        // Моки ингредиентов
        Ingredient salad = Mockito.mock(Ingredient.class);
        when(salad.getPrice()).thenReturn(30f);

        Ingredient sauce = Mockito.mock(Ingredient.class);
        when(sauce.getPrice()).thenReturn(50f);

        Ingredient cheese = Mockito.mock(Ingredient.class);
        when(cheese.getPrice()).thenReturn(80f);

        Ingredient cutlet = Mockito.mock(Ingredient.class);
        when(cutlet.getPrice()).thenReturn(100f);


        return Arrays.asList(new Object[][]{
                {cheapBun, new Ingredient[]{}, 200f},
                {cheapBun, new Ingredient[]{sauce}, 250f},
                {cheapBun, new Ingredient[]{cutlet, cheese}, 380f},
                {expensiveBun, new Ingredient[]{}, 400f},
                {cheapBun, new Ingredient[]{sauce, cutlet, cheese, salad}, 460f},
                {expensiveBun, new Ingredient[]{cutlet, cheese}, 580f}
        });
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        float actualPrice = burger.getPrice();
        Assertions.assertThat(actualPrice).isCloseTo(expectedPrice, Assertions.offset(DELTA));
    }


}

