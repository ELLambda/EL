package gui;

import java.io.IOException;

import achievements.Calculator;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWin extends Stage{

	@FXML
	AnchorPane root;

	
	public MainWin() {
		// TODO Auto-generated constructor stub

		try {
			root=FXMLLoader.load(getClass().getResource("MainWin.fxml"));
			Scene scene=new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("mainwin.css").toExternalForm());
			this.initStyle(StageStyle.TRANSPARENT);
			this.setScene(scene);
			

			this.show();


			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
