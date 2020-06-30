package cn.tycoding.biz.service;


import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.biz.entity.SysLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface LoginLogService extends IService<SysLoginLog> {

    IPage<SysLoginLog> list(SysLoginLog log, QueryPage queryPage);

    void delete(Long id);

    void saveLog(SysLoginLog log);
}
