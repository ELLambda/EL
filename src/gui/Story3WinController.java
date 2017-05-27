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
public class Story3WinController
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
	FileManager filemanager = new FileManager(3);

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
		if (i == word.size())
			Platform.runLater(() ->
			{
				new GameWin();
				// StorySelectBox ssb = new StorySelectBox(3);
				root.getScene().getWindow().hide();
			});
		else
		{
			subline.setText(word.get(i));

			i++;
		}
	}
}
