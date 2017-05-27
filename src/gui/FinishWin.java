package gui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FinishWin extends Stage {

    AnchorPane root;

    /**
     * n为第几关
     */
    public FinishWin() {
        // TODO Auto-generated constructor stub
        root = new AnchorPane();
        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.TRANSPARENT);

        //设置背景图片
        root.setBackground(new Background(new BackgroundImage(new Image("gui/img/Finish2.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
        ));

        scene.setOnMouseClicked(e -> {
            Data.setLimit(Data.order, 0);
            new MainWin();
            this.close();
        });

        this.initStyle(StageStyle.TRANSPARENT);
        this.setScene(scene);
        this.show();
    }


}