package achievements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**  
* 一个读取和存储成就状况的类
* 用于和游戏界面交互
* 用于和读取和存储成就状况的数据类交互
*  
* @author Andy
* @version  
*/

public class AchievementsManager

{
	
	//存储成就的名称
	public static final String[] names = {"","","","","","","","","",""};
	
	//记录成就的位置
	public static final int HEIGHT = 2;
	public static final int WIDTH = 5;
	public static final int ACHIEVEMENT_NUMBER = HEIGHT*WIDTH;
	public static final Achievement[][] AchievementsList = new Achievement[HEIGHT][WIDTH];
			
	//确定成就
	public final void setAchievements(){
		int index = 0;
		while(index < names.length)
		for(int i = 0; i<HEIGHT; i++)
			for(int j = 0; j<WIDTH;j++){
				AchievementsList[i][j] = new Achievement(names[index],false);
				index++;
			}
		
	}

	//读档
	public static void getAchieveCondition() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"src/achievements/achieveCondition.txt"));
			while(br.readLine() != null){
			String tem = br.readLine();
			String[] spl = tem.split("@");
			

			for (int i = 0; i < ACHIEVEMENT_NUMBER; i++) 
				for(int m = 0; m < HEIGHT;m++)
				for(int n = 0; n < WIDTH; n++)
				
				if(Integer.parseInt(spl[i]) == 1)
					AchievementsList[m][n].setAchieved(true);
				else
					AchievementsList[m][n].setAchieved(false);
			
			
//				System.out.println(achieveCondition[i]);
			}
			
//			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//存档
	public static void setAchieveCondition() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"src/achievements/achieveCondition.txt",false));
			for(int i=0;i<ACHIEVEMENT_NUMBER-1;i++){ 
				for(int m = 0; m < HEIGHT; m++)
					for(int n = 0; n < WIDTH; n++)
						if(AchievementsList[m][n].getAchieved() == true)
				bw.write("1"+"@");
						else
							bw.write("0"+"@");
			}
			if(AchievementsList[HEIGHT - 1][WIDTH - 1].getAchieved() == true)
	bw.write("1");
			else
				bw.write("0");

			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


