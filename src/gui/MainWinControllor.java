package gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.annotation.Resources;

import achievements.AchievementsManager;
import achievements.Calculator;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

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
	private void onStartBtnClick(){

		Platform.runLater(()->{
			startBtn.getScene().getWindow().hide();
			new ModeWin();
		});

//		Data.mode=1;
//		Platform.runLater(()->{
//			startBtn.getScene().getWindow().hide();
//			Data.mode=1;
//			LevelWin levelWin=new LevelWin();
//		});


	}

    public void onAboutBtnClick(ActionEvent actionEvent) {
		Platform.runLater(()->{
			startBtn.getScene().getWindow().hide();
			new AboutWin();
		});
    }


    public void onAchievementBtnClick(ActionEvent actionEvent) {
		new AchievementWin();
    }


}
