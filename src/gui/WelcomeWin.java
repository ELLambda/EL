package gui;

import achievements.AchievementsManager;
import achievements.Billboard;
import achievements.Calculator;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class WelcomeWin extends Application{

	@FXML
	private AnchorPane rootPan;
	@FXML
	private Text prompText;
	
	Group ellipseGroup=new Group();//�����Բ����,���ں���һ����ת
	
	public void musics() {
		Music.playBgMusic(5);//open
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Calculator.initialize();
		AchievementsManager.setAchievements();
		AchievementsManager.getAchieveCondition();
		Billboard.getBillboardCondition();
		musics();
		rootPan=FXMLLoader.load(getClass().getResource("WelcomeWin.fxml"));
		Scene scene=new Scene(rootPan,600,400);
		scene.getStylesheets().add(getClass().getResource("welcome.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("/fontstyle.css").toExternalForm());
		scene.setFill(Color.TRANSPARENT);
		
		for(int i=0;i<6;i++){
			createEllipse(i);				
		}
		
		//������ʾ
		ellipseGroup.setLayoutX(scene.getWidth()/2);
		ellipseGroup.setLayoutY(scene.getHeight()/2-50);
		rootPan.getChildren().add(ellipseGroup);
		//��ת
		rotateEllipses(ellipseGroup);

		//�����������ط�������Ϸ
		rootPan.setOnMouseClicked(e->{
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					MainWin main=new MainWin();
					primaryStage.close();
				}
			});
		});
		
		
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();

	}
	
//	public static void main(String[] args) {
//		launch(args);
//	}

//��ת��Բ��
public void rotateEllipses(Group ellipse) {
	FadeTransition fd=new FadeTransition(Duration.millis(3000),ellipse);
	fd.setFromValue(0.1);
	fd.setToValue(1);
	fd.setCycleCount(Timeline.INDEFINITE);
	fd.setAutoReverse(true);
	//fd.setAutoReverse(true);
	fd.play();
	
	RotateTransition rt=new RotateTransition(Duration.millis(3000), ellipse);
	rt.setFromAngle(0);
	rt.setToAngle(180);
	rt.setCycleCount(1);
	rt.play();
}	
/*
 * @param int i:���ڼ���ƫת�Ƕ�
 */
public void createEllipse(int i) {
	//�½���Բ�������ɫ
	Ellipse ellipse=new Ellipse(50, 100);
	ellipse.setFill(Color.web("rgba(255,255,255,0.3)"));
	
	//����ԭʼ��ת�Ƕ�
	ellipse.setRotate(i*30);
	
	
	ellipseGroup.getChildren().add(ellipse);
}


}
