package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by wenxi on 2017/5/18.
 */
public class ModeWinController {


    public Button storyBtn;

    public void onStoryBtnCLick(ActionEvent actionEvent) {
        Data.mode=0;
        Platform.runLater(()->{
            try {
                storyBtn.getScene().getWindow().hide();
                ChapterSelectWin chapter=new ChapterSelectWin();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    public void onBirthdayBtnClick(ActionEvent actionEvent) {
        Data.mode=1;
		Platform.runLater(()->{
			storyBtn.getScene().getWindow().hide();
			LevelWin levelWin=new LevelWin();
		});
    }

    public void onEndlessBtnCLick(ActionEvent actionEvent) {
    }
}
