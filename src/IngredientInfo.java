package src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.TreeMap;

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

    public static TreeMap<String, IngredientInfo> ingredientInfos = new TreeMap<String, IngredientInfo>();
    public static ObservableList<String> ingredientInfosGUI = FXCollections.observableArrayList();

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
    public static void makeInfo(String init_barcode, String init_name, Integer init_weight) throws Exception {
        if (init_barcode.equals("")) {
            throw new Exception("ERROR:ingredient_barcode_cannot_be_empty");
        } else if (ingredientInfos.containsKey(init_barcode)) {
            throw new Exception("ERROR:ingredient_barcode_already_exists");
        } else if (init_name.equals("")) {
            throw new Exception("ERROR:ingredient_name_cannot_be_empty");
        } else if (init_weight < 0) {
            throw new Exception("ERROR:ingredient_cannot_have_negative_weight");
        }
        ingredientInfos.put(init_barcode, new IngredientInfo(init_barcode, init_name, init_weight));
        ingredientInfosGUI.add(init_barcode);
        FXCollections.sort(ingredientInfosGUI);
    }
    public static void infoExists(String barcode) throws Exception {
        if (!ingredientInfos.containsKey(barcode)) {
            throw new Exception("ERROR:ingredient_does_not_exist");
        } 
    }
}
