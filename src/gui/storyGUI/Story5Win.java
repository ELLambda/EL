package gui.storyGUI;

import gui.Music;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author julia98
 */
public class Story5Win extends Stage {
    AnchorPane root;

    public Story5Win() throws IOException {
        Music.playBgMusic(8);
        root = FXMLLoader.load(getClass().getResource("Story5.fxml"));

        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("storyGUI.css").toExternalForm());
        this.setScene(scene);

        this.initStyle(StageStyle.UNDECORATED);
        this.show();

    }
}
