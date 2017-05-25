package gui;

import java.util.*;

import achievements.AchievementsManager;
import achievements.Calculator;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**  
* 玩家点击四种块分别作为两个加数和和中的两个加数
* 等式平衡且各项都不为零时游戏结束
*   
*  
* @author Andy
* @version  
*/

public class GameWinControllor2 extends GameWinControllor
{
	public IntegerProperty adder1;
	public IntegerProperty adder2;
	public IntegerProperty sumadder1;
	public IntegerProperty sumadder2;
	
	public String adder1block;
	public String adder2block;
	public String sumadder1block;
	public String sumadder2block;

	//选中了的数目
	private int number = 0;
	ArrayList<String> selectedBlocksColor=new ArrayList<>();
	Block[] selectedBlock=new Block[4];//被选中的方块
	public VBox slide;

	@Override
@FXML  void initialize(){

	//blockGridPan.setGridLinesVisible(true);
	//blockGridPan.set
	createBlocks();
	
	steps=Data.totalstpes;
	adder1 = new SimpleIntegerProperty(0);
	adder2 = new SimpleIntegerProperty(0);
	sumadder1 = new SimpleIntegerProperty(0);
	sumadder2 = new SimpleIntegerProperty(0);

//	one=new Block(-1,-1);
//	two=new Block(-1,-1);
//	Block three=new Block(-1,-1);
//	Block four=new Block(-1,-1);
		for(int i=0;i<4;i++){
			selectedBlock[i]=new Block(-1,-1);
			selectedBlock[i].setStyle("-fx-background-color: transparent;");
			selectedBlock[i].setMouseTransparent(true);//对鼠标动作无反应
		}

	VBox selectedBox=new VBox();
		selectedBox.setAlignment(Pos.CENTER);
	selectedBox.setSpacing(10);
	selectedBox.setPadding(new Insets(10));
	selectedBox.setStyle("-fx-border-width: 3;-fx-border-color: white;-fx-border-radius: 15;");

	HBox left=new HBox();
		Label addSign1=new Label("+");
		addSign1.getStyleClass().add("sign");
	left.getChildren().addAll(selectedBlock[0],addSign1,selectedBlock[1]);

	Label equalSign=new Label("=");
	equalSign.getStyleClass().add("sign");

		HBox right=new HBox();
		Label addSign2=new Label("+");
		addSign2.getStyleClass().add("sign");
		right.getChildren().addAll(selectedBlock[2],addSign2,selectedBlock[3]);

		selectedBox.getChildren().addAll(left,equalSign,right);
		slide.getChildren().add(selectedBox);

	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
	stepLabel.setText("Steps Left:"+steps);
}
@Override
public  void createOneBlock(int x,int y){
	Block btn=new Block(x,y);
	btn.getStyleClass().add("block");
	btn.setOnMouseClicked(e->{
		if(number == 0){
			adder1block = btn.getColor();
			if(!selectedBlocksColor.contains(adder1block)){
				selectedBlocksColor.add(adder1block);
				selectedBlock[number].setBackgroundColor(adder1block);
				number++;
				System.out.println("adder1"+adder1block);
			}
		}
		else if(number == 1){
			adder2block = btn.getColor();
			if(!selectedBlocksColor.contains(adder2block)){
				selectedBlocksColor.add(adder2block);
				selectedBlock[number].setBackgroundColor(adder2block);
				number++;
				System.out.println("adder2"+adder2block);
			}
		}
		else if(number == 2){
			sumadder1block = btn.getColor();
			if(!selectedBlocksColor.contains(sumadder1block)){
				selectedBlocksColor.add(sumadder1block);
				selectedBlock[number].setBackgroundColor(sumadder1block);
				number++;
				System.out.println("sumadder1block"+sumadder1block);
			}
		}
		else if(number == 3){
			sumadder2block = btn.getColor();
			if(!selectedBlocksColor.contains(sumadder2block)){
				selectedBlocksColor.add(sumadder2block);
				selectedBlock[number].setBackgroundColor(sumadder2block);
				number++;
				System.out.println("sumadder2block"+sumadder2block);
			}
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
					if(BlockManager.isNear() == true){		//点的两个块相邻
						if(Data.mode != 3){
							steps--;//步数减1
							if(Data.mode == 0)
								stepLabel.setText("HP:"+steps*100);
							else if(Data.mode == 1)
								stepLabel.setText("Steps Left:"+steps);
							else if(Data.mode == 2)
								stepLabel.setText("Energy Value:"+steps*10);
							stepProgressBar.setProgress((double) steps/Data.totalstpes);
						}
	
						if(!BlockManager.twoBlocks.get(0).getSpecialType().equals("MagicBird") &&
						   !BlockManager.twoBlocks.get(1).getSpecialType().equals("MagicBird")){
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
                                HashSet<Block> h = new HashSet<Block>();
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
                                        BlockManager.twoBlocks.get(0).setSpecialType("null");
                                        BlockManager.twoBlocks.get(1).setSpecialType("null");
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
                                        BlockManager.twoBlocks.get(0).setSpecialType("null");
                                        BlockManager.twoBlocks.get(1).setSpecialType("null");
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
						stepLabel.setText("HP:"+steps*100);
					else if(Data.mode == 1)
						stepLabel.setText("Steps Left:"+steps);
					else if(Data.mode == 2)
						stepLabel.setText("Energy Value:"+steps*10);
					stepProgressBar.setProgress((double) steps/Data.totalstpes);
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
				
				//使用魔力棒将点击的块改变为一个特殊块儿，使用此技能减500分
			case"Magic":
				if(btn.getSpecialType().equals("MagicBird"))
					break;
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

	Calculator.steps += erasedTimes;
	if(Calculator.steps>= STEPBOUND)
		AchievementsManager.AchievementsList[1][3].setAchieved(true);
	else
			AchievementsManager.AchievementsList[1][3].setRate((double)Calculator.steps/(double)STEPBOUND);

	
	 Music.playEffectMusic(1);//eliminate
	
	 System.out.println("start erasing");
	 
	for(int i = 0;i < BlockManager.length;i++){
		
		Block block = BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]];
		
		if(block.getColor().equals(adder1block))
			adder1.set(adder1.intValue()+1);
		if(block.getColor().equals(adder2block))
		adder2.set(adder2.intValue()+1);
		if(block.getColor().equals(sumadder1block))
		sumadder1.set(sumadder1.intValue()+1);
		if(block.getColor().equals(sumadder2block))
		sumadder2.set(sumadder2.intValue()+1);
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
        final int iFinal = i;
      //消失的动画
        FadeTransition transition = new FadeTransition(Duration.seconds(SECOND),block);
        transition.setFromValue(1);
        transition.setToValue(0);
        if(block.getSpecialType().equals("null")){		//不变成特效块
            
            transition.setOnFinished(e->{
                
                blockGridPan.getChildren().remove(block);
                if(iFinal == BlockManager.length-1){
                	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
                    descend();
                    
                }
            });
        }
        else if(block.getSpecialType().equals("MagicBird")){			//变成魔力鸟
            if(block.getColor().equals("MagicBird")){
                
                transition.setOnFinished(e->{
                    
                    blockGridPan.getChildren().remove(block);
                    if(iFinal == BlockManager.length-1){
                    	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
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
                    	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
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
                //		        		bombBlock.setBombColor("Bomb");
                blockGridPan.add(bombBlock, bombBlock.getX(),bombBlock.getY());
                
                if(iFinal == BlockManager.length-1){
                	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
                    descend();
                }
            });
        }
        else if(block.getSpecialType().equals("horizon")){
            if(block.getPattern().equals("horizon")){
                
                transition.setOnFinished(e->{
                    
                    blockGridPan.getChildren().remove(block);
                    
                    if(iFinal == BlockManager.length-1){
                    	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
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
                    	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
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
                    	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
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
                    	noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
                        descend();
                        
                    }
                    
                });
            }
        }
        
        
        transition.play();
        
	}
        	
        
}


@Override
public void onSmallHammerBtnClick(ActionEvent actionEvent){
	if(isMoving == false){
		noticeText.setText("Can not use it in this mode!");
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				Platform.runLater(()->{
					noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
				});
			}
			
		},1000);
	}
}

@Override
public void onBigHammerBtnClick(ActionEvent actionEvent){
	if(isMoving == false){
		noticeText.setText("Can not use it in this mode!");
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				Platform.runLater(()->{
					noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
				});
			}
			
		},1000);
	}
}

