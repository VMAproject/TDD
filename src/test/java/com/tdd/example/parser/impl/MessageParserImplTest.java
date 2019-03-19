package com.tdd.example.parser.impl;

import com.tdd.example.exception.InstructionMessageParseException;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MessageParserImplTest {

    private static final String VALID_MESSAGE =
            "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_INVALID_HEAD =
            "Header A MZ89 5678 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_INVALID_INSTRUCTION_TYPE =
            "InstructionMessage E MZ89 5678 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_INVALID_PRODUCT_CODE =
            "InstructionMessage A <ProductCode> 5678 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_INVALID_QUANTITY =
            "InstructionMessage A MZ89 <Quantity> 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_INVALID_UOM =
            "InstructionMessage A MZ89 5678 <UOM> 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_INVALID_TIMESTAMP_FORMAT =
            "InstructionMessage A MZ89 5678 50 2015/03/05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_MISSING_HEAD =
            "A MZ89 5678 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_MISSING_INSTRUCTION_TYPE =
            "InstructionMessage MZ89 5678 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_MISSING_PRODUCT_CODE =
            "InstructionMessage A 5678 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_MISSING_QUANTITY =
            "InstructionMessage A MZ89 50 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_MISSING_UOM =
            "InstructionMessage A MZ89 5678 2015-03-05T10:04:56.012Z\n";

    private static final String MESSAGE_WITH_MISSING_TIMESTAMP_FORMAT =
            "InstructionMessage A MZ89 5678 50\n";

    private static final String MESSAGE_WITH_MISSING_NEWLINE_CHARACTER =
            "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z";

    private static final String MESSAGE_WITH_REDUNDANT_PARTS =
            "InstructionMessage A MZ89 5678 50 2015-03-05T10:04:56.012Z redundant parts\n";

    private static final String MESSAGE_WITH_REDUNDANT_SPACES =
            "InstructionMessage  A MZ89 5678  50 2015-03-05T10:04:56.012Z \n";

    private static final String EXCEPTION_MESSAGE =
            "Message format is not valid. Expected format: \n   " +
                    "\"InstructionMessage <InstructionType> <ProductCode> <Quantity> <UOM> <Timestamp>\"\n" +
                    "Timestamp must use the format: \"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\".\n" +
                    "Message must end with a newline character.";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MessageParserImpl parser;

    @Before
    public void init() {
        parser = new MessageParserImpl();
    }

    @Test
    public void shouldParseValidMessage() {
        parser.parse(VALID_MESSAGE);
    }

    @Test
    public void shouldFailWhenMessageIsNull() {
        expectedException.expect(NullPointerException.class);
        parser.parse(null);
    }

    @Test
    public void shouldFailWhenMessageIsEmpty() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(StringUtils.EMPTY);
    }

    @Test
    public void shouldFailWhenMessageHasInvalidHead() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_INVALID_HEAD);
    }

    @Test
    public void shouldFailWhenMessageHasInvalidInstructionType() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_INVALID_INSTRUCTION_TYPE);
    }

    @Test
    public void shouldFailWhenMessageHasInvalidProductCode() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_INVALID_PRODUCT_CODE);
    }

    @Test
    public void shouldFailWhenMessageHasInvalidQuantity() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_INVALID_QUANTITY);
    }

    @Test
    public void shouldFailWhenMessageHasInvalidUOM() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_INVALID_UOM);
    }

    @Test
    public void shouldFailWhenMessageHasInvalidTimestampFormat() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_INVALID_TIMESTAMP_FORMAT);
    }

    @Test
    public void shouldFailWhenMessageHasMissingHead() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_MISSING_HEAD);
    }

    @Test
    public void shouldFailWhenMessageHasMissingInstructionType() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_MISSING_INSTRUCTION_TYPE);
    }

    @Test
    public void shouldFailWhenMessageHasMissingProductCode() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_MISSING_PRODUCT_CODE);
    }

    @Test
    public void shouldFailWhenMessageHasMissingQuantity() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_MISSING_QUANTITY);
    }

    @Test
    public void shouldFailWhenMessageHasMissingUOM() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_MISSING_UOM);
    }

    @Test
    public void shouldFailWhenMessageHasMissingTimestampFormat() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_MISSING_TIMESTAMP_FORMAT);
    }

    @Test
    public void shouldFailWhenMessageHasMissingNewlineCharacter() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_MISSING_NEWLINE_CHARACTER);
    }

    @Test
    public void shouldFailWhenMessageHasRedundantParts() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_REDUNDANT_PARTS);
    }

    @Test
    public void shouldFailWhenMessageHasRedundantSpaces() {
        expectedException.expect(InstructionMessageParseException.class);
        expectedException.expectMessage(EXCEPTION_MESSAGE);
        parser.parse(MESSAGE_WITH_REDUNDANT_SPACES);
    }

}