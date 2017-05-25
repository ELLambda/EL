package gui;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import shop.Shop;

import javax.jws.Oneway;


import achievements.AchievementsManager;
import achievements.Calculator;


import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import static shop.Shop.coins;


//为了方便继承修改了作用域
public class GameWinControllor {
	public static BlockManager BlockManager = new BlockManager();
	public Label stepLabel;
	public ProgressBar stepProgressBar;
	public Button smallHammer;
	public Button bigHammer;
	public Button magic;
	@FXML public GridPane blockGridPan;
	@FXML public AnchorPane root;
	@FXML public TextField noticeText;
	protected final static int HEIGHT = 10;
	protected final static int WIDE = 10;
	public static final double SECOND = 0.5;
	public static final double ZOOMSECOND = 0.5;
	//public static int score=0;
	public static IntegerProperty score = new SimpleIntegerProperty(0);
	public static StringProperty s_coins = new SimpleStringProperty("");
	public static StringProperty s_battle = new SimpleStringProperty("");
	public static DoubleProperty s_bar = new SimpleDoubleProperty(0);;
	
	protected static int erasedTimes = 1;
	protected static boolean isMoving = false;
	protected static int steps = Data.totalstpes;
	protected static String itemSelected = "null";
	
	protected static final int BLOCKBOUND = 666;
	protected static final int ITEMBOUND = 99;
	protected static final int STEPBOUND = 10086;
	protected static final int SCOREBOUND = 5201314;
	
	private static final int MAGIC_BIRD_SCORD = 500;  //使用魔力鸟所需的分数

	
	

	
	//private SimpleIntegerProperty scoreProperty=new SimpleIntegerProperty();
//    ChangeListener<? super EventHandler<ActionEvent>> listener =
//	null;
//	EventHandler<ActionEvent> eraseOnFinished = null;
	
	
	@FXML  void initialize(){

		//blockGridPan.setGridLinesVisible(true);
		//blockGridPan.set
		createBlocks();
		
		steps=Data.totalstpes;
		

		//在OK键后生效使用道具
		//steps += PackCtr.addedStep;
		//使用上一关购买的商品 （此方法只能满足于买下药品只能在下一关使用，所以被注释）
//		for(int i = 0;i<Shop.selectedList.size();i++)
//			steps = Shop.selectedList.get(i).addStep(steps);
//
//		for(int i = 0; i<Shop.selectedList.size();i++)
//			score = new SimpleIntegerProperty(Shop.selectedList.get(i).addScore(score.intValue()));
		
		
		//剧情模式
		if(Data.mode == 0){
			noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
			stepLabel.setLayoutX(980);
			s_battle.set("HP:"+steps*100);
			stepLabel.textProperty().bind(s_battle);
			s_bar.set((double) steps/Data.totalstpes);
			stepProgressBar.progressProperty().bind(s_bar);

		}
		//生日模式
		else if(Data.mode == 1){
			noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
			stepLabel.setText("Steps Left:"+steps);
		}
		//金币模式
		else if(Data.mode == 2){
			noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Stars:"+String.valueOf(score.intValue()/1000));
			stepLabel.setLayoutX(980);
			s_coins.set("Energy Value:"+steps*10);
			stepLabel.textProperty().bind(s_coins);
			s_bar.set((double) steps/Data.totalstpes);
			stepProgressBar.progressProperty().bind(s_bar);
		}
		//无尽模式
		else if(Data.mode == 3){
			noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
			stepLabel.setText("No steps limit!");
			s_bar.set(-1);
			stepProgressBar.progressProperty().bind(s_bar);
			stepLabel.setLayoutX(997);
		}
		
	}

	
//	@FXML void onExitBtnClick(){
//		//root.setVisible(false);
//		
//	}
	
	public  void createBlocks(){
		blockGridPan.setPrefWidth(600);
		blockGridPan.setPrefHeight(600);
		blockGridPan.setVgap(10);
		blockGridPan.setHgap(10);


		int x,y;
		
		for(int i=0;i<100;i++){
			x=i%10;
			y=i/10;
			
			createOneBlock(x,y);		
			BlockManager.setBlockBackgroundColor(BlockManager.blocks[x][y]);
			blockGridPan.add(BlockManager.blocks[x][y], x, y);	
		}
		BlockManager.resetArrays();
		
		//BlockManager.blocks.toArray(BlockManager.blocksList);
	}