@Override
public void onMagicBtnClick(ActionEvent actionEvent){
	if(isMoving == false){
		noticeText.setText("Can not use it in this mode!");
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				Platform.runLater(()->{
					noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
				});
			}
			
		},1000);
	}
}



@Override
public void onRestartBtnClick(ActionEvent actionEvent) {
	if(isMoving == false){
		Music.playEffectMusic(2);//click
//		if(Data.mode == 3){
//			Calculator.scores += score.intValue();
//		if(Calculator.scores >= SCOREBOUND)
//			AchievementsManager.AchievementsList[1][4].setAchieved(true);
//		}
		blockGridPan.getChildren().clear();
		BlockManager.twoBlocks.clear();
		createBlocks();
        noticeText.clear();
        noticeText.setText("Restart!");
        Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				Platform.runLater(()->{
					noticeText.setText(adder1.intValue()+"+"+adder2.intValue()+"="+sumadder1.intValue()+"+"+sumadder2.intValue());
				});
			}
			
		},1000);
       
	        steps=Data.totalstpes;
	        stepLabel.setText("Steps Left:"+steps);
	        stepProgressBar.setProgress(1.0);

	    adder1 = new SimpleIntegerProperty(0);
        adder2 = new SimpleIntegerProperty(0);
        sumadder1 = new SimpleIntegerProperty(0);
        sumadder2 = new SimpleIntegerProperty(0);
        
        for(int i=0;i<4;i++){
			selectedBlock[i].setStyle("-fx-background-color: transparent;-fx-background-image: null;");
		}
        number = 0;
        selectedBlocksColor.clear();
	}

}
@Override
public void checkIsLose(){
	if(adder1.intValue()+adder2.intValue()==sumadder1.intValue()+sumadder2.intValue()
			&&adder1.intValue() != 0
			&&adder2.intValue() != 0
			&&sumadder1.intValue() != 0
			&&sumadder2.intValue() != 0){
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
}
