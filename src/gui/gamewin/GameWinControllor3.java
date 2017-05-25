package gui.gamewin;

import achievements.AchievementsManager;
import achievements.Billboard;
import achievements.Calculator;
import gui.*;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;

/**
 * 玩家点击四个块，其中前两个块相克，后两个块相克
 * 相克的块不能交换
 *
 * @author Andy
 */

public class GameWinControllor3 extends GameWinControllor {
    //按顺序分别表示选择的四个块儿
//	public static IntegerProperty adder1;
//	public static IntegerProperty adder2;
//	public static IntegerProperty sumadder1;
//	public static IntegerProperty sumadder2;

    public static String adder1block;
    public static String adder2block;
    public static String sumadder1block;
    public static String sumadder2block;
    //点击到了第几个块
    public static int number;
//	ArrayList<String> opposite2=new ArrayList<>();
    public VBox slideVBox;
    ArrayList<String> opposite = new ArrayList<>();
    ArrayList<Block> btns;


    @Override
    @FXML
    void initialize() {

        //blockGridPan.setGridLinesVisible(true);
        //blockGridPan.set
        createBlocks();
        number = 0;
        btns = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Block btn = new Block(-1, -1);
            btn.setPrefSize(50, 50);
            btn.setStyle("-fx-background-color: transparent");
            btn.setMouseTransparent(true);
            btns.add(btn);
        }

        VBox vBox = new VBox(30);
        slideVBox.getChildren().add(vBox);

        for (int i = 0; i < 2; i++) {
            HBox hBox = new HBox(20);
            hBox.setPadding(new Insets(10));
            hBox.setStyle("-fx-border-color: rgba(255,255,0,0.8);-fx-border-width: 2;-fx-border-radius: 15;");
            for (int j = i * 2; j < i * 2 + 2; j++) {
                hBox.getChildren().add(btns.get(j));
            }
            vBox.getChildren().add(hBox);
        }


        steps = Data.totalstpes;
        score = new SimpleIntegerProperty(0);

