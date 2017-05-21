package gui;

import achievements.Achievement;
import achievements.AchievementsManager;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
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
    public static final int starElementedNum=666;
    public static final int toolUsedNum=99;
    public static final int stepsUsedNum=10086;
    public static final int scoreGot=5201314;
    public String[] tips=new String[]{"消灭"+String.valueOf(starElementedNum)+"个该星星，点亮该勋章",
            "使用了"+String.valueOf(toolUsedNum)+"次该道具，点亮该勋章",
            "总步数超过了"+String.valueOf(stepsUsedNum)+"步，点亮该勋章",
            "总分数超过了"+String.valueOf(scoreGot)+"分，点亮该勋章","\n点击该勋章可查看积分榜"};

    public AchievementWin(){
        root=new AnchorPane();

        HBox box1=new HBox();
        box1.setSpacing(spacing);
        box1.setPadding(new Insets(5));
        for(int i=0;i< AchievementsManager.WIDTH;i++){
            Achievement achievement=AchievementsManager.AchievementsList[0][i];
            box1.getChildren().add(achievement);
            achievement.setBackground(new Background(
                    new BackgroundImage(new Image("gui/img/achievement/"+String.valueOf(i+1)+".png"),
                            BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,BackgroundSize.DEFAULT)
            ));
            achievement.setMinSize(100,120);
            Tooltip tooltip = new Tooltip(tips[0]);
            achievement.setTooltip(tooltip);

//            VBox vbox=new VBox(10);
//            vbox.getChildren().addAll()
        }

        HBox box2=new HBox();
        box2.setSpacing(spacing);
        box2.setPadding(new Insets(5));
        for(int i=0;i< AchievementsManager.WIDTH;i++){
            Achievement achievement=AchievementsManager.AchievementsList[1][i];
            box2.getChildren().add(achievement);
            achievement.setBackground(new Background(
                    new BackgroundImage(new Image("gui/img/achievement/"+String.valueOf(i+6)+".png"),
                            BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,BackgroundSize.DEFAULT)
            ));
            achievement.setMinSize(100,120);
            if(i<3){
                achievement.setTooltip(new Tooltip(tips[1]));
            }else if(i==4){
                achievement.setTooltip(new Tooltip((tips[3]+tips[4])));
                achievement.setOnAction(e->{
                    achievement.getScene().getWindow().hide();
                    new BillboardWin();
                });
            }else {
                achievement.setTooltip(new Tooltip(tips[i-1]));
            }


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
                achievement.setOpacity(achievement.getRate());

            }
        }

        Scene scene = new Scene(root,800,600);
       // scene.getStylesheets().add(getClass().getResource("achievementwin.css").toExternalForm());

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
            new MainWin();
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
