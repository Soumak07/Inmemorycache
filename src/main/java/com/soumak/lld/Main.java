package com.soumak.lld;

import com.soumak.lld.cache.Cache;
import com.soumak.lld.cache.policies.LRUEvictionPolicy;
import com.soumak.lld.cache.storage.HashMapStorage;

public class Main {

    public static void main(String[] args){

        // Driver class to test it out
        Cache<String, TestClass> testcache = new Cache<String, TestClass>(new HashMapStorage<String, TestClass>(3), new LRUEvictionPolicy<String>());

        testcache.put("testcache1", new TestClass(1));
        testcache.put("testcache2", new TestClass(2));
        testcache.put("testcache3", new TestClass(3));
        testcache.put("testcache4", new TestClass(4));
        try {
            System.out.println(testcache.get("testcache1").toString());  // null pointer exception expected as get will not fetch anything
        }catch (NullPointerException e){
            System.out.println("NUll pointer exception : Expected, as testcache1 has been evicted");
        }

        System.out.println(testcache.get("testcache2").toString());
        System.out.println(testcache.get("testcache3").toString());
        System.out.println(testcache.get("testcache4").toString());
        testcache.put("testcache5", new TestClass(5));
        System.out.println("--------");
        System.out.println(testcache.get("testcache4").toString());

        try {
            System.out.println(testcache.get("testcache2").toString());  // null pointer exception expected as get will not fetch anything
        }catch (NullPointerException ex){
            System.out.println("NUll pointer exception : Expected, as testcache2 has been evicted");
        }

    }
}


class TestClass {
    int id;

    public TestClass(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "This test class id is : "+ this.id;
    }
}