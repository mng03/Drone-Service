package src;

/**
 * Class that defines the specific packaged instances of general ingredients to
 * satisfy the requirement of variable prices for the same ingredient.
 *
 * @author Group 9
 * @version 1.0
 */
public class Package {
    private Ingredient ingredient;
    private double price;

    public Package(Ingredient ingredient, double price) {
        this.ingredient = ingredient;
        this.price = price;
    }

    public Package(int barcode, String name, int unitWeight, int price) {
        this(new Ingredient(barcode, name, unitWeight), price);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getPrice() {
        return price;
    }

    public void setIngredient(Ingredient i) {
        ingredient = i;
    }

    public void setPrice(double p) {
        price = p;
    }

    public String toString() {
        return ingredient + " Price: $" + price;
    }
}
