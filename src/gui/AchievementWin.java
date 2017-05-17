package gui;

import achievements.Achievement;
import achievements.AchievementsManager;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Created by wenxi on 2017/5/17.
 */
public class AchievementWin extends Stage{

    public static final double spacing=15;
    AnchorPane root;

    public AchievementWin(){
        root=new AnchorPane();

        HBox box1=new HBox();
        box1.setSpacing(spacing);
        box1.setPadding(new Insets(5));
        for(int i=0;i< AchievementsManager.WIDTH;i++){
            box1.getChildren().add(AchievementsManager.AchievementsList[0][i]);
            AchievementsManager.AchievementsList[0][i].setBackground(new Background(
                    new BackgroundImage(new Image("gui/img/achievement/"+String.valueOf(i+1)+".png"),
                            BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,BackgroundSize.DEFAULT)
            ));
            AchievementsManager.AchievementsList[0][i].setMinSize(100,120);
        }

        HBox box2=new HBox();
        box2.setSpacing(spacing);
        box2.setPadding(new Insets(5));
        for(int i=0;i< AchievementsManager.WIDTH;i++){
            box2.getChildren().add(AchievementsManager.AchievementsList[1][i]);
            AchievementsManager.AchievementsList[1][i].setBackground(new Background(
                    new BackgroundImage(new Image("gui/img/achievement/"+String.valueOf(i+6)+".png"),
                            BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,BackgroundSize.DEFAULT)
            ));
            AchievementsManager.AchievementsList[1][i].setMinSize(100,120);
        }

        VBox box=new VBox();
        box.setPadding(new Insets(30));
        box.setSpacing(10);
        box.getChildren().addAll(box1,box2);
        box.setLayoutX(80);
        box.setLayoutY(120);
        box.setStyle("-fx-border-color: rgba(255,255,255,0.5);-fx-border-width: 2;-fx-border-radius: 20");

        root.getChildren().add(box);

        root.setBackground(new Background(
                new BackgroundImage(new Image("gui/img/achievement/bg.jpg"),
                        BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,BackgroundSize.DEFAULT)
        ));

        for(int i=0;i<AchievementsManager.HEIGHT;i++){
            for(int j=0;j<AchievementsManager.WIDTH;j++){
                Achievement achievement =AchievementsManager.AchievementsList[i][j];
                if(!achievement.getAchieved()){
                    achievement.setOpacity(0.2);
                }

            }
        }

        Scene scene = new Scene(root,800,600);

        circleAnimation();

        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);
        this.show();

    }


    public void circleAnimation(){

        int x=400;
        int y=525;

        Circle circle=new Circle(x, y, 20);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
        circle.setStrokeWidth(5);
        circle.setCursor(Cursor.HAND);

        //点击圆形关闭窗口
        circle.setOnMouseClicked(e->{
            this.close();
        });

//		ImageView circle2=new ImageView(new Image("img\\circle2.png"));
//		circle2.setLayoutX(x);
//		circle2.setLayoutY(y);

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
