package cn.tycoding.common.utils;

import cn.tycoding.common.constants.CommonConstant;
import cn.tycoding.common.constants.enums.CommonEnum;
import lombok.*;

import java.io.Serializable;

/**
 * 全局请求响应结果封装
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Builder
@ToString
@AllArgsConstructor
public class R<T> implements Serializable {

    @Getter
    @Setter
    private int code = CommonConstant.SUCCESS;

    @Getter
    @Setter
    private Object msg = "success";

    @Getter
    @Setter
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(CommonEnum enums) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }

    public R(Throwable e) {
        super();
        this.code = CommonConstant.ERROR;
        this.msg = e.getMessage();
    }
}
