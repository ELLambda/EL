package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameWin extends Stage{

	Parent root;

	
	
	public GameWin() {
		// TODO Auto-generated constructor stub
		Music.playBgMusic(9);//bgm
		try {
			this.initStyle(StageStyle.TRANSPARENT);
			
			root=FXMLLoader.load(getClass().getResource("GameWin.fxml"));
			
			Scene scene=new Scene(root, 1200, 800);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("gamewin.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());

			
			//用lookup来定位控件
			Button exitBtn=(Button) root.lookup("#exitBtn");
			exitBtn.setOnAction(e->{
				this.close();
				Music.stopBgMusic();
				Platform.runLater(()->{
					new LevelWin();
				});
			});
			
			this.setScene(scene);
			this.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
