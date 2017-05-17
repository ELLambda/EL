package achievements;

import java.util.Comparator;

/**  
* 高分榜中的一条高分信息
* 实现了comparator方法
*  
* @author Andy
* @version  
*/

public class BillboardItem implements Comparable<Object>
{
	//分数
	public int score;
	//时间
	public int time;

//	public int getScore()
//	{
//		return score;
//	}
//
//	public void setScore(int score)
//	{
//		this.score = score;
//	}
//
//	public int getTime()
//	{
//		return time;
//	}
//
//	public void setTime(int time)
//	{
//		this.time = time;
//	}

	
	//构造体，获取高分信息
	public void setBillboardItem(String input){
		if(input != null){
			//文件中用&分隔了时间和分数
			//时间在前分数在后
			String[] temp = input.split("&");
			this.time = Integer.valueOf(temp[0]);
			this.score = Integer.valueOf(temp[1]);
			
			}
	}
	
	public String getBillboardItem(){
		return this.time + "&" + this.score;
	}
	

	//降序排列
	@Override
	public int compareTo(Object o)
	{
		// TODO Auto-generated method stub
		if(this.score >((BillboardItem)o).score)
			return -1;
		else
			return 1;
		
	}


	
}


