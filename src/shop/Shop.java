package shop;

import java.util.ArrayList;

/**  
* 商店
* 
*  
* @author Andy
* @version  
*/

public class Shop
{
	static{
	//商品清单
	ArrayList<ShopItem> list = new ArrayList<ShopItem>();

	//创建商品
	//构造体顺序为名字，价格，添加的步数，添加的分数
	list.add(new ShopItem("",0,0,0));
	
//	//被选择的商品清单，后期可以实现背包或队伍在剧情主关卡的可视化
//	static ArrayList<ShopItem> selectedList = new ArrayList<ShopItem>();
//
//	//清空被选择的商品清单
//	selectedList.clear();
	
	}
	
	//上一关获得的分数在上一关的界面中改成金币数
	//上一关结束的时候把分数转为金币数
	public static int coins;
	
	
	//被选择的商品清单，后期可以实现背包或队伍在剧情主关卡的可视化
	public static ArrayList<ShopItem> selectedList = new ArrayList<ShopItem>();
	
	//允许购买的数目
	public static final int SIZE_LIMIT = 2;

//	//上一关结束后实例化一个商店
//	public Shop(int coins){
//		this.coins = coins;
//	}
	
	//购买此商品
	public  static void buy(ShopItem s){
		if(selectedList.size()<=SIZE_LIMIT)
		if(s.isSelected()&&s.isAffordable(coins))
		{
			coins-=s.getPrice();
			selectedList.add(s);
		}
		}
	
	//不再购买此商品	
	public static void regret(ShopItem s){
		if(s.isSelected()){
			coins+=s.getPrice();
			selectedList.remove(s);
		}
				
		
	}
	
	
	
}
