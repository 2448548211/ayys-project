package com.xlibaba.ayys.entity;

/**
 * @author ChenWang
 * @className BaseResponseEntity
 * @date 2020/10/13 19:21
 * @since JDK 1.8
 */
public class BaseResponseEntity<T> {
    //状态值
    private final static int SUCCESS_CODE = 200;
    private final static int ERROR_CODE= 404;
    private final static String SUCCESS_MSG = "OK";
    private final static String ERROR_MSG = "error";
    //属性值
    private Integer code;
    private String msg;
    private T data;
    /**
     * 响应成功的数据对象，默认响应码
     * @param data  传输到前端的数据对象
     * @return com.qf.chen.app.entity.BaseResponseEntity<T>
     * @author ChenWang
     * @date 2020/10/13 20:02
     */
    public static <T> BaseResponseEntity<T> success(T data){
        return success(SUCCESS_CODE,SUCCESS_MSG,data);
    }
    /**
     * 响应成功的数据对象，带有自定义的成功响应码和成功信息
     * @param status    指定的成功码
	 * @param msg       指定的成功提示信息
	 * @param data      需要传输到前端的数据对象
     * @return com.qf.chen.app.entity.BaseResponseEntity<T>
     * @author ChenWang
     * @date 2020/10/13 20:02
     */
    public static <T> BaseResponseEntity<T> success(int status,String msg,T data){
        BaseResponseEntity<T> entity = new BaseResponseEntity<>();
        entity.setCode(status);
        entity.setMsg(msg);
        entity.setData(data);
        return entity;
    }
    /**
     * 响应错误的数据对象，默认的错误码
     * @return com.qf.chen.app.entity.BaseResponseEntity<T>
     * @author ChenWang
     * @date 2020/10/13 20:00
     */
    public static <T> BaseResponseEntity<T> error(){
        return error(ERROR_CODE,ERROR_MSG);
    }
    /**
     * 响应错误的数据对象，自定义错误码和错误信息
     * @param status    指定的错误码
	 * @param msg       指定的错误提示信息
     * @return com.qf.chen.app.entity.BaseResponseEntity<T>
     * @author ChenWang
     * @date 2020/10/13 20:00
     */
    public static <T> BaseResponseEntity<T> error(int status,String msg){
        BaseResponseEntity<T> entity = new BaseResponseEntity<>();
        entity.setCode(status);
        entity.setMsg(msg);
        return entity;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
