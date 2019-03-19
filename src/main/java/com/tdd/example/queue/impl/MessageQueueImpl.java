package com.tdd.example.queue.impl;


import com.tdd.example.model.InstructionMessage;
import com.tdd.example.queue.MessageQueue;

import java.util.PriorityQueue;

public class MessageQueueImpl implements MessageQueue<InstructionMessage> {

    private PriorityQueue<InstructionMessage> priorityQueue;

    MessageQueueImpl() {
        priorityQueue = new PriorityQueue<>((m1, m2) -> {
            Integer value1 = getValue(m1);
            Integer value2 = getValue(m2);
            if (value1.equals(value2)) {
                return 1;
            } else return value1 - value2;
        });
    }

    @Override
    public void enqueue(InstructionMessage message) {
        priorityQueue.offer(message);
    }

    @Override
    public InstructionMessage dequeue() {
        return priorityQueue.poll();
    }

    @Override
    public InstructionMessage peek() {
        return priorityQueue.peek();
    }

    @Override
    public int count() {
        return priorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    PriorityQueue<InstructionMessage> getPriorityQueue() {
        return priorityQueue;
    }

    private Integer getValue(final InstructionMessage message) {
        return message.getInstructionType().getPriority().getValue();
    }

}
