package gui;

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

<<<<<<< HEAD

					new BillboardWin();

=======
<<<<<<< HEAD
					new BillboardWin();

=======
>>>>>>> 2d576e7ba2db7b610733c745555448b6ae848652
>>>>>>> f66200cd568bf0e15cac4ab6ef6255f349b8eea2
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



