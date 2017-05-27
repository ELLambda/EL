package gui;


import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * 信息窗口，即每关游戏开始前的窗口
 */
public class MessageWin extends Stage {

    AnchorPane root;

    /**
     * n为第几关
     */
    public MessageWin(int n) {
        root = new AnchorPane();
        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.TRANSPARENT);

        //设置背景图片
        String url = "gui/img/msgWinBg" + n + ".jpg";//图片路径
        root.setBackground(new Background(new BackgroundImage(new Image(url),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
        ));

        circleAnimation();


        this.initStyle(StageStyle.TRANSPARENT);
        this.setScene(scene);
        this.show();


    }

    public void circleAnimation() {

        int x = 100;
        int y = 400;

        Circle circle = new Circle(x, y, 20);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
        circle.setStrokeWidth(5);
        circle.setCursor(Cursor.HAND);

        //点击圆形关闭消息窗口
        circle.setOnMouseClicked(e -> {

            Data.setLimit(Data.order, 1);
            if (Data.order == 1
                    || Data.order == 4
                    || Data.order == 9) {
                new GameWin();
            } else if (Data.order == 2
                    || Data.order == 5
                    || Data.order == 10) {
                new GameWin1();
            } else if (Data.order == 3
                    || Data.order == 6
                    || Data.order == 11) {
                new GameWin2();
            } else {
                new GameWin3();
            }

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
