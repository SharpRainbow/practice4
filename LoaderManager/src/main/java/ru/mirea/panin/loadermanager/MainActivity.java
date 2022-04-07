package ru.mirea.panin.loadermanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public final String TAG = this.getClass().getSimpleName();
    private int LoaderID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportLoaderManager().initLoader(LoaderID, bundle, this);
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARG_WORD, String.valueOf(((EditText) findViewById(R.id.editText)).getText()));
        LoaderManager.getInstance(this).restartLoader(LoaderID, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LoaderID){
            System.out.println("started");
            Toast.makeText(this, "onCreateLoader: " + id, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, args);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(loader.getId() == LoaderID){
            Log.d(TAG, "onLoadFinished " + data);
            Toast.makeText(this, "onLoadFinished: " + data, Toast.LENGTH_SHORT).show();
            ((TextView) findViewById(R.id.textView)).setText(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }
}