package cn.tycoding.admin.dto;

/**
 * @auther TyCoding
 * @date 2018/12/12
 */
public class StatusCode {

    public static final int SUCCESS = 20000; //成功
    public static final int ERROR = 20001; //失败
    public static final int LOGINERROR = 20002; //用户名或密码错误
    public static final int ACCESSERROR = 20003; //权限不足
    public static final int REMOTEERROR = 20004; //远程调用失败
    public static final int REPEERROR = 20005; //重复操作
}
