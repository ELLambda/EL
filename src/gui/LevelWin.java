package gui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class LevelWin extends Stage {
    AnchorPane root;

    public LevelWin() {
        // TODO Auto-generated constructor stub
        Music.playBgMusic(2);//bgm
        try {
            root = FXMLLoader.load(getClass().getResource("LevelWin.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1200, 800);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("levelwin.css").toExternalForm());


        this.initStyle(StageStyle.TRANSPARENT);

        circleAnimation();


        this.setScene(scene);
        this.show();

    }

    public void circleAnimation() {
        Circle circle = new Circle(600, 400, 50);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
        circle.setStrokeWidth(2);

        Circle circle2 = new Circle(600, 400, 40);
        circle2.setFill(Color.TRANSPARENT);
        circle2.setStroke(Color.web("rgba(255,255,255,0.8);"));
        circle2.setStrokeWidth(5);

        root.getChildren().add(circle);
        root.getChildren().add(circle2);

        circle2.setOnMouseClicked(e -> {
            this.close();
            new MainWin();
        });

        //���Ŷ���
        ScaleTransition st = new ScaleTransition(Duration.millis(3000), circle);
        st.setByX(0.5);
        st.setByY(0.5);
        st.setCycleCount(Timeline.INDEFINITE);
        st.play();

        FadeTransition ft = new FadeTransition(Duration.millis(3000), circle);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setFromValue(1);
        ft.setToValue(0.1);
        ft.play();
    }

}
