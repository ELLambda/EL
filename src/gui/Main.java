package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application
{

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception
	{

		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					new WelcomeWin().start(new Stage());

				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		});

	}

}
