package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;

/**
 * Created by julia98 on 2017/5/23.
 */
public class HelperCtr {
    @FXML
    public AnchorPane root;
    @FXML
    public Label label;
    @FXML
    public Button backBtn;

    @FXML
    public void onBackBtnClicked(ActionEvent actionEvent){
        Platform.runLater(()->{
            root.getScene().getWindow().hide();
                }
        );
    }

}
