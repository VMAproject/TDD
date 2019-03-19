package com.tdd.example.parser.impl;


import com.tdd.example.exception.InstructionMessageParseException;
import com.tdd.example.model.InstructionMessage;
import com.tdd.example.model.InstructionType;
import com.tdd.example.parser.MessageParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageParserImpl implements MessageParser<InstructionMessage> {

    private static final String MESSAGE_REGEX =
            "^InstructionMessage ([A-D]) ([A-Za-z0-9]+) (\\d+) (\\d+) (\\S+)\\n$";

    private static final String NOT_VALID_MESSAGE_FORMAT =
            "Message format is not valid. Expected format: \n   " +
                    "\"InstructionMessage <InstructionType> <ProductCode> <Quantity> <UOM> <Timestamp>\"\n" +
                    "Timestamp must use the format: \"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\".\n" +
                    "Message must end with a newline character.";

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final String DELIMITER = " ";

    private static final Integer INSTRUCTION_TYPE_INDEX = 1;

    private static final Integer PRODUCT_CODE_INDEX = 2;

    private static final Integer QUANTITY_INDEX = 3;

    private static final Integer UOM_INDEX = 4;

    private static final Integer TIMESTAMP_INDEX = 5;

    @Override
    public InstructionMessage parse(final String message) {
        if (message.matches(MESSAGE_REGEX)) {
            String[] words = message.trim().split(DELIMITER);
            return createInstructionMessage(words);
        } else {
            throw new InstructionMessageParseException(NOT_VALID_MESSAGE_FORMAT);
        }
    }

    private InstructionMessage createInstructionMessage(final String[] words) {
        final InstructionType instructionType = InstructionType.valueOf(words[INSTRUCTION_TYPE_INDEX]);
        final String productCode = words[PRODUCT_CODE_INDEX];
        final Integer quantity = Integer.valueOf(words[QUANTITY_INDEX]);
        final Integer uom = Integer.valueOf(words[UOM_INDEX]);
        final LocalDateTime timestamp = parseTimestamp(words[TIMESTAMP_INDEX]);

        final InstructionMessage instructionMessage = new InstructionMessage();
        instructionMessage.setInstructionType(instructionType);
        instructionMessage.setProductCode(productCode);
        instructionMessage.setQuantity(quantity);
        instructionMessage.setUom(uom);
        instructionMessage.setTimestamp(timestamp);

        return instructionMessage;
    }

    private LocalDateTime parseTimestamp(final String timestamp) {
        try {
            return LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (Exception e) {
            throw new InstructionMessageParseException(NOT_VALID_MESSAGE_FORMAT);
        }
    }

}
