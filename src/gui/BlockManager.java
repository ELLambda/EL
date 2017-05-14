package gui;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.jws.Oneway;


/*
 * ������
 */
public class BlockManager {

	private final static int WIDE = 10;
	private final static int HEIGHT = 10;
	public static final double SECOND = 0.5;
	
	public ArrayList<Block> twoBlocks=new ArrayList<>();
	//public static Block[] blocksList=new Block[100];

	public Block[][] blocks=new Block[HEIGHT][WIDE];
	//static ArrayList<Block> blocks=new ArrayList<>();
	//static HashMap<String,Block> blockHashMap=new HashMap<>();

//	EventHandler <ActionEvent> exchangeOnFinished = null;

	public void setBlockBackgroundColor(Block block) {

		//??????�?????????
		int ramdonNum=0;
		while(true){
			ramdonNum=(int)(Math.random()*5)+1;
			block.setBackgroundColor(String.valueOf(ramdonNum));
			if(!erasable(block))
				break;
		}
	}

	public void setBlockColorWithoutCheck(Block block) {

		//??????�?????????
		int ramdonNum=0;

		ramdonNum=(int)(Math.random()*5)+1;
		block.setBackgroundColor(String.valueOf(ramdonNum));


	}
	
	
	//����Ѿ����ķ���
	public synchronized void addBlocksToList(Block block){
		twoBlocks.add(block);
	}
	
	public synchronized void removeBlocksFromList(Block block){
		twoBlocks.remove(block);
	}
	
	//�����������Ƿ�����
	public synchronized boolean isNear(){
        Block block1 = twoBlocks.get(0);
        Block block2 = twoBlocks.get(1);
        
        int deltX=block2.getX()-block1.getX();
        int deltY=block2.getY()-block1.getY();
        //��
        if(deltX==1&&deltY==0) return true;
        //��
        else if(deltX==-1&&deltY==0) return true;
        //��
        else if(deltX==0&&deltY==-1) return true;
        //��
        else if(deltX==0&&deltY==1) return true;
        //������
        else return false;
    }

	public synchronized Transition exchange(){
		

		Block block0=twoBlocks.get(0);
		Block block1=twoBlocks.get(1);

		blockTransition(block0,block1);
		Transition t = blockTransition(block1,block0);

		int x0=block0.getX();
		int y0=block0.getY();
//		String key0=x0+","+y0;
		//System.out.println(key0);

		int x1=block1.getX();
		int y1=block1.getY();
//		String key1=x1+","+y1;
		//System.out.println(key1);

		block1.setPosition(x0,y0);
		blocks[x0][y0]=block1;
//		System.out.println(key0+":"+blocks[x0][y0].getColor());


		block0.setPosition(x1,y1);
		blocks[x1][y1]=block0;
//		System.out.println(key1+":"+blocks[x1][y1].getColor());
		
		return t;


		//System.out.println("block0"+blockHashMap.get(x1+","+y1).getColor());

//		for (int i=0;i<100;i++){
//			if(blocksList[i]==block0){
//				for(int j=0;j<100;j++){
//					if (blocksList[j]==block1){
//						blocksList[j]=block0;
//						blocksList[i]=block1;
//						break;
//					}
//				}
//				break;
//			}
//		}



	}
	


	
	int[][] erased = new int[HEIGHT*WIDE][2];
	int length = 0;
	
	
	
	/*
    ��������
    block Ϊ�������
	 */
	
	ArrayList<Block> erasableHBlocks = new ArrayList<Block>();
	ArrayList<Block> erasableVBlocks = new ArrayList<Block>();
    public synchronized boolean hSearch(Block block){

        int x=block.getX();
        int y=block.getY();//�������

        int count=1;//��ͬ��ɫ�ķ�������������㷽��

        //����
        for (int i = x+1;i<WIDE;i++){
            //�����߽磬����
            if (i>=WIDE) break;
            if(blocks[i][y]==null) break;				//ʲôʱ�����null��
            if(blocks[i][y].getColor().equals(block.getColor())){
            	if(!erasableHBlocks.contains(blocks[i][y])){
                erasableHBlocks.add(blocks[i][y]);//���뵽�������ĺ��򷽿�list
                count++;
            	}
            }else break;
        }

        //����
        for (int i = x-1;i>=0;i--){
            //�����߽磬����
            if (i<0) break;
            if(blocks[i][y]==null) break;				//ʲôʱ�����null��
            if(blocks[i][y].getColor().equals(block.getColor())){
            	if(!erasableHBlocks.contains(blocks[i][y])){
                    erasableHBlocks.add(blocks[i][y]);//���뵽�������ĺ��򷽿�list
                    count++;
                	}
            }else break;
        }

        //�Ƿ��Ѿ���������
//        System.out.println("H:"+count);
        if (count>=3){
//        	for(Block b : erasableHBlocks){
//        		b.setDescended(false);
//        	}
        	if(!erasableHBlocks.contains(block))
        		erasableHBlocks.add(block);//����㷽��Ҳ����
            return true;
        }else{
            erasableHBlocks.clear();
            return false;
        }
    }

