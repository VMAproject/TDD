package com.tdd.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@EqualsAndHashCode
public class InstructionMessage {

    private InstructionType instructionType;

    private String productCode;

    private Integer quantity;

    private Integer uom;

    private LocalDateTime timestamp;

}
