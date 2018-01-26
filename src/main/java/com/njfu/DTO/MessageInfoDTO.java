package com.njfu.DTO;

public class MessageInfoDTO {

    private int code;
    private String msg;

    public MessageInfoDTO() {
    }

    public MessageInfoDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message) {
        this.msg = message;
    }
}
