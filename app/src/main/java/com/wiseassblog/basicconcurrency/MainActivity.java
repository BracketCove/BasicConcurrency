package com.wiseassblog.basicconcurrency;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IStorage.LoadStringCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StorageImpl impl = new StorageImpl(
                new ApplicationExecutors(),
                new LocalFileStorageImpl()
        );

        impl.getItem("SOME_ID", this);
    }

    @Override
    public void onSuccess(String s) {
        Toast.makeText(this, "It worked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onException(Exception e) {
        Toast.makeText(this, "It failed", Toast.LENGTH_SHORT).show();
    }
}
