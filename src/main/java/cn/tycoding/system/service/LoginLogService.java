package cn.tycoding.system.service;


import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @date 2019-03-13
 */
public interface LoginLogService extends IService<SysLoginLog> {

    IPage<SysLoginLog> list(SysLoginLog log, QueryPage queryPage);

    void delete(Long id);

    void saveLog(SysLoginLog log);
}
