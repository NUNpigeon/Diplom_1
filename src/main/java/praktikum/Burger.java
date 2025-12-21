package praktikum;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Burger {


    public Bun bun;
    public List<Ingredient> ingredients = new ArrayList<>();


    public void setBuns(Bun bun) {
        this.bun = bun;
    }


    public Bun getBuns() {
        return bun;
    }


    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }


    public void removeIngredient(int index) {
        ingredients.remove(index);
    }


    public void moveIngredient(int index, int newIndex) {
        ingredients.add(newIndex, ingredients.remove(index));
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }


    public float getPrice() {
        float price = 0;
        if (bun != null) {
            price = bun.getPrice() * 2;
        }


        for (Ingredient ingredient : ingredients) {
            price += ingredient.getPrice();
        }


        return price;
    }


    public String getReceipt() {
        StringBuilder receipt = new StringBuilder();
        if (bun != null) {
            receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        }


        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format(
                    "= %s %s =%n",
                    ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()
            ));
        }


        if (bun != null) {
            receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        }


        receipt.append(String.format(Locale.US, "Price: %.6f%n", getPrice()));


        return receipt.toString();
    }
}
