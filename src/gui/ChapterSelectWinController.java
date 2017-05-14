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
	public GridPane catalogPane;
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
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(2);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter3Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(3);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter4Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(4);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter5Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(5);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter6Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(6);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter7Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(7);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter8Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(8);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter9Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(9);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter10Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(10);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter11Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(11);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onChapter12Clicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(12);
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onEndingClicked(){
		Platform.runLater(()->{
			//main
			ChapterWin chapter=new ChapterWin(13);
			root.getScene().getWindow().hide();
		});
	}

}
