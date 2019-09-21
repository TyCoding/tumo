package cn.tycoding.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tycoding
 * @date 2019-03-26
 */
public class IPUtil {

    private static final String UNKNOWN = "unknown";

    /**
     * 通过request.getRemoteAddr()可以获取到客户端的IP地址
     * 但是若使用Nginx等反向代理软件就不能用其获取客户端IP了，
     * 比如将http://37.12.456/index反向代理为http://tycoding.cn/index，那么通过getRemoteAddr()获取的是代理服务器的地址：127.0.0.1、192.168.1.110、unknown
     * 但反向代理时会在HTTP Header中加入x-forwarded-for信息，用以跟踪原客户端IP地址
     * <p>
     * 如果通过多级反向代理，x-forwarded-for的值并不止一个，而是一串IP值，那么x-forwarded-for中第一个非unknown的字符串是IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
