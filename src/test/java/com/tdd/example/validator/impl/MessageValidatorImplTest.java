package com.tdd.example.validator.impl;

import com.tdd.example.exception.InstructionMessageValidationException;
import com.tdd.example.model.InstructionMessage;
import com.tdd.example.model.InstructionType;
import com.tdd.example.validator.MessageValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MessageValidatorImplTest {

    private static final String VALID_PRODUCT_CODE = "MZ89";

    private static final String INVALID_PRODUCT_CODE = "89MZ";

    private static final String PRODUCT_CODE_WITH_LETTERS = "ABCD";

    private static final String PRODUCT_CODE_WITH_NUMBERS = "1234";

    private static final Integer VALID_QUANTITY = 5678;

    private static final Integer MIN_VALID_QUANTITY = 1;

    private static final Integer INVALID_QUANTITY = 0;

    private static final Integer VALID_UOM = 50;

    private static final Integer MIN_VALID_UOM = 0;

    private static final Integer MAX_VALID_UOM = 255;

    private static final Integer FIRST_INVALID_UOM = -1;

    private static final Integer SECOND_INVALID_UOM = 256;

    private static final LocalDateTime VALID_TIMESTAMP = LocalDateTime.now().minusYears(1);

    private static final LocalDateTime MIN_VALID_TIMESTAMP =
            LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault()).plusNanos(1);

    private static final LocalDateTime MAX_VALID_TIMESTAMP = LocalDateTime.now();

    private static final LocalDateTime FIRST_INVALID_TIMESTAMP =
            LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());

    private static final LocalDateTime SECOND_INVALID_TIMESTAMP = LocalDateTime.now().plusSeconds(1);

    private static final String NOT_VALID_PRODUCT_CODE_MESSAGE =
            "Product code is not valid. Expected value: two uppercase letters followed by two digits.";

    private static final String NOT_VALID_QUANTITY_MESSAGE =
            "Quantity is not valid. Expected value: positive integer number.";

    private static final String NOT_VALID_UOM_MESSAGE =
            "Uom is not valid. Expected value: integer number between %d and %d inclusively.";

    private static final String NOT_VALID_TIMESTAMP_MESSAGE =
            "Timestamp is not valid. Expected value: timestamp being greater than unix epoch " +
                    "and less or equal than current data time.";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private InstructionMessage instructionMessage;

    private MessageValidator validator;

    @Before
    public void init() {
        validator = new MessageValidatorImpl();
        instructionMessage = new InstructionMessage();
        instructionMessage.setInstructionType(InstructionType.A);
        instructionMessage.setProductCode(VALID_PRODUCT_CODE);
        instructionMessage.setQuantity(VALID_QUANTITY);
        instructionMessage.setUom(VALID_UOM);
        instructionMessage.setTimestamp(VALID_TIMESTAMP);
    }

    @Test
    public void shouldValidateInstructionMessage() {
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWhenProductCodeIsInvalid() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(NOT_VALID_PRODUCT_CODE_MESSAGE);
        instructionMessage.setProductCode(INVALID_PRODUCT_CODE);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWhenProductConsistsOfLettersOnly() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(NOT_VALID_PRODUCT_CODE_MESSAGE);
        instructionMessage.setProductCode(PRODUCT_CODE_WITH_LETTERS);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWhenProductConsistsOfNumbersOnly() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(NOT_VALID_PRODUCT_CODE_MESSAGE);
        instructionMessage.setProductCode(PRODUCT_CODE_WITH_NUMBERS);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldValidateInstructionMessageWithMinimalValidQuantity() {
        instructionMessage.setQuantity(MIN_VALID_QUANTITY);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWhenQuantityIsInvalid() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(NOT_VALID_QUANTITY_MESSAGE);
        instructionMessage.setQuantity(INVALID_QUANTITY);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldValidateInstructionMessageWithMinimalValidUOM() {
        instructionMessage.setUom(MIN_VALID_UOM);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldValidateInstructionMessageWithMaximalValidUOM() {
        instructionMessage.setUom(MAX_VALID_UOM);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWithFirstInvalidUOM() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(String.format(NOT_VALID_UOM_MESSAGE, MIN_VALID_UOM, MAX_VALID_UOM));
        instructionMessage.setUom(FIRST_INVALID_UOM);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWithSecondInvalidUOM() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(String.format(NOT_VALID_UOM_MESSAGE, MIN_VALID_UOM, MAX_VALID_UOM));
        instructionMessage.setUom(SECOND_INVALID_UOM);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldValidateInstructionMessageWithMinimalValidTimestamp() {
        instructionMessage.setTimestamp(MIN_VALID_TIMESTAMP);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldValidateInstructionMessageWithMaximalValidTimestamp() {
        instructionMessage.setTimestamp(MAX_VALID_TIMESTAMP);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWithFirstInvalidTimestamp() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(NOT_VALID_TIMESTAMP_MESSAGE);
        instructionMessage.setTimestamp(FIRST_INVALID_TIMESTAMP);
        validator.validate(instructionMessage);
    }

    @Test
    public void shouldFailWithSecondInvalidTimestamp() {
        expectedException.expect(InstructionMessageValidationException.class);
        expectedException.expectMessage(NOT_VALID_TIMESTAMP_MESSAGE);
        instructionMessage.setTimestamp(SECOND_INVALID_TIMESTAMP);
        validator.validate(instructionMessage);
    }

}