package achievements;

import javafx.scene.Node;
import javafx.scene.control.Button;

/**  
* 成就按钮
* 写了很多空方法
*  
* @author Andy
* @version  
*/

//使用了button，后期可以在点击每个成就的时候弹出一个说明窗口，类似于图鉴的东西

public class Achievement extends Button
{
	//成就名
	public String name = null;
	//成就是否完成
	public boolean achieved = false;
	
	
	//构造体
	public Achievement(String name,boolean value){
		this.name = name;
		this.achieved = value;
		this.minHeight(180);
		this.minWidth(150);
	}
	
	//成就的完成情况
	public void setAchieved(boolean value){
		this.achieved = value;
	}
	
	public boolean getAchieved(){
		return this.achieved;
	}
	
	//成就完成情况的视觉效果
	public void setOnAchieved() {
//		this.setStyle("-fx-effect: dropshadow(gaussian,rgba(255,255,255,1),8,0.8,0,0) ;");
	}
	public void setOnNotAchieved(){
//		this.setStyle("-fx-effect: null;");
	
	}
 
	//点击的效果
	public void setOnPressed(){
		
	}
	
	public void setOnNotPressed(){
		
	}
}
