package Example;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class ItemOnMenu implements Serializable {

    private String mealName;
    private double mealCost;
    private double calorificValue;
    private String description;
    private String kitchenNote;

    //makes prices be displayed in £
    Locale locale = new Locale("en", "GB");
    NumberFormat cf = NumberFormat.getCurrencyInstance(locale);

    public ItemOnMenu(String mName, double mCost, double cValue, String desc, String kNote) {
        mealName = mName;
        mealCost = mCost;
        calorificValue = cValue;
        description = desc;
        kitchenNote = kNote;
    }

    public String getMealName() {
        return mealName;
    }

    public double getMealCost() {
        return mealCost;
    }

    public double getCalorificValue() {
        return calorificValue;
    }

    public String getDescription() {
        return description;
    }

    public String getKitchenNote() {
        return kitchenNote;
    }

    public void setKitchenNote(String kitchenNote) {
        this.kitchenNote = kitchenNote;
    }

    @Override
    public String toString() {
        return mealName + " - " + cf.format(mealCost) +
                "\ncalories: " + calorificValue +
                "\n - "  + kitchenNote;
    }
}
