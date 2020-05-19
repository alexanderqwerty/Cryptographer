package sample.Crypter;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EncryptForm {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField Text;

    @FXML
    private TextField Key;

    @FXML
    private TextArea EnText;

    @FXML
    private AnchorPane anchorPane2;

    @FXML
    private TextField Text2;

    @FXML
    private TextField Key1;

    @FXML
    private TextField Text1;

    @FXML
    private Button ChangeBt;

    @FXML
    private TextField NameFile;


    //Проверяет парвильно ли заполнены строки и производит зашифровывает
    @FXML
    void DoEncript(MouseEvent event) {
        //Проверки
        boolean flag = true;
        if (Text != null && Text.getText().equals("")) {
            flag = false;
            Text.setStyle("-fx-border-color:red");
        } else
            Text.setStyle("-fx-border-color:green");

        if (Key != null && Key.getText().length() != 16) {
            flag = false;
            Key.setStyle("-fx-border-color:red");
        } else
            Key.setStyle("-fx-border-color:green");

        //Зашифровывание и вывод
        if (flag) {
            try {
                EnText.setText(Base64.getEncoder().encodeToString(AES.encrypt(Key.getText(), (Text.getText().getBytes(StandardCharsets.UTF_8)))));
            } catch (GeneralSecurityException e) {
            }
        }
    }

    @FXML
    private Circle BackCirBt;

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
            e.printStackTrace();
        }

    }

    //Замена Pane
    @FXML
    void Change(MouseEvent event) {
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
    void initialize() {
    }

    //Зашифровывание файла
    @FXML
    void DoEncriptFile(MouseEvent event) {
        boolean flag = true;
        if (Text1 != null && Text1.getText().equals("")) {
            flag = false;
            Text1.setStyle("-fx-border-color:red");
        } else {
            Text1.setStyle("-fx-border-color:green");
        }
        if (Text2 != null && Text2.getText().equals("")) {
            flag = false;
            Text2.setStyle("-fx-border-color:red");
        } else {
            Text2.setStyle("-fx-border-color:green");
        }
        if (Key != null && Key.getText().equals("") && Key != null && Key.getText().length() != 16) {
            flag = false;
            Key.setStyle("-fx-border-color:red");
        } else {
            Key.setStyle("-fx-border-color:green");
        }

        if (flag) {
            try {
                byte[] fileBytes = Files.readAllBytes((Path) Text1);
                FileOutputStream f = new FileOutputStream(Text2.getText() + "\\" + NameFile.getText());
                f.write(AES.encrypt(Key1.getText(), fileBytes));
            } catch (IOException | GeneralSecurityException e) {
                e.printStackTrace();
            }
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



