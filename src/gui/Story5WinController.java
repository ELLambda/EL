package gui;

import java.util.ArrayList;

import Story.FileManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @author julia98
 */
public class Story5WinController
{
	@FXML
	public AnchorPane root;
	@FXML
	public Button skipBtn;
	@FXML
	public Separator seperate;
	@FXML
	public ImageView picture1;
	@FXML
	public ImageView picture2;
	@FXML
	public ImageView picture3;
	@FXML
	public Label subline;
	int i = 0;
	FileManager filemanager = new FileManager(5);

	ArrayList<String> word = filemanager.word;

	@FXML
	public void onSkipBtnClicked()
	{
		Platform.runLater(() ->
		{
			// game
			new GameWin();
			root.getScene().getWindow().hide();
		});
	}

	@FXML
	public void onChartClicked()
	{
		if (i == word.size() + 1)// 退出对话框
			Platform.runLater(() ->
			{
				new GameWin();
				// StorySelectBox ssb = new StorySelectBox(2);
				root.getScene().getWindow().hide();
			});
		else
		{
			subline.setText(word.get(i));

			i++;
		}

		if (i == word.size())// 出现选择框
			Platform.runLater(() ->
			{
				// GameWin game=new GameWin();
				new StorySelectBox(5);
				// root.getScene().getWindow().hide();
				i++;
			});
	}
}
