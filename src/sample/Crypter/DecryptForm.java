package sample.Crypter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
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
    private javafx.scene.text.Text decription;

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
    void DoDecrypt() {

        //Проверки
        boolean flag = true;
        if (Text != null && Text.getText().equals("")) {
            flag = false;
            Text.setStyle("-fx-border-color:red");
        } else {
            try {
                assert Text != null;
                AES.decrypt("1234567812345678", Base64.getDecoder().decode(Text.getText()));
                Text.setStyle("-fx-border-color:green");
            } catch (GeneralSecurityException | IllegalArgumentException e) {
                flag = false;
                Text.setStyle("-fx-border-color:red");
            }
        }
        if (Key != null && Key.getText().length() != 16) {
            flag = false;
            Key.setStyle("-fx-border-color:red");
        } else {
            assert Key != null;
            Key.setStyle("-fx-border-color:green");
        }

        //Расшифровывание
        if (flag) {
            try {
                DeText.setText(new String(AES.decrypt(Key.getText(), Base64.getDecoder().decode(Text.getText()))));
            } catch (GeneralSecurityException ignored) {
            }
        }
    }

    //Расшифровывание файла
    @FXML
    void DoEncriptFile() {

        //Проверки
        boolean flag = true;
        if (Text1 != null && Text1.getText().equals("") || !(new File(Objects.requireNonNull(Text1).getText()).isFile())) {
            decription.setOpacity(0);
            flag = false;
            Text1.setStyle("-fx-border-color:red");
        } else {
            Text1.setStyle("-fx-border-color:green");
        }
        if (Key1 != null && Key1.getText().length() != 16) {
            decription.setOpacity(0);
            flag = false;
            Key1.setStyle("-fx-border-color:red");
        } else {
            assert Key1 != null;
            Key1.setStyle("-fx-border-color:green");
        }
        if (NameFile != null && NameFile.getText().equals("")) {
            decription.setOpacity(0);
            flag = false;
            NameFile.setStyle("-fx-border-color:red");
        } else {
            assert NameFile != null;
            NameFile.setStyle("-fx-border-color:green");
        }
        if (Text2 != null && Text2.getText().equals("") || !(Files.isDirectory(Paths.get(Objects.requireNonNull(Text2).getText())))) {
            decription.setOpacity(0);
            flag = false;
            Text2.setStyle("-fx-border-color:red");
        } else {
            assert Text2 != null;
            Text2.setStyle("-fx-border-color:green");
        }

        //Расшифровывание
        if (flag) {
            try {
                byte[] fileBytes = Files.readAllBytes(Paths.get(Text1.getText()));
                FileOutputStream f = new FileOutputStream(Text2.getText() + "\\" + NameFile.getText());
                f.write(AES.decrypt(Key1.getText(), fileBytes));
                decription.setOpacity(1);
            } catch (IOException | GeneralSecurityException ignored) {
            }
        }
    }

    //Смена Pane
    @FXML
    void Chage() {
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
    void goToMainForm() {
        BackCirBt.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ignored) {
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
            final Stage stage = new Stage();

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
            final Stage stage = new Stage();

            @Override
            public void handle(ActionEvent event) {
                Text2.clear();
                File files = directoryChooser.showDialog(stage);
                Text2.setText(files.getAbsolutePath());
            }
        });
    }
}

