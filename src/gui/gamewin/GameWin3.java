package gui.gamewin;

import gui.Music;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameWin3 extends Stage {

    Parent root;


    public GameWin3() {
        // TODO Auto-generated constructor stub
        Music.playBgMusic(15);//bgm
        try {
            this.initStyle(StageStyle.TRANSPARENT);

            root = FXMLLoader.load(getClass().getResource("GameWin3.fxml"));

            Scene scene = new Scene(root, 1200, 800);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add(getClass().getResource("gamewin.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());


//			//��lookup����λ�ؼ�
//			Button exitBtn=(Button) root.lookup("#exitBtn");
//			exitBtn.setOnAction(e->{
//
//
//			});
//
            this.setScene(scene);
            this.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
