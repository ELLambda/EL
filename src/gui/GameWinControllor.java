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
//	boolean remainDescended = false;	//ʣ��Ŀ��Ƿ��Ѿ�����
	
//    ChangeListener<? super EventHandler<ActionEvent>> listener =
//	null;
//	EventHandler<ActionEvent> eraseOnFinished = null;
	
	
	@FXML void initialize(){
		createBlocks();

		CountDownLatch countDownLatch=new CountDownLatch(60);
		CountThread countThread=new CountThread(countDownLatch, 60);
		countThread.start();
		time.progressProperty().bind(countThread.doubleProperty);

	}
	
//	@FXML void onExitBtnClick(){
//		//root.setVisible(false);
//		
//	}
	
	public synchronized void createBlocks(){
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


	//�Ѵ���һ��block�ķ�������д
	public synchronized void createOneBlock(int x,int y){
		Block btn=new Block(x,y);
		btn.getStyleClass().add("block");
		btn.setOnMouseClicked(e->{
			if(isMoving == false){
			Music.playEffectMusic(2);//click
			if(btn.getIsPressed()==false){		//֮ǰû����
				btn.setIsPressed(true);
				btn.setSelected();
				BlockManager.addBlocksToList(btn);//���뵽�������list
				System.out.println(btn.getColor()+":"+btn.getX()+","+btn.getY());
			}
			else{								//֮ǰ������
				btn.setIsPressed(false);
				btn.setNotSelected();
				BlockManager.removeBlocksFromList(btn);//���������list���Ƴ�
			}
			if(BlockManager.twoBlocks.size()==2){		//������������
				isMoving = true;
				if(BlockManager.isNear() == true){		//�������������
					
					System.out.println("start exchanging");
					Transition transition = BlockManager.exchange();
					transition.setOnFinished(e2 ->{ 
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
				else{												//��������鲻����
					Block b = BlockManager.twoBlocks.get(0);		//�ѵ�һ����Ŀ�ȡ��
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
	
	
	
	
	//��������
	public synchronized void erase(){
		//�������Ŀ��λ��(x,y)����һ����ά����int[][] erased = new int[100][2]��,��length��
//		boolean onFinished = false;
//		while(!onFinished){
//			if(BlockManager.exchangeOnFinished != null){
//				onFinished = true;
		
		
		score += BlockManager.length*(erasedTimes++);
		
		noticeText.setText(String.valueOf(score));
		
		 Music.playEffectMusic(1);//eliminate
		
		 System.out.println("start erasing");
		 
		for(int i = 0;i < BlockManager.length;i++){
			Block block = BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]];
			BlockManager.blocks[BlockManager.erased[i][0]][BlockManager.erased[i][1]] = null;
			//��������
	        FadeTransition transition = new FadeTransition(Duration.seconds(SECOND),block);
	        transition.setFromValue(1);
	        transition.setToValue(0);
	        
	        if(i == BlockManager.length - 1)
		        transition.setOnFinished(e->{
		           System.out.println("erase done");
		        	blockGridPan.getChildren().remove(block);
	
		        	 descend();
		        	 
		        	 //BlockManager.resetArrays();
		           
		          
		        });
	        
	        else
	        	transition.setOnFinished(e->{

	        		blockGridPan.getChildren().remove(block);
	
		        });
	        	
	        
	        transition.play();
	        
	        
		}
//	        ChangeListener<? super EventHandler<ActionEvent>> listener =
//			null;
//	        eraseOnFinished = transition.getOnFinished();
	       
	        	
	        
	}

	int[][] descend = new int[HEIGHT*WIDE][2];
	int descendLength = 0;

	
	//�½�����
	public synchronized void descend(){
		int[] columnX = new int[10];		//������ÿ���м���
//		int[] theUpperOne = new int[10];	//ÿ�������Ŀ��������һ��
//		boolean lastAnimationFinished = false;
//		boolean remainDescended = false;	//ʣ��Ŀ��Ƿ��Ѿ�����
		
//		Arrays.fill(theUpperOne, 10);
		Arrays.fill(columnX,0);
		System.out.println("length: "+BlockManager.length);
		
		for(int j = 0;j < BlockManager.length;j++){		
			columnX[BlockManager.erased[j][0]]++;	
//			if(BlockManager.erased[j][1] < theUpperOne[BlockManager.erased[j][0]]){
//				theUpperOne[BlockManager.erased[j][0]] = BlockManager.erased[j][1];
//			}
		}
		
		TranslateTransition transition = null;
		
		System.out.println("start descending");
		System.out.println("length  : "+BlockManager.length);

		
		for(int i = 0;i < 10;i++){		//�����i��
			//��i��û�������Ŀ�
			if(columnX[i] == 0) 
				continue;
			
			//��i���б������Ŀ�
			//���еĿ��½�
			
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
												//�½��Ķ���
					BlockManager.blocks[i][j + dY] = BlockManager.blocks[i][j];
					BlockManager.blocks[i][j + dY].setPosition(i, j + dY);
					BlockManager.blocks[i][j] = null;
					
					BlockManager.blocks[i][j + dY].setDescended(true);
					transition.play();
					transition.setOnFinished(ei ->{
						System.out.println("old block descended");
					});
				}
			}
			
				
			
			
			//�����¿鲢�½�
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
			
		  

			//��������
			if(transition != null)
			transition.setOnFinished(e ->{
				System.out.println("descend done");
				BlockManager.resetArrays();
				if(BlockManager.check())
					erase();
				else{
					BlockManager.resetArrays();
					erasedTimes = 1;
					isMoving = false;
					
				}
			});

		
	}


	public void onRestartBtnClick(ActionEvent actionEvent) {
		blockGridPan.getChildren().clear();
		createBlocks();
		noticeText.clear();
		score = 0;
	}

	public void onSettingBtnClick(ActionEvent actionEvent) {
		Platform.runLater(()->{
			try {
				new SettingWin().show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void onStoreBtnClick(ActionEvent actionEvent) {
	}
}

		

	

	






