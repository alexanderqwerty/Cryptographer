package sample.Crypter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DecryptForm {

    @FXML
    private Button ChangeBt;

    @FXML
    private AnchorPane anchorPane2;

    @FXML
    private AnchorPane anchorPane;

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

    @FXML
    private TextField Key1;

    @FXML
    private TextField Text1;

    @FXML
    private TextField NameFile;

    @FXML
    private TextField Text2;

    //Проверяет парвильно ли заполнены строки и производит расшифровываине
    @FXML
    void DoDecrypt(MouseEvent event) {
        //Проверки
        if (Text != null && Text.getText().equals("")) {
            Text.setStyle("-fx-border-color:red");
        } else {
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
    void DoEncriptFile(MouseEvent event) {

    }

    @FXML
    void Chage(MouseEvent event) {
        if (anchorPane.isVisible()) {
            anchorPane.setVisible(false);
            anchorPane2.setVisible(true);
            ChangeBt.setText("Текст");
        } else if (anchorPane2.isVisible()) {
            anchorPane2.setVisible(false);
            anchorPane.setVisible(true);
            ChangeBt.setText("Файл");
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

    //FileChooser
    @FXML
    private Button FileChooserBt1;

    @FXML
    private Button FileChooserBt2;

    final FileChooser fileChooser = new FileChooser();

    private void printLog(TextField textField, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textField.appendText(file.getAbsolutePath());
        }
    }

    @FXML
    void FileChooser1(MouseEvent event) {
        FileChooserBt1.setOnAction(new EventHandler<ActionEvent>() {
            Stage stage = new Stage();

            @Override
            public void handle(ActionEvent event) {
                Text1.clear();
                List<File> files = fileChooser.showOpenMultipleDialog(stage);
                printLog(Text1, files);
            }
        });
    }

    @FXML
    void FileChooser2(MouseEvent event) {
        FileChooserBt2.setOnAction(new EventHandler<ActionEvent>() {
            Stage stage = new Stage();

            @Override
            public void handle(ActionEvent event) {
                Text2.clear();
                List<File> files = fileChooser.showOpenMultipleDialog(stage);
                printLog(Text2, files);
            }
        });
    }
}

