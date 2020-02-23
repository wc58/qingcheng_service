package com.qingcheng.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义具体数据返回格式
 */
@Data
public class R implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public R() {
    }

    /**
     * 操作成功
     * @return
     */
    public static R OK(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(com.qingcheng.pojo.entity.ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    /**
     * 操作失败
     * @return
     */
    public static R ERROR(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(com.qingcheng.pojo.entity.ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    /**
     * 链式编程
     * @param success
     * @return
     */
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String ,Object> map){
        this.setData(map);
        return this;
    }
}
