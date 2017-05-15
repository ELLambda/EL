package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.PublicKey;

/**
 * Created by wenxi on 2017/5/10.
 */
public class SettingWin extends Stage {

    AnchorPane root;

    public SettingWin() throws IOException {
        root= FXMLLoader.load(getClass().getResource("SettingWin.fxml"));
        root.setBackground(new Background(new BackgroundImage(new Image("gui/img/settingBG.jpg"),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));



        Scene scene=new Scene(root,600,400);
        scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());
        this.setScene(scene);
        this.initStyle(StageStyle.UNDECORATED);
        this.show();
    }

}
