package cn.tycoding.admin.dto;

/**
 * 操作状态码
 *
 * @auther TyCoding
 * @date 2018/12/12
 */
public class StatusCode {

    public static final int SUCCESS = 20000; //成功
    public static final int ERROR = 20001; //失败
    public static final int LOGINE_RROR = 20002; //用户名或密码错误
    public static final int ACCESS_ERROR = 20003; //权限不足
    public static final int REPEE_RROR = 20004; //重复操作
    public static final int PARAMETER_ERROR = 20005; //入参错误
}
