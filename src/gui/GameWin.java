package gui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import achievements.AchievementsManager;
import achievements.Billboard;
import achievements.Calculator;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameWin extends Stage
{

	Parent root;

	public GameWin()
	{
		if (Data.mode != 1)
		{
			Music.playBgMusic(9);// bgm
		}
		try
		{
			this.initStyle(StageStyle.TRANSPARENT);

			root = FXMLLoader.load(getClass().getResource("GameWin.fxml"));

			Scene scene = new Scene(root, 1200, 800);
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("gamewin.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("menubtn.css").toExternalForm());

			// ��lookup����λ�ؼ�
			Button exitBtn = (Button) root.lookup("#exitBtn");
			exitBtn.setOnAction(e ->
			{

				Calculator.scores += GameWinControllor.score.intValue();
				Music.stopBgMusic();
				Platform.runLater(() ->
				{
					switch (Data.mode)
					{
					case 0:
					case 2:
						try
						{
							new ChapterSelectWin();
						} catch (IOException e1)
						{
							e1.printStackTrace();
						}
						break;
					case 1:
						new LevelWin();
						break;
					case 3:
						if (GameWinControllor.score.intValue() > Billboard.scorelist[Billboard.RANK - 1].getScore())
						{
							Data.scoreToBeSet = GameWinControllor.score.intValue();
							Data.timeToBeSet = (new SimpleDateFormat("yyyy-MM-dd"))
									.format(Calendar.getInstance().getTime());
							AchievementsManager.AchievementsList[1][4]
									.setRate((double) (AchievementsManager.AchievementsList[1][4].getRate()
											+ Calculator.scores / GameWinControllor.SCOREBOUND));

							new InputNameWin();
						} else
							new MainWin();
						break;
					}
					// GameWinControllor.score.set(0);
					this.close();
				});
			});

			this.setScene(scene);
			this.show();

		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
