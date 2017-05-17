package gui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
	public Button ending;
	@FXML
	public Button back;
	
	
	@FXML void initialize(){
	
		switch(Data.chapterReached){
		case 13:
			ending.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 12:
			chapter12.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 11:
			chapter11.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 10:
			chapter10.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 9:
			chapter9.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 8:
			chapter8.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 7:
			chapter7.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 6:
			chapter6.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 5:
			chapter5.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 4:
			chapter4.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 3:
			chapter3.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		case 2:
			chapter2.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
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
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(1);
			root.getScene().getWindow().hide();
		});
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

}
