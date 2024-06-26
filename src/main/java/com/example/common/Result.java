package com.example.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private T data;
    private String message;

    public Result(Builder<T> builder) {
        this.message = builder.message;
        this.code = builder.code;
        this.data = builder.data;
    }

    public static class Builder<T> {
        private Integer code;
        private T data;
        private String message;

        public Builder<T> success() {
            this.code = 1;
            this.message = "SUCCESS";
            return this;
        }

        public Builder<T> fail(Integer code, String msg) {
            this.code = code;
            this.message = msg;
            return this;
        }

        public Builder<T> addData(T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            return new Result<>(this);
        }
    }
    public static <T> Builder<T> newBuilder() {
        return new Builder<>();
    }
    public static <T> Result<T> genSuccessResult(T data){
        return Result.<T>newBuilder().success().addData(data).build();
    }
}
