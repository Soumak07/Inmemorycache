package com.soumak.lld.cache.policies;


import com.soumak.lld.algorithms.DoublyLinkedList;
import com.soumak.lld.algorithms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    DoublyLinkedList<Key> linkedList;
    Map<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy() {
        this.linkedList = new DoublyLinkedList<Key>();
        this.mapper = new HashMap<Key, DoublyLinkedListNode<Key>>();
    }

    public Key evictKey() {

        DoublyLinkedListNode<Key> node = this.linkedList.getFirstNode();
        if(node != null){
            Key key = node.getElement();
            this.mapper.remove(key);
            this.linkedList.detachFirstNode();
            return key;
        }

        return null;
    }

    public void keyAccessed(Key key) {
        // get or put operation will have key accessed
        if(mapper.containsKey(key)){
            DoublyLinkedListNode<Key> node = mapper.get(key);
            this.linkedList.detachNode(node);
            this.mapper.remove(key);
        }

        this.linkedList.addElementAtLast(key);
        this.mapper.put(key, this.linkedList.getLastNode());
    }
}
