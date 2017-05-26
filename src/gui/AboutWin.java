package gui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
 * Created by wenxi on 2017/5/16.
 */
public class AboutWin extends Stage {

    AnchorPane root;

    public AboutWin() {
        Music.playBgMusic(10);

        root = new AnchorPane();
        root.setBackground(new Background(new BackgroundImage(new Image("gui/img/aboutwin.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
        ));


        circleAnimation();

        Scene scene = new Scene(root, 600, 400);

        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);
        this.show();
    }

    public void circleAnimation() {

        int x = 300;
        int y = 330;

        Circle circle = new Circle(x, y, 20);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
        circle.setStrokeWidth(5);
        circle.setCursor(Cursor.HAND);

        //点击圆形关闭消息窗口
        circle.setOnMouseClicked(e -> {

            new MainWin();
            this.close();
        });

        root.getChildren().add(circle);

        //缩放动画
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), circle);
        st.setByX(0.4);
        st.setByY(0.4);
        st.setCycleCount(Timeline.INDEFINITE);
        st.play();

        FadeTransition ft = new FadeTransition(Duration.millis(2000), circle);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setFromValue(1);
        ft.setToValue(0.1);
        ft.play();

    }
}
