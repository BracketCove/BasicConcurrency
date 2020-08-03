package com.wiseassblog.basicconcurrency;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose:
 * To handle concurrency and hide the details of the back end
 */
public class StorageImpl implements IStorage<String> {
    private ApplicationExecutors exec;
    private LocalFileStorageImpl storage;

    public StorageImpl(ApplicationExecutors exec,
                       LocalFileStorageImpl storage) {
        this.exec = exec;
        this.storage = storage;
    }

    /**
     * Make a call to a backend API on the background thread, put return that
     * result on the main thread.
     *
     * @param id
     * @param callback
     */
    @Override
    public void getItem(String id, LoadStringCallback callback) {
        exec.getBackground().execute(
                //This is a lambda expression. It is called on a background worker thread
                //so it does not block the mainThread while it asks LocalFileStorageImpl for data.
                () -> {
                    //TODO remove after testing
                    Log.d("STORAGE",
                            Thread.currentThread().getName()
                    );

                    Object data;
                    try {
                        data = storage.get();
                    } catch (IOException e) {
                        data = e;
                    }

                    final Object finalData = data;
                    exec.getMainThread().execute(
                            //This is another lambda expression. It is called on the main thread
                            //since that is where the
                            () -> {
                                //TODO remove after testing
                                Log.d("STORAGE",
                                        Thread.currentThread().getName()
                                );

                                if (finalData instanceof String) callback.onSuccess(
                                        (String) finalData
                                );
                                else {
                                    callback.onException(
                                            (Exception)finalData
                                    );
                                }
                            });
                });

    }

    @Override
    public void getItems(LoadListCallback callback) {
        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        callback.onSuccess(list);
    }
}
