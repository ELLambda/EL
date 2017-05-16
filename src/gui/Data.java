package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wenxi on 2017/5/14.
 * 用于存放数据使得可以跨窗口调用
 */
public class Data {

    public static int totalstpes=0;
    public static int targetScore=0;
    public static int order;//第几关
    public static int warnNumber=0;
    public static int mode;//0代表故事模式，1代表生日模式
    public static int chapterReached = 1; //存档中最高到了第几关
    
    //读文件
    static{
    	BufferedReader br = null;
    	try {
			br = new BufferedReader(new FileReader("src/gui/StoryMoodStore.txt"));
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		}
    	String in = null;
    	if(br != null)
	    	try {
				in = br.readLine();
				if(in != null)
					chapterReached = Integer.parseInt(in.substring(15));
			} catch (IOException e) {
//				e.printStackTrace();
			}
    	
    }
    
    /*
    为关卡设置限制
    @param n 为第几关
     */
    public static void setLimit(int n){
        order = n;
        warnNumber=0;
        switch (n){
            case 1:
                targetScore=60;
                totalstpes=15;
                break;
            case 2:
                targetScore=100;
                totalstpes=15;
                break;
            case 3:
                targetScore=200;
                totalstpes=15;
                break;
            case 4:
                targetScore=250;
                totalstpes=20;
                break;
            case 5:
                targetScore=300;
                totalstpes=20;
                break;
            case 6:
                targetScore=300;
                totalstpes=15;
                break;
            case 7:
                targetScore=400;
                totalstpes=20;
                break;
            case 8:
                targetScore=500;
                totalstpes=20;
                break;
            case 9:
                targetScore=600;
                totalstpes=15;
                break;
            case 10:
                targetScore=1000;
                totalstpes=15;
                break;
            case 11:
                targetScore=300;
                totalstpes=5;
                break;
            case 12:
                targetScore=2000;
                totalstpes=-1;
                break;

        }
    }

}

