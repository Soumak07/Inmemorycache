package com.soumak.lld.cache.storage;

import com.soumak.lld.cache.exceptions.KeyNotFound;

public interface Storage<Key, Value> {

    void put(Key key, Value value);
    Value get(Key key) throws KeyNotFound;
    void remove(Key key);
}
