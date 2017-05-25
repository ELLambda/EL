package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static gui.ShopController.med_1_remain;
import static gui.ShopController.med_2_remain;
import static gui.ShopController.med_3_remain;

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

    public static int num1;
    public static int num2;
    public static int num3;

    public int use1 = 0;
    public int use2 = 0;
    public int use3 = 0;

    public static final int med1addedStep = 2;
    public static final int med2addedStep = 3;
    public static final int med3addedStep = 5;

    public static int addedStep = 0;
    
    private boolean hasUsed = false;


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
        med_1_remain--;
        num1--;
    }
    public void setmed2now(){
        med_2_remain--;
        num2--;
    }
    public void setmed3now(){
        med_3_remain--;
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

        num1 = shop.Shop.getPack1Condition();
        num2 = shop.Shop.getPack2Condition();
        num3 = shop.Shop.getPack3Condition();

        med1now.setText("现有:" + num1);
        med2now.setText("现有:" + num2);
        med3now.setText("现有:" + num3);
        System.out.println("现有物品1数量：" + num1);
        System.out.println("现有物品2数量：" + num2);
        System.out.println("现有物品3数量：" + num3);
    }

    @FXML
    public void onMed1BtnClicked(){
    	med1btn.setStyle("-fx-effect: dropshadow(gaussian, red, 8, 0.8, 0, 0)");
    	med2btn.setStyle(null);
    	med3btn.setStyle(null);
        if(num1 <= 0) {
            pack.setText("药品不够啦~");
        }else{
            setmed1now();
            setmed1use();
            med1now.setText("现有:" + getmed1now());
            med1use.setText("使用:" + getmed1use());
            hasUsed = true;
        }

    }

    @FXML
    public void onMed2BtnClicked(){
    	med2btn.setStyle("-fx-effect: dropshadow(gaussian, red, 8, 0.8, 0, 0)");
    	med1btn.setStyle(null);
    	med3btn.setStyle(null);

        if(num2 <= 0) {
            pack.setText("药品不够啦~");
            //System.out.println("我是输出2");
        }else{
            setmed2now();
            setmed2use();
            med2now.setText("现有:" + getmed2now());
            med2use.setText("使用:" + getmed2use());
            hasUsed = true;
        }
    }

    @FXML
    public void onMed3BtnClicked(){
    	med3btn.setStyle("-fx-effect: dropshadow(gaussian, red, 8, 0.8, 0, 0)");
    	med1btn.setStyle(null);
    	med2btn.setStyle(null);

        if(num3 <= 0) {
            pack.setText("药品不够啦~");
        }else{
            setmed3now();
            setmed3use();
            med3now.setText("现有:" + getmed3now());
            med3use.setText("使用:" + getmed3use());
            hasUsed = true;
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
        addedStep = getmed1use()*med1addedStep + getmed2use()*med2addedStep + getmed3use()*med3addedStep;
        GameWinControllor.steps += addedStep;
        shop.Shop.setPack1Condition();
        shop.Shop.setPack2Condition();
        shop.Shop.setPack3Condition();
        if(GameWinControllor.s_coins != null)
			GameWinControllor.s_coins.setValue("Energy Value:"+GameWinControllor.steps*10);
        if(GameWinControllor.s_battle != null)
            GameWinControllor.s_battle.setValue("HP:"+GameWinControllor.steps*100);
//        GameWinControllor.stepProgressBar.setProgress((double) GameWinControllor.steps/Data.totalstpes);    不能把stepprogressbar给static
        pack.setText("使用成功~");
        new Timer().schedule(new TimerTask(){

			@Override
			public void run() {
				 Platform.runLater(()->{
			        	root.getScene().getWindow().hide();
			     });
			}
        	
        },800);
        
    }


}
