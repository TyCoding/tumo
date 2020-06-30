package cn.tycoding.common.auth;

import cn.tycoding.common.properties.ShiroProperties;
import cn.tycoding.common.properties.TumoProperties;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private TumoProperties properties;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroProperties shiro = properties.getShiro();
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        filter.setLoginUrl(shiro.getLoginUrl());
        filter.setSuccessUrl(shiro.getSuccessUrl());

        Map<String, String> filterChain = new LinkedHashMap<>();
        String[] urls = shiro.getAnonUrl().split(",");
        for (String url : urls) {
            filterChain.put(url, "anon");
        }
        filterChain.put("/**", "user");
        filter.setFilterChainDefinitionMap(filterChain);
        return filter;
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        ShiroProperties shiro = properties.getShiro();
        // 需要和前端<input name="remember">中的name对应
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        simpleCookie.setMaxAge(shiro.getCookieTimeout());
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        ShiroProperties shiro = properties.getShiro();
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode(shiro.getCipherKey()));
        return cookieRememberMeManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheManager();
    }

    @Bean
    public SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    @Bean
    public AuthSessionManager sessionManager() {
        // 自定义SessionManager，校验请求头中的Token信息
        AuthSessionManager sessionManager = new AuthSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        // 设置 session超时时间
        sessionManager.setGlobalSessionTimeout(properties.getShiro().getSessionTimeout() * 1000L);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

}
