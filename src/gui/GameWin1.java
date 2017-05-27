package gui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import achievements.Billboard;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameWin1 extends Stage {

    Parent root;


    public GameWin1() {
        Music.playBgMusic(9);//bgm
        try {
            this.initStyle(StageStyle.TRANSPARENT);

            root = FXMLLoader.load(getClass().getResource("GameWin1.fxml"));

            Scene scene = new Scene(root, 1200, 800);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add(getClass().getResource("gamewin.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());
            //scene.getStylesheets().add(getClass().getResource("resources/fontstyle.css").toExternalForm());

            //��lookup����λ�ؼ�
            Button exitBtn = (Button) root.lookup("#exitBtn");
            exitBtn.setOnAction(e -> {

//				Calculator.scores += GameWinControllor.score.intValue();
                Music.stopBgMusic();
                Platform.runLater(() -> {
                    switch (Data.mode) {
                        case 0:
                        case 2:
                            try {
                                new ChapterSelectWin();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case 1:
                            new LevelWin();
                            break;
                        case 3:

                            Billboard.scorelist[Billboard.RANK].setScore(GameWinControllor.score.intValue());
                            String str = (new SimpleDateFormat("yyyy-MM-dd")).format(Calendar.getInstance().getTime());


                            Billboard.scorelist[Billboard.RANK].setTime(str);

                            Arrays.sort(Billboard.scorelist);
                            for (int i = 0; i < Billboard.RANK + 1; i++)
                                System.out.println(Billboard.scorelist[i]);

                            Billboard.setBillboardCondition();

                            new MainWin();
                            break;
                    }
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
