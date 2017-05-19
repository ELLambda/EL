package gui;

import java.io.IOException;

import achievements.Calculator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameWin extends Stage{

	Parent root;



	public GameWin() {
		// TODO Auto-generated constructor stub
		Music.playBgMusic(13);//bgm
		try {
			this.initStyle(StageStyle.TRANSPARENT);

			root=FXMLLoader.load(getClass().getResource("GameWin.fxml"));

			Scene scene=new Scene(root, 1200, 800);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("gamewin.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());


			//��lookup����λ�ؼ�
			Button exitBtn=(Button) root.lookup("#exitBtn");
			exitBtn.setOnAction(e->{
				this.close();
				Calculator.scores += GameWinControllor.score.intValue();
				Music.stopBgMusic();
				Platform.runLater(()->{
					switch (Data.mode){
						case 0:
						case 2:
							try {
								new ChapterSelectWin();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							break;
						case 1:
							new LevelWin();
							break;
						case 3:
							new MainWin();
							break;
					}
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
