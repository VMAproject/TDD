package com.tdd.example.receiver.impl;


import com.tdd.example.model.InstructionMessage;
import com.tdd.example.parser.MessageParser;
import com.tdd.example.queue.MessageQueue;
import com.tdd.example.receiver.MessageReceiver;
import com.tdd.example.validator.MessageValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageReceiverImpl implements MessageReceiver {

    private MessageParser<InstructionMessage> messageParser;

    private MessageValidator<InstructionMessage> messageValidator;

    private MessageQueue<InstructionMessage> messageQueue;

    @Override
    public void receive(final String message) {
        InstructionMessage instructionMessage = messageParser.parse(message);
        messageValidator.validate(instructionMessage);
        messageQueue.enqueue(instructionMessage);
    }
}
