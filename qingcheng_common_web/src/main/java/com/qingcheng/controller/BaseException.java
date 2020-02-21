package com.qingcheng.controller;

import com.qingcheng.pojo.entity.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理器
 */
@ControllerAdvice
public class BaseException {

    /**
     * 拦截所有异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.ERROR().data("message", e.getMessage());
    }

}
