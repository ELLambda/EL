package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ChapterWinController {
	@FXML
	public AnchorPane root;
	@FXML
	public ImageView chapter;
	@FXML
	public Label chapterTag;
	@FXML
	public ProgressIndicator progressindicator;
	@FXML
	public Button backBtn;
	
	@FXML
	public void onChapterClicked(){
		Platform.runLater(()->{
			//game
			try {
				StoryWin story=new StoryWin();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onBackBtnClicked(){
		Platform.runLater(()->{
			MainWin main=new MainWin();
	        root.getScene().getWindow().hide();
		});
	}
	

}
