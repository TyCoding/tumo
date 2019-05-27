package cn.tycoding.admin.service;


import cn.tycoding.admin.entity.LoginLog;
import cn.tycoding.common.service.BaseService;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-03-13
 */
public interface LoginLogService extends BaseService<LoginLog> {

    List<LoginLog> findByPage(LoginLog log);

    void delete(List<Long> ids);

    void saveLog(LoginLog log);
}
