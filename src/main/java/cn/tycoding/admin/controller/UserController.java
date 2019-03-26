package cn.tycoding.admin.controller;

import cn.tycoding.admin.annotation.Log;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Setting;
import cn.tycoding.admin.entity.User;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.UserService;
import cn.tycoding.admin.utils.PasswordHelper;
import cn.tycoding.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/user")
public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(userService.findById(id));
    }

    @PostMapping(value = "/save")
    @Log("新增用户")
    public ResponseCode save(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping("/update")
    @Log("更新用户")
    public ResponseCode update(@RequestBody User user) {
        try {
            userService.update(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    @Log("删除用户")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            userService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @GetMapping("/getSetting")
    public ResponseCode getSetting() {
        return ResponseCode.success(userService.findSetting());
    }

    @PostMapping("/updateSetting")
    @Log("更新系统设置")
    public ResponseCode updateSetting(@RequestBody Setting setting) {
        try {
            userService.updateSetting(setting);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
