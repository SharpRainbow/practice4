package ru.mirea.panin.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MyLooper myLooper;
    private Date date;
    private int years = 19;
    private String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = new Date();
        myLooper = new MyLooper();
        myLooper.start();
    }

    public void onClick(View v){
        Date now = new Date();
        int pass = now.getMinutes() - date.getMinutes();
        if (pass >= 1) {
            date = new Date();
            years += pass;
        }
        if(years < 22)
            pos = "Student";
        else if(years < 24)
            pos = "Junior";
        else if(years < 26)
            pos = "Middle";
        else
            pos = "Senior";
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("YEARS", Integer.toString(years));
        bundle.putString("POS", pos);
        msg.setData(bundle);
        if (myLooper != null){
            myLooper.handler.sendMessage(msg);
        }
    }
}