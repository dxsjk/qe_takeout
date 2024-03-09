package org.app.interceptor;

/**
 * 作者:疏狂难除
 * 时间:2024-03-09
 */

import org.app.constant.JwtClaims;
import org.app.context.BaseContext;
import org.app.properities.JwtProperty;
import org.app.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperty JwtProperty;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(JwtProperty.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Map<String, Object> claims = JWTUtils.parseJWT(JwtProperty.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaims.EMP_ID).toString());
            BaseContext.setCurrentId(empId);
            log.info("当前员工id：", empId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            response.setStatus(401);
            return false;
        }
    }
}
