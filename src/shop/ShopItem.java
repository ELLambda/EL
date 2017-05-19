package shop;

import gui.ShopController;

/**
* 类说明   
*  
* @author Andy
* @version  
*/

public class ShopItem
{
	//商品名
	private String name;

	//商品价格
	private int price;

	//所选商品数量
	private int num;
	
	//商品是否可获得
	//可以获得的为亮或者弹出提示框
	private boolean affordable = false;
	
	//玩家是否选中该商品
	private boolean selected = false;
	
	//添加的生命值或精力值（gyb将此变为静态）
	private static int addedStep;
	
	//添加的分数
	private int addedScore;
	
	//构造体
	public ShopItem(String name ,int price, int num, int addedStep, int addedScore){
		this.name = name;
		this.price = price;
		this.num = num;
		this.addedStep = addedStep;
		this.addedScore = addedScore;
	}

	//添加生命值或精力值(gyb将此变为静态）
	public static int addStep(int step){
		return step+addedStep;
	}

	//添加分数
	public int addScore(int score){
		return score+addedScore;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public boolean isAffordable(int coins)
	{
		if(coins >= price)
			affordable = true;
		
		return affordable;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
//	public void setSelected(boolean selected)
//	{
//		this.selected = selected;
//	}

//	public void setAffordable(boolean affordable)
//	{
//		this.affordable = affordable;
//	}
	
	
	
}
