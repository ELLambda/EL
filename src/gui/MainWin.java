/*
@author Eric
 */

package gui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

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


			circleAnimation();


			this.show();


			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void circleAnimation(){

		int x=60;
		int y=300;

		Circle circle=new Circle(x, y, 20);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
		circle.setStrokeWidth(5);
		circle.setCursor(Cursor.HAND);

		//点击圆形退出程序
		circle.setOnMouseClicked(e->{
			System.exit(0);
		});

		root.getChildren().add(circle);

		//缩放动画
		ScaleTransition st=new  ScaleTransition(Duration.millis(2000),circle);
		st.setByX(0.4);
		st.setByY(0.4);
		st.setCycleCount(Timeline.INDEFINITE);
		st.play();

		FadeTransition ft=new FadeTransition(Duration.millis(2000), circle);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setFromValue(1);
		ft.setToValue(0.1);
		ft.play();

	}
}
