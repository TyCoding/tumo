package cn.tycoding.biz.service;

import cn.tycoding.biz.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface UserService extends IService<SysUser> {

    SysUser findByName(String username);

    SysUser checkName(String username, String currentName);

    void add(SysUser sysUser);

    void update(SysUser sysUser);

    void updatePass(SysUser sysUser);

    void delete(Long id);

    List<Long[]> chart();
}
