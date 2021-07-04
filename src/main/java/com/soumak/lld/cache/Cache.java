package com.soumak.lld.cache;

import com.soumak.lld.cache.exceptions.KeyNotFound;
import com.soumak.lld.cache.exceptions.StorageFullException;
import com.soumak.lld.cache.policies.EvictionPolicy;
import com.soumak.lld.cache.storage.Storage;

public class Cache<Key, Value> {

    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key,Value> storage, EvictionPolicy<Key> evictionPolicy){
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key key, Value value){
        try {
            storage.put(key,value);
            evictionPolicy.keyAccessed(key);
        }catch (StorageFullException ex){
            Key keytobedeleted = evictionPolicy.evictKey();
            storage.remove(keytobedeleted);
            put(key, value);
        }
    }

    public Value get(Key key){
        try {
            Value value = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        }catch (KeyNotFound ex){
            System.out.println("Key to be accessed does not exists");
            return null;
        }
    }
}
