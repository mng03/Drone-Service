package src;

/**
 * Class that defines the specific packaged instances of general ingredients to
 * satisfy the requirement of variable prices for the same ingredient.
 *
 * @author Group 9
 * @version 1.0
 */
public class Package {
    private IngredientInfo ingredient;
    private int unit_price;
    private int quantity;

    public Package(IngredientInfo ingredient, int price, int quantity) {
        this.ingredient = ingredient;
        this.unit_price = price;
        this.quantity = quantity;
    }

    public Package(String barcode, String name, int unitWeight, int price, int quantity) {
        this(new IngredientInfo(barcode, name, unitWeight), price, quantity);
    }

    public IngredientInfo getIngredient() {
        return ingredient;
    }

    public double getUnitPrice() {
        return unit_price;
    }

    public void setIngredient(IngredientInfo i) {
        ingredient = i;
    }

    public void setUnitPrice(int p) {
        unit_price = p;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void addToPackage(int addedQuantity) {
        setQuantity(addedQuantity + quantity);
    }

    public void unloadPackage(int subtractedQuantity) {
        setQuantity(subtractedQuantity + quantity);
    }

    public String toString() {
        return "&> barcode: " + ingredient.getBarcode() + ", item_name: " + ingredient.getName() + ", total_quantity: " +
            quantity + ", unit_cost: " + unit_price + ", total_weight: " + (quantity * ingredient.getUnitWeight());
    }
}
