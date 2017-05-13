package gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wenxi on 2017/5/11.
 */
public class CountThread extends Thread {

    CountDownLatch countDownLatch;
    int totalTime;
    DoubleProperty doubleProperty;


    public CountThread(CountDownLatch countDownLatch,int totalTime){
        this.countDownLatch=countDownLatch;
        this.totalTime=totalTime;
        doubleProperty = new SimpleDoubleProperty(1.0);
    }

    @Override
    public void run() {
        for(int i=0;i<totalTime;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(doubleProperty.getValue());
            doubleProperty.set((double)countDownLatch.getCount()/totalTime);
            countDownLatch.countDown();
        }
    }
}
