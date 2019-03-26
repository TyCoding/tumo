package cn.tycoding.admin.controller;

import cn.tycoding.admin.annotation.Log;
import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.SysLog;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.LogService;
import cn.tycoding.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-03-13
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @PostMapping("/list")
    public ResponseCode findByPage(QueryPage page, SysLog log) {
        return ResponseCode.success(super.selectByPageNumSize(page, () -> logService.findByPage(log)));
    }

    @Log("删除系统日志")
    @PostMapping("/delete")
    @RequiresPermissions("log:delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            logService.deleteLogs(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
