package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 选关界面
 *
 * @author julia98
 */
public class ChapterSelectWin extends Stage {
    AnchorPane root;

    public ChapterSelectWin() throws IOException {

        Music.playBgMusic(4);
        root = FXMLLoader.load(getClass().getResource("chapterSelect.fxml"));

        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.TRANSPARENT);
        //scene.getStylesheets().add(getClass().getResource("/fontstyle2.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("chapterSelect.css").toExternalForm());
        this.setScene(scene);

        this.initStyle(StageStyle.UNDECORATED);
        this.show();

    }


}
