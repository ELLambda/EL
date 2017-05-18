package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wenxi on 2017/5/14.
 * 用于存放数据使得可以跨窗口调用
 * version 2.0 增加了商店模式，提高了关卡难度
 */
public class Data {

    public static int totalstpes=0;
    public static int targetScore=0;
    public static int order;//第几关
    public static int warnNumber=0;
    public static int mode;//0代表故事模式，1代表生日模式,2代表商店模式,3代表无尽模式
    public static int chapterReached = 1; //存档中最高到了第几关
    
    private static final int BIRTHDAY_CHAPTER1_SCORE = 1500;
    private static final int BIRTHDAY_CHAPTER1_STEP = 15;
    private static final int BIRTHDAY_CHAPTER2_SCORE = 6000;
    private static final int BIRTHDAY_CHAPTER2_STEP = 30;
    private static final int BIRTHDAY_CHAPTER3_SCORE = 7000;
    private static final int BIRTHDAY_CHAPTER3_STEP = 35;
    private static final int BIRTHDAY_CHAPTER4_SCORE = 4000;
    private static final int BIRTHDAY_CHAPTER4_STEP = 20;
    private static final int BIRTHDAY_CHAPTER5_SCORE = 5000;
    private static final int BIRTHDAY_CHAPTER5_STEP = 25;
    private static final int BIRTHDAY_CHAPTER6_SCORE = 3500;
    private static final int BIRTHDAY_CHAPTER6_STEP = 20;
    private static final int BIRTHDAY_CHAPTER7_SCORE = 2000;
    private static final int BIRTHDAY_CHAPTER7_STEP = 15;
    private static final int BIRTHDAY_CHAPTER8_SCORE = 8000;
    private static final int BIRTHDAY_CHAPTER8_STEP = 40;
    private static final int BIRTHDAY_CHAPTER9_SCORE = 6500;
    private static final int BIRTHDAY_CHAPTER9_STEP = 35;
    private static final int BIRTHDAY_CHAPTER10_SCORE = 4500;
    private static final int BIRTHDAY_CHAPTER10_STEP = 25;
    private static final int BIRTHDAY_CHAPTER11_SCORE = 5500;
    private static final int BIRTHDAY_CHAPTER11_STEP = 30;
    private static final int BIRTHDAY_CHAPTER12_SCORE = 1000;
    private static final int BIRTHDAY_CHAPTER12_STEP = 15;
    
    private static final int STORY_CHAPTER1_SCORE = 1500;
    private static final int STORY_CHAPTER1_STEP = 15;
    private static final int STORY_CHAPTER2_SCORE = 8000;
    private static final int STORY_CHAPTER2_STEP = 30;
    private static final int STORY_CHAPTER3_SCORE = 10000;
    private static final int STORY_CHAPTER3_STEP = 35;
    private static final int STORY_CHAPTER4_SCORE = 8000;
    private static final int STORY_CHAPTER4_STEP = 20;
    private static final int STORY_CHAPTER5_SCORE = 10000;
    private static final int STORY_CHAPTER5_STEP = 25;
    private static final int STORY_CHAPTER6_SCORE = 9500;
    private static final int STORY_CHAPTER6_STEP = 20;
    private static final int STORY_CHAPTER7_SCORE = 9000;
    private static final int STORY_CHAPTER7_STEP = 15;
    private static final int STORY_CHAPTER8_SCORE = 14000;
    private static final int STORY_CHAPTER8_STEP = 40;
    private static final int STORY_CHAPTER9_SCORE = 12000;
    private static final int STORY_CHAPTER9_STEP = 35;
    private static final int STORY_CHAPTER10_SCORE = 80000;
    private static final int STORY_CHAPTER10_STEP = 25;
    private static final int STORY_CHAPTER11_SCORE = 10000;
    private static final int STORY_CHAPTER11_STEP = 30;
    private static final int STORY_CHAPTER12_SCORE = 8000;
    private static final int STORY_CHAPTER12_STEP = 15;
    private static final int STORY_CHAPTER13_SCORE = 5000;
    private static final int STORY_CHAPTER13_STEP = 5;
 
    
    private static final int SHOP_CHAPTER1_STEP = 5;
    private static final int SHOP_CHAPTER2_STEP = 10;
    private static final int SHOP_CHAPTER3_STEP = 15;
    private static final int SHOP_CHAPTER4_STEP = 20;
    private static final int SHOP_CHAPTER5_STEP = 25;
    private static final int SHOP_CHAPTER6_STEP = 30;
    private static final int SHOP_CHAPTER7_STEP = 35;
    private static final int SHOP_CHAPTER8_STEP = 35;
    private static final int SHOP_CHAPTER9_STEP = 40;
    private static final int SHOP_CHAPTER10_STEP = 40;
    private static final int SHOP_CHAPTER11_STEP = 35;
    private static final int SHOP_CHAPTER12_STEP = 30;
    private static final int SHOP_CHAPTER13_STEP = 20;


    //读文件
    static{
  
	    	BufferedReader br = null;
	    	try {
				br = new BufferedReader(new FileReader("src/gui/StoryMoodStore.txt"));
			} catch (FileNotFoundException e) {
			}
	    	String in = null;
	    	if(br != null)
		    	try {
					in = br.readLine();
					if(in != null)
						chapterReached = Integer.parseInt(in.substring(15));
				} catch (IOException e) {
				}
    	
   
    }
    
