package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    private Button ChoiceEnForm;

    @FXML
    private Button ChoiceDeForm;

    @FXML
    private Text InfoForm;

    //Переход в расшифровывание
    @FXML
    void goToDecrypt() {
        ChoiceDeForm.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Crypter/DecryptForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Переход в зашифровывание
    @FXML
    void goToEncrypt() {
        ChoiceEnForm.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Crypter/EncryptForm.fxml"));
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Переход в описание
    @FXML
    void goToInfo() {
        InfoForm.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Crypter/InfoForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
