package com.hillel.task_management_system.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETE("COMPLETE"),
    PENDING("PENDING");


    @JsonProperty("status")
    private final String value;


    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }
}
