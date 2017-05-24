package gui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by julia98 on 2017/5/23.
 */
public class Helper extends Stage {
    AnchorPane root;
    int next=1;

    public Helper() throws IOException{

        root=new AnchorPane();
        root.setBackground(new Background(new BackgroundImage(
                new Image("gui/img/helper0.png"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,BackgroundSize.DEFAULT
        )));

        circleAnimation();

        Scene scene = new Scene(root,900,600);
        this.setScene(scene);
        this.initStyle(StageStyle.TRANSPARENT);
        this.show();


    }

    public void circleAnimation(){

        int x=450;
        int y=505;

        Circle circle=new Circle(x, y, 20);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
        circle.setStrokeWidth(5);
        circle.setCursor(Cursor.HAND);

        //点击圆形
        circle.setOnMouseClicked(e->{

            if(next==6) this.close();
            else {
                String url = "gui/img/helper" + String.valueOf(next) + ".png";
                root.setBackground(new Background(new BackgroundImage(
                        new Image(url), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, BackgroundSize.DEFAULT
                )));
                next++;
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
