package com.atguigu.cloud.resp;

import com.atguigu.cloud.enums.ReturnCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangbo
 * @date 2025/3/22
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private T data;
    private String code;
    private String message;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> result = new ResultData<>();
        result.setData(data);
        result.setCode(ReturnCodeEnum.RC200.getCode());
        result.setMessage(ReturnCodeEnum.RC200.getMessage());
        return result;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> result = new ResultData<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> ResultData<T> fail(ReturnCodeEnum codeEnum) {
        ResultData<T> result = new ResultData<>();
        result.setCode(codeEnum.getCode());
        result.setMessage(codeEnum.getMessage());
        return result;
    }
}
