package cn.tycoding.biz.service;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.biz.entity.SysLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface LogService extends IService<SysLog> {

    IPage<SysLog> list(SysLog log, QueryPage queryPage);

    void delete(Long id);

    @Async
    void saveLog(ProceedingJoinPoint proceedingJoinPoint, SysLog log) throws JsonProcessingException;
}
