package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by julia98 on 2017/5/24.
 */
public class PackCtr {
    @FXML
    public AnchorPane root;
    @FXML
    public Button med1btn;
    @FXML
    public Button med2btn;
    @FXML
    public Button med3btn;
    @FXML
    public Label med1now;
    @FXML
    public Label med2now;
    @FXML
    public Label med3now;
    @FXML
    public Label med1use;
    @FXML
    public Label med2use;
    @FXML
    public Label med3use;
    @FXML
    public Label pack;
    @FXML
    public Button back;
    @FXML
    public Button ok;

    public static int num1 = ShopController.med_1_remain;
    public static int num2 = ShopController.med_2_remain;
    public static int num3 = ShopController.med_3_remain;

    public int use1 = 0;
    public int use2 = 0;
    public int use3 = 0;


    public int getmed1now(){
        return num1;
    }
    public int getmed2now(){
        return num2;
    }
    public int getmed3now(){
        return num3;
    }
    public int getmed1use(){
        return use1;
    }
    public int getmed2use(){
        return use2;
    }
    public int getmed3use(){
        return use3;
    }
    public void setmed1now(){
        num1--;
    }
    public void setmed2now(){
        num2--;
    }
    public void setmed3now(){
        num3--;
    }
    public void setmed1use(){
        use1++;
    }
    public void setmed2use(){
        use2++;
    }
    public void setmed3use(){
        use3++;
    }

    @FXML void initialize(){
        med1now.setText("现有：" + String.valueOf(getmed1now()));
        med2now.setText("现有: " + String.valueOf(getmed2now()));
        med3now.setText("现有: " + String.valueOf(getmed3now()));
    }

    @FXML
    public void onMed1BtnClicked(){
        if(num1 < 0) {
            pack.setText("药品不够啦~");
        }else{
            setmed1now();
            setmed1use();
            med1now.setText("现有:" + getmed1now());
            med1use.setText("使用:" + getmed1use());
        }

    }

    @FXML
    public void onMed2BtnClicked(){
        if(num2 < 0) {
            pack.setText("药品不够啦~");
        }else{
            setmed2now();
            setmed2use();
            med2now.setText("现有:" + getmed2now());
            med2use.setText("使用:" + getmed2use());
        }
    }

    @FXML
    public void onMed3BtnClicked(){
        if(num3 < 0) {
            pack.setText("药品不够啦~");
        }else{
            setmed3now();
            setmed3use();
            med3now.setText("现有:" + getmed3now());
            med3use.setText("使用:" + getmed3use());
        }
    }

    @FXML
    public void onBackBtnClicked(){
        Platform.runLater(()->{
           root.getScene().getWindow().hide();
        });
    }

    @FXML
    public void onOKBtnClicked(){
        pack.setText("使用成功~");
    }


}
