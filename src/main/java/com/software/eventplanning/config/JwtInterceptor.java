package com.software.eventplanning.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.service.ILoginService;
import com.software.eventplanning.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//实现了 Spring MVC 拦截器接口的类，用于处理请求前的验证和处理逻辑。
public class JwtInterceptor implements HandlerInterceptor {

    //一个静态的 usersMap，用于缓存已登录的用户信息。
    private static Map<String, Users> usersMap = new HashMap<>();
    @Resource
    //通过 Spring 的 @Resource 注解注入登录服务接口的实现类。
    private ILoginService loginService;

    @Override
    //重写了拦截器的 preHandle 方法，该方法在请求处理之前执行。
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.error("401 error!requestURL is:{}",request.getRequestURL());
            throw new ServiceException(401, "用户未登录");
        }
        //从请求头中取得 JWT token
        String token = authHeader.substring(7);
        //验证token
        Claims claims = JwtUtil.checkToken(token);

        if( claims == null || StringUtils.isBlank(claims.getSubject())){
            // Token 无效或用户未登录，抛出异常
            throw new ServiceException(401, "用户未登录,请重新登录");
        }
        log.info("--------------------token username is:{}", claims.getSubject());
        if (!usersMap.containsKey(claims.getSubject())) {
            Users users = loginService.getBaseMapper().selectOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, claims.getSubject()));
            if (users == null) {
                log.error("not found users by username:{}", claims.getSubject());
                throw new ServiceException(401, "用户未登录或者超时,请重新登录");
            }
            usersMap.put(users.getUsername(), users);
            request.setAttribute("users", users);
        }else{
            request.setAttribute("users", usersMap.get(claims.getSubject()));
        }
        return true;
    }
}
