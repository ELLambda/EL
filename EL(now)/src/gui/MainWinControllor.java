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

		Platform.runLater(()->{
			LevelWin levelWin=new LevelWin();
		});


	}


    public void onLordBtnClick(ActionEvent actionEvent) {
    	Platform.runLater(()->{
			try {
				StoryWin storyWin=new StoryWin();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }

    public void onAboutBtnClick(ActionEvent actionEvent) {
    }
}
