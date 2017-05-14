package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChapterWin extends Stage {
	@FXML
	AnchorPane root;

	public ChapterWin() {
		try {
			root=FXMLLoader.load(getClass().getResource("Chapter.fxml"));
			Scene scene=new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("chapter.css").toExternalForm());
			this.initStyle(StageStyle.TRANSPARENT);
			this.setScene(scene);
			

			this.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