    /*
    为关卡设置限制
    @param n 为第几关 
    @param m 为所在的模式,0代表故事模式，1代表生日模式，2代表商店模式,3代表无尽模式
     */
    public static void setLimit(int n,int m){
        order = n;
        mode = m;
        warnNumber=0;
        switch (m){
        case 0:
        	switch(n){
            case 1:
                targetScore=STORY_CHAPTER1_SCORE;
                totalstpes=STORY_CHAPTER1_STEP;
                break;
            case 2:
                targetScore=STORY_CHAPTER2_SCORE;
                totalstpes=STORY_CHAPTER2_STEP;
                break;
            case 3:
                targetScore=STORY_CHAPTER3_SCORE;
                totalstpes=STORY_CHAPTER3_STEP;
                break;
            case 4:
                targetScore=STORY_CHAPTER4_SCORE;
                totalstpes=STORY_CHAPTER4_STEP;
                break;
            case 5:
                targetScore=STORY_CHAPTER5_SCORE;
                totalstpes=STORY_CHAPTER5_STEP;
                break;
            case 6:
                targetScore=STORY_CHAPTER6_SCORE;
                totalstpes=STORY_CHAPTER6_STEP;
                break;
            case 7:
                targetScore=STORY_CHAPTER7_SCORE;
                totalstpes=STORY_CHAPTER7_STEP;
                break;
            case 8:
                targetScore=STORY_CHAPTER8_SCORE;
                totalstpes=STORY_CHAPTER8_STEP;
                break;
            case 9:
                targetScore=STORY_CHAPTER9_SCORE;
                totalstpes=STORY_CHAPTER9_STEP;
                break;
            case 10:
                targetScore=STORY_CHAPTER10_SCORE;
                totalstpes=STORY_CHAPTER10_STEP;
                break;
            case 11:
                targetScore=STORY_CHAPTER11_SCORE;
                totalstpes=STORY_CHAPTER11_STEP;
                break;
            case 12:
                targetScore=STORY_CHAPTER12_SCORE;
                totalstpes=STORY_CHAPTER12_STEP;
                break;
            case 13:
            	targetScore = STORY_CHAPTER13_SCORE;
            	totalstpes = STORY_CHAPTER13_STEP;
            	break;
        }
        	break;
        case 1:
        	switch(n){
            case 1:
                targetScore=BIRTHDAY_CHAPTER1_SCORE;
                totalstpes=BIRTHDAY_CHAPTER1_STEP;
                break;
            case 2:
                targetScore=BIRTHDAY_CHAPTER2_SCORE;
                totalstpes=BIRTHDAY_CHAPTER2_STEP;
                break;
            case 3:
                targetScore=BIRTHDAY_CHAPTER3_SCORE;
                totalstpes=BIRTHDAY_CHAPTER3_STEP;
                break;
            case 4:
                targetScore=BIRTHDAY_CHAPTER4_SCORE;
                totalstpes=BIRTHDAY_CHAPTER4_STEP;
                break;
            case 5:
                targetScore=BIRTHDAY_CHAPTER5_SCORE;
                totalstpes=BIRTHDAY_CHAPTER5_STEP;
                break;
            case 6:
                targetScore=BIRTHDAY_CHAPTER6_SCORE;
                totalstpes=BIRTHDAY_CHAPTER6_STEP;
                break;
            case 7:
                targetScore=BIRTHDAY_CHAPTER7_SCORE;
                totalstpes=BIRTHDAY_CHAPTER7_STEP;
                break;
            case 8:
                targetScore=BIRTHDAY_CHAPTER8_SCORE;
                totalstpes=BIRTHDAY_CHAPTER8_STEP;
                break;
            case 9:
                targetScore=BIRTHDAY_CHAPTER9_SCORE;
                totalstpes=BIRTHDAY_CHAPTER9_STEP;
                break;
            case 10:
                targetScore=BIRTHDAY_CHAPTER10_SCORE;
                totalstpes=BIRTHDAY_CHAPTER10_STEP;
                break;
            case 11:
                targetScore=BIRTHDAY_CHAPTER11_SCORE;
                totalstpes=BIRTHDAY_CHAPTER11_STEP;
                break;
            case 12:
                targetScore=BIRTHDAY_CHAPTER12_SCORE;
                totalstpes=BIRTHDAY_CHAPTER12_STEP;
                break;
        }
        	break;
        case 2:
        	switch(n){
        	
            case 1:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER1_STEP;
                break;
            case 2:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER2_STEP;
                break;
            case 3:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER3_STEP;
               break;
            case 4:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER4_STEP;
               break;
            case 5:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER5_STEP;
                break;
            case 6:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER6_STEP;
                break;
            case 7:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER7_STEP;
               break;
            case 8:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER8_STEP;
                break;
            case 9:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER9_STEP;
                break;
            case 10:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER10_STEP;
                break;
            case 11:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER11_STEP;
                break;
            case 12:
            	targetScore=-1;
                totalstpes=SHOP_CHAPTER12_STEP;
                break;
            case 13:
            	targetScore = -1;
            	totalstpes = SHOP_CHAPTER13_STEP;
            	break;
           }
        	break;
        	
        case 3:
        	targetScore = -1;
        	totalstpes = -1;

        }
    }

}

