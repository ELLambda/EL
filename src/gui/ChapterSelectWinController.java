package gui;



import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ChapterSelectWinController {
	@FXML
	public AnchorPane root;
	@FXML
	public ImageView chapterPicture;
	@FXML
	public Button chapter1;
	@FXML
	public Button chapter2;
	@FXML
	public Button chapter3;
	@FXML
	public Button chapter4;
	@FXML
	public Button chapter5;
	@FXML
	public Button chapter6;
	@FXML
	public Button chapter7;
	@FXML
	public Button chapter8;
	@FXML
	public Button chapter9;
	@FXML
	public Button chapter10;
	@FXML
	public Button chapter11;
	@FXML
	public Button chapter12;
	@FXML
	public Button gold1;
	@FXML
	public Button gold2;
	@FXML
	public Button gold3;
	@FXML
	public Button gold4;
	@FXML
	public Button gold5;
	@FXML
	public Button gold6;
	@FXML
	public Button gold7;
	@FXML
	public Button gold8;
	@FXML
	public Button gold9;
	@FXML
	public Button gold10;
	@FXML
	public Button gold11;
	@FXML
	public Button gold12;
	@FXML
	public Button ending;
	@FXML
	public Button shop;
	@FXML 
	public Button back;
	
	
	@FXML void initialize(){
	
		switch(Data.chapterReached){
		case 13:
			ending.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold12.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 12:
			chapter12.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold11.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 11:
			chapter11.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold10.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 10:
			chapter10.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold9.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 9:
			chapter9.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold8.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 8:
			chapter8.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold7.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 7:
			chapter7.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold6.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 6:
			chapter6.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold5.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 5:
			chapter5.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold4.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 4:
			chapter4.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold3.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 3:
			chapter3.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold2.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 2:
			chapter2.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			gold1.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 1:
			chapter1.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
			
		
			
		}

	}
	
	@FXML
	public void onBackBtnClicked(){
		Platform.runLater(()->{
			//main
			MainWin main=new MainWin();
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter1Clicked(){
		//Platform.runLater(()->{
			//main
			//ChapterWin chapter=new ChapterWin(1);

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					Circle circle = new Circle(chapter1.getLayoutX(),chapter1.getLayoutY(),20);
					circle.setFill(Color.FLORALWHITE);
					circle.setVisible(true);
					TranslateTransition translateTransition=new TranslateTransition(Duration.millis(2000), circle);
					translateTransition.setFromX(chapter1.getLayoutX());
					//translateTransition.setFromY(chapter1.getLayoutY());
					translateTransition.setToX(chapter2.getLayoutX());
					//translateTransition.setToY(chapter2.getLayoutY());
				}
			}, 10000, 1000);
		root.getScene().getWindow().hide();
		//});
	}
	
	@FXML
	public void onChapter2Clicked(){
		if(Data.chapterReached >= 2){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(2);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter3Clicked(){
		if(Data.chapterReached >= 3){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(3);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter4Clicked(){
		if(Data.chapterReached >= 4){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(4);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter5Clicked(){
		if(Data.chapterReached >= 5){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(5);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter6Clicked(){
		if(Data.chapterReached >= 6){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(6);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter7Clicked(){
		if(Data.chapterReached >= 7){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(7);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter8Clicked(){
		if(Data.chapterReached >= 8){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(8);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter9Clicked(){
		if(Data.chapterReached >= 9){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(9);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter10Clicked(){
		if(Data.chapterReached >= 10){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(10);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter11Clicked(){
		if(Data.chapterReached >= 11){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(11);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onChapter12Clicked(){
		if(Data.chapterReached >= 12){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(12);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onEndingClicked(){
		if(Data.chapterReached >= 13){
			Platform.runLater(()->{
				//main
				ChapterWin chapter=new ChapterWin(13);
				root.getScene().getWindow().hide();
			});
		}
	}
	
	
	public void ongold1Clicked(){
		if(Data.chapterReached >= 2){
			Platform.runLater(()->{
				Data.setLimit(1,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold2Clicked(){
		if(Data.chapterReached >= 3){
			Platform.runLater(()->{
				Data.setLimit(2,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold3Clicked(){
		if(Data.chapterReached >= 4){
			Platform.runLater(()->{
				Data.setLimit(3,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold4Clicked(){
		if(Data.chapterReached >= 5){
			Platform.runLater(()->{
				Data.setLimit(4,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold5Clicked(){
		if(Data.chapterReached >= 6){
			Platform.runLater(()->{
				Data.setLimit(5,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold6Clicked(){
		if(Data.chapterReached >= 7){
			Platform.runLater(()->{
				Data.setLimit(6,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold7Clicked(){
		if(Data.chapterReached >= 8){
			Platform.runLater(()->{
				Data.setLimit(7,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold8Clicked(){
		if(Data.chapterReached >= 9){
			Platform.runLater(()->{
				Data.setLimit(8,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold9Clicked(){
		if(Data.chapterReached >= 10){
			Platform.runLater(()->{
				Data.setLimit(9,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold10Clicked(){
		if(Data.chapterReached >= 11){
			Platform.runLater(()->{
				Data.setLimit(10,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold11Clicked(){
		if(Data.chapterReached >= 12){
			Platform.runLater(()->{
				Data.setLimit(11,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	public void ongold12Clicked(){
		if(Data.chapterReached >= 13){
			Platform.runLater(()->{
				Data.setLimit(12,2);
				new GameWin();
				root.getScene().getWindow().hide();
			});
		}
	}
	
	@FXML
	public void onShopBtnClicked(){
			Platform.runLater(()->{
				//shopWindow
				try {
					ShopWin shop = new ShopWin();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
	}


}
