package gui;



import javafx.scene.layout.*;
import org.omg.CORBA.TIMEOUT;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;

/**
信息窗口，即每关游戏开始前的窗口
 */
public class MessageWin extends Stage{

	AnchorPane root;
	
	/**
	n为第几关
	 */
	public MessageWin(int n) {
		// TODO Auto-generated constructor stub
		root=new AnchorPane();
		Scene scene=new Scene(root, 1200, 800);
		scene.setFill(Color.TRANSPARENT);

		//设置背景图片
		String url="gui/img/msgWinBg"+n+".jpg";//图片路径
		root.setBackground(new Background(new BackgroundImage(new Image(url),
				BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)
				));
		
		circleAnimation();


		this.initStyle(StageStyle.TRANSPARENT);
		this.setScene(scene);
		this.show();
		
		
	}
	
	public void circleAnimation(){
		
		int x=100;
		int y=400;
		
		Circle circle=new Circle(x, y, 20);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
		circle.setStrokeWidth(5);
		circle.setCursor(Cursor.HAND);
		
		//点击圆形关闭消息窗口
		circle.setOnMouseClicked(e->{

			Data.setLimit(Data.order,1);
//			new GameWin();
<<<<<<< HEAD
			new GameWin();
			//new GameWin3();
=======
//			new GameWin1();
			new GameWin2();
//			new GameWin3();
>>>>>>> 642a6a94c8ca02226f9e4dbaf80c40fd33b9de86
			this.close();
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
