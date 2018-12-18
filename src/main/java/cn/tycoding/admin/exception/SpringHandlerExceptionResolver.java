package cn.tycoding.admin.exception;

import cn.tycoding.admin.dto.HttpExceptionCode;
import cn.tycoding.admin.dto.Result;
import cn.tycoding.admin.enums.HttpExceptionEnum;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP全局请求异常处理
 * <p>
 * SpringMVC统一处理异常信息有三种方式：
 * 1. 使用@ExceptionHandler
 * 2. 实现HandlerExceptionResolver接口
 * 3. 使用@Controlleradvice接口
 * </p>
 *
 * @auther TyCoding
 * @date 2018/12/18
 */
@Component
public class SpringHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 设置自己定义的异常处理器优先级最高
     * 虽然我们实现了HandlerExceptionResolver接口来处理全局异常信息
     * 但，当然SpringMVC有自己的处理机制，而当程序发生异常时总是先执行自己内置的异常处理机制，也就是页面上展示的英文信息
     * 为了解决让SpringMVC遇到错误时执行自己定义的全局异常处理类，可以设置Ordered优先级别为最高。当然内置的异常处理类也继承了Ordered接口
     */
    private int order = Ordered.HIGHEST_PRECEDENCE;

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * 通过FastJsonView对象将需要返回的数据封装为JSON格式并通过ModelAndView对象返回给页面
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Result result = doResolveException(httpServletRequest, httpServletResponse, o, e);

        ModelAndView mv = new ModelAndView();
        // 使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", result.getCode());
        attributes.put("data", result.getData());
        view.setAttributesMap(attributes);
        mv.setView(view);
        logger.debug("异常:" + e.getMessage(), e);
        return mv;
    }

    /**
     * copy自 {@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver}
     * 加入自定义异常处理，将400, 404, 405, 406, 500, 503 异常信息捕获并返回
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    protected Result doResolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        try {
            if (ex instanceof HttpRequestMethodNotSupportedException) {
                return new Result(HttpExceptionCode.NOT_SUPPORTED_METHOD_EXCEPTION, HttpExceptionEnum.NOT_SUPPORTED_METHOD_EXCEPTION);
            }

            if (ex instanceof HttpMediaTypeNotSupportedException) {
                return new Result(HttpExceptionCode.NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION, HttpExceptionEnum.NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION);
            }

            if (ex instanceof HttpMediaTypeNotAcceptableException) {
                return new Result(HttpExceptionCode.NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION, HttpExceptionEnum.NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION);
            }

            if (ex instanceof MissingPathVariableException) {
                return new Result(HttpExceptionCode.MISSING_PATH_VARIABLE, HttpExceptionEnum.MISSING_PATH_VARIABLE);
            }

            if (ex instanceof MissingServletRequestParameterException) {
                return new Result(HttpExceptionCode.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION, HttpExceptionEnum.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
            }

            if (ex instanceof ServletRequestBindingException) {
                return new Result(HttpExceptionCode.SERVLET_REQUEST_BINDING_EXCEPTION, HttpExceptionEnum.SERVLET_REQUEST_BINDING_EXCEPTION);
            }

            if (ex instanceof ConversionNotSupportedException) {
                return new Result(HttpExceptionCode.NOT_SUPPORT_CONVERSION_EXCEPTION, HttpExceptionEnum.NOT_SUPPORT_CONVERSION_EXCEPTION);
            }

            if (ex instanceof TypeMismatchException) {
                return new Result(HttpExceptionCode.TYPE_MISMATCH_EXCEPTION, HttpExceptionEnum.TYPE_MISMATCH_EXCEPTION);
            }

            if (ex instanceof HttpMessageNotReadableException) {
                return new Result(HttpExceptionCode.NOT_READABLE_HTTP_MESSAGE_EXCEPTION, HttpExceptionEnum.NOT_READABLE_HTTP_MESSAGE_EXCEPTION);
            }

            if (ex instanceof HttpMessageNotWritableException) {
                return new Result(HttpExceptionCode.NOT_WRITABLE_HTTP_MESSAGE_EXCEPTION, HttpExceptionEnum.NOT_WRITABLE_HTTP_MESSAGE_EXCEPTION);
            }

            if (ex instanceof MethodArgumentNotValidException) {
                return new Result(HttpExceptionCode.NOT_VALID_METHOD_ARGUMENT_EXCEPTION, HttpExceptionEnum.NOT_VALID_METHOD_ARGUMENT_EXCEPTION);
            }

            if (ex instanceof MissingServletRequestPartException) {
                return new Result(HttpExceptionCode.MISSING_SERVLET_REQUEST_PART_EXCEPTION, HttpExceptionEnum.MISSING_SERVLET_REQUEST_PART_EXCEPTION);
            }

            if (ex instanceof BindException) {
                return new Result(HttpExceptionCode.BIND_EXCEPTION, HttpExceptionEnum.BIND_EXCEPTION);
            }

            if (ex instanceof NoHandlerFoundException) {
                return new Result(HttpExceptionCode.NO_HANDLER_FOUND_EXCEPTION, HttpExceptionEnum.NO_HANDLER_FOUND_EXCEPTION);
            }

            if (ex instanceof AsyncRequestTimeoutException) {
                return new Result(HttpExceptionCode.ASYNC_REQUEST_TIMEOUT_EXCEPTION, HttpExceptionEnum.ASYNC_REQUEST_TIMEOUT_EXCEPTION);
            }
        } catch (Exception var6) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Failure while trying to resolve exception [" + ex.getClass().getName() + "]", var6);
            }
        }
        return null;
    }

}
