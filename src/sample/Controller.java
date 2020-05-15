package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ChoiceEnForm;

    @FXML
    private Button ChoiceDeForm;

    @FXML
    private Text InfoForm;

    //Переход в расшифровывание
    @FXML
    void goToDecrypt(MouseEvent event) {
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
    void goToEncrypt(MouseEvent event) {
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
    void goToInfo(MouseEvent event) {
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
