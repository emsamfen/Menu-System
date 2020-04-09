package Example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryScreenController extends Application {
    public TextField tableNoTextField;
    public TextField noDiningTextField;
    public Button enterButton;
    public Button ownerButton;
    public Label maxDinersNumberLabel;
    public Label maxTableNumberLabel;
    public Label enterNoDiningLabel;
    public Label enterTableNoLabel;
    public Button startButton;
    public Button backButton;

    public int tableNumber;
    public int numberDining;
    public static int maxTableNumber = 10;
    public static int maxDinersPerTable = 5;


    public void startMenu() throws IOException {
        if (tableNoTextField.getText().equals("") || noDiningTextField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a table number and number of customers dining",
                    ButtonType.OK);
            alert.showAndWait();
        }
        else {
            //check that inputs are numbers and are in the max table number and max diner ranges
            try {
                String tempTableNumber = tableNoTextField.getText();
                tableNumber = Integer.parseInt(tempTableNumber);
                String tempNumberDining = noDiningTextField.getText();
                numberDining = Integer.parseInt(tempNumberDining);
                if (tableNumber <= maxTableNumber && tableNumber >= 1 && numberDining <= maxDinersPerTable && numberDining >= 1) {
                    MenuController.updateNumberDining(numberDining);
                    MenuController.updateTableNumber(tableNumber);
                    tableNoTextField.clear();
                    noDiningTextField.clear();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                    Scene MenuScene = new Scene(loader.load());
                    MenuController controller = loader.getController();
                    controller.setParent(EntryScreenController.this);
                    Stage inputStage = new Stage();
                    inputStage.setScene(MenuScene);
                    inputStage.initModality(Modality.APPLICATION_MODAL);
                    inputStage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    Label description = new Label("Please enter table number and customers dining within the range");
                    alert.getDialogPane().setContent(description);
                    alert.showAndWait();
                }
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter actual numbers",
                        ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    public void ownerOptions() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ownerOptions.fxml"));
        Scene OwnerOptionsScene = new Scene(loader.load());
        OwnerOptionsController controller = loader.getController();
        controller.setParent(EntryScreenController.this);
        Stage inputStage = new Stage();
        inputStage.setScene(OwnerOptionsScene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.show();
        EditRestaurantController.setMaxTables(maxTableNumber);
        EditRestaurantController.setCurrentMaxDinersPerTable(maxDinersPerTable);
    }

    //Show Table number and diners entry boxes - could these be refactored??
    public void start() {
        tableNoTextField.setDisable(false);
        tableNoTextField.setOpacity(1);
        maxTableNumberLabel.setDisable(false);
        maxTableNumberLabel.setOpacity(1);
        noDiningTextField.setDisable(false);
        noDiningTextField.setOpacity(1);
        maxDinersNumberLabel.setDisable(false);
        maxDinersNumberLabel.setOpacity(1);
        maxTableNumberLabel.setText("1 - " + maxTableNumber);
        maxDinersNumberLabel.setText("1 - " + maxDinersPerTable);
        enterButton.setDisable(false);
        enterButton.setOpacity(1);
        ownerButton.setDisable(false);
        ownerButton.setOpacity(1);
        backButton.setDisable(false);
        backButton.setOpacity(1);
        startButton.setDisable(true);
        startButton.setOpacity(0);
        ownerButton.setDisable(true);
        ownerButton.setOpacity(0);
        enterNoDiningLabel.setDisable(false);
        enterNoDiningLabel.setOpacity(1);
        enterTableNoLabel.setDisable(false);
        enterTableNoLabel.setOpacity(1);
    }

    //Return to initial state - could these be refactored??
    public void back() {
        tableNoTextField.setDisable(true);
        tableNoTextField.setOpacity(0);
        maxTableNumberLabel.setDisable(true);
        maxTableNumberLabel.setOpacity(0);
        noDiningTextField.setDisable(true);
        noDiningTextField.setOpacity(0);
        maxDinersNumberLabel.setDisable(true);
        maxDinersNumberLabel.setOpacity(0);
        enterButton.setDisable(true);
        enterButton.setOpacity(0);
        ownerButton.setDisable(true);
        ownerButton.setOpacity(0);
        backButton.setDisable(true);
        backButton.setOpacity(0);
        startButton.setDisable(false);
        startButton.setOpacity(1);
        ownerButton.setDisable(false);
        ownerButton.setOpacity(1);
        enterNoDiningLabel.setDisable(true);
        enterNoDiningLabel.setOpacity(0);
        enterTableNoLabel.setDisable(true);
        enterTableNoLabel.setOpacity(0);

    }

    //called from EditRestaurantController
    public static void setMaxTables(int maxTableValue) {
        maxTableNumber = maxTableValue;
    }

    //called from EditRestaurantController
    public static void setCurrentMaxDinersPerTable(int maxDinersValue) {
        maxDinersPerTable = maxDinersValue;
    }

    @Override
    public void start(Stage menuStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("entryScreen.fxml"));
        menuStage.setTitle("Example Menu");
        menuStage.setScene(new Scene(root, 400, 200));
        menuStage.show();
    }

    public static void main(String[] args) {
     //initializing starter menus - update to text file if you get time before deadline
        MyMenu starters = new MyMenu();
        starters.addFoodItem("Smoked Salmon", 1.6, 180, "Wild alaskan smoked " +
                "salmon served on a bed of iceberg lettuce with thousand island dressing", "");
        starters.addFoodItem("Pate", 2.5, 195, "aaa", "");
        starters.addFoodItem("Melon", 1., 98, "aaa", "");
        starters.addFoodItem("Ciabatta", 1.75, 102, "aaaa", "");

        MyMenu mains = new MyMenu();
        mains.addFoodItem("Steak and Ale Pie", 7.5, 870, "aaa", "");
        mains.addFoodItem("Chicken Pie", 7, 780, "aaa", "");
        mains.addFoodItem("Fish and Chips", 6.5, 880, "aaa", "");
        mains.addFoodItem("Bangers and Mash", 6.5, 690, "aaa", "");
        mains.addFoodItem("Steak", 9.5, 680, "aaa", "");
        mains.addFoodItem("Fajitas", 7.5, 640, "aaa", "");

        MyMenu desserts = new MyMenu();
        desserts.addFoodItem("Chocolate Fudge Cake", 1.5, 480, "aaa", "");
        desserts.addFoodItem("Sticky toffee pudding", 1.5, 560, "aaa", "");
        desserts.addFoodItem("Mousse", 1.5, 250, "aaa", "");

        MyMenu drinks = new MyMenu();
        drinks.addFoodItem("Coke", 1, 100, "aaa", "");
        drinks.addFoodItem("San Miguel", 2, 260, "aaa", "");

        MyMenu extras = new MyMenu();
        extras.addFoodItem("Chips", 1.5, 200, "aaa", "");
        extras.addFoodItem("Wedges", 1.75, 230, "aaa", "");

        //send initial menus to the owner options editor and main menu selector
        MealSelectionController.setMenus(starters, mains, desserts, drinks, extras);
        EditMenuController.setEditingMenus(starters, mains, desserts, drinks, extras);

        launch(args);
    }
}