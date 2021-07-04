package com.soumak.lld.algorithms;

public class DoublyLinkedListNode<E> {

    DoublyLinkedListNode<E> next;
    DoublyLinkedListNode<E> previous;

    E element;

    public DoublyLinkedListNode(E element) {
        this.next = null;
        this.previous = null;
        this.element = element;
    }

    public E getElement() {
        return element;
    }
}
