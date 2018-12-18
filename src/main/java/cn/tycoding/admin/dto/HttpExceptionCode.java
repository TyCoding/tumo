package cn.tycoding.admin.dto;

/**
 * HTTP请求异常状态码
 *
 * @auther TyCoding
 * @date 2018/12/18
 */
public class HttpExceptionCode {

    public static final int NOT_SUPPORTED_METHOD_EXCEPTION = 405; //请求方法不支持异常
    public static final int NOT_SUPPORTED_MEDIA_TYPE_EXCEPTION = 415; //不支持的媒体类型异常
    public static final int NOT_ACCEPTABLE_MEDIA_TYPE_EXCEPTION = 406; //不接受的媒体类型异常
    public static final int MISSING_PATH_VARIABLE = 500; //丢失URL实例变量异常
    public static final int MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = 400; //丢失请求参数异常
    public static final int SERVLET_REQUEST_BINDING_EXCEPTION = 400; //请求参数绑定异常
    public static final int NOT_SUPPORT_CONVERSION_EXCEPTION = 500; //不支持的参数转化异常
    public static final int TYPE_MISMATCH_EXCEPTION = 400; //类型不匹配异常
    public static final int NOT_READABLE_HTTP_MESSAGE_EXCEPTION = 400; //Http消息不可读异常
    public static final int NOT_WRITABLE_HTTP_MESSAGE_EXCEPTION = 500; //Http消息不可写异常
    public static final int NOT_VALID_METHOD_ARGUMENT_EXCEPTION = 400; //方法参数无效异常
    public static final int MISSING_SERVLET_REQUEST_PART_EXCEPTION = 400; //缺少Servlet请求部分异常
    public static final int BIND_EXCEPTION = 400; //绑定例外
    public static final int NO_HANDLER_FOUND_EXCEPTION = 404; //没有找到资源异常
    public static final int ASYNC_REQUEST_TIMEOUT_EXCEPTION = 503; //异步请求超时异常
}
