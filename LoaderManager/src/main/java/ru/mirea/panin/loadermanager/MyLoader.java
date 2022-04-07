package ru.mirea.panin.loadermanager;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.util.Random;

public class MyLoader extends AsyncTaskLoader<String> {
    private String firstName;
    public static final String ARG_WORD = "word";

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            firstName = args.getString(ARG_WORD);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Random rnd = new Random();
        int len = firstName.length();
        char[] list = firstName.toCharArray();
        for(int i = 0; i < firstName.length(); i++){
            int num = rnd.nextInt(len);
            char j = list[i];
            list[i] = list[num];
            list[num] = j;
        }
        firstName = String.valueOf(list);
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000);
        return firstName;
    }
}
