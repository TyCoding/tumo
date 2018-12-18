package cn.tycoding.admin.enums;

/**
 * 全局HTTP请求异常处理
 * @auther TyCoding
 * @date 2018/12/18
 */
public enum HttpExceptionEnum {

    NOT_SUPPORTED_METHOD_EXCEPTION("请求方法不支持异常"),
    NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION("不支持的媒体类型异常"),
    NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION("不接受的媒体类型异常"),
    MISSING_PATH_VARIABLE("丢失URL实例变量异常"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("丢失请求参数异常"),
    SERVLET_REQUEST_BINDING_EXCEPTION("请求参数绑定异常"),
    NOT_SUPPORT_CONVERSION_EXCEPTION("不支持的参数转化异常"),
    TYPE_MISMATCH_EXCEPTION("类型不匹配异常"),
    NOT_READABLE_HTTP_MESSAGE_EXCEPTION("Http消息不可读异常"),
    NOT_WRITABLE_HTTP_MESSAGE_EXCEPTION("Http消息不可写异常"),
    NOT_VALID_METHOD_ARGUMENT_EXCEPTION("方法参数无效异常"),
    MISSING_SERVLET_REQUEST_PART_EXCEPTION("缺少Servlet请求部分异常"),
    BIND_EXCEPTION("绑定例外"),
    NO_HANDLER_FOUND_EXCEPTION("没有找到资源异常"),
    ASYNC_REQUEST_TIMEOUT_EXCEPTION("异步请求超时异常");

    private String info;

    public String getInfo() {
        return info;
    }

    HttpExceptionEnum(String info) {
        this.info = info;
    }
}