        noticeText.setText("Your score:" + String.valueOf(score.intValue()) + "    Target score:" + Data.targetScore);
        stepLabel.setText("Steps Left:" + steps);
    }

    @Override
    public void createOneBlock(int x, int y) {
        Block btn = new Block(x, y);
        btn.getStyleClass().add("block");
        btn.setOnMouseClicked(e -> {
            if (number == 0) {

                adder1block = btn.getColor();
                if (!opposite.contains(adder1block)) {
                    btns.get(number).setBackgroundColor(adder1block);
                    opposite.add(adder1block);
                    number++;
                }
            } else if (number == 1) {

                adder2block = btn.getColor();
                if (!opposite.contains(adder2block)) {
                    btns.get(number).setBackgroundColor(adder2block);
                    opposite.add(adder2block);
                    number++;
                }

            } else if (number == 2) {

                sumadder1block = btn.getColor();
                if (!opposite.contains(sumadder1block)) {
                    btns.get(number).setBackgroundColor(sumadder1block);
                    opposite.add(sumadder1block);
                    number++;
                }
            } else if (number == 3) {

                sumadder2block = btn.getColor();
                if (!opposite.contains(sumadder2block)) {
                    btns.get(number).setBackgroundColor(sumadder2block);
                    opposite.add(sumadder2block);
                    number++;
                }
            } else if (isMoving == false && ((number == 4))) {
                System.out.println("I have clicked");
                switch (itemSelected) {
                    case "null":
                        Music.playEffectMusic(2);//click
                        if (btn.getIsPressed() == false) {        //之前没被点
                            btn.setIsPressed(true);
                            btn.setSelected();
                            gui.BlockManager.addBlocksToList(btn);//加入两个块的list中
                            System.out.println(btn.getColor() + ":" + btn.getX() + "," + btn.getY());
                        } else {                                //之前被点了
                            btn.setIsPressed(false);
                            btn.setNotSelected();
                            gui.BlockManager.removeBlocksFromList(btn);//从两个块的list中移除
                        }
                        if (gui.BlockManager.twoBlocks.size() == 2) {        //已经点了两个块了
                            isMoving = true;
                            if ((gui.BlockManager.isNear() == true)
                                    && (!(gui.BlockManager.twoBlocks.get(0).getColor().equals(adder1block)
                                    && gui.BlockManager.twoBlocks.get(1).getColor().equals(adder2block)))
                                    && (!(gui.BlockManager.twoBlocks.get(0).getColor().equals(sumadder1block)
                                    && gui.BlockManager.twoBlocks.get(1).getColor().equals(sumadder2block)))
                                    && (!(gui.BlockManager.twoBlocks.get(0).getColor().equals(adder2block)
                                    && gui.BlockManager.twoBlocks.get(1).getColor().equals(adder1block)))
                                    && (!(gui.BlockManager.twoBlocks.get(0).getColor().equals(sumadder2block)
                                    && gui.BlockManager.twoBlocks.get(1).getColor().equals(sumadder1block))))


                            {        //点的两个块相邻
                                if (Data.mode != 3) {
                                    steps--;//步数减1
                                    if (Data.mode == 0)
                                        stepLabel.setText("HP:" + steps * 100);
                                    else if (Data.mode == 1)
                                        stepLabel.setText("Steps Left:" + steps);
                                    else if (Data.mode == 2)
                                        stepLabel.setText("Energy Value:" + steps * 10);
                                    stepProgressBar.setProgress((double) steps / Data.totalstpes);
                                }

                                if (!gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
                                        !gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird")) {
                                    System.out.println("start exchanging");
                                    Transition transition = gui.BlockManager.exchange();    //交换
                                    transition.setOnFinished(e2 -> {
                                        gui.BlockManager.twoBlocks.get(0).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(1).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(0).setIsPressed(false);
                                        gui.BlockManager.twoBlocks.get(1).setIsPressed(false);
                                        System.out.println("exchange done");
                                        if (gui.BlockManager.erasable(gui.BlockManager.twoBlocks.get(0)) | gui.BlockManager.erasable(gui.BlockManager.twoBlocks.get(1))) {
                                            gui.BlockManager.twoBlocks.clear();
                                            erase();

                                        } else {

                                            Transition t = null;
                                            t = gui.BlockManager.exchange();
                                            gui.BlockManager.twoBlocks.clear();
                                            t.setOnFinished(e3 -> {
                                                if (Data.mode != 3)
                                                    checkIsLose();

                                                isMoving = false;

                                            });
                                            gui.BlockManager.resetArrays();

                                        }
                                    });
                                } else if (gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
                                        gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird")) {    //全屏消
                                    Transition transition = gui.BlockManager.exchange();
                                    transition.setOnFinished(e2 -> {
                                        gui.BlockManager.twoBlocks.get(0).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(1).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(0).setIsPressed(false);
                                        gui.BlockManager.twoBlocks.get(1).setIsPressed(false);
                                        gui.BlockManager.twoBlocks.get(0).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.get(1).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.clear();
                                        for (int i = 0; i < 10; i++)
                                            for (int j = 0; j < 10; j++) {
                                                gui.BlockManager.erased[gui.BlockManager.length][0] = i;
                                                gui.BlockManager.erased[gui.BlockManager.length][1] = j;
                                                gui.BlockManager.length++;
                                            }
                                        erase();


                                    });

                                } else if ((gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
                                        gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("null")) |
                                        (gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("null") &&
                                                gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird"))) {   //消掉全部相同颜色的
                                    Transition transition = gui.BlockManager.exchange();
                                    transition.setOnFinished(e2 -> {
                                        gui.BlockManager.twoBlocks.get(0).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(1).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(0).setIsPressed(false);
                                        gui.BlockManager.twoBlocks.get(1).setIsPressed(false);
                                        String color = gui.BlockManager.twoBlocks.get(0).getColor();        //将要消掉的颜色
                                        if (gui.BlockManager.twoBlocks.get(0).getColor().equals("MagicBird"))
                                            color = gui.BlockManager.twoBlocks.get(1).getColor();
                                        gui.BlockManager.twoBlocks.get(0).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.get(1).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.get(0).setColor(color);
                                        gui.BlockManager.twoBlocks.get(1).setColor(color);
                                        gui.BlockManager.twoBlocks.clear();
                                        HashSet<Block> h = new HashSet<Block>();
                                        for (int i = 0; i < 10; i++)
                                            for (int j = 0; j < 10; j++) {
                                                if (gui.BlockManager.blocks[i][j].getColor().equals(color)) {
                                                    h.add(gui.BlockManager.blocks[i][j]);


                                                }
                                            }
                                        while (true) {
                                            int size = h.size();
                                            h = gui.BlockManager.checkLineSpecial(h);
                                            if (size == h.size())
                                                break;
                                        }
                                        Iterator<Block> iterator = h.iterator();
                                        while (iterator.hasNext()) {
                                            Block b = iterator.next();
                                            gui.BlockManager.erased[gui.BlockManager.length][0] = b.getX();
                                            gui.BlockManager.erased[gui.BlockManager.length][1] = b.getY();
                                            gui.BlockManager.length++;
                                        }
                                        erase();


                                    });


                                } else if ((gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
                                        gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("horizon")) |
                                        (gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("horizon") &&
                                                gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird"))) {
                                    Transition transition = gui.BlockManager.exchange();
                                    transition.setOnFinished(e2 -> {
                                        gui.BlockManager.twoBlocks.get(0).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(1).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(0).setIsPressed(false);
                                        gui.BlockManager.twoBlocks.get(1).setIsPressed(false);
                                        int line = gui.BlockManager.twoBlocks.get(0).getY();
                                        String color = gui.BlockManager.twoBlocks.get(0).getColor();        //将要消掉的颜色
                                        if (gui.BlockManager.twoBlocks.get(0).getColor().equals("MagicBird")) {
                                            color = gui.BlockManager.twoBlocks.get(1).getColor();
                                            line = gui.BlockManager.twoBlocks.get(1).getY();
                                        }
                                        gui.BlockManager.twoBlocks.get(0).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.get(1).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.get(0).setColor(color);
                                        gui.BlockManager.twoBlocks.get(1).setColor(color);
                                        gui.BlockManager.twoBlocks.clear();

                                        HashSet<Block> h = new HashSet<Block>();
                                        for (int i = 0; i < 10; i++)
                                            for (int j = 0; j < 10; j++) {
                                                if (gui.BlockManager.blocks[i][j].getColor().equals(color)) {
                                                    h.add(gui.BlockManager.blocks[i][j]);
                                                }
                                            }
                                        for (int i = 0; i < 10; i++) {
                                            h.add(gui.BlockManager.blocks[i][line]);
                                        }
                                        while (true) {
                                            int size = h.size();
                                            h = gui.BlockManager.checkLineSpecial(h);
                                            if (size == h.size())
                                                break;
                                        }
                                        Iterator<Block> iterator = h.iterator();
                                        while (iterator.hasNext()) {
                                            Block b = iterator.next();
                                            gui.BlockManager.erased[gui.BlockManager.length][0] = b.getX();
                                            gui.BlockManager.erased[gui.BlockManager.length][1] = b.getY();
                                            gui.BlockManager.length++;
                                        }
                                        erase();


                                    });
                                } else if ((gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
                                        gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("vertical")) |
                                        (gui.BlockManager.twoBlocks.get(0).getSpecialType().equals("vertical") &&
                                                gui.BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird"))) {
                                    Transition transition = gui.BlockManager.exchange();
                                    transition.setOnFinished(e2 -> {
                                        gui.BlockManager.twoBlocks.get(0).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(1).setNotSelected();
                                        gui.BlockManager.twoBlocks.get(0).setIsPressed(false);
                                        gui.BlockManager.twoBlocks.get(1).setIsPressed(false);
                                        int line = gui.BlockManager.twoBlocks.get(0).getX();
                                        String color = gui.BlockManager.twoBlocks.get(0).getColor();        //将要消掉的颜色
                                        if (gui.BlockManager.twoBlocks.get(0).getColor().equals("MagicBird")) {
                                            color = gui.BlockManager.twoBlocks.get(1).getColor();
                                            line = gui.BlockManager.twoBlocks.get(1).getX();
                                        }
                                        gui.BlockManager.twoBlocks.get(0).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.get(1).setSpecialType("null");
                                        gui.BlockManager.twoBlocks.get(0).setColor(color);
                                        gui.BlockManager.twoBlocks.get(1).setColor(color);
                                        gui.BlockManager.twoBlocks.clear();

                                        HashSet<Block> h = new HashSet<Block>();
                                        for (int i = 0; i < 10; i++)
                                            for (int j = 0; j < 10; j++) {
                                                if (gui.BlockManager.blocks[i][j].getColor().equals(color)) {
                                                    h.add(gui.BlockManager.blocks[i][j]);
                                                }
                                            }
                                        for (int i = 0; i < 10; i++) {
                                            h.add(gui.BlockManager.blocks[line][i]);
                                        }
                                        while (true) {
                                            int size = h.size();
                                            h = gui.BlockManager.checkLineSpecial(h);
                                            if (size == h.size())
                                                break;
                                        }
                                        Iterator<Block> iterator = h.iterator();
                                        while (iterator.hasNext()) {
                                            Block b = iterator.next();
                                            gui.BlockManager.erased[gui.BlockManager.length][0] = b.getX();
                                            gui.BlockManager.erased[gui.BlockManager.length][1] = b.getY();
                                            gui.BlockManager.length++;
                                        }
                                        erase();


                                    });
                                }
                            } else {                                                //点的两个块不相邻
                                Block b = gui.BlockManager.twoBlocks.get(0);        //把第一个点的熄灭
                                b.setIsPressed(false);
                                b.setNotSelected();
                                gui.BlockManager.removeBlocksFromList(b);
                                isMoving = false;
                            }
                        }
                        break;
                    case "SmallHammer":
                        isMoving = true;
                        if (Data.mode != 3) {
                            steps--;
                            if (Data.mode == 0)
                                stepLabel.setText("HP:" + steps * 100);
                            else if (Data.mode == 1)
                                stepLabel.setText("Steps Left:" + steps);
                            else if (Data.mode == 2)
                                stepLabel.setText("Energy Value:" + steps * 10);
                            stepProgressBar.setProgress((double) steps / Data.totalstpes);
                        }

                        HashSet<Block> h2 = new HashSet<Block>();
                        h2.add(btn);
                        while (true) {
                            int size = h2.size();
                            h2 = gui.BlockManager.checkLineSpecial(h2);
                            if (size == h2.size())
                                break;
                        }
                        Iterator<Block> iterator2 = h2.iterator();
                        while (iterator2.hasNext()) {
                            Block b = iterator2.next();
                            gui.BlockManager.erased[gui.BlockManager.length][0] = b.getX();
                            gui.BlockManager.erased[gui.BlockManager.length][1] = b.getY();
                            gui.BlockManager.length++;
                        }

                        Calculator.smallHammer++;
                        if (Calculator.smallHammer >= ITEMBOUND)
                            AchievementsManager.AchievementsList[1][0].setAchieved(true);
                        else
                            AchievementsManager.AchievementsList[1][0].setRate((double) Calculator.smallHammer / (double) ITEMBOUND);

                        setToolNotSelected(smallHammer);
                        score.set(score.intValue() - 20);        //使用小锤子技能要减20分
                        itemSelected = "null";
                        erase();
                        break;
                    case "BigHammer":
                        isMoving = true;
                        if (Data.mode != 3) {
                            steps--;
                            if (Data.mode == 0)
                                stepLabel.setText("HP:" + steps * 100);
                            else if (Data.mode == 1)
                                stepLabel.setText("Steps Left:" + steps);
                            else if (Data.mode == 2)
                                stepLabel.setText("Energy Value:" + steps * 10);
                            stepProgressBar.setProgress((double) steps / Data.totalstpes);
                        }
                        int i = btn.getX();
                        int j = btn.getY();

                        HashSet<Block> h = new HashSet<Block>();
                        if (i >= 1) {
                            if (j >= 1)
                                h.add(gui.BlockManager.blocks[i - 1][j - 1]);
                            h.add(gui.BlockManager.blocks[i - 1][j]);
                            if (j < 9)
                                h.add(gui.BlockManager.blocks[i - 1][j + 1]);
                        }
                        if (j >= 1)
                            h.add(gui.BlockManager.blocks[i][j - 1]);
                        h.add(gui.BlockManager.blocks[i][j]);
                        if (j < 9)
                            h.add(gui.BlockManager.blocks[i][j + 1]);
                        if (i < 9) {
                            if (j >= 1)
                                h.add(gui.BlockManager.blocks[i + 1][j - 1]);
                            h.add(gui.BlockManager.blocks[i + 1][j]);
                            if (j < 9)
                                h.add(gui.BlockManager.blocks[i + 1][j + 1]);
                        }

                        while (true) {
                            int size = h.size();
                            h = gui.BlockManager.checkLineSpecial(h);
                            if (size == h.size())
                                break;
                        }
                        Iterator<Block> iterator = h.iterator();
                        while (iterator.hasNext()) {
                            Block block = iterator.next();
                            gui.BlockManager.erased[gui.BlockManager.length][0] = block.getX();
                            gui.BlockManager.erased[gui.BlockManager.length][1] = block.getY();
                            gui.BlockManager.length++;
                        }
                        Calculator.bigHammer++;
                        if (Calculator.bigHammer >= ITEMBOUND)
                            AchievementsManager.AchievementsList[1][1].setAchieved(true);
                        else
                            AchievementsManager.AchievementsList[1][1].setRate((double) Calculator.bigHammer / (double) ITEMBOUND);


                        setToolNotSelected(bigHammer);
                        score.set(score.intValue() - 200);        //使用大锤子技能减200分
                        itemSelected = "null";
                        erase();
                        break;

                    //使用魔力棒将点击的块改变为一个特殊块儿，使用此技能减500分
                    case "Magic":
                        if (btn.getSpecialType().equals("MagicBird"))
                            break;
                        if (Data.mode != 3) {
                            steps--;
                            if (Data.mode == 0)
                                stepLabel.setText("Health Point:" + steps * 100);
                            else if (Data.mode == 1)
                                stepLabel.setText("Steps Left:" + steps);
                            else if (Data.mode == 2)
                                stepLabel.setText("Energy Value:" + steps * 10);
                            stepProgressBar.setProgress((double) steps / Data.totalstpes);
                        }
                        blockGridPan.getChildren().remove(btn);

                        createOneBlock(btn.getX(), btn.getY());
                        Block specialBlock = gui.BlockManager.blocks[btn.getX()][btn.getY()];
                        String specialType = gui.BlockManager.getBlockSpecialTypeRandom();
                        specialBlock.setSpecialType(specialType);
                        if (specialType.equals("MagicBird") || specialType.equals("Bomb"))
                            specialBlock.setBackgroundColor(specialType);
                        else {
                            specialBlock.setBackgroundColor(btn.getColor());
                            specialBlock.setPattern(specialType);
                        }
                        blockGridPan.add(specialBlock, specialBlock.getX(), specialBlock.getY());

                        Calculator.magic++;
                        if (Calculator.magic >= ITEMBOUND)
                            AchievementsManager.AchievementsList[1][2].setAchieved(true);
                        else
                            AchievementsManager.AchievementsList[1][2].setRate((double) Calculator.magic / (double) ITEMBOUND);


                        setToolNotSelected(magic);
                        score.set(score.intValue() - 500);        //使用魔力棒技能减500分
                        if (Data.mode == 2) {
                            noticeText.setText("      coins:      " + String.valueOf(score.intValue()));

                        } else if (Data.mode == 3) {
                            noticeText.setText("    Your score:   " + String.valueOf(score.intValue()));
                        } else {
                            noticeText.setText("Your score:" + String.valueOf(score.intValue()) + "    Target score:" + Data.targetScore);
                        }
                        itemSelected = "null";
                        if (specialType.equals("Bomb")) {
                            isMoving = true;
                            bombExplode();
                        }
                        break;

//			case 其他技能

                }//end of switch
            }
        });


        gui.BlockManager.blocks[x][y] = btn;

    }

    @Override
    public void erase() {

        //int temp=score.intValue()+ BlockManager.length*BlockManager.length*(erasedTimes++);
//	score.set(score.intValue()+ BlockManager.length*BlockManager.length*(erasedTimes++));

        //改变记录值
        score.set(score.intValue() + gui.BlockManager.length * gui.BlockManager.length * (erasedTimes++));
        Calculator.steps += erasedTimes;
        if (Calculator.steps >= STEPBOUND)
            AchievementsManager.AchievementsList[1][3].setAchieved(true);
        else
            AchievementsManager.AchievementsList[1][3].setRate((double) Calculator.steps / (double) STEPBOUND);

        noticeText.setText("Your score:" + String.valueOf(score.intValue()) + "    Target score:" + Data.targetScore);


        Music.playEffectMusic(1);//eliminate

        System.out.println("start erasing");

        for (int i = 0; i < gui.BlockManager.length; i++) {

            Block block = gui.BlockManager.blocks[gui.BlockManager.erased[i][0]][gui.BlockManager.erased[i][1]];

            switch (block.getColor()) {
                case ("1"):
                    Calculator.first++;

                    if (Calculator.first >= BLOCKBOUND)
                        AchievementsManager.AchievementsList[0][0].setAchieved(true);
                    else
                        AchievementsManager.AchievementsList[0][0].setRate((double) Calculator.first / (double) BLOCKBOUND);


                    break;
                case ("2"):
                    Calculator.second++;
                    if (Calculator.second >= BLOCKBOUND)
                        AchievementsManager.AchievementsList[0][1].setAchieved(true);
                    else
                        AchievementsManager.AchievementsList[0][1].setRate((double) Calculator.second / (double) BLOCKBOUND);


                    break;
                case ("3"):
                    Calculator.third++;
                    if (Calculator.third >= BLOCKBOUND)
                        AchievementsManager.AchievementsList[0][2].setAchieved(true);
                    else
                        AchievementsManager.AchievementsList[0][2].setRate((double) Calculator.third / (double) BLOCKBOUND);


                    break;
                case ("4"):
                    Calculator.fourth++;
                    if (Calculator.fourth >= BLOCKBOUND)
                        AchievementsManager.AchievementsList[0][3].setAchieved(true);
                    else
                        AchievementsManager.AchievementsList[0][3].setRate((double) Calculator.fourth / (double) BLOCKBOUND);


                    break;
                case ("5"):
                    Calculator.fifth++;
                    if (Calculator.fifth >= BLOCKBOUND)
                        AchievementsManager.AchievementsList[0][4].setAchieved(true);
                    else
                        AchievementsManager.AchievementsList[0][4].setRate((double) Calculator.fifth / (double) BLOCKBOUND);


                    break;
            }
            gui.BlockManager.blocks[gui.BlockManager.erased[i][0]][gui.BlockManager.erased[i][1]] = null;
            final int iFinal = i;
            //消失的动画
            FadeTransition transition = new FadeTransition(Duration.seconds(SECOND), block);
            transition.setFromValue(1);
            transition.setToValue(0);
            if (block.getSpecialType().equals("null")) {        //不变成特效块

                transition.setOnFinished(e -> {

                    blockGridPan.getChildren().remove(block);
                    if (iFinal == gui.BlockManager.length - 1) {
                        descend();
                    }
                });
            } else if (block.getSpecialType().equals("MagicBird")) {            //变成魔力鸟
                if (block.getColor().equals("MagicBird")) {

                    transition.setOnFinished(e -> {

                        blockGridPan.getChildren().remove(block);
                        if (iFinal == gui.BlockManager.length - 1) {
                            descend();

                        }
                    });
                } else {

                    transition.setOnFinished(e -> {

                        blockGridPan.getChildren().remove(block);

                        createOneBlock(block.getX(), block.getY());
                        Block magicBirdBlock = gui.BlockManager.blocks[block.getX()][block.getY()];
                        magicBirdBlock.setSpecialType("MagicBird");
                        magicBirdBlock.setBackgroundColor("MagicBird");
                        blockGridPan.add(magicBirdBlock, magicBirdBlock.getX(), magicBirdBlock.getY());
                        if (iFinal == gui.BlockManager.length - 1) {
                            descend();
                        }
                    });
                }
            } else if (block.getSpecialType().equals("Bomb")) {            //变成爆炸块

                transition.setOnFinished(e -> {

                    blockGridPan.getChildren().remove(block);

                    createOneBlock(block.getX(), block.getY());
                    Block bombBlock = gui.BlockManager.blocks[block.getX()][block.getY()];
                    bombBlock.setSpecialType("Bomb");
                    bombBlock.setBackgroundColor("Bomb");
                    //		        		bombBlock.setBombColor("Bomb");
                    blockGridPan.add(bombBlock, bombBlock.getX(), bombBlock.getY());

                    if (iFinal == gui.BlockManager.length - 1) {
                        descend();
                    }
                });
            } else if (block.getSpecialType().equals("horizon")) {
                if (block.getPattern().equals("horizon")) {

                    transition.setOnFinished(e -> {

                        blockGridPan.getChildren().remove(block);

                        if (iFinal == gui.BlockManager.length - 1) {
                            descend();
                        }
                    });
                } else {

                    transition.setOnFinished(e -> {

                        blockGridPan.getChildren().remove(block);

                        createOneBlock(block.getX(), block.getY());
                        Block nb = gui.BlockManager.blocks[block.getX()][block.getY()];
                        nb.setSpecialType("horizon");
                        nb.setBackgroundColor(block.getColor());
                        nb.setPattern("horizon");
                        blockGridPan.add(nb, nb.getX(), nb.getY());

                        if (iFinal == gui.BlockManager.length - 1) {
                            descend();
                        }

                    });
                }
            } else if (block.getSpecialType().equals("vertical")) {
                if (block.getPattern().equals("vertical")) {

                    transition.setOnFinished(e -> {

                        blockGridPan.getChildren().remove(block);

                        if (iFinal == gui.BlockManager.length - 1) {
                            descend();
                        }

                    });
                } else {

                    transition.setOnFinished(e -> {

                        blockGridPan.getChildren().remove(block);

                        createOneBlock(block.getX(), block.getY());
                        Block nb = gui.BlockManager.blocks[block.getX()][block.getY()];
                        nb.setSpecialType("vertical");
                        nb.setBackgroundColor(block.getColor());
                        nb.setPattern("vertical");
                        blockGridPan.add(nb, nb.getX(), nb.getY());

                        if (iFinal == gui.BlockManager.length - 1) {
                            descend();

                        }

                    });
                }
            }


            transition.play();
        }


    }

    @Override
    public void onRestartBtnClick(ActionEvent actionEvent) {
        if (isMoving == false) {
            Music.playEffectMusic(2);//click
            blockGridPan.getChildren().clear();
            gui.BlockManager.twoBlocks.clear();
            createBlocks();
            number = 0;
            for (Block block : btns) {
                block.setStyle("-fx-background-color: transparent;-fx-background-image: null;");
            }
            opposite.clear();

            noticeText.setText("Restart!");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    noticeText.setText("Your score:" + String.valueOf(score.intValue()) + "    Target score:" + Data.targetScore);
                }
            }, 1000);

            steps = Data.totalstpes;
            stepLabel.setText("Steps Left:" + steps);
            stepProgressBar.setProgress(1.0);
            score = new SimpleIntegerProperty(0);
        }

    }

    @Override
    public void checkIsLose() {
        if (score.intValue() >= Data.targetScore) {
            Calculator.scores += score.intValue();
            if (Calculator.scores >= SCOREBOUND)
                AchievementsManager.AchievementsList[1][4].setAchieved(true);

            AchievementsManager.AchievementsList[1][4].setRate((double) Calculator.scores / (double) SCOREBOUND);


            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        blockGridPan.getScene().getWindow().hide();
                        new WarnWin(true);
                        Data.warnNumber++;
                    });
                }
            }, 1000);
        } else if (steps == 0) {

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        blockGridPan.getScene().getWindow().hide();
                        new WarnWin(false);
                        Data.warnNumber++;
                    });
                }

            }, 1000);
        }
    }

    public void onExitBtnClick(ActionEvent actionEvent) {
        Calculator.scores += score.intValue();
//		number=0;
        Music.stopBgMusic();
        Platform.runLater(() -> {
            switch (Data.mode) {
                case 0:
                case 2:
                    try {
                        new ChapterSelectWin();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 1:
                    new LevelWin();
                    break;
                case 3:

                    AchievementsManager.AchievementsList[1][4].setRate(score.doubleValue() / SCOREBOUND);

                    Billboard.scorelist[Billboard.RANK].setScore(score.intValue());
                    String str = (new SimpleDateFormat("yyyy-MM-dd")).format(Calendar.getInstance().getTime());


                    Billboard.scorelist[Billboard.RANK].setTime(str);

                    Arrays.sort(Billboard.scorelist);
                    for (int i = 0; i < Billboard.RANK + 1; i++)
                        System.out.println(Billboard.scorelist[i]);

                    Billboard.setBillboardCondition();

                    new MainWin();
                    break;
            }
            blockGridPan.getScene().getWindow().hide();
        });

    }
}
