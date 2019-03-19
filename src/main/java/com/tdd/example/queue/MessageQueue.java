package com.tdd.example.queue;

public interface MessageQueue<E> {

    void enqueue(E e);

    E dequeue();

    E peek();

    int count();

    boolean isEmpty();

}
