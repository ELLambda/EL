package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameWin2 extends Stage {

    Parent root;


    public GameWin2() {
        Music.playBgMusic(9);//bgm
        try {
            this.initStyle(StageStyle.TRANSPARENT);

            root = FXMLLoader.load(getClass().getResource("GameWin2.fxml"));

            Scene scene = new Scene(root, 1200, 800);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add(getClass().getResource("gamewin.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());

            //��lookup����λ�ؼ�
            Button exitBtn = (Button) root.lookup("#exitBtn");
            exitBtn.setOnAction(e -> {

//				Calculator.scores += GameWinControllor.score.intValue();
                Music.stopBgMusic();
                Platform.runLater(() -> {
                    new LevelWin();
                    this.close();
                });
            });

            this.setScene(scene);
            this.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

