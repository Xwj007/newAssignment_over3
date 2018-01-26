package com.njfu.DTO;

/**http请求返回的最外层对象
 * code        错误码
 * msg         提示信息码
 * data        具体内容，NewJson
 */

public class ResponseInfoDTO<T> extends MessageInfoDTO {

    private T data;

    public ResponseInfoDTO() {
    }

    public ResponseInfoDTO(int code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
