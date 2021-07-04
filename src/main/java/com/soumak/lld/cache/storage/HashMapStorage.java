package com.soumak.lld.cache.storage;

import com.soumak.lld.cache.exceptions.KeyNotFound;
import com.soumak.lld.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {

    private Map<Key,Value> hashMapStorage;
    private Integer capacity;

    public HashMapStorage(Integer capacity) {
        this.hashMapStorage = new HashMap<Key, Value>();
        this.capacity = capacity;
    }

    public void put(Key key, Value value) {

        if(isCapacityFull()) throw new StorageFullException("Storage limit reached..");

        hashMapStorage.put(key,value);
    }

    public Value get(Key key) throws KeyNotFound {

        if(hashMapStorage.containsKey(key)){
            return hashMapStorage.get(key);
        }

        throw new KeyNotFound("Key does not exists");
    }

    public void remove(Key key) {
        if(hashMapStorage.containsKey(key)){
            hashMapStorage.remove(key);
            return;
        }

        throw new KeyNotFound("Key to be deleted does not exist");
    }

    private boolean isCapacityFull(){
        if(this.hashMapStorage.size() == this.capacity) return true;
        return false;
    }
}
