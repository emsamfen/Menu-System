package Example;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

public class MyMenu extends ObservableListWrapper<ItemOnMenu> {
    public MyMenu() {
        super(FXCollections.observableArrayList());
    }

    public void addFoodItem(String mealName, double mealCost, double calorificValue, String description, String kitchenNote) {
        super.add(new ItemOnMenu(mealName, mealCost, calorificValue, description, kitchenNote) {
        });
    }

    //get total price of an array for checks later
    public double getTotalPrice(){
        double totalPrice = 0;
        for (ItemOnMenu i: MyMenu.this) {
            totalPrice += i.getMealCost();
        }
        return totalPrice;
    }

    //need to find items to add/remove in owner options
    public ItemOnMenu findItemByName(String name){
        ItemOnMenu temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++){
            temp = super.get(i);
            if(temp.getMealName().equals(name)){
                indexLocation = i;
                break;
            }
        }
        if (indexLocation == -1){
            return null;
        }
        return super.get(indexLocation);
    }

    public void removeItem(String name){
        ItemOnMenu itemToGo = this.findItemByName(name);
        super.remove(itemToGo);
    }
}