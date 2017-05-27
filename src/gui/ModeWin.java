package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by wenxi on 2017/5/18.
 */
public class ModeWin extends Stage {

    AnchorPane root;

    public ModeWin() {
        try {
            root = FXMLLoader.load(getClass().getResource("ModeWin.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("modewin.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);

        this.setScene(scene);
        this.initStyle(StageStyle.TRANSPARENT);
        this.show();
    }

}
