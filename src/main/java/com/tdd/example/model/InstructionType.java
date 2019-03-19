package com.tdd.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstructionType {

    A(Priority.HIGH),

    B(Priority.MEDIUM),

    C(Priority.LOW),

    D(Priority.LOW);

    private final Priority priority;

}
