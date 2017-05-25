package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author julia98
 */
public class ShopWin extends Stage {
    AnchorPane root;

    public ShopWin() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Shop.fxml"));

        Scene scene = new Scene(root, 600, 400);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("Shop.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("resources/fontstyle.css").toExternalForm());
        this.setScene(scene);

        this.initStyle(StageStyle.UNDECORATED);
        this.show();

    }
}

