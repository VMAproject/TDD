package com.tdd.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {

    HIGH(1),

    MEDIUM(2),

    LOW(3);

    private final Integer value;
}
