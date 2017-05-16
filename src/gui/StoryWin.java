package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * 
 * @author julia98
 *
 */
public class StoryWin extends Stage {
	AnchorPane root;

	public StoryWin() throws IOException {
		Music.playBgMusic(4);
		root= FXMLLoader.load(getClass().getResource("Story.fxml"));

		 Scene scene=new Scene(root,1200,800);
		 scene.setFill(Color.TRANSPARENT);
		 scene.getStylesheets().add(getClass().getResource("story.css").toExternalForm());
		 this.setScene(scene);
		 
	        this.initStyle(StageStyle.UNDECORATED);
	        this.show();

	}
}
