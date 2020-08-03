package com.wiseassblog.basicconcurrency;

import java.util.List;

public interface IStorage<T> {
    interface LoadStringCallback {
        void onSuccess(String s);

        void onException(Exception e);
    }

    interface LoadListCallback {
        void onSuccess(List<String> ls);

        void onException(Exception e);
    }

    void getItem(String taskId, LoadStringCallback callback);

    void getItems(LoadListCallback callback);
}
