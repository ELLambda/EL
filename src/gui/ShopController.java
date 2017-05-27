package gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import shop.ShopItem;

public class ShopController {
    public static int price_1 = 2;//金创药单价
    public static int price_2 = 3;//还神丹单价
    public static int price_3 = 5;//天香续命露
    public static int med_1_remain = 0;//应该在pack中出现的物品1
    public static int med_2_remain = 0;//应该在pack中出现的物品2
    public static int med_3_remain = 0;//应该在pack中出现的物品3
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
    public int i_1 = 0;
    public int i_2 = 0;
    public int i_3 = 0;
    public int p_1 = 0;
    public int p_2 = 0;
    public int p_3 = 0;
    public String item_1;
    public String item_2;
    public String item_3;
    //ArrayList<ShopItem> selectedList= new ArrayList<ShopItem>();

    @FXML
    public void onExitBtnClicked() {
        Platform.runLater(() -> {
            root.getScene().getWindow().hide();
        });
    }

    @FXML
    public void onOKBtnClicked() {
        Platform.runLater(() -> {
            //some store and compute
            int total = Integer.parseInt(p_sum.getText().replaceAll(" ", ""));
            System.out.println("应付多少钱： " + total);

            if (shop.Shop.coins >= total) {
                //j将购买内容存储在remain里方便pack调用
                med_1_remain += i_1;
                med_2_remain += i_2;
                med_3_remain += i_3;
                System.out.println("pack中应有物品1：" + med_1_remain);
                System.out.println("pack中应有物品2：" + med_2_remain);
                System.out.println("pack中应有物品3：" + med_3_remain);
                shop.Shop.setPack1Condition();
                shop.Shop.setPack2Condition();
                shop.Shop.setPack3Condition();
                System.out.println("此时文件中拥有物品1:" + shop.Shop.getPack1Condition());
                System.out.println("此时文件中拥有物品2:" + shop.Shop.getPack2Condition());
                System.out.println("此时文件中拥有物品3:" + shop.Shop.getPack3Condition());

                shop.Shop.coins = shop.Shop.coins - total;
                shop.Shop.setCoinsCondition();
                System.out.println("还剩多少钱： " + shop.Shop.coins);
                //new TipsWin 提示购买成功窗口
                label.setText("     购买成功！");
                //root.getScene().getWindow().hide();
                new Timer().schedule(new TimerTask() {

                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            root.getScene().getWindow().hide();
                        });

                    }

                }, 800);

            } else {
                //new TipsWin 提示金币不够
                shop.Shop.selectedList.clear();
                label.setText("金币数量不够哦~");
                md1.setText("0");
                md2.setText("0");
                md3.setText("0");
                md1_price.setText("0");
                md2_price.setText("0");
                md3_price.setText("0");
                i_sum.setText(String.valueOf("0"));
                p_sum.setText(String.valueOf("0"));
                i_1 = 0;
                i_2 = 0;
                i_3 = 0;
                p_1 = 0;
                p_2 = 0;
                p_3 = 0;

            }
        });
    }

    @FXML
    void initialize() {
        shop.Shop.getCoinsCondition();
        coin.setText("   现有星星：" + shop.Shop.coins + "  ");
        System.out.println("coins = " + shop.Shop.coins);
        md1.setText("0");
        md2.setText("0");
        md3.setText("0");
        md1_price.setText("0");
        md2_price.setText("0");
        md3_price.setText("0");
    }

    @FXML
    public void onMed1Clicked() {
        i_1++;
        md1.setText("     " + i_1);

        p_1++;
        md1_price.setText("   " + p_1 * price_1);

        int sum_1 = i_1 + i_2 + i_3;
        int sum_2 = p_1 * price_1 + p_2 * price_2 + p_3 * price_3;

        i_sum.setText(String.valueOf("     " + String.valueOf(sum_1)));
        p_sum.setText(String.valueOf("   " + String.valueOf(sum_2)));

        ShopItem S_1 = new ShopItem(item_1, price_1, i_1, price_1, 0);
        shop.Shop.selectedList.add(S_1);
    }

    @FXML
    public void onMed2Clicked() {
        i_2++;
        md2.setText("     " + i_2);

        p_2++;
        md2_price.setText("   " + p_2 * price_2);

        int sum_1 = i_1 + i_2 + i_3;
        int sum_2 = p_1 * price_1 + p_2 * price_2 + p_3 * price_3;

        i_sum.setText(String.valueOf("     " + String.valueOf(sum_1)));
        p_sum.setText(String.valueOf("   " + String.valueOf(sum_2)));

        ShopItem S_2 = new ShopItem(item_2, price_2, i_2, price_2, 0);
        shop.Shop.selectedList.add(S_2);
    }

    @FXML
    public void onMed3Clicked() {
        i_3++;
        md3.setText("     " + i_3);

        p_3++;
        md3_price.setText("   " + p_3 * price_3);

        int sum_1 = i_1 + i_2 + i_3;
        int sum_2 = p_1 * price_1 + p_2 * price_2 + p_3 * price_3;

        i_sum.setText("     " + String.valueOf(sum_1));
        p_sum.setText("   " + String.valueOf(sum_2));

        ShopItem S_3 = new ShopItem(item_3, price_3, i_3, price_3, 0);
        shop.Shop.selectedList.add(S_3);
    }

}
