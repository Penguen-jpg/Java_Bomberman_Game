package Utility;

public class Timer {
    private int fps = 60;//60 frames per second(每秒要呼叫tick和render60次)
    private double timePerTick = 1000000000 / fps;//執行一次tick和render的時間
    private double deltaTime = 0.0;//呼叫下次tick和render前還有多少時間
    private long now;//這張畫面開始的時間
    private long lastTime = System.nanoTime();//上一張畫面開始的時間
    public int ticks = 0;//目前做了幾次tick

    //檢查是否要呼叫tick和render
    public boolean check() {
        now = System.nanoTime();//更新現在時間
        //(經過時間)/(處理一次需要的時間)=(目前處理的進度)，例如:經過2秒，總共需要3秒，2/3就是目前的進度
        deltaTime += (now - lastTime) / timePerTick;
        lastTime = now;//更新過去時間

        //時間到
        if (deltaTime >= 1) {
            deltaTime--;
            return true;
        }
        return false;
    }
}
