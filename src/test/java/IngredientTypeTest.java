import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void valuesShouldReturnAllEnumConstants() {
        IngredientType[] expected = { IngredientType.SAUCE, IngredientType.FILLING };
        assertArrayEquals("values() should return all IngredientType constants",
                expected, IngredientType.values());
    }

    @Test
    public void valueOfWithValidNames() {
        assertEquals("valueOf('SAUCE') should return IngredientType.SAUCE",
                IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals("valueOf('FILLING') should return IngredientType.FILLING",
                IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueOfWithInvalidNameThrowsException() {
        IngredientType.valueOf("INVALID");
    }
}