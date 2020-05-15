package sample.Crypter;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class EncryptForm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Circle BackCirBt;

    @FXML
    private TextField Key;

    @FXML
    private TextField Text;

    @FXML
    private TextArea EnText;



    //Проверяет парвильно ли заполнены строки и производит зашифровывает
    @FXML
    void DoEncript(MouseEvent event) {
        //Проверки
        boolean flag = true;
        if (Text != null && Text.getText().equals("")) {
            flag = false;
            Text.setStyle("-fx-border-color:red");
        }
        else
            Text.setStyle("-fx-border-color:green");

        if (Key != null && Key.getText().length() != 16) {
            flag = false;
            Key.setStyle("-fx-border-color:red");
        }
        else
            Key.setStyle("-fx-border-color:green");

        //Зашифровывание и вывод
        if (flag){
            try {
               EnText.setText(Base64.getEncoder().encodeToString(AES.encrypt(Key.getText(), (Text.getText().getBytes(StandardCharsets.UTF_8)))));
            } catch (GeneralSecurityException e) {
            }
        }
    }


    //Возврант на главную
    @FXML
    void goToMainForm(MouseEvent event) {
        BackCirBt.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
        }
    }
}
