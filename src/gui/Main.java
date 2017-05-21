package gui;

import achievements.AchievementsManager;
import achievements.Billboard;
import achievements.BillboardItem;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub


		Platform.runLater(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					new WelcomeWin().start(new Stage());
					new GameWin3();
					//new BillboardWin();

					//new ShopWin();
					//new AchievementWin();
//					Data.setLimit(12);
//					new GameWin();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		
		
		//MessageWin msg=new MessageWin();
		
		//LevelWin level=new LevelWin();
		
		//GameWin game=new GameWin();
		//MessageWin msg1=new MessageWin(1);
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}



