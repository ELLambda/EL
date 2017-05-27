package achievements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * 无尽模式的高分榜
 *
 * @author Andy
 */

public class Billboard
{
	// 榜单上记录的排位个数
	public static final int RANK = 10;
	// 用长度为rank+1的数组存，每次新存的数据放到最后一位，GUI上只显示前10位
	public static BillboardItem[] scorelist = new BillboardItem[RANK + 1];

	// 榜单
	// 按照分数高低排序
	// 如果游戏次数少于rank的数目，存空串

	public static int getRank()
	{
		return RANK;
	}

	// public static void main(String args[]){
	// getBillboardCondition();
	// }
	// static{
	// 读高分榜
	public static void getBillboardCondition()
	{
		String line = new String();

		try
		{
			BufferedReader br = new BufferedReader(new FileReader("src/achievements/Billboard.txt"));

			if ((line = br.readLine()) != null)
			{

				String[] spl = line.split("@");

				for (int i = 0; i < spl.length; i++)
				{
					System.out.println(i);

					// scorelist[i].setBillboardItem(spl[i]);
					scorelist[i] = new BillboardItem(spl[i]);
					System.out.println(scorelist[i]);
				}
				// 长于spl长度的，也就是没有记录的BillboardItem也要初始化
				for (int i = spl.length; i < RANK + 1; i++)
				{
					scorelist[i] = new BillboardItem(" &0& ");
					System.out.println(scorelist[i]);
				}
			}
			System.out.println("");
			Arrays.sort(scorelist);

			for (int i = 0; i < RANK; i++)
				System.out.println(scorelist[i]);

			br.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// 存高分榜
	// 写入的时候调用了getBillboardItem方法
	public static void setBillboardCondition()
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/achievements/Billboard.txt", false));
			for (int i = 0; i < RANK; i++)
			{
				bw.write(scorelist[i].getBillboardItem() + "@");
			}
			bw.write(scorelist[RANK].getBillboardItem() + "");
			bw.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
