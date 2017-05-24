package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by julia98 on 2017/5/24.
 */
public class Pack extends Stage {

    @FXML
    AnchorPane root;


    public Pack() {
        // TODO Auto-generated constructor stub

        try {
            root = FXMLLoader.load(getClass().getResource("Pack.fxml"));
            Scene scene = new Scene(root, 700, 400);
            scene.getStylesheets().add(getClass().getResource("story.css").toExternalForm());
            this.initStyle(StageStyle.UNDECORATED);
            this.setFullScreen(true);
            this.setScene(scene);
            this.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

