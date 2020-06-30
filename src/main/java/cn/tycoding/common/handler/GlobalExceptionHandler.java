package cn.tycoding.common.handler;

import cn.tycoding.common.constants.CommonConstant;
import cn.tycoding.common.exception.GlobalException;
import cn.tycoding.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 在前后端分离项目中，请求都是异步的，请求执行过程无论是否正常都应该返回指定JSON格式对象（R）
 * 但是在单体项目中，请求不一定是异步的，返回JSON数据可能也并不友好，此时需要返回一个自定义错误页面
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R exception(Exception e) {
        log.error("内部错误, {}", e.getMessage());
        e.printStackTrace();
        return new R(e);
    }

    @ExceptionHandler(value = GlobalException.class)
    public R globalExceptionHandle(GlobalException e) {
        log.error("全局异常, {}", e.getMessage());
        e.printStackTrace();
        return new R<>(CommonConstant.ERROR, e.getMsg());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public R handleUnauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException, {}", e.getMessage());
        return new R(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public R handleAuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException, {}", e.getMessage());
        return new R(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public R handleAuthorizationException(AuthorizationException e) {
        log.error("AuthorizationException, {}", e.getMessage());
        return new R<>(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
}
