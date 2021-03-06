package com.feng.framework.web.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import com.feng.framework.web.domain.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理器
 * 
 * @author feng
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler
{

    /**
     * 权限校验失败
     */
    @ExceptionHandler(AuthorizationException.class)
    public JSON handleAuthorizationException(AuthorizationException e)
    {
        log.error(e.getMessage(), e);
        return JSON.error("您没有数据的权限，请联系管理员添加");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public JSON handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e);
        return JSON.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public JSON notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return JSON.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public JSON handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return JSON.error("服务器错误，请联系管理员");
    }

}