	//创建一个block
	public  void createOneBlock(int x,int y){
		Block btn=new Block(x,y);
		btn.getStyleClass().add("block");
		btn.setOnMouseClicked(e->{
			if(isMoving == false){
				System.out.println("I have clicked");
				switch (itemSelected){
				case "null":
					Music.playEffectMusic(2);//click
					if(btn.getIsPressed()==false){		//之前没被点
						btn.setIsPressed(true);
						btn.setSelected();
						BlockManager.addBlocksToList(btn);//加入两个块的list中
						System.out.println(btn.getColor()+":"+btn.getX()+","+btn.getY());
					}
					else{								//之前被点了
						btn.setIsPressed(false);
						btn.setNotSelected();
						BlockManager.removeBlocksFromList(btn);//从两个块的list中移除
					}
					if(BlockManager.twoBlocks.size()==2){		//已经点了两个块了
						isMoving = true;
						if(BlockManager.isNear() == true){		//点的两个块相邻
							if(Data.mode != 3){
								steps--;//步数减1
								if(Data.mode == 0)
									s_battle.set("HP:"+steps*100);
								else if(Data.mode == 1)
									stepLabel.setText("Steps Left:"+steps);
								else if(Data.mode == 2)
									s_coins.set("Energy Value:"+steps*10);
								s_bar.set((double) steps/Data.totalstpes);
							}
		
							if(!(BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird")) && 
							   !(BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird"))){
								System.out.println("start exchanging");
								Transition transition = BlockManager.exchange();	//交换
								transition.setOnFinished(e2 ->{
									BlockManager.twoBlocks.get(0).setNotSelected();
									BlockManager.twoBlocks.get(1).setNotSelected();
									BlockManager.twoBlocks.get(0).setIsPressed(false);
									BlockManager.twoBlocks.get(1).setIsPressed(false);
									System.out.println("exchange done");
									if(BlockManager.erasable(BlockManager.twoBlocks.get(0))|BlockManager.erasable(BlockManager.twoBlocks.get(1))){
										BlockManager.twoBlocks.clear();
										erase();
							
									}
									else{
										
										Transition t = null;
										t = BlockManager.exchange();
										BlockManager.twoBlocks.clear();
										t.setOnFinished(e3 ->{
											if(Data.mode != 3)
												checkIsLose();
											
											isMoving = false;
											
										});
										BlockManager.resetArrays();
										
									}
								});
							}
							else if(BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") && 
									BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird")){	//全屏消
								Transition transition = BlockManager.exchange();	
								transition.setOnFinished(e2 ->{
									BlockManager.twoBlocks.get(0).setNotSelected();
									BlockManager.twoBlocks.get(1).setNotSelected();
									BlockManager.twoBlocks.get(0).setIsPressed(false);
									BlockManager.twoBlocks.get(1).setIsPressed(false);
//									BlockManager.twoBlocks.get(0).setSpecialType("null");
//									BlockManager.twoBlocks.get(1).setSpecialType("null");
									BlockManager.twoBlocks.clear();
									for(int i = 0; i < 10;i++)
										for(int j = 0; j< 10;j++){
											BlockManager.erased[BlockManager.length][0] = i;
											BlockManager.erased[BlockManager.length][1] = j;
											BlockManager.length++;
										}
									erase();
							
									
									
								});
								
							}
							else if((BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
									BlockManager.twoBlocks.get(1).getSpecialType().equals("null")) | 
									(BlockManager.twoBlocks.get(0).getSpecialType().equals("null") &&
									BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird"))){   //消掉全部相同颜色的
								Transition transition = BlockManager.exchange();	
								transition.setOnFinished(e2 ->{
									HashSet<Block> h = new HashSet<Block>();

									BlockManager.twoBlocks.get(0).setNotSelected();
									BlockManager.twoBlocks.get(1).setNotSelected();
									BlockManager.twoBlocks.get(0).setIsPressed(false);
									BlockManager.twoBlocks.get(1).setIsPressed(false);
									String color = BlockManager.twoBlocks.get(0).getColor();		//将要消掉的颜色
									if(BlockManager.twoBlocks.get(0).getColor().equals("MagicBird"))
										color = BlockManager.twoBlocks.get(1).getColor();

//									if(BlockManager.twoBlocks.get(0).getColor().equals("MagicBird")){
//										color = BlockManager.twoBlocks.get(1).getColor();
//										h.add(BlockManager.twoBlocks.get(0));
//									}else{
//										h.add(BlockManager.twoBlocks.get(1));
//									}

//									BlockManager.twoBlocks.get(0).setSpecialType("null");
//									BlockManager.twoBlocks.get(1).setSpecialType("null");
									BlockManager.twoBlocks.get(0).setColor(color);
									BlockManager.twoBlocks.get(1).setColor(color);
									BlockManager.twoBlocks.clear();



									for(int i = 0; i < 10;i++)
										for(int j = 0; j < 10;j++){
											if(BlockManager.blocks[i][j].getColor().equals(color)){
												h.add(BlockManager.blocks[i][j]);


											}
										}
									while(true){
							        	int size = h.size();
							        	h = BlockManager.checkLineSpecial(h);
							        	if(size == h.size())
							        		break;
							        }
									Iterator<Block> iterator = h.iterator();
									while(iterator.hasNext()){
										Block b = iterator.next();
										BlockManager.erased[BlockManager.length][0] = b.getX();
										BlockManager.erased[BlockManager.length][1] = b.getY();
										BlockManager.length++;
									}
									erase();
							
									
									
								});
								
								
								
							}
							else if((BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
									BlockManager.twoBlocks.get(1).getSpecialType().equals("horizon")) | 
									(BlockManager.twoBlocks.get(0).getSpecialType().equals("horizon") &&
									BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird"))){
								Transition transition = BlockManager.exchange();	
								transition.setOnFinished(e2 ->{
									BlockManager.twoBlocks.get(0).setNotSelected();
									BlockManager.twoBlocks.get(1).setNotSelected();
									BlockManager.twoBlocks.get(0).setIsPressed(false);
									BlockManager.twoBlocks.get(1).setIsPressed(false);
									int line = BlockManager.twoBlocks.get(0).getY();
									String color = BlockManager.twoBlocks.get(0).getColor();		//将要消掉的颜色
									if(BlockManager.twoBlocks.get(0).getColor().equals("MagicBird")){
										color = BlockManager.twoBlocks.get(1).getColor();
										line = BlockManager.twoBlocks.get(1).getY();
									}
//									BlockManager.twoBlocks.get(0).setSpecialType("null");
//									BlockManager.twoBlocks.get(1).setSpecialType("null");
									BlockManager.twoBlocks.get(0).setColor(color);
									BlockManager.twoBlocks.get(1).setColor(color);
									BlockManager.twoBlocks.clear();
									
									HashSet<Block> h = new HashSet<Block>();
									for(int i = 0; i < 10;i++)
										for(int j = 0; j < 10;j++){
											if(BlockManager.blocks[i][j].getColor().equals(color)){
												h.add(BlockManager.blocks[i][j]);
											}
										}
									for(int i = 0;i < 10;i++){
										h.add(BlockManager.blocks[i][line]);
									}
									while(true){
							        	int size = h.size();
							        	h = BlockManager.checkLineSpecial(h);
							        	if(size == h.size())
							        		break;
							        }
									Iterator<Block> iterator = h.iterator();
									while(iterator.hasNext()){
										Block b = iterator.next();
										BlockManager.erased[BlockManager.length][0] = b.getX();
										BlockManager.erased[BlockManager.length][1] = b.getY();
										BlockManager.length++;
									}
									erase();
							
									
									
								});
							}
							else if((BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
									BlockManager.twoBlocks.get(1).getSpecialType().equals("vertical")) | 
									(BlockManager.twoBlocks.get(0).getSpecialType().equals("vertical") &&
									BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird"))){
								Transition transition = BlockManager.exchange();	
								transition.setOnFinished(e2 ->{
									BlockManager.twoBlocks.get(0).setNotSelected();
									BlockManager.twoBlocks.get(1).setNotSelected();
									BlockManager.twoBlocks.get(0).setIsPressed(false);
									BlockManager.twoBlocks.get(1).setIsPressed(false);
									int line = BlockManager.twoBlocks.get(0).getX();
									String color = BlockManager.twoBlocks.get(0).getColor();		//将要消掉的颜色
									if(BlockManager.twoBlocks.get(0).getColor().equals("MagicBird")){
										color = BlockManager.twoBlocks.get(1).getColor();
										line = BlockManager.twoBlocks.get(1).getX();
									}
//									BlockManager.twoBlocks.get(0).setSpecialType("null");
//									BlockManager.twoBlocks.get(1).setSpecialType("null");
									BlockManager.twoBlocks.get(0).setColor(color);
									BlockManager.twoBlocks.get(1).setColor(color);
									BlockManager.twoBlocks.clear();
									
									HashSet<Block> h = new HashSet<Block>();
									for(int i = 0; i < 10;i++)
										for(int j = 0; j < 10;j++){
											if(BlockManager.blocks[i][j].getColor().equals(color)){
												h.add(BlockManager.blocks[i][j]);
											}
										}
									for(int i = 0;i < 10;i++){
										h.add(BlockManager.blocks[line][i]);
									}
									while(true){
							        	int size = h.size();
							        	h = BlockManager.checkLineSpecial(h);
							        	if(size == h.size())
							        		break;
							        }
									Iterator<Block> iterator = h.iterator();
									while(iterator.hasNext()){
										Block b = iterator.next();
										BlockManager.erased[BlockManager.length][0] = b.getX();
										BlockManager.erased[BlockManager.length][1] = b.getY();
										BlockManager.length++;
									}
									erase();
							
									
									
								});
							}
						}
						else{												//点的两个块不相邻
							Block b = BlockManager.twoBlocks.get(0);		//把第一个点的熄灭
							b.setIsPressed(false);
							b.setNotSelected();
							BlockManager.removeBlocksFromList(b);
							isMoving = false;
						}
					}
					break;
				case "SmallHammer":
					isMoving = true;
					if(Data.mode != 3){
						steps--;
						if(Data.mode == 0)
							s_battle.set("HP:"+steps*100);
						else if(Data.mode == 1)
							stepLabel.setText("Steps Left:"+steps);
						else if(Data.mode == 2)
							s_coins.set("Energy Value:"+steps*10);
						s_bar.set((double) steps/Data.totalstpes);
					}
					
					HashSet<Block> h2 = new HashSet<Block>();
					h2.add(btn);
					while(true){
			        	int size = h2.size();
			        	h2 = BlockManager.checkLineSpecial(h2);
			        	if(size == h2.size())
			        		break;
			        }
			        Iterator<Block> iterator2 = h2.iterator();
			        while(iterator2.hasNext()){
			        	Block b = iterator2.next();
			        	BlockManager.erased[BlockManager.length][0] = b.getX();
			        	BlockManager.erased[BlockManager.length][1] = b.getY();
			        	BlockManager.length++;
			        }
					
					Calculator.smallHammer++;
					if(Calculator.smallHammer >= ITEMBOUND)
						AchievementsManager.AchievementsList[1][0].setAchieved(true);
					else
						AchievementsManager.AchievementsList[1][0].setRate((double)Calculator.smallHammer/(double)ITEMBOUND);

					setToolNotSelected(smallHammer);
					score.set(score.intValue() - 20);		//使用小锤子技能要减20分
					itemSelected="null";
					erase();
					break;
				case "BigHammer":
					isMoving = true;
					if(Data.mode != 3){
						steps--;
						if(Data.mode == 0)
							s_battle.set("HP:"+steps*100);
						else if(Data.mode == 1)
							stepLabel.setText("Steps Left:"+steps);
						else if(Data.mode == 2)
							s_coins.set("Energy Value:"+steps*10);
						s_bar.set((double) steps/Data.totalstpes);
					}
					int i = btn.getX();
					int j = btn.getY();
					
					HashSet<Block> h = new HashSet<Block>();
					if(i >= 1){
						if(j >= 1)
							h.add(BlockManager.blocks[i-1][j-1]);
						h.add(BlockManager.blocks[i-1][j]);
						if(j < 9)
							h.add(BlockManager.blocks[i-1][j+1]);
					}
					if(j >= 1)
						h.add(BlockManager.blocks[i][j-1]);
					h.add(BlockManager.blocks[i][j]);
					if(j < 9)
						h.add(BlockManager.blocks[i][j+1]);
					if(i < 9){
						if(j >= 1)
							h.add(BlockManager.blocks[i+1][j-1]);
						h.add(BlockManager.blocks[i+1][j]);
						if(j < 9)
							h.add(BlockManager.blocks[i+1][j+1]);
					}
					
					while(true){
			        	int size = h.size();
			        	h = BlockManager.checkLineSpecial(h);
			        	if(size == h.size())
			        		break;
			        }
					
					Iterator<Block> iterator = h.iterator();
					while(iterator.hasNext()){
						Block block = iterator.next();
						BlockManager.erased[BlockManager.length][0] = block.getX();
						BlockManager.erased[BlockManager.length][1] = block.getY();
						BlockManager.length++;
					}
					Calculator.bigHammer++;
					if(Calculator.bigHammer >= ITEMBOUND)
						AchievementsManager.AchievementsList[1][1].setAchieved(true);
					else
						AchievementsManager.AchievementsList[1][1].setRate((double)Calculator.bigHammer/(double)ITEMBOUND);


					setToolNotSelected(bigHammer);
					score.set(score.intValue() - 200);		//使用大锤子技能减200分
					itemSelected="null";
					erase();
					break;
					
					//使用魔力棒将点击的块改变为一个特殊块儿，使用此技能减MAGIC_BIRD_SCORD分
				case"Magic":
					if(btn.getSpecialType().equals("MagicBird"))
						break;
					if(Data.mode != 3){
						steps--;
						if(Data.mode == 0)
							s_battle.set("HP:"+steps*100);
						else if(Data.mode == 1)
							stepLabel.setText("Steps Left:"+steps);
						else if(Data.mode == 2)
							s_coins.set("Energy Value:"+steps*10);
						s_bar.set((double) steps/Data.totalstpes);
					}
	        		blockGridPan.getChildren().remove(btn);
	        		
	        		createOneBlock(btn.getX(),btn.getY());
	        		Block specialBlock = BlockManager.blocks[btn.getX()][btn.getY()];
	        		String specialType = BlockManager.getBlockSpecialTypeRandom();
	        		specialBlock.setSpecialType(specialType);
	        		if(specialType.equals("MagicBird") || specialType.equals("Bomb"))
	        			specialBlock.setBackgroundColor(specialType);
	        		else{
	        			specialBlock.setBackgroundColor(btn.getColor());
	        			specialBlock.setPattern(specialType);
	        		}
	        		blockGridPan.add(specialBlock, specialBlock.getX(), specialBlock.getY());

	        		Calculator.magic++;
					if(Calculator.magic >= ITEMBOUND)
						AchievementsManager.AchievementsList[1][2].setAchieved(true);
					else
						AchievementsManager.AchievementsList[1][2].setRate((double)Calculator.magic/(double)ITEMBOUND);


	        		setToolNotSelected(magic);
					score.set(score.intValue() - MAGIC_BIRD_SCORD);		//使用魔力棒技能减500分
					if(Data.mode == 2){
						noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Coins:"+String.valueOf(score.intValue()/1000));

					}
					else if(Data.mode == 3){
						noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
					}
					else{
						noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
					}
					itemSelected="null";
					if(specialType.equals("Bomb")){
						isMoving = true;
					    bombExplode();
                    }
					break;

//				case 其他技能
					
				}//end of switch
			}
		});
		

		BlockManager.blocks[x][y]=btn;

	}
	
	
	//消除
	public  void erase(){

		//int temp=score.intValue()+ BlockManager.length*BlockManager.length*(erasedTimes++);
		score.set(score.intValue()+ BlockManager.length*BlockManager.length*(erasedTimes++));
		
		//改变记录值

		Calculator.steps += erasedTimes;
		if(Calculator.steps>= STEPBOUND)
			AchievementsManager.AchievementsList[1][3].setAchieved(true);
		AchievementsManager.AchievementsList[1][3].setRate((double)Calculator.steps/(double)STEPBOUND);



		if(Data.mode == 2) {
			noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Coins:"+String.valueOf(score.intValue()/1000));
		}
		else if(Data.mode == 3){
			noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
		}
		else{
			noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
		}

		//金币换算 + 测试金币
		coins += score.intValue()/1000;
		System.out.println("in gameWin coins = " + coins);
		shop.Shop.setCoinsCondition();

		 Music.playEffectMusic(1);//eliminate
		
		 System.out.println("start erasing");



		for(int i = 0;i < BlockManager.length;i++){

			Block block = BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]];
			if(block == null)
				continue;

			switch(block.getColor()){
			case("1"):
				Calculator.first++;

			if(Calculator.first >= BLOCKBOUND)
					AchievementsManager.AchievementsList[0][0].setAchieved(true);
				AchievementsManager.AchievementsList[0][0].setRate((double)Calculator.first/(double)BLOCKBOUND);
					

			break;
			case("2"):
				Calculator.second++;
			if(Calculator.second >= BLOCKBOUND)
				AchievementsManager.AchievementsList[0][1].setAchieved(true);
			AchievementsManager.AchievementsList[0][1].setRate((double)Calculator.second/(double)BLOCKBOUND);


			break;
			case("3"):
				Calculator.third++;
			if(Calculator.third >= BLOCKBOUND)
				AchievementsManager.AchievementsList[0][2].setAchieved(true);
			AchievementsManager.AchievementsList[0][2].setRate((double)Calculator.third/(double)BLOCKBOUND);


			break;
			case("4"):
				Calculator.fourth++;
			if(Calculator.fourth >= BLOCKBOUND)
				AchievementsManager.AchievementsList[0][3].setAchieved(true);
			AchievementsManager.AchievementsList[0][3].setRate((double)Calculator.fourth/(double)BLOCKBOUND);


			break;
			case("5"):
				Calculator.fifth++;
			if(Calculator.fifth >= BLOCKBOUND)
				AchievementsManager.AchievementsList[0][4].setAchieved(true);
			AchievementsManager.AchievementsList[0][4].setRate((double)Calculator.fifth/(double)BLOCKBOUND);


			break;
			}
			BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]] = null;
			
			final int iFinal = i;
			
			
			if(Data.mode == 2){
				
			
			block.setBackground(new Background(new BackgroundImage(
					new Image("gui/img/star/little.png"),
					BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.CENTER,
					BackgroundSize.DEFAULT
			)));
			block.setStyle("-fx-effect: null;");

	
			//消失的动画
	        FadeTransition transition = new FadeTransition(Duration.seconds(ZOOMSECOND),block);
	        transition.setFromValue(1);
	        transition.setToValue(0);
	        //变形动画
	        ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(ZOOMSECOND),block);
	        scaleTransition.setFromX(1);
	        scaleTransition.setToX(2);
			scaleTransition.setFromY(1);
			scaleTransition.setToY(2);

			//旋转动画
			RotateTransition rotateTransition=new RotateTransition(Duration.seconds(SECOND),block);
			rotateTransition.setFromAngle(0);
			rotateTransition.setToAngle(60);
			rotateTransition.setCycleCount(Timeline.INDEFINITE);
			rotateTransition.setAutoReverse(true);
			rotateTransition.play();
			//移动动画
			TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(SECOND),block);
			translateTransition.setByX((7-block.getX())*60);
			translateTransition.setByY((-1.3-block.getY())*60);
			translateTransition.play();

	        if(block.getSpecialType().equals("null")){		//不变成特效块
		        
	        	translateTransition.setOnFinished(e->{
	        		transition.play();
					scaleTransition.play();
					transition.setOnFinished(e2 -> {
						blockGridPan.getChildren().remove(block);
					});
		        		if(iFinal == BlockManager.length-1){
		        			descend();
		        		}
			        });
		    }
	        else if(block.getSpecialType().equals("MagicBird")){			//变成魔力鸟
	        	if(block.getColor().equals("MagicBird")){
	        		 
	        		translateTransition.setOnFinished(e->{
	        			transition.play();
						scaleTransition.play();
						transition.setOnFinished(e2 -> {
							blockGridPan.getChildren().remove(block);
						});
	 		        		if(iFinal == BlockManager.length-1){
	 		        			descend();
			        			
			        		}
	 			        });
	        	}
	        	else{	
		        	
	        		translateTransition.setOnFinished(e->{
			        		
	        			transition.play();
						scaleTransition.play();
						transition.setOnFinished(e2 -> {
							blockGridPan.getChildren().remove(block);
						});
			        		
			        		createOneBlock(block.getX(),block.getY());
			        		Block magicBirdBlock = BlockManager.blocks[block.getX()][block.getY()];
			        		magicBirdBlock.setSpecialType("MagicBird");
			        		magicBirdBlock.setBackgroundColor("MagicBird");
			        		blockGridPan.add(magicBirdBlock, magicBirdBlock.getX(), magicBirdBlock.getY());
			        		if(iFinal == BlockManager.length-1){
			        			descend();
			        		}
				        });
	        	}
	        }
	        else if(block.getSpecialType().equals("Bomb")){			//变成爆炸块

	        	translateTransition.setOnFinished(e->{
		        		
	        		transition.play();
					scaleTransition.play();
					transition.setOnFinished(e2 -> {
						blockGridPan.getChildren().remove(block);
					});
		        		
		        		createOneBlock(block.getX(),block.getY());
		        		Block bombBlock = BlockManager.blocks[block.getX()][block.getY()];
		        		bombBlock.setSpecialType("Bomb");
		        		bombBlock.setBackgroundColor("Bomb");
//		        		bombBlock.setBombColor("Bomb");
		        		blockGridPan.add(bombBlock, bombBlock.getX(),bombBlock.getY());
		        		
		        		if(iFinal == BlockManager.length-1){
		        			descend();
		        		}
			        });
	        }
	        else if(block.getSpecialType().equals("horizon")){
	        	if(block.getPattern().equals("horizon")){
	        		
	        		translateTransition.setOnFinished(e->{
	 		        		
	        			transition.play();
						scaleTransition.play();
						transition.setOnFinished(e2 -> {
							blockGridPan.getChildren().remove(block);
						});
	 		        		
						if(iFinal == BlockManager.length-1){
	 		        		descend();
			        	}
	 			    });
	        	}
	        	else{
	        		
	        		translateTransition.setOnFinished(e->{
			        		
	        			transition.play();
						scaleTransition.play();
						transition.setOnFinished(e2 -> {
							blockGridPan.getChildren().remove(block);
						});
			        		
			        		createOneBlock(block.getX(),block.getY());
			        		Block nb = BlockManager.blocks[block.getX()][block.getY()];
			        		nb.setSpecialType("horizon");
			        		nb.setBackgroundColor(block.getColor());
			        		nb.setPattern("horizon");
			        		blockGridPan.add(nb, nb.getX(), nb.getY());
			        		
			        		if(iFinal == BlockManager.length-1){
			        			descend();
			        		}
			        		
				        });
	        	}
	        }
	        else if(block.getSpecialType().equals("vertical")){
	        	if(block.getPattern().equals("vertical")){
	        		
	        		translateTransition.setOnFinished(e->{
	 		        		
	        			transition.play();
						scaleTransition.play();
						transition.setOnFinished(e2 -> {
							blockGridPan.getChildren().remove(block);
						});
	 		        		
	 		        		if(iFinal == BlockManager.length-1){
	 		        			descend();
			        		}
	 		        		
	 			        });
	        	}
	        	else{
	        		
	        		translateTransition.setOnFinished(e->{
			        		
	        			transition.play();
						scaleTransition.play();
						transition.setOnFinished(e2 -> {
							blockGridPan.getChildren().remove(block);
						});
			        		
			        		createOneBlock(block.getX(),block.getY());
			        		Block nb = BlockManager.blocks[block.getX()][block.getY()];
			        		nb.setSpecialType("vertical");
			        		nb.setBackgroundColor(block.getColor());
			        		nb.setPattern("vertical");
			        		blockGridPan.add(nb, nb.getX(), nb.getY());
			        		
			        		if(iFinal == BlockManager.length-1){
			        			descend();
			        			
			        		}
			        		
				        });
	        	}
	        }

			}
			else{
				//消失的动画
		        FadeTransition transition = new FadeTransition(Duration.seconds(SECOND),block);
		        transition.setFromValue(1);
		        transition.setToValue(0);
		        if(block.getSpecialType().equals("null")){		//不变成特效块
			        
			        	transition.setOnFinished(e->{
			        		
			        		blockGridPan.getChildren().remove(block);
			        		if(iFinal == BlockManager.length-1){
			        			descend();
			        		}
				        });
			    }
		        else if(block.getSpecialType().equals("MagicBird")){			//变成魔力鸟
		        	if(block.getColor().equals("MagicBird")){
		        		 
		 		        	transition.setOnFinished(e->{
		 		        		
		 		        		blockGridPan.getChildren().remove(block);
		 		        		if(iFinal == BlockManager.length-1){
		 		        			descend();
				        			
				        		}
		 			        });
		        	}
		        	else{	
			        	
				        	transition.setOnFinished(e->{
				        		
				        		blockGridPan.getChildren().remove(block);
				        		
				        		createOneBlock(block.getX(),block.getY());
				        		Block magicBirdBlock = BlockManager.blocks[block.getX()][block.getY()];
				        		magicBirdBlock.setSpecialType("MagicBird");
				        		magicBirdBlock.setBackgroundColor("MagicBird");
				        		blockGridPan.add(magicBirdBlock, magicBirdBlock.getX(), magicBirdBlock.getY());
				        		if(iFinal == BlockManager.length-1){
				        			descend();
				        		}
					        });
		        	}
		        }
		        else if(block.getSpecialType().equals("Bomb")){			//变成爆炸块
		        	
			        	transition.setOnFinished(e->{
			        		
			        		blockGridPan.getChildren().remove(block);
			        		
			        		createOneBlock(block.getX(),block.getY());
			        		Block bombBlock = BlockManager.blocks[block.getX()][block.getY()];
			        		bombBlock.setSpecialType("Bomb");
			        		bombBlock.setBackgroundColor("Bomb");
//			        		bombBlock.setBombColor("Bomb");
			        		blockGridPan.add(bombBlock, bombBlock.getX(),bombBlock.getY());
			        		
			        		if(iFinal == BlockManager.length-1){
			        			descend();
			        		}
				        });
		        }
		        else if(block.getSpecialType().equals("horizon")){
		        	if(block.getPattern().equals("horizon")){
		        		
		 		        	transition.setOnFinished(e->{
		 		        		
		 		        		blockGridPan.getChildren().remove(block);
		 		        		
		 		        		if(iFinal == BlockManager.length-1){
		 		        			descend();
				        		}
		 			        });
		        	}
		        	else{
		        		
				        	transition.setOnFinished(e->{
				        		
				        		blockGridPan.getChildren().remove(block);
				        		
				        		createOneBlock(block.getX(),block.getY());
				        		Block nb = BlockManager.blocks[block.getX()][block.getY()];
				        		nb.setSpecialType("horizon");
				        		nb.setBackgroundColor(block.getColor());
				        		nb.setPattern("horizon");
				        		blockGridPan.add(nb, nb.getX(), nb.getY());
				        		
				        		if(iFinal == BlockManager.length-1){
				        			descend();
				        		}
				        		
					        });
		        	}
		        }
		        else if(block.getSpecialType().equals("vertical")){
		        	if(block.getPattern().equals("vertical")){
		        		
		 		        	transition.setOnFinished(e->{
		 		        		
		 		        		blockGridPan.getChildren().remove(block);
		 		        		
		 		        		if(iFinal == BlockManager.length-1){
		 		        			descend();
				        		}
		 		        		
		 			        });
		        	}
		        	else{
		        		
				        	transition.setOnFinished(e->{
				        		
				        		blockGridPan.getChildren().remove(block);
				        		
				        		createOneBlock(block.getX(),block.getY());
				        		Block nb = BlockManager.blocks[block.getX()][block.getY()];
				        		nb.setSpecialType("vertical");
				        		nb.setBackgroundColor(block.getColor());
				        		nb.setPattern("vertical");
				        		blockGridPan.add(nb, nb.getX(), nb.getY());
				        		
				        		if(iFinal == BlockManager.length-1){
				        			descend();
				        			
				        		}
				        		
					        });
		        	}
		        }
		        
		        
		        transition.play();
			}

		}
			
	        
	}
	


	
	//下降
	public  void descend(){
		int[] columnX = new int[10];		//每一列消掉了几个

		for(int i = 0;i < 10;i++){
			columnX[i] = 0;
			for(int j = 0;j < 10;j++){
				if(BlockManager.blocks[i][j] == null)
					columnX[i]++;
			}
		}
		
		TranslateTransition transition = null;
		
		System.out.println("start descending");
		System.out.println("length  : "+BlockManager.length);

		
		for(int i = 0;i < 10;i++){		//处理第i列
			//第i列没有消除掉的
			if(columnX[i] == 0) 
				continue;
			
			//第i列有消除掉的
			
			//已有的块下降
			for(int j = WIDE - 1;j >= 0;j--){
				if(BlockManager.blocks[i][j] == null)
					continue;
				int dY = 0;
				for(int k = j + 1;k < WIDE;k++){
					if(BlockManager.blocks[i][k] == null)
						dY++;
				}
				if(dY != 0){
					int deltaY = dY * 60;
					
					transition = new TranslateTransition(Duration.seconds(SECOND),BlockManager.blocks[i][j]);
					transition.setByY(deltaY);
					BlockManager.blocks[i][j + dY] = BlockManager.blocks[i][j];
					BlockManager.blocks[i][j + dY].setPosition(i, j + dY);
					BlockManager.blocks[i][j] = null;
					
					BlockManager.blocks[i][j + dY].setDescended(true);
					transition.play();
				}
			}
			
				
			
			
			//产生新块并下降
			for(int j = columnX[i] - 1;j >= 0;j--){

				int deltYJ = j * 60;
				createOneBlock(i,j);
				BlockManager.setBlockColorWithoutCheck(BlockManager.blocks[i][j]);
				System.out.println("create it: "+i+" , "+j);
				blockGridPan.add(BlockManager.blocks[i][j], i, 0);
				System.out.println("show it");
				
				BlockManager.blocks[i][j].setDescended(true);
				
				transition = new TranslateTransition(Duration.seconds(SECOND),BlockManager.blocks[i][j]);
				transition.setByY(deltYJ);
				transition.play();
				System.out.println("new block descending");
				
				}
				
			
		}
			
		  

			//连续消除
			if(transition != null)
			transition.setOnFinished(e ->{
				System.out.println("descend done");
				BlockManager.resetArrays();
				if(BlockManager.check())
					erase();
				else{
					BlockManager.resetArrays();
					bombExplode();			//炸弹块爆炸
		
					
				}
			});

		
	}

	public void bombExplode(){
		HashSet<Block> h = new HashSet<Block>();
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				if(BlockManager.blocks[i][j].getSpecialType().equals("Bomb")){
					BlockManager.blocks[i][j].setSpecialType("null");
					if(i >= 2)
						h.add(BlockManager.blocks[i-2][j]);
					if(i >= 1){
						h.add(BlockManager.blocks[i-1][j]);
						if(j >= 1)
							h.add(BlockManager.blocks[i-1][j-1]);
						if(j < 9)
							h.add(BlockManager.blocks[i-1][j+1]);
					}
					h.add(BlockManager.blocks[i][j]);
					if(j >= 2)
						h.add(BlockManager.blocks[i][j-2]);
					if(j >= 1)
						h.add(BlockManager.blocks[i][j-1]);
					if(j < 9)
						h.add(BlockManager.blocks[i][j+1]);
					if(j < 8)
						h.add(BlockManager.blocks[i][j+2]);
					if(i < 9){
						h.add(BlockManager.blocks[i+1][j]);
						if(j >= 1)
							h.add(BlockManager.blocks[i+1][j-1]);
						if(j < 9)
							h.add(BlockManager.blocks[i+1][j+1]);
					}
					if(i < 8)
						h.add(BlockManager.blocks[i+2][j]);

						
				}
			}
		}
		
		if(h.isEmpty()){		//没有爆炸块
			erasedTimes = 1;
			System.out.println("everything finished");
			if(Data.mode != 3)
				checkIsLose();
			isMoving = false;
			return;
		}
		
		while(true){
        	int size = h.size();
        	h = BlockManager.checkLineSpecial(h);
        	if(size == h.size())
        		break;
        }
			
		
		Iterator<Block> iterator = h.iterator();
		while(iterator.hasNext()){
			Block block = iterator.next();
			BlockManager.erased[BlockManager.length][0] = block.getX();
			BlockManager.erased[BlockManager.length][1] = block.getY();
			BlockManager.length++;
		}
		
		erase();
		
		
	}
	
	
	public void checkIsLose(){
		if(score.intValue() >= Data.targetScore){
			Calculator.scores += score.intValue();
			if(Calculator.scores >= SCOREBOUND)
				AchievementsManager.AchievementsList[1][4].setAchieved(true);

			AchievementsManager.AchievementsList[1][4].setRate((double)Calculator.scores/(double)SCOREBOUND);


			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run(){
					Platform.runLater(()->{
						blockGridPan.getScene().getWindow().hide();
						new WarnWin(true);
						Data.warnNumber++;
					});
				}
			}, 1000);
			
			if(Data.mode == 0 && Data.chapterReached < 13){
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("src/gui/StoryMoodStore.txt"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Data.chapterReached++;
				try {
					bw.write("chapterReached:"+String.valueOf(Data.chapterReached));
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		else if(steps == 0){
			Calculator.scores += score.intValue();
			if(Calculator.scores >= SCOREBOUND)
				AchievementsManager.AchievementsList[1][4].setAchieved(true);

			AchievementsManager.AchievementsList[1][4].setRate((double)Calculator.scores/(double)SCOREBOUND);

			
			if(Data.mode == 2){
				
				coins = score.intValue();
				
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run(){
						Platform.runLater(()->{
							blockGridPan.getScene().getWindow().hide();;
							try {
								new ChapterSelectWin();
							} catch (IOException e) {
								e.printStackTrace();
							}
						});
					}
				}, 1000);

				
			}
			else{
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run(){
						Platform.runLater(()->{
							blockGridPan.getScene().getWindow().hide();
							new WarnWin(false);
							Data.warnNumber++;
						});
					}
					
				},1000);
			}
			
		}
		
		
	}


	public void onRestartBtnClick(ActionEvent actionEvent) {
		if(isMoving == false){
			Music.playEffectMusic(2);//click
			if(Data.mode == 3){
				Calculator.scores += score.intValue();
			if(Calculator.scores >= SCOREBOUND)
				AchievementsManager.AchievementsList[1][4].setAchieved(true);
			}
			blockGridPan.getChildren().clear();
			BlockManager.twoBlocks.clear();
			createBlocks();
	        noticeText.clear();
	        noticeText.setText("Restart!");
	       
			if(Data.mode != 3){
		        steps=Data.totalstpes;
		        if(Data.mode == 0)
					s_battle.set("HP:"+steps*100);
				else if(Data.mode == 1)
					stepLabel.setText("Steps Left:"+steps);
				else if(Data.mode == 2)
					s_coins.set("Energy Value:"+steps*10);
		        s_bar.set(1.0);
			}
	        score.set(0);
	        Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run(){
					Platform.runLater(()->{
						if(Data.mode == 2)
							noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Stars:"+String.valueOf(score.intValue()/1000));
						else if(Data.mode == 3){
							noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
						}
						else{
							noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
						}
					});
				}
				
			},1000);
		}
	}

	public void onSettingBtnClick(ActionEvent actionEvent) {
		Music.playEffectMusic(2);//click
		Platform.runLater(()->{
			try {
				new SettingWin();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void onPackBtnClick(ActionEvent actionEvent) {
		if(Data.mode == 0){
			Platform.runLater(()->{
				new Pack();
			});
		}	
	}
	
	public void onSmallHammerBtnClick(ActionEvent actionEvent) {
		
		if(isMoving == false){
			Music.playEffectMusic(2);//click
			if(score.intValue()<20) {
				noticeText.setText("Your score is inadequate!");
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run(){
						Platform.runLater(()->{
							if(Data.mode == 2)
								noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Stars:"+String.valueOf(score.intValue()/1000));
							else if(Data.mode == 3){
								noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
							}
							else{
								noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
							}
						});
					}
					
				},1000);
				return;
			}
			if(!BlockManager.twoBlocks.isEmpty()){
				Block b = BlockManager.twoBlocks.get(0);		//如果有，把之前点的块熄灭
				b.setIsPressed(false);
				b.setNotSelected();
				BlockManager.removeBlocksFromList(b);
			}
			
			switch (itemSelected){
			case "SmallHammer":
				setToolNotSelected(smallHammer);
				itemSelected = "null";
				break;
			case "BigHammer":
				setToolNotSelected(bigHammer);
				setToolSelected(smallHammer);
				itemSelected = "SmallHammer";
				break;
			case"Magic":
				setToolNotSelected(magic);
				setToolSelected(smallHammer);
				itemSelected = "SmallHammer";
				break;
//			case 其他技能
//				;
			case "null":
				setToolSelected(smallHammer);
				itemSelected = "SmallHammer";
				break;
			}
			
			
			
		}
		
	}
	
	public void onBigHammerBtnClick(ActionEvent actionEvent){
		
		if(isMoving == false){
			
			Music.playEffectMusic(2);//click
			if(score.intValue()<200) {
				noticeText.setText("Your score is inadequate!");
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run(){
						Platform.runLater(()->{
							if(Data.mode == 2)
								noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Stars:"+String.valueOf(score.intValue()/1000));
							else if(Data.mode == 3){
								noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
							}
							else{
								noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
							}						
						});
					}
					
				},1000);
				return;
			}
			
			if(!BlockManager.twoBlocks.isEmpty()){
				Block b = BlockManager.twoBlocks.get(0);		//如果有，把之前点的块熄灭
				b.setIsPressed(false);
				b.setNotSelected();
				BlockManager.removeBlocksFromList(b);
			}
			

			switch (itemSelected){
			case "BigHammer":
				setToolNotSelected(bigHammer);
				itemSelected = "null";
				break;
			case "SmallHammer":
				setToolNotSelected(smallHammer);
				setToolSelected(bigHammer);
				itemSelected = "BigHammer";
				break;
			case"Magic":
				setToolNotSelected(magic);
				setToolSelected(bigHammer);
				itemSelected = "BigHammer";
				break;
//			case 其他技能
//				,
			case "null":
				setToolSelected(bigHammer);
				itemSelected = "BigHammer";
				break;
			}	
				
		}
	}
	public void onMagicBtnClick(ActionEvent actionEvent){
			if(score.intValue() < MAGIC_BIRD_SCORD) {
				noticeText.setText("Your score is inadequate!");
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run(){
						Platform.runLater(()->{
							if(Data.mode == 2)
								noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Stars:"+String.valueOf(score.intValue()/1000));
							else if(Data.mode == 3){
								noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
							}
							else{
								noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
							}
						});
					}
					
				},1000);
				return;
			}
			if(isMoving == false){
				Music.playEffectMusic(2);//click
				if(!BlockManager.twoBlocks.isEmpty()){
					Block b = BlockManager.twoBlocks.get(0);		//如果有，把之前点的块熄灭
					b.setIsPressed(false);
					b.setNotSelected();
					BlockManager.removeBlocksFromList(b);
				}
				

				switch (itemSelected){
				case"Magic":
					setToolNotSelected(magic);
					itemSelected = "null";
					break;
				
				case "BigHammer":
					setToolNotSelected(bigHammer);
					setToolSelected(magic);
					itemSelected = "Magic";
					break;
				case "SmallHammer":
					setToolNotSelected(smallHammer);
					setToolSelected(magic);
					itemSelected = "Magic";
					break;
//				case 其他技能
//					,
				case "null":
					//这里加把大锤子按钮变亮
					setToolSelected(magic);
					itemSelected = "Magic";
					break;
				}	
			}
	}

	public void setToolSelected(Button tool){
		tool.setStyle("-fx-effect: dropshadow(gaussian, white, 8, 0.8, 0, 0)");
		ScaleTransition transition=new ScaleTransition(Duration.seconds(0.5),tool);
		transition.setFromX(1);
		transition.setFromY(1);
		transition.setToX(2);
		transition.setToY(2);
		transition.setCycleCount(2);
		transition.setAutoReverse(true);
		transition.play();
	}
	public void setToolNotSelected(Button tool){
		tool.setStyle("-fx-effect: null");
	}
	
}

		

	

	






