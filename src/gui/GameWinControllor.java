package gui;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import javax.jws.Oneway;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class GameWinControllor {
	public static BlockManager BlockManager = new BlockManager();
	public Label stepLabel;
	public ProgressBar stepProgressBar;
	public Button smallHammer;
	public Button bigHammer;
	public Button magic;
	@FXML private GridPane blockGridPan;
	@FXML private AnchorPane root;
	@FXML private TextField noticeText;
	private final static int HEIGHT = 10;
	private final static int WIDE = 10;
	public static final double SECOND = 0.5;
	//public static int score=0;
	private static IntegerProperty score;
	private static int erasedTimes = 1;
	private static boolean isMoving = false;
	private static int steps=Data.totalstpes;
	private static String itemSelected = "null";

	//private SimpleIntegerProperty scoreProperty=new SimpleIntegerProperty();
//    ChangeListener<? super EventHandler<ActionEvent>> listener =
//	null;
//	EventHandler<ActionEvent> eraseOnFinished = null;
	
	
	@FXML  void initialize(){

		//blockGridPan.setGridLinesVisible(true);
		//blockGridPan.set
		createBlocks();
		
		steps=Data.totalstpes;
		score = new SimpleIntegerProperty(0);
		if(Data.order == 12){
			noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
			stepLabel.setText("No steps limit!");
			stepProgressBar.setProgress(-1);
		}
		else{
			noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
			stepLabel.setText("Steps Left:"+steps);
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
							if(Data.order != 12){
								steps--;//步数减1
								
								stepLabel.setText("Steps Left:"+steps);
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
											if(Data.order != 12)
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
					if(Data.order != 12){
						steps--;
					}
					BlockManager.erased[0][0] = btn.getX();
					BlockManager.erased[0][1] = btn.getY();
					BlockManager.length = 1;
					//把小锤子按钮熄灭
					setToolNotSelected(smallHammer);
					score.set(score.intValue() - 20);		//使用小锤子技能要减20分
					itemSelected="null";
					erase();
					break;
				case "BigHammer":
					if(Data.order != 12){
						steps--;
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
					//把小锤子熄灭
					setToolNotSelected(bigHammer);
					score.set(score.intValue() - 200);		//使用大锤子技能减200分
					itemSelected="null";
					erase();
					break;
					
					//使用魔力棒将点击的块改变为一个特殊块儿，使用此技能减250分
				case"Magic":
					if(Data.order != 12){
						steps--;
					}
	        		blockGridPan.getChildren().remove(btn);
	        		
	        		createOneBlock(btn.getX(),btn.getY());
	        		Block specialBlock = BlockManager.blocks[btn.getX()][btn.getY()];
	        		String specialType = BlockManager.getBlockSpecialTypeRandom();
	        		specialBlock.setSpecialType(specialType);
	        		specialBlock.setBackgroundColor(specialType);
	        		blockGridPan.add(specialBlock, specialBlock.getX(), specialBlock.getY());

					setToolNotSelected(magic);
					score.set(score.intValue() - 250);		//使用魔力棒技能减250分
					itemSelected="null";
					if(specialType.equals("Bomb")){
					    bombExplode();
                    }
					
//					erase();
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

		if(Data.order == 12){
			noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
		}
		else{
			noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
		}
		
		 Music.playEffectMusic(1);//eliminate
		
		 System.out.println("start erasing");
		 
		for(int i = 0;i < BlockManager.length;i++){
			Block block = BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]];
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
//		        		bombBlock.setBombColor("Bomb");
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
//		        		bombBlock.setBombColor("Bomb");
		        		blockGridPan.add(bombBlock, bombBlock.getX(),bombBlock.getY());
		        		
			        });
	        }
	        
	        transition.play();
	        
		}
//	        ChangeListener<? super EventHandler<ActionEvent>> listener =
//			null;
//	        eraseOnFinished = transition.getOnFinished();
	       
	        	
	        
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
			
		
//		Arrays.fill(theUpperOne, 10);
//		Arrays.fill(columnX,0);
//		System.out.println("length: "+BlockManager.length);
//		
//		for(int j = 0;j < BlockManager.length;j++){		
//			columnX[BlockManager.erased[j][0]]++;	
////			if(BlockManager.erased[j][1] < theUpperOne[BlockManager.erased[j][0]]){
////				theUpperOne[BlockManager.erased[j][0]] = BlockManager.erased[j][1];
////			}
//		}
		
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
			if(Data.order != 12)
				checkIsLose();
			isMoving = false;
			return;
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
	
	
	private void checkIsLose(){
		if(score.intValue()>=Data.targetScore){
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
		
		
		if(steps==0){
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
	
	

	public void onRestartBtnClick(ActionEvent actionEvent) {
		if(isMoving == false){
			Music.playEffectMusic(2);//click
			blockGridPan.getChildren().clear();
			createBlocks();
	
	        noticeText.clear();
	        noticeText.setText("Restart!");
	        Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run(){
					Platform.runLater(()->{
						if(Data.order == 12){
							noticeText.setText("    Your score:   "+String.valueOf(score.intValue()));
						}
						else{
							noticeText.setText("Your score:"+String.valueOf(score.intValue())+"    Target score:"+Data.targetScore);
						}
					});
				}
				
			},1000);
			if(Data.order != 12){
		        steps=Data.totalstpes;
		        stepLabel.setText("Steps Left:"+steps);
		        stepProgressBar.setProgress(1.0);
			}
	        score.set(0);
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

	public void onStoreBtnClick(ActionEvent actionEvent) {
		
		
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
							if(Data.order == 12){
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
							if(Data.order == 12){
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
			if(score.intValue()<250) {
				noticeText.setText("Your score is inadequate!");
				Timer timer = new Timer();
				timer.schedule(new TimerTask(){
					public void run(){
						Platform.runLater(()->{
							if(Data.order == 12){
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
	}
	public void setToolNotSelected(Button tool){
		tool.setStyle("-fx-effect: null");
	}
	
}

		

	

	






