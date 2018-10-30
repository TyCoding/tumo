package cn.tycoding.admin.exception;

import cn.tycoding.admin.enums.ModifyEnums;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
public class ModifyException extends RuntimeException {

    public ModifyException(String message) {
        super(message);
    }

    public ModifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModifyException(ModifyEnums modifyEnums) {

    }
}