    /*
    ��������
     */
    public synchronized boolean vSearch(Block block){
        int x=block.getX();
        int y=block.getY();//�������

        int count=1;//��ͬ��ɫ�ķ�������������㷽��

        //����
        for (int i = y-1;i>=0;i--){
            //�����߽磬����
            if (i<0) break;
            if(blocks[x][i]==null) break;
            if(blocks[x][i].getColor().equals(block.getColor())){
            	if(!erasableVBlocks.contains(blocks[x][i])){
                erasableVBlocks.add(blocks[x][i]);
                count++;
            	}
            }else break;
        }

        //����
        for (int i = y+1;i<WIDE;i++){
            //�����߽磬����
            if (i>=WIDE) break;
            if(blocks[x][i]==null) break;
            if(blocks[x][i].getColor().equals(block.getColor())){
            	if(!erasableVBlocks.contains(blocks[x][i])){
                    erasableVBlocks.add(blocks[x][i]);
                    count++;
                	}
           }else break;
        }

//        System.out.println("V:"+count);
        //�Ƿ��Ѿ���������
        if (count>=3){
//        	for(Block b : erasableVBlocks){
//        		b.setDescended(false);
//        	}
        	if(!erasableVBlocks.contains(block))
        		erasableVBlocks.add(block);//����㷽��Ҳ����
            return true;
        }else{
            erasableVBlocks.clear();
           
            return false;
        }

    }


    /*
    �Ƿ��������
     */
    public synchronized boolean erasable(Block block){

        Boolean isErasable = hSearch(block)|vSearch(block);
        for(int i = 0;i < erasableVBlocks.size();i++){
        	erased[length][0] = erasableVBlocks.get(i).getX();
        	erased[length][1] = erasableVBlocks.get(i).getY();
        	length++;
        }
        for(int i = 0;i < erasableHBlocks.size();i++){
        	erased[length][0] = erasableHBlocks.get(i).getX();
        	erased[length][1] = erasableHBlocks.get(i).getY();
        	length++;
        }
        if(erasableHBlocks.size() != 0 && erasableVBlocks.size() != 0){
        	length--;
        	erased[length][0] = 0;
        	erased[length][1] = 0;
        }
        erasableVBlocks.clear();
        erasableHBlocks.clear();
        
        System.out.println("after erasable(block x,y): "+block.getX()+" , "+block.getY());
        System.out.println("length : "+ length);
        return isErasable;
    }
   
    //�������
    public synchronized void resetArrays(){
    	System.out.println("start reseting Arrays");
    	for(int i = 0;i < length;i++){
    		erased[i][0] = 0;
    		erased[i][1] = 0;
    	}
    	
    	length = 0;
    	
    	erasableHBlocks.clear();
    	erasableVBlocks.clear();
    	
    	System.out.println("Reseting arrays done");
    }
    
    
	//���齻������Ч��
	public Transition blockTransition(Block block0,Block block1){


		int deltX=(block1.getX()-block0.getX())*60;
		int deltY=(block1.getY()-block0.getY())*60;

		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltX:"+deltX);
		System.out.println(block0.getColor()+"to"+block1.getColor()+"deltY:"+deltY);

		TranslateTransition transition = new TranslateTransition(Duration.seconds(SECOND),block0);
		transition.setByX(deltX);
		transition.setByY(deltY);
		transition.play();
		//��ʱ�������ȴ��������
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				block0.setNotSelected();
				block0.setIsPressed(false);
				//block1.setNotSelected();
				//block1.setIsPressed(false);


			}
		},(long)(SECOND*1000));
		
		return transition;

	}
	
	//����ܷ�����
	public synchronized boolean check(){
		boolean hasErasableBlocks = false;
		for(int i = 0 ; i< HEIGHT; i++ )
			for(int j = 0; j < WIDE; j++){
//				if(blocks[i][j] == null) System.out.println("null pointer: "+ i + "  "+j);
				if(blocks[i][j].getDescended()){
				erasable(blocks[i][j]);
				blocks[i][j].setDescended(false);
				 
				}
			}
		
		System.out.println("before remove duplicate, length: "+ length);
		if(length != 0){
			hasErasableBlocks = true;
			  //ȥ���ظ���Ԫ��
	        ArrayList<Block> removeDuplicate = new ArrayList<Block>();
	        
	        for(int i = 0; i< length; i++){
	        removeDuplicate.add(blocks[erased[i][0]][erased[i][1]]);
	        erased[i][0] = 0;
	        erased[i][1] = 0;
	        }
	        
	        HashSet<Block> h = new HashSet<Block>(removeDuplicate);
	        removeDuplicate.clear();
	        removeDuplicate.addAll(h);
	        
	        length = 0;
	        
	        for(int i = 0; i<removeDuplicate.size(); i++){
	        	erased[i][0] = removeDuplicate.get(i).getX();
	        	erased[i][1] = removeDuplicate.get(i).getY();
	        	length++;
	        }
	        
	        removeDuplicate.clear();
	        
		}
		System.out.println("after remove duplicate, length: "+ length);

		System.out.println(hasErasableBlocks);
		return hasErasableBlocks;
						
						
	}


}
