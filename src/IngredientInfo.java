package src;

/**
 * Class that defines the general functionality of ingredients.
 *
 * @author Group 9
 * @version 1.0
 */
public class IngredientInfo {
    private String barcode;
    private String name;
    private int unitWeight;

    public IngredientInfo(String barcode, String name, int unitWeight) {
        this.barcode = barcode;
        this.name = name;
        this.unitWeight = unitWeight;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getUnitWeight() {
        return unitWeight;
    }

    public void setBarcode(String bc) {
        barcode = bc;
    }

    public void setName(String n) {
        name = n;
    }

    public void setUnitWeight(int uw) {
        unitWeight = uw;
    }

    public String toString() {
        return "barcode: " + barcode + ", name: " + name + ", unit_weight: " + unitWeight;
    }
}
