package com.tapusd.springmybaties.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static Response<Object> getSuccessResponse() {
        return new Response<Object>()
                .setCode(HttpStatus.OK.value())
                .setMessage("Success");
    }

    public static <T> Response<T> getSuccessDataResponse(T data) {
        return new Response<T>()
                .setCode(HttpStatus.OK.value())
                .setMessage("Success")
                .setData(data);
    }
}
