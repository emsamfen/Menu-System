package Example;

import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditRestaurantController {
    public Button editDinersButton;
    public Button editTablesButton;
    public Button submitButton;
    public Button doneButton;
    public Label currentValueLabel;
    public TextField newValueTF;

    public static int maxDinersPerTable;
    public static int maxTables;
    public int option = 0;

    public void setParent(OwnerOptionsController p) {
        OwnerOptionsController parent = p;
    }

    public void editDiners() {
        option = 1;
        goToInput();
        currentValueLabel.setText("Current value: " + maxDinersPerTable);
    }

    public void editTables() {
        option = 2;
        goToInput();
        currentValueLabel.setText("Current value: " + maxTables);
    }

    //submit either max number of diners of tables to the rest of the system
    public void submitChanges() {
        if (newValueTF.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter something",
                    ButtonType.OK);
            alert.showAndWait();
        }
        else {
            try {
                String tempString = newValueTF.getText();
                int newValue = Integer.parseInt(tempString);
                if (option == 1) {
                    maxDinersPerTable = newValue;
                    EntryScreenController.setCurrentMaxDinersPerTable(newValue);
                } else if (option == 2) {
                    maxTables = newValue;
                    EntryScreenController.setMaxTables(newValue);
                }
                newValueTF.setText("");
                goToOptions();
            }
            catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter actual numbers",
                        ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    //return to entry screen
    public void done() {
        ((Stage)doneButton.getScene().getWindow()).close();
    }

    //display necessary items for inputting max diners/tables and submitting them
    public void goToInput(){
        currentValueLabel.setDisable(false);
        currentValueLabel.setOpacity(1);
        editDinersButton.setDisable(true);
        editDinersButton.setOpacity(0);
        editTablesButton.setDisable(true);
        editTablesButton.setOpacity(0);
        doneButton.setDisable(true);
        doneButton.setOpacity(0);
        newValueTF.setDisable(false);
        newValueTF.setOpacity(1);
        newValueTF.setDisable(false);
        newValueTF.setOpacity(1);
        submitButton.setDisable(false);
        submitButton.setOpacity(1);
    }

    //return to buttons for choosing to edit either max table umber or number of diners
    public void goToOptions(){
        currentValueLabel.setDisable(true);
        currentValueLabel.setOpacity(0);
        editDinersButton.setDisable(false);
        editDinersButton.setOpacity(1);
        editTablesButton.setDisable(false);
        editTablesButton.setOpacity(1);
        doneButton.setDisable(false);
        doneButton.setOpacity(1);
        newValueTF.setDisable(true);
        newValueTF.setOpacity(0);
        newValueTF.setDisable(true);
        newValueTF.setOpacity(0);
        submitButton.setDisable(true);
        submitButton.setOpacity(0);
    }

    //called from EntryScreenController
    public static void setMaxTables(int maxTableValue) {
        maxTables = maxTableValue;
    }

    //called from EntryScreenController
    public static void setCurrentMaxDinersPerTable(int maxDinersValue) {
        maxDinersPerTable = maxDinersValue;
    }
}
