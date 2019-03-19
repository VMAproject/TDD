package com.tdd.example.queue.impl;

import com.tdd.example.model.InstructionMessage;
import com.tdd.example.model.InstructionType;
import org.junit.Before;
import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MessageQueueImplTest {

    private InstructionMessage instructionMessage;

    private PriorityQueue<InstructionMessage> priorityQueue;

    private MessageQueueImpl instructionMessageQueue;

    @Before
    public void init() {
        instructionMessage = new InstructionMessage();
        instructionMessageQueue = new MessageQueueImpl();
        priorityQueue = instructionMessageQueue.getPriorityQueue();
    }

    @Test
    public void shouldEnqueueInstructionMessage() {
        instructionMessageQueue.enqueue(instructionMessage);
        assertEquals(instructionMessage, priorityQueue.peek());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenEnqueueNull() {
        instructionMessageQueue.enqueue(null);
    }

    @Test
    public void shouldDequeueInstructionMessage() {
        priorityQueue.offer(instructionMessage);
        InstructionMessage result = instructionMessageQueue.dequeue();
        assertEquals(instructionMessage, result);
        assertEquals(0, priorityQueue.size());
    }

    @Test
    public void shouldReturnNullWhenDequeueEmptyInstructionQueue() {
        InstructionMessage result = instructionMessageQueue.dequeue();
        assertNull(result);
    }

    @Test
    public void shouldDequeueSameInstructionMessagesInRightOrder() {
        InstructionMessage instructionMessage1 = createInstructionMessage(InstructionType.C);
        InstructionMessage instructionMessage2 = createInstructionMessage(InstructionType.C);
        priorityQueue.offer(instructionMessage1);
        priorityQueue.offer(instructionMessage2);

        InstructionMessage result1 = instructionMessageQueue.dequeue();
        InstructionMessage result2 = instructionMessageQueue.dequeue();

        assertEquals(instructionMessage1, result1);
        assertEquals(instructionMessage2, result2);
        assertEquals(0, priorityQueue.size());
    }

    @Test
    public void shouldDequeueInstructionMessagesWithSamePriorityInRightOrder() {
        InstructionMessage instructionMessage1 = createInstructionMessage(InstructionType.A);
        InstructionMessage instructionMessage2 = createInstructionMessage(InstructionType.A);
        priorityQueue.offer(instructionMessage1);
        priorityQueue.offer(instructionMessage2);

        InstructionMessage result1 = instructionMessageQueue.dequeue();
        InstructionMessage result2 = instructionMessageQueue.dequeue();

        assertEquals(instructionMessage1, result1);
        assertEquals(instructionMessage2, result2);
        assertEquals(0, priorityQueue.size());
    }

    @Test
    public void shouldDequeueInstructionMessagesInRightOrder() {
        InstructionMessage instructionMessage1 = createInstructionMessage(InstructionType.C);
        InstructionMessage instructionMessage2 = createInstructionMessage(InstructionType.A);
        InstructionMessage instructionMessage3 = createInstructionMessage(InstructionType.D);
        InstructionMessage instructionMessage4 = createInstructionMessage(InstructionType.B);
        priorityQueue.offer(instructionMessage1);
        priorityQueue.offer(instructionMessage2);
        priorityQueue.offer(instructionMessage3);
        priorityQueue.offer(instructionMessage4);

        InstructionMessage result1 = instructionMessageQueue.dequeue();
        InstructionMessage result2 = instructionMessageQueue.dequeue();
        InstructionMessage result3 = instructionMessageQueue.dequeue();
        InstructionMessage result4 = instructionMessageQueue.dequeue();

        assertEquals(instructionMessage2, result1);
        assertEquals(instructionMessage4, result2);
        assertEquals(instructionMessage1, result3);
        assertEquals(instructionMessage3, result4);
        assertEquals(0, priorityQueue.size());
    }

    @Test
    public void shouldPeekInstructionMessage() {
        priorityQueue.offer(instructionMessage);
        InstructionMessage result = instructionMessageQueue.peek();
        assertEquals(instructionMessage, result);
        assertEquals(1, priorityQueue.size());
    }

    @Test
    public void shouldReturnNullWhenPeekEmptyInstructionQueue() {
        InstructionMessage result = instructionMessageQueue.peek();
        assertNull(result);
    }

    @Test
    public void shouldPeekInstructionMessageForSameInstructionMessages() {
        InstructionMessage instructionMessage1 = createInstructionMessage(InstructionType.A);
        InstructionMessage instructionMessage2 = createInstructionMessage(InstructionType.A);
        priorityQueue.offer(instructionMessage1);
        priorityQueue.offer(instructionMessage2);

        InstructionMessage result1 = instructionMessageQueue.peek();
        InstructionMessage result2 = instructionMessageQueue.peek();

        assertEquals(instructionMessage1, result1);
        assertEquals(instructionMessage1, result2);
        assertEquals(2, priorityQueue.size());
    }

    @Test
    public void shouldPeekInstructionMessageForInstructionMessagesWithSamePriority() {
        InstructionMessage instructionMessage1 = createInstructionMessage(InstructionType.C);
        InstructionMessage instructionMessage2 = createInstructionMessage(InstructionType.D);
        priorityQueue.offer(instructionMessage1);
        priorityQueue.offer(instructionMessage2);

        InstructionMessage result1 = instructionMessageQueue.peek();
        InstructionMessage result2 = instructionMessageQueue.peek();

        assertEquals(instructionMessage1, result1);
        assertEquals(instructionMessage1, result2);
        assertEquals(2, priorityQueue.size());
    }

    @Test
    public void shouldPeekInstructionMessageForInstructionMessages() {
        InstructionMessage instructionMessage1 = createInstructionMessage(InstructionType.C);
        InstructionMessage instructionMessage2 = createInstructionMessage(InstructionType.A);
        InstructionMessage instructionMessage3 = createInstructionMessage(InstructionType.D);
        InstructionMessage instructionMessage4 = createInstructionMessage(InstructionType.B);
        priorityQueue.offer(instructionMessage1);
        priorityQueue.offer(instructionMessage2);
        priorityQueue.offer(instructionMessage3);
        priorityQueue.offer(instructionMessage4);

        InstructionMessage result1 = instructionMessageQueue.peek();
        InstructionMessage result2 = instructionMessageQueue.peek();
        InstructionMessage result3 = instructionMessageQueue.peek();
        InstructionMessage result4 = instructionMessageQueue.peek();

        assertEquals(instructionMessage2, result1);
        assertEquals(instructionMessage2, result2);
        assertEquals(instructionMessage2, result3);
        assertEquals(instructionMessage2, result4);
        assertEquals(4, priorityQueue.size());
    }

    @Test
    public void shouldReturnCountOfInstructionMessagesForInstructionQueue() {
        priorityQueue.offer(instructionMessage);
        int result = instructionMessageQueue.count();
        assertEquals(priorityQueue.size(), result);
    }

    @Test
    public void shouldReturnZeroWhenInstructionQueueIsEmpty() {
        int result = instructionMessageQueue.count();
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnTrueWhenInstructionQueueIsEmpty() {
        boolean result = instructionMessageQueue.isEmpty();
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenInstructionQueueIsNotEmpty() {
        priorityQueue.offer(instructionMessage);
        boolean result = instructionMessageQueue.isEmpty();
        assertFalse(result);
    }

    private InstructionMessage createInstructionMessage(InstructionType instructionType) {
        InstructionMessage message = new InstructionMessage();
        message.setInstructionType(instructionType);
        return message;
    }

}