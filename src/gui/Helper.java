package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by julia98 on 2017/5/23.
 */
public class Helper extends Stage {
    AnchorPane root;

    public Helper() throws IOException{
        root = FXMLLoader.load(getClass().getResource("Helper.fxml"));
        Scene scene = new Scene(root,685,400);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("story.css").toExternalForm());
        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);
        this.show();
    }
}
