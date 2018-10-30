package cn.tycoding.admin.dto;

import cn.tycoding.admin.enums.ModifyEnums;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
public class ModifyResult {

    private boolean success;
    private String info;

    public ModifyResult() {
    }

    public ModifyResult(boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public ModifyResult(boolean success, ModifyEnums modifyEnums) {
        this.success = success;
        this.info = modifyEnums.getInfo();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
