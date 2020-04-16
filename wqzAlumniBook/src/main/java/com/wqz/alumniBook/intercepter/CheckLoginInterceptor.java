package com.wqz.alumniBook.intercepter;

import com.google.common.base.Strings;
import com.wqz.alumniBook.annotation.CheckLogin;
import com.wqz.alumniBook.service.Impl.TokenService;
import com.wqz.alumniBook.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@RequiredArgsConstructor
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!handlerMethod.hasMethodAnnotation(CheckLogin.class)) {
            return true;
        }
        String token = CookieUtil.getCookie(request, "token");
        if (Strings.isNullOrEmpty(token)) {

            response.sendRedirect("login");
            return false;
        }
        String uid = tokenService.checkLogin(token);
        if (Strings.isNullOrEmpty(uid)) {
            response.sendRedirect("login");
            return false;
        }
        return true;
    }
}
