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
import java.io.IOException;

/**
 * 章节封面
 * @author julia98
 *
 */
public class ChapterWin extends Stage{

	AnchorPane root;
	public static int i = 0;
	/**
	n为第几关
	 */
	public ChapterWin(int n) {
		// TODO Auto-generated constructor stub
		i = n;
		Data.order=n;
		root=new AnchorPane();
		Scene scene=new Scene(root, 1200, 800);
		scene.setFill(Color.TRANSPARENT);

		//设置背景图片
		String url="gui/img/chapter"+n+".png";//图片路径
		root.setBackground(new Background(new BackgroundImage(new Image(url),
				BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)
				));
		
		circleAnimation();


		this.initStyle(StageStyle.TRANSPARENT);
		this.setScene(scene);
		this.show();
		
		
	}
	
	public void circleAnimation(){
		
		int x=620;
		int y=420;
		
		Circle circle=new Circle(x, y, 20);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
		circle.setStrokeWidth(5);
		circle.setCursor(Cursor.HAND);
		
		//点击圆形跳转到storyWin
		circle.setOnMouseClicked(e->{
			if(i == 1){
				try {
					Data.setLimit(Data.order);
					StoryWin story=new StoryWin();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 2){
				try {
					Data.setLimit(Data.order);
					Story2Win story2=new Story2Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 3){
				try {
					Data.setLimit(Data.order);
					Story3Win story3=new Story3Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 4){
				try {
					Data.setLimit(Data.order);
					Story4Win story4=new Story4Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 5){
				try {
					Data.setLimit(Data.order);
					Story5Win story5=new Story5Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 6){
				try {
					Data.setLimit(Data.order);
					Story6Win story6=new Story6Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 7){
				try {
					Data.setLimit(Data.order);
					Story7Win story7=new Story7Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 8){
				try {
					Data.setLimit(Data.order);
					Story8Win story8=new Story8Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 9){
				try {
					Data.setLimit(Data.order);
					Story9Win story9=new Story9Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 10){
				try {
					Data.setLimit(Data.order);
					Story10Win story10=new Story10Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 11){
				try {
					Data.setLimit(Data.order);
					Story11Win story11=new Story11Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 12){
				try {
					Data.setLimit(Data.order);
					Story12Win story12=new Story12Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 13 && StorySelectBox.ending_1){
				try {
					Data.setLimit(Data.order);
					Story13Win story13=new Story13Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 13 && StorySelectBox.ending_2){
				try {
					Data.setLimit(Data.order);
					Story14Win story14=new Story14Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 13 && (StorySelectBox.rationality == -2)){
				try {
					Data.setLimit(Data.order);
					Story15Win story15=new Story15Win();
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(i == 13){
				//Data.setLimit(Data.order);
				//应该有提示窗口 显示未达到开启结局条件
				try {
					ChapterSelectWin csw = new ChapterSelectWin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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
