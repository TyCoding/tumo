package cn.tycoding.common.auth;

import cn.tycoding.common.constants.enums.CommonEnum;
import cn.tycoding.biz.entity.SysUser;
import cn.tycoding.biz.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Shiro Realm实现
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 身份校验
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if (username == null) {
            throw new AuthenticationException(CommonEnum.TOKEN_ERROR.getMsg());
        }
        String password = new String((char[]) authenticationToken.getCredentials());
        SysUser sysUser = userService.findByName(username);

        if (sysUser == null || !sysUser.getPassword().equals(password)) {
            throw new IncorrectCredentialsException(CommonEnum.LOGIN_ERROR.getMsg());
        }

        return new SimpleAuthenticationInfo(
                sysUser,
                password,
                getName()
        );
    }

    /**
     * 权限校验
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
