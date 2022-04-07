package ru.mirea.panin.looper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread{
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    public void run(){
        Log.d("MyLooper", "run");
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.d("MyLooper", "AGE:" + msg.getData().getString("YEARS")
                        + " POSITION:" + msg.getData().getString("POS")) ;
            }
        };
        Looper.loop();
    }
}
