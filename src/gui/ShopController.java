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
	public Label md1;
	@FXML
	public Label md2;
	@FXML
	public Label md3;
	@FXML
	public Label md1_price;
	@FXML 
	public Label md2_price;
	@FXML 
	public Label md3_price;
	@FXML
	public Label i_sum;
	@FXML
	public Label p_sum;
	
	public int scores = 0;
	public int i_1 = 0;
	public int i_2 = 0;
	public int i_3 = 0;
	public int p_1 = 0;
	public int p_2 = 0;
	public int p_3 = 0;
	public int price_1 = 200;//金创药单价
	public int price_2 = 300;//还神丹单价
	public int price_3 = 500;//天香续命露
	public int med_1_remain = 0;//之前剩余的
	public int med_2_remain = 0;
	public int med_3_remain = 0;
	
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
	void init(){
		coin.setText("现有积分："+ scores);
		md1.setText(String.valueOf(i_1));
		md2.setText(String.valueOf(i_2));
		md3.setText(String.valueOf(i_3));
		md1_price.setText(String.valueOf(price_1));
		md2_price.setText(String.valueOf(price_2));
		md3_price.setText(String.valueOf(price_3));
	}
	@FXML
	public void onMed1Clicked(){
			i_1++;
			md1.setText("           " + i_1);

			p_1++;
			md1_price.setText("         " + p_1*price_1);

			i_sum.setText(String.valueOf("         " + i_1 + i_2 + i_3));
			p_sum.setText(String.valueOf("         " + p_1 * price_1 + p_2 * price_2 + p_3 * price_3));
		}
	
	@FXML
	public void onMed2Clicked() {
		i_2++;
		md2.setText("           " + i_2);

		p_2++;
		md2_price.setText("         " + p_2*price_2);
		i_sum.setText(String.valueOf("         " + i_1 + i_2 + i_3));
		p_sum.setText(String.valueOf("         " + p_1 * price_1 + p_2 * price_2 + p_3 * price_3));
	}
	
	@FXML
	public void onMed3Clicked(){
			i_3++;
			md3.setText("           " + i_3);

			p_3++;
			md3_price.setText("         " + p_3*price_3);
			i_sum.setText("           " + String.valueOf(i_1 + i_2 + i_3));
			p_sum.setText("         " + String.valueOf(p_1 * price_1 + p_2 * price_2 + p_3 * price_3));
	}

}
