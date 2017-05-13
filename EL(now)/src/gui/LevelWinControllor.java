package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class LevelWinControllor {

	@FXML
	private void onLevelOneBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(1);
		});

	}
	
	@FXML
	private void onLevelTwoBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(2);
		});

	}
	
	@FXML
	private void onLevelThreeBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(3);
		});

	}
	
	@FXML
	private void onLevelFourBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(4);
		});

	}
	
	@FXML
	private void onLevelFiveBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(5);
		});

	}
	
	@FXML
	private void onLevelSixBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(6);
		});

	}
	
	@FXML
	private void onLevelSevenBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(7);
		});

	}
	
	@FXML
	private void onLevelEightBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(8);
		});

	}
	
	@FXML
	private void onLevelNineBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(9);
		});

	}
	
	@FXML
	private void onLevelTenBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(10);
		});

	}
	
	@FXML
	private void onLevelElevenBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(11);
		});

	}
	
	@FXML
	private void onLevelTwelveBtnClick(){
		Platform.runLater(()->{
			GameWin game=new GameWin();
			MessageWin msg=new MessageWin(12);
		});

	}
}
