package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Block extends Button{



	//private int[] position=new int[2];
	private int x;
	private  int y;//存储行列信息
	private String color="0";
	private boolean isPressed=false;//记录方块是否被按下
	private boolean Descended=false;

	public Block(int x,int y) {
		// TODO Auto-generated constructor stub
		super();
		this.setMinSize(50, 50);
		this.x=x;
		this.y=y;
	}
	
	public void setBackgroundColor(String color){
		this.color=color;
		//通过使用css设置背景
		//gui/img/star/1.png
		String path="gui/img/star/"+this.color+".png";
		//this.setStyle("-fx-background-color: red;");
		//this.setStyle("-fx-border-color: white;-fx-border-radius: 5px;");
		//this.setStyle("-fx-effect: dropshadow(gaussian,rgba(255,255,255,1),8,0.2,0,0) ;");
		this.setBackground(new Background(new BackgroundImage(new Image(path),
				BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	//方块被选中后产生的视觉效果
	public void setSelected() {
		this.setStyle("-fx-effect: dropshadow(gaussian,rgba(255,255,255,1),8,0.8,0,0) ;");
	}
	public void setNotSelected(){
		this.setStyle("-fx-effect: null;");
		//this.setStyle("-fx-effect: dropshadow(gaussian,rgba(255,255,255,1),8,0.2,0,0) ;");
	}
	
	
	//color的get set
	public String getColor() {return this.color;}
	public void setColor(String color){this.color=color;}
	
	//x get set
	public void setX(int x){
		this.x=x;
	}
	public int getX(){
		return  this.x;
	}

	//y get set
	public void setY(int y){
		this.y=y;
	}
	public int getY(){
		return  this.y;
	}

	public void setPosition(int x,int y){
		this.x=x;
		this.y=y;
	}

	public void setIsPressed(boolean value){
		this.isPressed=value;
	}
	public boolean getIsPressed(){
		return  this.isPressed;
	}


	public void setDescended(boolean value){
		this.Descended = value;
	}

	public boolean getDescended(){
		return this.Descended;
	}
}
