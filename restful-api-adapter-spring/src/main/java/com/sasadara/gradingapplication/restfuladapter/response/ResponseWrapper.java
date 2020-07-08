package com.sasadara.gradingapplication.restfuladapter.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This is a Data Transfer Object used as a wrapper around HTTP response body
 *
 * @param <T>
 */
@Getter
@NoArgsConstructor
public class ResponseWrapper<T> {

    private T data;

    public ResponseWrapper(T data) {
        this.data = data;
    }

}
