package com.wiseassblog.basicconcurrency;

public class ServiceLocator {
    private final IStorage storage;

    public ServiceLocator(IStorage storage) {
        this.storage = storage;
    }

    public IStorage getStorage() {
        return storage;
    }
}
