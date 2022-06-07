package src;

/**
 * Class that defines the general functionality of ingredients.
 *
 * @author Group 9
 * @version 1.0
 */
public class Ingredient {
    private int barcode;
    private String name;
    private int unitWeight;

    public Ingredient(int barcode, String name, int unitWeight) {
        this.barcode = barcode;
        this.name = name;
        this.unitWeight = unitWeight;
    }

    public int getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getUnitWeight() {
        return unitWeight;
    }

    public void setBarcode(int bc) {
        barcode = bc;
    }

    public void setName(String n) {
        name = n;
    }

    public void setUnitWeight(int uw) {
        unitWeight = uw;
    }

    public String toString() {
        return "Barcode: " + barcode + " Ingredient name: " + name + " Unit Weight: " + unitWeight;
    }
}
