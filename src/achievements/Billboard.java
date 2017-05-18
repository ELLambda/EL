
package achievements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**  
* 无尽模式的高分榜
* 
*  
* @author Andy
* @version  
*/

public class Billboard
{
	//榜单上记录的排位个数
	private static final int RANK = 10;
	
	//榜单
	//按照分数高低排序
	//如果游戏次数少于rank的数目，存空串
	
	//用长度为rank+1的数组存，每次新存的数据放到最后一位，GUI上只显示前10位
	static BillboardItem[] scorelist= new BillboardItem[RANK + 1];
	
	
	//读高分榜
	public static void getBillboardCondition() {
		String line = new String();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"src/achievements/Billboard.txt"));

			if((line = br.readLine()) != null){
				


			String[] spl = line.split("@");
			

			for (int i = 0; i < RANK; i++) {
				scorelist[i].setBillboardItem(spl[i]);
			

			}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//存高分榜
	public static void setBillboardCondition() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"src/achievements/Billboard.txt",false));
			for(int i=0;i<RANK-1;i++){
				bw.write(scorelist[i]+"@");
			}
			bw.write(scorelist[RANK-1]+"");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
