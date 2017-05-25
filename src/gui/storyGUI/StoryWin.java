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
public class StoryWin extends Stage {
    AnchorPane root;

    public StoryWin() throws IOException {
        Music.playBgMusic(4);
        root = FXMLLoader.load(getClass().getResource("Story.fxml"));

        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("storyGUI.css").toExternalForm());
        this.setScene(scene);

        this.initStyle(StageStyle.UNDECORATED);
        this.show();

    }
}
