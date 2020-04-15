package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        // 无论如何都放行。具体能不能操作还是在具体的操作中去判断。
        // 拦截器只是负责把有请求头中包含token的令牌进行一个解析验证。
        String header = request.getHeader("Authorization");

        if(StringUtils.isNotBlank(header)){
            // 如果包含有 Authorization 头信息，就对其进行解析
            if(StringUtils.startsWith(header,"Bearer ")){
                // 得到token
                String token = StringUtils.substring(header,7);
                // 对令牌进行验证
                // 解析token
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if(StringUtils.isNotBlank(roles) && roles.equals("admin")){
                        request.setAttribute("claims_admin",token);
                    }
                    if(StringUtils.isNotBlank(roles) && roles.equals("user")){
                        request.setAttribute("claims_user",token);
                    }
                }catch (Exception e){
                    throw new RuntimeException("令牌不正确！");
                }
            }
        }
        return true;
    }

}
