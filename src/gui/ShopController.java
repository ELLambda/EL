package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ShopController {
	@FXML
	public AnchorPane root;
	@FXML
	public ImageView Shop;
	@FXML
	public GridPane shopPane;
	@FXML
	public Label label;
	@FXML
	public Button exit;
	@FXML
	public Button ok;
	@FXML
	public Label coin;
	@FXML
	public ScrollBar scrollBar;
	@FXML
	public MenuButton mb1;
	@FXML
	public MenuButton mb2;
	@FXML
	public MenuButton mb3;
	
	@FXML
	public void onExitBtnClicked(){
		Platform.runLater(()->{
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onOKBtnClicked(){
		Platform.runLater(()->{
			//some store and compute
			root.getScene().getWindow().hide();
		});
	}
	
	@FXML
	public void onCoinClicked(){
		Platform.runLater(()->{
			//coin compute
			
			//root.getScene().getWindow().hide();
		});
	}
	
	

}
