package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * Created by wenxi on 2017/5/18.
 */
public class ModeWinController
{

	public Button storyBtn;

	public void onStoryBtnCLick(ActionEvent actionEvent)
	{
		Data.mode = 0;
		Platform.runLater(() ->
		{
			try
			{
				storyBtn.getScene().getWindow().hide();
				new ChapterSelectWin();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}

	public void onBirthdayBtnClick(ActionEvent actionEvent)
	{
		Data.mode = 1;
		Platform.runLater(() ->
		{
			storyBtn.getScene().getWindow().hide();
			new LevelWin();
		});
	}

	public void onEndlessBtnCLick(ActionEvent actionEvent)
	{
		Data.mode = 3;
		Platform.runLater(() ->
		{
			storyBtn.getScene().getWindow().hide();
			new GameWin();
		});
	}
}
