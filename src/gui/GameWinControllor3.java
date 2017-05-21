package gui;

import achievements.AchievementsManager;
import achievements.Billboard;
import achievements.Calculator;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
//import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**  
* 玩家点击四个块，其中前两个块相克，后两个块相克
* 相克的块不能交换
*   
*  
* @author Andy
* @version  
*/

public class GameWinControllor3 extends GameWinControllor
{
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
	public VBox slideVBox;
	ArrayList<Block> btns;

	
@Override
@FXML  void initialize(){

	//blockGridPan.setGridLinesVisible(true);
	//blockGridPan.set
	createBlocks();
	number=0;
	btns=new ArrayList<>();
	for(int i=0;i<4;i++){
		Block btn=new Block(-1,-1);
		btn.setPrefSize(50,50);
		btn.setStyle("-fx-background-color: transparent");
		btn.setMouseTransparent(true);
		btns.add(btn);
	}

	VBox vBox=new VBox(30);
	slideVBox.getChildren().add(vBox);

	for(int i=0;i<2;i++){
		HBox hBox=new HBox(20);
		hBox.setPadding(new Insets(10));
		hBox.setStyle("-fx-border-color: rgba(255,255,0,0.8);-fx-border-width: 2;-fx-border-radius: 15;");
		for(int j=i*2;j<i*2+2;j++){
			hBox.getChildren().add(btns.get(j));
		}
		vBox.getChildren().add(hBox);
	}


	
	steps=Data.totalstpes;
	score = new SimpleIntegerProperty(0);
//	adder2 = new SimpleIntegerProperty(0);
//	sumadder1 = new SimpleIntegerProperty(0);
//	sumadder2 = new SimpleIntegerProperty(0);
//	//使用上一关购买的商品
//	for(int i = 0;i<Shop.selectedList.size();i++)
//		steps = Shop.selectedList.get(i).addStep(steps);
//	
//	for(int i = 0; i<Shop.selectedList.size();i++)
//		score = new SimpleIntegerProperty(Shop.selectedList.get(i).addScore(score.intValue()));
//	
//	
//	//剧情模式
//	if(Data.mode == 0){
//		noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
//		stepLabel.setLayoutX(980);
//		stepLabel.setText("HP:"+steps*100);
//
//	}
//	//生日模式
//	else if(Data.mode == 1){
//		noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
//		stepLabel.setText("Steps Left:"+steps);
//	}
//	//金币模式
//	else if(Data.mode == 2){
//		noticeText.setText("      coins:      "+String.valueOf(score.intValue()));
//		stepLabel.setText("Energy Value:"+steps*10);
//
//	}
//	//无尽模式
//	else if(Data.mode == 3){
//		noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
//		stepLabel.setText("No steps limit!");
//		stepProgressBar.setProgress(-1);
//		stepLabel.setLayoutX(997);
//	}
	noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
	stepLabel.setText("Steps Left:"+steps);
}
@Override
public  void createOneBlock(int x,int y){
	Block btn=new Block(x,y);
	btn.getStyleClass().add("block");
	btn.setOnMouseClicked(e->{
		if(number == 0){
			btns.get(number).setBackgroundColor(btn.getColor());
			adder1block = btn.getColor();
			number++;
			System.out.println("adder1"+adder1block);
		}
		else if(number == 1){
			btns.get(number).setBackgroundColor(btn.getColor());
			adder2block = btn.getColor();
			number++;
			System.out.println("adder2"+adder2block);
		}
		else if(number == 2){
			btns.get(number).setBackgroundColor(btn.getColor());
			sumadder1block = btn.getColor();
			number++;		
			System.out.println("sumadder1"+sumadder1block);
		}
		else if(number == 3){
			btns.get(number).setBackgroundColor(btn.getColor());
			sumadder2block = btn.getColor();
			number++;
			System.out.println("sumadder2"+sumadder2block);
		}
		
	else if(isMoving == false && (( number == 4))){
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
					if((BlockManager.isNear() == true) 
							&&(!(BlockManager.twoBlocks.get(0).getColor().equals(adder1block)
									&&BlockManager.twoBlocks.get(1).getColor().equals(adder2block)))
							&&(!(BlockManager.twoBlocks.get(0).getColor().equals(sumadder1block)
							&&BlockManager.twoBlocks.get(1).getColor().equals(sumadder2block)))
							&&(!(BlockManager.twoBlocks.get(0).getColor().equals(adder2block)
									&&BlockManager.twoBlocks.get(1).getColor().equals(adder1block)))
							&&(!(BlockManager.twoBlocks.get(0).getColor().equals(sumadder2block)
							&&BlockManager.twoBlocks.get(1).getColor().equals(sumadder1block))))

							
							{		//点的两个块相邻
						if(Data.mode != 3){
							steps--;//步数减1
							if(Data.mode == 0)
								stepLabel.setText("Health Point:"+steps*100);
							else if(Data.mode == 1)
								stepLabel.setText("Steps Left:"+steps);
							else if(Data.mode == 2)
								stepLabel.setText("Energy Value:"+steps*10);
							stepProgressBar.setProgress((double) steps/Data.totalstpes);
						}
	
						if(BlockManager.twoBlocks.get(0).getSpecialType().equals("null") && 
						   BlockManager.twoBlocks.get(1).getSpecialType().equals("null")){
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
								BlockManager.twoBlocks.get(0).setSpecialType("null");
								BlockManager.twoBlocks.get(1).setSpecialType("null");
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
								BlockManager.twoBlocks.get(0).setNotSelected();
								BlockManager.twoBlocks.get(1).setNotSelected();
								BlockManager.twoBlocks.get(0).setIsPressed(false);
								BlockManager.twoBlocks.get(1).setIsPressed(false);
								String color = BlockManager.twoBlocks.get(0).getColor();		//将要消掉的颜色
								if(BlockManager.twoBlocks.get(0).getColor().equals("MagicBird"))
									color = BlockManager.twoBlocks.get(1).getColor();
								BlockManager.twoBlocks.get(0).setSpecialType("null");
								BlockManager.twoBlocks.get(1).setSpecialType("null");
								BlockManager.twoBlocks.get(0).setColor(color);
								BlockManager.twoBlocks.get(1).setColor(color);
								BlockManager.twoBlocks.clear();
								for(int i = 0; i < 10;i++)
									for(int j = 0; j < 10;j++){
										if(BlockManager.blocks[i][j].getColor().equals(color)){
											BlockManager.erased[BlockManager.length][0] = i;
											BlockManager.erased[BlockManager.length][1] = j;
											BlockManager.length++;
										}
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
						stepLabel.setText("Health Point:"+steps*100);
					else if(Data.mode == 1)
						stepLabel.setText("Steps Left:"+steps);
					else if(Data.mode == 2)
						stepLabel.setText("Energy Value:"+steps*10);
					stepProgressBar.setProgress((double) steps/Data.totalstpes);
				}
				BlockManager.erased[0][0] = btn.getX();
				BlockManager.erased[0][1] = btn.getY();
				BlockManager.length = 1;
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
						stepLabel.setText("Health Point:"+steps*100);
					else if(Data.mode == 1)
						stepLabel.setText("Steps Left:"+steps);
					else if(Data.mode == 2)
						stepLabel.setText("Energy Value:"+steps*10);
					stepProgressBar.setProgress((double) steps/Data.totalstpes);
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
				
				//使用魔力棒将点击的块改变为一个特殊块儿，使用此技能减500分
			case"Magic":
				if(Data.mode != 3){
					steps--;
					if(Data.mode == 0)
						stepLabel.setText("Health Point:"+steps*100);
					else if(Data.mode == 1)
						stepLabel.setText("Steps Left:"+steps);
					else if(Data.mode == 2)
						stepLabel.setText("Energy Value:"+steps*10);
					stepProgressBar.setProgress((double) steps/Data.totalstpes);
				}
        		blockGridPan.getChildren().remove(btn);
        		
        		createOneBlock(btn.getX(),btn.getY());
        		Block specialBlock = BlockManager.blocks[btn.getX()][btn.getY()];
        		String specialType = BlockManager.getBlockSpecialTypeRandom();
        		specialBlock.setSpecialType(specialType);
        		specialBlock.setBackgroundColor(specialType);
        		blockGridPan.add(specialBlock, specialBlock.getX(), specialBlock.getY());

        		Calculator.magic++;
				if(Calculator.magic >= ITEMBOUND)
					AchievementsManager.AchievementsList[1][2].setAchieved(true);
				else
					AchievementsManager.AchievementsList[1][2].setRate((double)Calculator.magic/(double)ITEMBOUND);


        		setToolNotSelected(magic);
				score.set(score.intValue() - 500);		//使用魔力棒技能减500分
				if(Data.mode == 2){
					noticeText.setText("      coins:      "+String.valueOf(score.intValue()));

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

//			case 其他技能
				
			}//end of switch
		}
	});
	

	BlockManager.blocks[x][y]=btn;

}
@Override
public  void erase(){
	
	//int temp=score.intValue()+ BlockManager.length*BlockManager.length*(erasedTimes++);
//	score.set(score.intValue()+ BlockManager.length*BlockManager.length*(erasedTimes++));
	
	//改变记录值
	score.set(score.intValue()+ BlockManager.length*BlockManager.length*(erasedTimes++));
	Calculator.steps += erasedTimes;
	if(Calculator.steps>= STEPBOUND)
		AchievementsManager.AchievementsList[1][3].setAchieved(true);
	else
			AchievementsManager.AchievementsList[1][3].setRate((double)Calculator.steps/(double)STEPBOUND);

		noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
	
	
	 Music.playEffectMusic(1);//eliminate
	
	 System.out.println("start erasing");
	 
	for(int i = 0;i < BlockManager.length;i++){
		
		Block block = BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]];
		
			switch(block.getColor()){
		case("1"):
			Calculator.first++;

		if(Calculator.first >= BLOCKBOUND)
				AchievementsManager.AchievementsList[0][0].setAchieved(true);
		else
			AchievementsManager.AchievementsList[0][0].setRate((double)Calculator.first/(double)BLOCKBOUND);
				

		break;
		case("2"):
			Calculator.second++;
		if(Calculator.second >= BLOCKBOUND)
			AchievementsManager.AchievementsList[0][1].setAchieved(true);
		else
			AchievementsManager.AchievementsList[0][1].setRate((double)Calculator.second/(double)BLOCKBOUND);


		break;
		case("3"):
			Calculator.third++;
		if(Calculator.third >= BLOCKBOUND)
			AchievementsManager.AchievementsList[0][2].setAchieved(true);
		else
				AchievementsManager.AchievementsList[0][2].setRate((double)Calculator.third/(double)BLOCKBOUND);


		break;
		case("4"):
			Calculator.fourth++;
		if(Calculator.fourth >= BLOCKBOUND)
			AchievementsManager.AchievementsList[0][3].setAchieved(true);
		else
			AchievementsManager.AchievementsList[0][3].setRate((double)Calculator.fourth/(double)BLOCKBOUND);


		break;
		case("5"):
			Calculator.fifth++;
		if(Calculator.fifth >= BLOCKBOUND)
			AchievementsManager.AchievementsList[0][4].setAchieved(true);
		else
			AchievementsManager.AchievementsList[0][4].setRate((double)Calculator.fifth/(double)BLOCKBOUND);


		break;
		}
		BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]] = null;
		//消失的动画
        FadeTransition transition = new FadeTransition(Duration.seconds(SECOND),block);
        transition.setFromValue(1);
        transition.setToValue(0);
        if(block.getSpecialType().equals("null")){		//不变成特效块
	        if(i == BlockManager.length - 1)
		        transition.setOnFinished(e->{
		        	blockGridPan.getChildren().remove(block);
	
		        	 descend();
		          
		        });
	        
	        else
	        	transition.setOnFinished(e->{
	        		
	        		blockGridPan.getChildren().remove(block);
	        		
		        });
	    }
        else if(block.getSpecialType().equals("MagicBird")){			//变成魔力鸟
        	if(i == BlockManager.length - 1)
		        transition.setOnFinished(e->{
		        	blockGridPan.getChildren().remove(block);
		        	
		        	createOneBlock(block.getX(),block.getY());
	        		Block magicBirdBlock = BlockManager.blocks[block.getX()][block.getY()];
	        		magicBirdBlock.setSpecialType("MagicBird");
	        		magicBirdBlock.setBackgroundColor("MagicBird");
	        		blockGridPan.add(magicBirdBlock, magicBirdBlock.getX(), magicBirdBlock.getY());
	        		
	        		
		        	 descend();
		          
		        });
	        
	        else
	        	transition.setOnFinished(e->{
	        		
	        		blockGridPan.getChildren().remove(block);
	        		
	        		createOneBlock(block.getX(),block.getY());
	        		Block magicBirdBlock = BlockManager.blocks[block.getX()][block.getY()];
	        		magicBirdBlock.setSpecialType("MagicBird");
	        		magicBirdBlock.setBackgroundColor("MagicBird");
	        		blockGridPan.add(magicBirdBlock, magicBirdBlock.getX(), magicBirdBlock.getY());
	        		
		        });
        }
        else if(block.getSpecialType().equals("Bomb")){			//变成爆炸块
        	if(i == BlockManager.length - 1)
		        transition.setOnFinished(e->{
		        	blockGridPan.getChildren().remove(block);
		        	
		        	createOneBlock(block.getX(),block.getY());
	        		Block bombBlock = BlockManager.blocks[block.getX()][block.getY()];
	        		bombBlock.setSpecialType("Bomb");
	        		bombBlock.setBackgroundColor("Bomb");
//	        		bombBlock.setBombColor("Bomb");
	        		blockGridPan.add(bombBlock, bombBlock.getX(),bombBlock.getY());
	        		
	        		
		        	 descend();
		          
		        });
	        
	        else
	        	transition.setOnFinished(e->{
	        		
	        		blockGridPan.getChildren().remove(block);
	        		
	        		createOneBlock(block.getX(),block.getY());
	        		Block bombBlock = BlockManager.blocks[block.getX()][block.getY()];
	        		bombBlock.setSpecialType("Bomb");
	        		bombBlock.setBackgroundColor("Bomb");
//	        		bombBlock.setBombColor("Bomb");
	        		blockGridPan.add(bombBlock, bombBlock.getX(),bombBlock.getY());
	        		
		        });
        }
        
