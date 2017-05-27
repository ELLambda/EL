package gui;

import java.util.Arrays;

import achievements.Billboard;
import achievements.BillboardItem;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Created by wenxi on 2017/5/20. Eric 输入玩家姓名的窗口
 */
public class InputNameWin extends Stage
{

	AnchorPane root;
	TextField nameTextField;

	public InputNameWin()
	{
		root = new AnchorPane();
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("inputname.css").toExternalForm());

		Label prompText = new Label("喜欢刷分的你" + System.lineSeparator() + "留下你的大名吧");
		nameTextField = new TextField();
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(40);
		vBox.setLayoutX(100);
		vBox.setLayoutY(90);
		vBox.getChildren().addAll(prompText, nameTextField);
		root.getChildren().add(vBox);

		circleAnimation();

		this.setScene(scene);
		this.initStyle(StageStyle.TRANSPARENT);
		this.show();
	}

	public void circleAnimation()
	{

		int x = 225;
		int y = 320;

		Circle circle = new Circle(x, y, 20);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.web("rgba(255,255,255,0.8);"));
		circle.setStrokeWidth(5);
		circle.setCursor(Cursor.HAND);

		// 点击圆形关闭窗口
		circle.setOnMouseClicked(e ->
		{

			BillboardItem item = Billboard.scorelist[Billboard.RANK];

			String name = nameTextField.getText();
			if (name.equals(""))
			{
				item.name = "无名小卒";
			} else
			{
				item.name = name;
			}
			item.setScore(Data.scoreToBeSet);
			item.setTime(Data.timeToBeSet);

			Arrays.sort(Billboard.scorelist);

			for (int i = 0; i < Billboard.RANK + 1; i++)
				System.out.println(Billboard.scorelist[i]);

			Billboard.setBillboardCondition();

			new MainWin();
			this.close();
		});

		root.getChildren().add(circle);

		// 缩放动画
		ScaleTransition st = new ScaleTransition(Duration.millis(2000), circle);
		st.setByX(0.4);
		st.setByY(0.4);
		st.setCycleCount(Timeline.INDEFINITE);
		st.play();

		FadeTransition ft = new FadeTransition(Duration.millis(2000), circle);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setFromValue(1);
		ft.setToValue(0.1);
		ft.play();

	}
}
