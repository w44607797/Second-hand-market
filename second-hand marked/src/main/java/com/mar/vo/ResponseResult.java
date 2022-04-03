package com.mar.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author guokaifeng
 * @createDate: 2022/4/3
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private T data;
    private boolean ok;



    public static <T> ResponseResult<T> success(T t){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setOk(true);
        responseResult.setCode(200);
        responseResult.setData(t);
        return responseResult;
    }
    public static <T> ResponseResult<T> success(){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setOk(true);
        responseResult.setCode(200);
        return responseResult;
    }
    public static <T> ResponseResult<T> message(String message){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setOk(true);
        responseResult.setCode(200);
        responseResult.setMessage(message);
        return responseResult;
    }



    public static <T> ResponseResult<T> failed(int code,String error){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(code);
        responseResult.setData(null);
        responseResult.setMessage(error);
        responseResult.setOk(false);
        return responseResult;
    }

}
