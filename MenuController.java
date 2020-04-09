package Example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {
    public Button doneButton;
    public Button mealButton;
    public Button extrasButton;
    public Button drinksButton;
    public Button starterButton;
    public Button mainButton;
    public Button dessertButton;
    public Button removeButton;
    public Button addNoteButton;
    public Button cancelOrderButton;
    public Button addKitchenNoteButton;
    public Label infoLabel;
    public TextField kitchenNoteTF;
    public ListView<ItemOnMenu> chosenList;

    private static MyMenu order;
    private static int tableNumber;
    private static int noOfDiners;

    public void setParent(EntryScreenController p) {
        EntryScreenController parent = p;
    }

    public void initialize() {
        order = new MyMenu();
        chosenList.setItems(order);
        infoLabel.setText("Table Number: " + tableNumber + "   Number of diners: " + noOfDiners);
    }
    //add functions take to MealSelection screen and initialize the ListView to a different array
    public void addStarter() throws IOException {
        MealSelectionController.updateOption(1);
        launchMealSelection();
    }

    public void addMain() throws IOException {
        MealSelectionController.updateOption(2);
        launchMealSelection();
    }

    public void addDessert() throws IOException {
        MealSelectionController.updateOption(3);
        launchMealSelection();
    }

    public void addDrink() throws IOException {
        MealSelectionController.updateOption(4);
        launchMealSelection();
    }

    public void addExtras() throws IOException {
        MealSelectionController.updateOption(5);
        launchMealSelection();
    }

    public void add3Courses() throws IOException {
        MealSelectionController.updateOption(6);
        launchMealSelection();
    }

    public void launchMealSelection() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mealSelection.fxml"));
        Scene mealSelectionScene = new Scene(loader.load());
        MealSelectionController controller = loader.getController();
        controller.setParent(MenuController.this);

        Stage inputStage = new Stage();
        inputStage.setScene(mealSelectionScene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.show();
    }

    //takes user to receipt screen, checks if they have selected food and throws error if not
    public void done() throws IOException {
        if (order.getTotalPrice() != 0) {
            ReceiptController.updateReceipt(order);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));
            Scene receiptScene = new Scene(loader.load());
            ReceiptController controller = loader.getController();
            controller.setParent(MenuController.this);

            Stage inputStage = new Stage();
            inputStage.setScene(receiptScene);
            inputStage.initModality(Modality.APPLICATION_MODAL);
            inputStage.show();
            ((Stage) extrasButton.getScene().getWindow()).close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please order some food before checking out", ButtonType.OK);
            alert.showAndWait();
        }
    }

    //make remove and add kitchen note buttons visible
    public void showOptions() {
        if (order.getTotalPrice() != 0) {
            removeButton.setDisable(false);
            addNoteButton.setDisable(false);
        }
    }

    public void removeItem() {
        int selectedIndex = chosenList.getSelectionModel().getSelectedIndex();
        ItemOnMenu toGo = chosenList.getItems().get(selectedIndex);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to remove: \n" + toGo + " ?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            order.remove(selectedIndex);
            chosenList.setItems(order);
            removeButton.setDisable(true);
            addNoteButton.setDisable(true);
        }
    }

    public void addKitchenNote() {
        if (!kitchenNoteTF.getText().equals("")) {
            int selectedIndex = chosenList.getSelectionModel().getSelectedIndex();
            ItemOnMenu toAdd = chosenList.getItems().get(selectedIndex);
            order.remove(toAdd);
            toAdd.setKitchenNote(kitchenNoteTF.getText());
            kitchenNoteTF.setOpacity(0);
            kitchenNoteTF.setDisable(true);
            addKitchenNoteButton.setOpacity(0);
            addKitchenNoteButton.setDisable(true);
            order.addAll(toAdd);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter the note you would like to add",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void submitNote() {
        kitchenNoteTF.setOpacity(1);
        kitchenNoteTF.setDisable(false);
        addKitchenNoteButton.setOpacity(1);
        addKitchenNoteButton.setDisable(false);
    }

    public void cancelOrder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            ((Stage)cancelOrderButton.getScene().getWindow()).close();
        }
    }

    //called from MealSelectionController to update order Array
    public static void updateOrder(MyMenu storedValue) {
        MenuController.order.addAll(storedValue);
    }

    //called from EntryScreenController
    public static void updateTableNumber(int tableValue) {
        MenuController.tableNumber = tableValue;
    }

    //called from EntryScreenController
    public static void updateNumberDining(int NumberDiningValue) {
        MenuController.noOfDiners = NumberDiningValue;
    }
}
