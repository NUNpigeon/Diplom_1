import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;


import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class BunTest {


    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тест {index}: булочка ''{0}'' с ценой {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f}
        });
    }

    @Test
    public void testGetName() {
        Bun bun = new Bun(name, price);
        assertEquals("Название булочки должно совпадать", name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(name, price);
        assertEquals("Цена булочки должна совпадать", price, bun.getPrice(), 0.001f);
    }
}