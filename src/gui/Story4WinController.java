package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Story.FileManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
/**
 * 
 * @author julia98
 *
 */
public class Story4WinController {
	int i = 0;
	@FXML
	public AnchorPane root;
	@FXML
	public Button skipBtn;
	@FXML
	public Separator seperate;
	@FXML
	public ImageView picture1;
	@FXML
	public ImageView picture2;
	@FXML
	public ImageView picture3;
	@FXML
	public Label subline;
    FileManager filemanager = new FileManager(4);
    
    ArrayList<String> word = filemanager.word;

	@FXML
	public void onSkipBtnClicked(){
		Platform.runLater(()->{
			//game
			GameWin game=new GameWin();
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChartClicked() {
		if(i == word.size())//退出对话框
    		Platform.runLater(()->{
    			GameWin game=new GameWin();
    			//StorySelectBox ssb = new StorySelectBox(2);
    			root.getScene().getWindow().hide();
    		});
		else{
			subline.setText(word.get(i));

	    	i++;
		}
		
    	if(i == 12)//出现选择框
    		Platform.runLater(()->{
    			//GameWin game=new GameWin();
    			StorySelectBox ssb = new StorySelectBox(4);
    			//root.getScene().getWindow().hide();
    		});
	}
}

