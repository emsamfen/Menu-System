package Example;

import javafx.scene.control.*;
import javafx.stage.Stage;

public class MealSelectionController {
    public TextField nameTextField;
    public TextField priceTextField;
    public TextField caloriesTextField;
    public TextField descriptionTextField;
    public TextField kitchenNoteTextField;
    public Button addStarterButton;
    public Button moreInfoButton;
    public Button backButton;
    public Label choiceLabel;
    public ListView<ItemOnMenu> foodListView;

    private static MyMenu starters;
    private static MyMenu mains;
    private static MyMenu desserts;
    private static MyMenu drinks;
    private static MyMenu extras;
    private MyMenu chosen;
    private int course = 3;
    private static int option = 0;
    private boolean priceReduce = false;
    private static final double DISCOUNT = 0.1;

    public void initialize() {
        chosen = new MyMenu();

        if (option == 1){
            foodListView.setItems(starters);
            choiceLabel.setText("Please choose a starter");
        }
        else if (option == 2){
            foodListView.setItems(mains);
            choiceLabel.setText("Please choose a main");
        }
        else if (option == 3){
            foodListView.setItems(desserts);
            choiceLabel.setText("Please choose a dessert");
        }
        else if (option == 4){
            foodListView.setItems(drinks);
            choiceLabel.setText("Please choose a drink");
        }
        else if (option == 5){
            foodListView.setItems(extras);
            choiceLabel.setText("Please choose an extra");
        }
        else if (option ==6){
            foodListView.setItems(starters);
            choiceLabel.setText("Please choose a starter");
            course = 0;
        }
    }

    void setParent(MenuController p) {
        MenuController parent = p;
    }

    public void selectItem() {
        ItemOnMenu selectedItem = foodListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            setAllTextFields(selectedItem);
        }
    }

    public void addFoodItem() {
        if (option == 6){
            course++;
            add3course();
        }
        else {
            chooseFood();
        }
    }

    public void showMoreInfo() {
        int selectedIndex = foodListView.getSelectionModel().getSelectedIndex();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Label description = new Label(foodListView.getItems().get(selectedIndex).getDescription());
        alert.getDialogPane().setContent(description);
        alert.showAndWait();
    }

    //takes back to main menu screen
    public void back() {
        ((Stage) backButton.getScene().getWindow()).close();
    }

    private void setAllTextFields(ItemOnMenu i) {
        nameTextField.setText(i.getMealName());
        priceTextField.setText(String.valueOf(i.getMealCost()));
        caloriesTextField.setText(String.valueOf(i.getCalorificValue()));
        descriptionTextField.setText(i.getDescription());
        kitchenNoteTextField.setText(i.getKitchenNote());
    }

    private void add3course(){
        priceReduce = true;
        if (course == 1) {
            chooseFood();
            foodListView.getSelectionModel().clearSelection();
            choiceLabel.setText("Please choose a main course");
            foodListView.setItems(mains);
        }

        if (course == 2) {
            chooseFood();
            foodListView.getSelectionModel().clearSelection();
            choiceLabel.setText("Please choose a dessert course");
            foodListView.setItems(desserts);
        }

        if (course == 3) {
            chooseFood();
            foodListView.getSelectionModel().clearSelection();
        }
    }

    //gets selected food item and adds it to the chosen array
    private void chooseFood(){
        //checking the customer has selected an item in the ListView
        int selectedIndex = foodListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item", ButtonType.OK);
            alert.showAndWait();
        } else {
            ItemOnMenu toGo = foodListView.getItems().get(selectedIndex);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to add: \n" + toGo + " ?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                //have to get foodItem properties from text fields set earlier
                String name = nameTextField.getText();
                String tempPrice = priceTextField.getText();
                double price = Double.parseDouble(tempPrice);
                String tempCValue = caloriesTextField.getText();
                double calorificValue = Double.parseDouble(tempCValue);
                String description = descriptionTextField.getText();
                String kitchenNote = kitchenNoteTextField.getText();
                if (priceReduce){
                    double discountAmount = 1 - DISCOUNT;
                    price = price * discountAmount;
                    kitchenNote = "Discount Applied";
                }
                chosen.addFoodItem(name, price, calorificValue, description, kitchenNote);
                if (course == 3) {
                    ((Stage) backButton.getScene().getWindow()).close();
                    MenuController.updateOrder(chosen);
                }
            }
        }
    }

    //called from MenuController to set ListView to course selected
    public static void updateOption(int optionValue) {
        option = optionValue;
    }

    //called from EditMenuController when owner updating menus
    public static void updateMenus(MyMenu updatedStarters, MyMenu updatedMains, MyMenu updatedDesserts, MyMenu updatedDrinks, MyMenu updatedExtras){
        starters = updatedStarters;
        mains = updatedMains;
        desserts = updatedDesserts;
        drinks = updatedDrinks;
        extras = updatedExtras;
    }

    //called from EntryScreenController to setup initial menus
    public static void setMenus(MyMenu Starters, MyMenu Mains, MyMenu Desserts, MyMenu Drinks, MyMenu Extras){
        starters = Starters;
        mains = Mains;
        desserts = Desserts;
        drinks = Drinks;
        extras = Extras;
    }
}
