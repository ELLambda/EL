package gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainWinControllor {


    public Button startBtn;

    @FXML
    public Button help;

    public static void changeScene(AnchorPane root, URL url) {
        try {
            root = FXMLLoader.load(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onStartBtnClick() {

        Platform.runLater(() -> {
            startBtn.getScene().getWindow().hide();
            new ModeWin();
        });

    }

    @FXML
    public void onHelpBtnClicked(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                new Helper();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public void onAboutBtnClick(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            startBtn.getScene().getWindow().hide();
            new AboutWin();
        });
    }


    public void onAchievementBtnClick(ActionEvent actionEvent) {
        startBtn.getScene().getWindow().hide();
        new AchievementWin();
    }


}
