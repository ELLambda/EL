package achievements;

import java.io.*;

/**
 * 一个读取和存储成就状况的类
 * 用于和游戏界面交互
 * 用于和读取和存储成就状况的数据类交互
 *
 * @author Andy
 */

public class AchievementsManager

{

    //存储成就的名称
    public static final String[] names = {"", "", "", "", "", "", "", "", "", ""};

    //记录成就的位置
    public static final int HEIGHT = 2;
    public static final int WIDTH = 5;
    public static final int ACHIEVEMENT_NUMBER = HEIGHT * WIDTH;
    public static final Achievement[][] AchievementsList = new Achievement[HEIGHT][WIDTH];

    //确定成就
    public final static void setAchievements() {
        int index = 0;
        while (index < names.length)
            for (int i = 0; i < HEIGHT; i++)
                for (int j = 0; j < WIDTH; j++) {
                    AchievementsList[i][j] = new Achievement(names[index], false);
                    index++;
                }

    }

    //读档
    //读rate
    public static void getAchieveCondition() {
        String line = new String();
        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    "src/achievements/achieveCondition.txt"));
            while ((line = br.readLine()) != null) {

                String[] spl = line.split("@");


                //for (int i = 0; i < ACHIEVEMENT_NUMBER; i++)
                for (int m = 0; m < HEIGHT; m++)
                    for (int n = 0; n < WIDTH; n++)


                        AchievementsList[m][n].setRate(Double.parseDouble(spl[m * WIDTH + n]));

//				System.out.println(achieveCondition[i]);
            }

//			br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //存档
    //存rate
    public static void setAchieveCondition() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "src/achievements/achieveCondition.txt", false));

            for (int m = 0; m < HEIGHT; m++)
                for (int n = 0; n < WIDTH; n++)
                    if (!((m == (HEIGHT - 1)) && (n == (WIDTH - 1)))) {
                        bw.write(String.valueOf(AchievementsList[m][n].getRate()) + "@");
                    } else {
                        bw.write(String.valueOf(AchievementsList[m][n].getRate()));
                    }


            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("成就存档成功");

    }

}


