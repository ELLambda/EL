package Story;
/**  
* 类说明   存储目前进行到的关卡和人物装备
*  
* @author Andy
* @version  
*/

public class State
{
	//所在关卡
	private static int chapter;
	
	//期待结局
	private static int ending;
	
	//理智值
	private static int rationality = 0;
	
	//金创药
	private static boolean medicine = false;
	
	//九转再造丹
	private static boolean dan = false;
	
	//玄铁剑
	private static boolean sword = false;
	
	//天香续命露
	private static boolean water = false;
	
	//骰子
	private static boolean dice = false;


	public static int getChapter()
	{
		return chapter;
	}

	public static void setChapter(int chapter)
	{
		State.chapter = chapter;
	}
	
	public static int getEnding()
	{
		return ending;
	}

	public static void setEnding(int ending)
	{
		State.ending = ending;
	}

	public static int getRationality()
	{
		return rationality;
	}

	public static void decreaseRationality(int rationality)
	{
		State.rationality --;
	}

	public static boolean hasMedicine()
	{
		return medicine;
	}

	public static void setMedicine(boolean medicine)
	{
		State.medicine = medicine;
	}

	public static boolean hasDan()
	{
		return dan;
	}

	public static void setDan(boolean dan)
	{
		State.dan = dan;
	}

	public static boolean hasSword()
	{
		return sword;
	}

	public static void setSword(boolean sword)
	{
		State.sword = sword;
	}

	public static boolean hasWater()
	{
		return water;
	}

	public static void setWater(boolean water)
	{
		State.water = water;
	}
	
	public static boolean hasDice()
	{
		return dice;
	}

	public static void setDice(boolean dice)
	{
		State.dice = dice;
	}



}
