package sample.Crypter;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DecryptForm {

    @FXML
    private TextArea DeText;

    @FXML
    private TextField Key;

    @FXML
    private TextField Text;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //Проверяет парвильно ли заполнены строки и производит расшифровываине
    @FXML
    void DoDecrypt(MouseEvent event) {
        //Проверки
        if (Text != null && Text.getText().equals("")) {
            Text.setStyle("-fx-border-color:red");
        }else {
            try {
                AES.decrypt("1234567812345678", Base64.getDecoder().decode(Text.getText()));
                Text.setStyle("-fx-border-color:green");
            } catch (GeneralSecurityException | IllegalArgumentException e) {
                Text.setStyle("-fx-border-color:red");
            }
        }

        if (Key != null && Key.getText().length() != 16)
            Key.setStyle("-fx-border-color:red");
        else
            Key.setStyle("-fx-border-color:green");
        //Расшифровывание
        try {
            DeText.setText(new String(AES.decrypt(Key.getText(), Base64.getDecoder().decode(Text.getText()))));
        } catch (GeneralSecurityException e) {

        }
    }
    @FXML
    private Circle BackCirBt;

    //Возврат на главную
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
