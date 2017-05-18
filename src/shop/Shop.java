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
	
	}
}
