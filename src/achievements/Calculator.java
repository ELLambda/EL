package achievements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**  
* 用来记录成就的实现条件
*   
*  
* @author Andy
* @version  
*/

public class Calculator
{
	public static AchievementsManager achievementsManager;
	
	public static int[] list = new int[achievementsManager.ACHIEVEMENT_NUMBER];
	
	public static int first;
	public static int second;
	public static int third;
	public static int fourth;
	public static int fifth;
	
	public static int smallHammer;
	public static int bigHammer;
	public static int magic;
	
	public static int steps;
	public static int scores;
	
	
	public static void getCalculatorCondition() {
		String line = new String();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"src/achievements/calculator.txt"));
			System.out.println("ready to read");
			if((line = br.readLine()) != null){
				

			System.out.println(line);
			String[] spl = line.split("@");
			

			for (int i = 0; i < achievementsManager.ACHIEVEMENT_NUMBER; i++) {
				list[i] = Integer.parseInt(spl[i]);
			
//				System.out.println(list[i]);
			}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialize(){
		
		getCalculatorCondition();
		first = list[0];
		second = list[1];
		third = list[2];
		fourth = list[3];
		fifth = list[4];
		
		smallHammer = list[5];
		bigHammer = list[6];
		magic = list[7];
		
		steps = list[8];
		scores = list[9];
		
	}
	


	
	public static void setCalculatorCondition() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"src/achievements/calculator.txt",false));
			for(int i=0;i<achievementsManager.ACHIEVEMENT_NUMBER - 1;i++){
				bw.write(list[i]+"@");
			}
			bw.write(list[achievementsManager.ACHIEVEMENT_NUMBER - 1]+"");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void leaving(){
		list[0] = first;
		list[1] = second;
		list[2] = third;
		list[3] = fourth;
		list[4] = fifth;
		
		list[5] = smallHammer;
		list[6] = bigHammer;
		list[7] = magic;
		
		list[8] = steps;
		list[9] = scores;
		System.out.println(list[0]);

		setCalculatorCondition();

	}
}
