package sample.Crypter;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EncryptForm {

    @FXML
    public ImageView BackBt1;

    @FXML
    private javafx.scene.text.Text Encription;

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
    void DoEncript() {

        //Проверки
        boolean flag = true;
        if (Text != null && Text.getText().equals("")) {
            flag = false;
            Text.setStyle("-fx-border-color:red");
        } else {
            assert Text != null;
            Text.setStyle("-fx-border-color:green");
        }
        if (Key != null && Key.getText().length() != 16) {
            flag = false;
            Key.setStyle("-fx-border-color:red");
        } else {
            assert Key != null;
            Key.setStyle("-fx-border-color:green");
        }

        //Зашифровывание и вывод
        if (flag) {
            try {
                EnText.setText(Base64.getEncoder().encodeToString(AES.encrypt(Key.getText(), (Text.getText().getBytes(StandardCharsets.UTF_8)))));
            } catch (GeneralSecurityException ignored) {
            }
        }
    }

    @FXML
    private Circle BackCirBt;

    //Возврант на главную
    @FXML
    void goToMainForm() {
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
    void Change() {
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

    //Зашифровывание файла
    @FXML
    void DoEncriptFile() {

        //Проверки
        boolean flag = true;
        if (Text1 != null && Text1.getText().equals("")) {
            Encription.setOpacity(0);
            flag = false;
            Text1.setStyle("-fx-border-color:red");
        } else {
            assert Text1 != null;
            Text1.setStyle("-fx-border-color:green");
        }
        if (Text2 != null && Text2.getText().equals("")) {
            Encription.setOpacity(0);
            flag = false;
            Text2.setStyle("-fx-border-color:red");
        } else {
            assert Text2 != null;
            Text2.setStyle("-fx-border-color:green");
        }
        if (Key1.getText().length() != 16) {
            Encription.setOpacity(0);
            flag = false;
            Key1.setStyle("-fx-border-color:red");
        } else {
            Key1.setStyle("-fx-border-color:green");
        }
        if (NameFile.getText() != null && Text2.getText().equals("")) {
            Encription.setOpacity(0);
            flag = false;
            NameFile.setStyle("-fx-border-color:red");
        } else {
            NameFile.setStyle("-fx-border-color:green");
        }

        //Зашифровывание
        if (flag) {
            try {
                byte[] fileBytes = Files.readAllBytes(Paths.get(Text1.getText()));
                FileOutputStream f = new FileOutputStream(Text2.getText() + "\\" + NameFile.getText());
                f.write(AES.encrypt(Key1.getText(), fileBytes));
                Encription.setOpacity(1);
            } catch (IOException | GeneralSecurityException ignored) {
            }
        }
    }

    //FileChooser
    @FXML
    private Button FileChooserBt1;

    @FXML
    private Button FileChooserBt2;

    final FileChooser fileChooser = new FileChooser();

    final DirectoryChooser directoryChooser = new DirectoryChooser();


    private void printLog(TextField textField, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textField.appendText(file.getAbsolutePath());
        }
    }

    @FXML
    void FileChooser1() {
        FileChooserBt1.setOnAction(new EventHandler<>() {
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
    void FileChooser2() {
        FileChooserBt2.setOnAction(new EventHandler<>() {
            Stage stage = new Stage();
            @Override
            public void handle(ActionEvent event) {
                Text2.clear();
                File files = directoryChooser.showDialog(stage);
                Text2.setText(files.getAbsolutePath());
            }
        });
    }
}



