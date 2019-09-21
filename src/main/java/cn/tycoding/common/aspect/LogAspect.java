package cn.tycoding.common.aspect;

import cn.tycoding.system.entity.SysLog;
import cn.tycoding.system.entity.SysUser;
import cn.tycoding.common.exception.GlobalException;
import cn.tycoding.system.service.LogService;
import cn.tycoding.common.utils.HttpContextUtil;
import cn.tycoding.common.utils.IPUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tycoding
 * @date 2019-03-26
 */
@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(cn.tycoding.common.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }
        //获取Request请求
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        //设置IP地址
        String ip = IPUtil.getIpAddr(request);
        //记录时间（毫秒）
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SysLog log = new SysLog();
        log.setUsername(sysUser.getUsername());
        log.setIp(ip);
        log.setTime(time);
        logService.saveLog(proceedingJoinPoint, log);
        return result;
    }
}
