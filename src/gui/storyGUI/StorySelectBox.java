package gui.storyGUI;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author julia98
 */
public class StorySelectBox extends Stage {
    public static int i = 0;
    //理智值
    public static int rationality = 0;
    //金创药
    public static boolean medicine = false;
    //九转再造丹
    public static boolean dan = false;
    //玄铁剑
    public static boolean sword = false;
    //天香续命露
    public static boolean water = false;
    //结局一
    public static boolean ending_1 = false;
    //结局二
    public static boolean ending_2 = false;
    //结局三
    public static boolean ending_3 = false;
    AnchorPane root;

    public StorySelectBox(int n) {
        i = n;
        root = new AnchorPane();
        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.TRANSPARENT);


        root.setBackground(new Background(new BackgroundImage(new Image("gui/img/storySelectBox2.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
        ));

        Label StorySelectLabel = new Label();
        StorySelectLabel.setLayoutX(522.0);
        StorySelectLabel.setLayoutY(273.0);
        StorySelectLabel.setPrefSize(183.0, 32.0);
        StorySelectLabel.setText("YOUR CHOICE");
        StorySelectLabel.setTextFill(Color.BLACK);
        Font a = new Font(24.0);
        StorySelectLabel.setFont(a);
        root.getChildren().add(StorySelectLabel);

        Button A = new Button();
        A.setLayoutX(416.0);
        A.setLayoutY(467.0);
        A.setMnemonicParsing(false);
        A.setText("选项 A");
        A.setFont(a);
        root.getChildren().add(A);

        Button B = new Button();
        B.setLayoutX(695.0);
        B.setLayoutY(467.0);
        B.setMnemonicParsing(false);
        B.setText("选项 B");
        B.setFont(a);
        root.getChildren().add(B);

        scene.getStylesheets().add(getClass().getResource("chapterSelect.css").toExternalForm());
        this.setScene(scene);

        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.show();

        A.setOnMouseClicked(e -> {
            if (i == 6)
                sword = true;
            else if (i == 8)
                ending_2 = true;
            else if (i == 11)
                water = true;

            Platform.runLater(() -> {
                //GameWin game=new GameWin();
                root.getScene().getWindow().hide();
            });
        });

        B.setOnMouseClicked(e -> {
            if (i == 2)
                medicine = true;
            else if (i == 5)
                rationality--;
            else if (i == 8)
                ending_1 = true;
            else if (i == 11)
                rationality--;

            Platform.runLater(() -> {
                //GameWin game=new GameWin();
                root.getScene().getWindow().hide();
            });
        });
    }
}
