package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by wenxi on 2017/5/14.
 */
public class WarnWin extends Stage {


    AnchorPane root;

    public WarnWin(boolean isSuccessful) {
        this.initStyle(StageStyle.UNDECORATED);
        try {
            root = FXMLLoader.load(getClass().getResource("WarnWin.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());

        if (isSuccessful) {
            root.setBackground(new Background(new BackgroundImage(new Image("gui/img/success.png"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
            ));
        } else {
            root.setBackground(new Background(new BackgroundImage(new Image("gui/img/failure.png"),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
            ));
        }

        Button replay = (Button) root.lookup("#replayBtn");
        Button back = (Button) root.lookup("#backBtn");

        replay.setOnAction(e -> {
            this.hide();
            Data.warnNumber = 0;
            new GameWin();
        });

        back.setOnAction(e -> {
            this.hide();
            Data.warnNumber = 0;
            if (Data.mode == 0) {
                try {
                    new ChapterSelectWin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (Data.mode == 1) {
                new LevelWin();
            }
        });


        this.setScene(scene);
        //控制warnWin的个数
        if (Data.warnNumber < 1) {
            this.show();
        }
    }
}
