package Example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class OwnerOptionsController {

    public Button setNoOfTablesButton;
    public Button editMenuButton;
    public Button doneButton;
    public Button enterButton;
    public Label passwordLabel;
    public Label attemptsLeftTF;
    public TextField passwordTF;

    final static String password = "password";
    public int attempts = 3;

    public void setParent(EntryScreenController p) {
        EntryScreenController parent = p;
    }

    //go to EditRestaurant screen
    public void setNoOfTables() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editRestaurant.fxml"));
        Scene EditRestaurantScene = new Scene(loader.load());
        EditRestaurantController controller = loader.getController();
        controller.setParent(OwnerOptionsController.this);
        Stage inputStage = new Stage();
        inputStage.setScene(EditRestaurantScene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.show();
    }

    //go to EditMenu screen
    public void editMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editMenu.fxml"));
        Scene EditMenuScene = new Scene(loader.load());
        EditMenuController controller = loader.getController();
        controller.setParent(OwnerOptionsController.this);
        Stage inputStage = new Stage();
        inputStage.setScene(EditMenuScene);
        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.show();
    }

    //return to entry screen
    public void done() {
        ((Stage)doneButton.getScene().getWindow()).close();
    }

    //checks if password matches preset password and blocks after 3 failed attempts - change tot ext file later?
    // what about resetting password??
    public void enterPassword() {
        String entered = passwordTF.getText();
        if (entered.equals(password)){
            passwordTF.setDisable(true);
            passwordTF.setOpacity(0);
            enterButton.setDisable(true);
            enterButton.setOpacity(0);
            editMenuButton.setDisable(false);
            editMenuButton.setOpacity(1);
            setNoOfTablesButton.setDisable(false);
            setNoOfTablesButton.setOpacity(1);
        }
        else if (attempts >= 2){
            attemptsLeftTF.setOpacity(1);
            attempts -= 1;
            attemptsLeftTF.setText("Incorrect - Attempts left: " + attempts);
        }

        else{
            attemptsLeftTF.setText("Too many incorrect guesses");
            passwordTF.setDisable(true);
            passwordTF.setOpacity(0);
            enterButton.setDisable(true);
            enterButton.setOpacity(0);
        }
    }
}
