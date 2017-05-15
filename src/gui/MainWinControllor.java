package gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.annotation.Resources;
import java.io.IOException;
import java.net.URL;

public class MainWinControllor {


	public Button startBtn;

	public static void changeScene(AnchorPane root, URL url){
		try {
			root= FXMLLoader.load(url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	private void onExitBtnClick(){
		System.exit(0);
	}
	
	@FXML
	private void onStartBtnClick(){

		Data.mode=1;
		Platform.runLater(()->{
			startBtn.getScene().getWindow().hide();
			LevelWin levelWin=new LevelWin();
		});


	}


    public void onStoryBtnClick(ActionEvent actionEvent) {
		Data.mode=0;
    	Platform.runLater(()->{
			try {
				startBtn.getScene().getWindow().hide();
				ChapterSelectWin chapter=new ChapterSelectWin();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }

    public void onAboutBtnClick(ActionEvent actionEvent) {
    }
}
