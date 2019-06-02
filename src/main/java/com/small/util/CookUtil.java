package com.small.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class CookUtil {

    private final static  String COOK_DOMAIN =".small.com";
    private final static  String COOK_NAME = "small_login_token";

    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cks = request.getCookies();
        if (cks != null){
            for (Cookie ck : cks){
                log.info("read cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
                if (StringUtils.equals(ck.getName(),COOK_NAME)){
                    log.info("return cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;

    }

    public static void writeLoginToken(HttpServletResponse response,String token){
        Cookie ck = new Cookie(COOK_NAME,token);
        ck.setDomain(COOK_DOMAIN);
        ck.setPath("/");//代表设置在根目录
        ck.setHttpOnly(true);
        //单位是秒。
        //如果这个maxage不设置的话，cookie就不会写入硬盘，而是写在内存。只在当前页面有效。
        ck.setMaxAge(60 * 60 * 24 * 365);//如果是-1，代表永久
        log.info("write cookName:{} cookValue:{}",ck.getName(),ck.getValue());
        response.addCookie(ck);
    }

    public static void delLoginToken(HttpServletResponse response,HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                {
                    if (StringUtils.equals(ck.getName(), COOK_NAME)) {
                        ck.setDomain(COOK_DOMAIN);
                        ck.setPath("/");
                        ck.setMaxAge(0);//设置成0，代表删除此cookie。
                        log.info("del cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                        response.addCookie(ck);
                        return;
                    }
                }
            }
        }
    }
}