        transition.play();
        
	}
//	        ChangeListener<? super EventHandler<ActionEvent>> listener =
//		null;
//        eraseOnFinished = transition.getOnFinished();
       
        	
        
}
@Override
public void onRestartBtnClick(ActionEvent actionEvent) {
	if(isMoving == false){
		Music.playEffectMusic(2);//click
		if(Data.mode == 3){
			Calculator.scores += score.intValue();
		if(Calculator.scores >= SCOREBOUND)
			AchievementsManager.AchievementsList[1][4].setAchieved(true);
		}
		blockGridPan.getChildren().clear();
		createBlocks();
		number=0;
		for(Block  block : btns){
			block.setStyle("-fx-background-color: transparent;-fx-background-image: null;");
		}

        //noticeText.clear();
        noticeText.setText("Restart!");
       
		if(Data.mode != 3){
	        steps=Data.totalstpes;
//	        if(Data.mode == 0)
//				stepLabel.setText("Health Point:"+steps*100);
//			else if(Data.mode == 1)
//				stepLabel.setText("Steps Left:"+steps);
//			else if(Data.mode == 2)
//				stepLabel.setText("Energy Value:"+steps*10);
	        stepLabel.setText("Steps Left:"+steps);
	        stepProgressBar.setProgress(1.0);
		}
        score = new SimpleIntegerProperty(0);
	}

}
@Override
public void checkIsLose(){
	if(score.intValue()>=Data.targetScore){
//		Calculator.scores += score.intValue();
//		if(Calculator.scores >= SCOREBOUND)
//			AchievementsManager.AchievementsList[1][4].setAchieved(true);
//		else
//			AchievementsManager.AchievementsList[1][4].setRate((double)Calculator.scores/(double)ITEMBOUND);
//
//
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
	}
		

	else if(steps == 0){

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

	public void onExitBtnClick(ActionEvent actionEvent) {
		Calculator.scores += GameWinControllor.score.intValue();
//		number=0;
		Music.stopBgMusic();
		Platform.runLater(()->{
			switch (Data.mode){
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

					Billboard.scorelist[Billboard.RANK].setScore(GameWinControllor.score.intValue());
					String str = (new SimpleDateFormat("yyyy-MM-dd")).format(Calendar.getInstance().getTime());


					Billboard.scorelist[Billboard.RANK].setTime(str);

					Arrays.sort(Billboard.scorelist);
					for(int i = 0 ; i < Billboard.RANK+1 ; i++)
						System.out.println(Billboard.scorelist[i]);

					Billboard.setBillboardCondition();

					new MainWin();
					break;
			}
			blockGridPan.getScene().getWindow().hide();
		});

	}
}