package gui;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class GameWinControllor {
	public static BlockManager BlockManager = new BlockManager();
	public ProgressBar time;
	@FXML private GridPane blockGridPan;
	@FXML private AnchorPane root;
	@FXML private TextField noticeText;
	private final static int HEIGHT = 10;
	private final static int WIDE = 10;
	public static final double SECOND = 0.5;
	public static int score=0;
	private static int erasedTimes = 1;
	private static boolean isMoving = false;
	
	//private SimpleIntegerProperty scoreProperty=new SimpleIntegerProperty();
//    ChangeListener<? super EventHandler<ActionEvent>> listener =
//	null;
//	EventHandler<ActionEvent> eraseOnFinished = null;
	
	
	@FXML  void initialize(){

		//blockGridPan.setGridLinesVisible(true);
		//blockGridPan.set
		createBlocks();
		
		noticeText.setText("0");


		
		CountDownLatch countDownLatch=new CountDownLatch(60);
		CountThread countThread=new CountThread(countDownLatch, 60);
		countThread.start();
		time.progressProperty().bind(countThread.doubleProperty);

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
			}
		});
		
//		String key=x+","+y;
//		System.out.println(key);
		BlockManager.blocks[x][y]=btn;
		

		//BlockManager.blockHashMap.replace()

		//BlockManager.blocks.add(btn);
		
		
	}
	
	
	
	
	//消除
	public  void erase(){
		
		
		score += BlockManager.length*BlockManager.length*(erasedTimes++);
		
		noticeText.setText(String.valueOf(score));
		
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

//	int[][] descend = new int[HEIGHT*WIDE][2];
//	int descendLength = 0;

	
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
					erasedTimes = 1;
					isMoving = false;
					
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
		
		if(h.isEmpty())		//没有爆炸块
			return;
		
		Iterator<Block> iterator = h.iterator();
		while(iterator.hasNext()){
			Block block = iterator.next();
			BlockManager.erased[BlockManager.length][0] = block.getX();
			BlockManager.erased[BlockManager.length][1] = block.getY();
			BlockManager.length++;
		}
		
		erase();
		
		
	}

	public void onRestartBtnClick(ActionEvent actionEvent) {
		blockGridPan.getChildren().clear();
		createBlocks();
		noticeText.setText("0");
        score = 0;
	}

	public void onSettingBtnClick(ActionEvent actionEvent) {
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
}

		

	

	






