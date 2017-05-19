package gui;

import achievements.Billboard;
import achievements.BillboardItem;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Arrays;

/**
 * Created by wenxi on 2017/5/18.
 */
public class BillboardWin extends Stage {
    public ListView<BillboardItem> listView = new ListView<>();
    AnchorPane root=new AnchorPane();

    public BillboardWin(){
        //放置数据



        //Billboard.scorelist[0].setBillboardItem("1000&100");
        Billboard.getBillboardCondition();

        BillboardItem[] items=new BillboardItem[Billboard.getRank()];
        for(int i=0;i<Billboard.getRank();i++){
            items[i]=Billboard.scorelist[i];
        }

        ObservableList<BillboardItem> list=FXCollections.observableArrayList(items);
//        for(int i=0;i<Billboard.getRank()-1;i++){
//           list.add(Billboard.scorelist[i].getBillboardItem());
//        }
        listView.setItems(list);
        listView.setLayoutX(300);
        listView.setLayoutY(24);

        root.getChildren().add(listView);
        root.setBackground(new Background(new BackgroundImage(new Image("gui/img/achievement/billboard.jpg"),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

        Scene scene=new Scene(root,600,400);
        scene.getStylesheets().add(getClass().getResource("billboard.css").toExternalForm());
        this.setScene(scene);

        circleAnimation();

        this.initStyle(StageStyle.TRANSPARENT);
        this.show();
    }


    public void circleAnimation(){

        int x=40;
        int y=200;

        Circle circle=new Circle(x, y, 20);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
        circle.setStrokeWidth(5);
        circle.setCursor(Cursor.HAND);

        //点击圆形关闭消息窗口
        circle.setOnMouseClicked(e->{
            this.close();
            new AchievementWin();
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
