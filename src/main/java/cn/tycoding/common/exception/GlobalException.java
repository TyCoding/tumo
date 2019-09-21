package cn.tycoding.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tycoding
 * @date 2019-03-09
 */
public class GlobalException extends RuntimeException {

    @Getter
    @Setter
    private String msg;

    public GlobalException(String message) {
        super(message);
        this.msg = message;
    }
}
