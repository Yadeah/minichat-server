package com.yadeah.minichat.common.response;

import lombok.Data;

@Data
public class HttpResponse <T> {

    private int code;

    private T data;

    private String message;
}
