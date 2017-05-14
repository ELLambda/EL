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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
/**
 * 
 * @author julia98
 *
 */
public class StoryWinController {
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
	
	@FXML
	public void onSkipBtnClicked(){
		Platform.runLater(()->{
			//game
			GameWin game=new GameWin();
			//MessageWin messageWin = new MessageWin(1);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChartClicked() {
	    FileManager filemanager = new FileManager(4);
	    
//	    ArrayList<ArrayList> type = filemanager.type ;
//	    ArrayList<String> ts = filemanager.ts;
//	    ArrayList<String> xj = filemanager.xj;
//	    ArrayList<String> lr = filemanager.lr;
//	    ArrayList<String> narration = filemanager.narration;
//	    ArrayList<String[]> choices = filemanager.choices;
	    ArrayList<String> word = filemanager.word;
		    	//ArrayList word = type.get(i);
		    	subline.setText(word.get(i));
//		    	if(ts.contains(word.get(i)))
//	    		subline.setText((String) word.get(i));
//		    	else if(xj.contains(word.get(i)))
//		    		subline.setText((String) word.get(i));
//       	    	else if(lr.contains(word.get(i)))
//		    		subline.setText((String) word.get(i));
//		    	else if(narration.contains(word.get(i)))
//		    		subline.setText((String) word.get(i));
//		    	else if(choices.contains(word.get(i)))
//		    		subline.setText((String) word.get(i));
		    	i++;
		    	if(i == word.size())
		    		Platform.runLater(()->{
		    			GameWin game=new GameWin();
		    			root.getScene().getWindow().hide();
		    		});
	}
}
