package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelWinControllor
{

	public Button btn;

	@FXML
	private void onLevelOneBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(1);
			Data.order = 1;
		});

	}

	@FXML
	private void onLevelTwoBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(2);
			Data.order = 2;
		});

	}

	@FXML
	private void onLevelThreeBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(3);
			Data.order = 3;
		});

	}

	@FXML
	private void onLevelFourBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(4);
			Data.order = 4;
		});

	}

	@FXML
	private void onLevelFiveBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(5);
			Data.order = 5;
		});

	}

	@FXML
	private void onLevelSixBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(6);
			Data.order = 6;
		});

	}

	@FXML
	private void onLevelSevenBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(7);
			Data.order = 7;
		});

	}

	@FXML
	private void onLevelEightBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(8);
			Data.order = 8;
		});

	}

	@FXML
	private void onLevelNineBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(9);
			Data.order = 9;
		});

	}

	@FXML
	private void onLevelTenBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(10);
			Data.order = 10;
		});

	}

	@FXML
	private void onLevelElevenBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(11);
			Data.order = 11;
		});

	}

	@FXML
	private void onLevelTwelveBtnClick()
	{
		Platform.runLater(() ->
		{
			btn.getScene().getWindow().hide();
			new MessageWin(12);
			Data.order = 12;
		});

	}
}
