package com.sasadara.gradingapplication.restfuladapter.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@ToString
public class ErrorMessageDto<T> {
    private String message;
    private List<T> errors;

    public ErrorMessageDto(String message) {
        this.message = message;
        this.errors = Collections.emptyList();
    }

    public ErrorMessageDto(String message, List<T> errors) {
        this.message = message;
        this.errors = errors;
    }
}
