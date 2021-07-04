package com.soumak.lld.algorithms;

public class DoublyLinkedList<E> {

    DoublyLinkedListNode<E> head;
    DoublyLinkedListNode<E> tail;

    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
    }

    private void addNodeAtLast(DoublyLinkedListNode<E> node){
        if(head == null && tail == null) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        node.previous = tail;
        tail = node;
    }

    public void addElementAtLast(E element){
        DoublyLinkedListNode<E> node = new DoublyLinkedListNode<E>(element);
        addNodeAtLast(node);
    }

    public void detachNode(DoublyLinkedListNode<E> node){

        if(node != null){

            if(node == head) {
                detachFirstNode();
                return;
            }

            if(node == tail){
                detachLastNode();
                return;
            }

            node.previous.next = node.next;
            node.next.previous = node.previous;
        }
    }

    public DoublyLinkedListNode<E> getFirstNode(){
        if(this.head != null) return head;
        return null;
    }

    public DoublyLinkedListNode<E> getLastNode() {
        if(this.tail == null) return tail;
        return null;
    }

    public void detachFirstNode(){
        if(head != null){
            DoublyLinkedListNode<E> node = head;
            if(tail == head){
                tail = null;
                head = null;
                return;
            }
            head = head.next;
            head.previous = null;
            node.previous = null;
            node.next = null;
        }
    }

    public void detachLastNode(){
        if(tail != null){
            DoublyLinkedListNode<E> node = tail;
            if(tail == head){
                tail = null;
                head = null;
                return;
            }

            tail = tail.previous;
            tail.next = null;
            node.previous = null;
            node.next = null;
        }
    }
}
