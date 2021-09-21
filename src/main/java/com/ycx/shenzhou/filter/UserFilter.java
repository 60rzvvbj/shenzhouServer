package com.ycx.shenzhou.filter;

import com.ycx.shenzhou.service.AdminService;
import com.ycx.shenzhou.service.GuideService;
import com.ycx.shenzhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "UserFilter")
public class UserFilter implements Filter {

    @Autowired
    private UserService userService;

    @Autowired
    private GuideService guideService;

    @Autowired
    private AdminService adminService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        boolean status = false;

        // 如果是这些路径就直接放行
        String[] allowUris = {"/public", "/index.html", "/login", "/register", "/error"};
        for (String allowUri : allowUris) {
            if (uri.startsWith(allowUri)) {
                filterChain.doFilter(servletRequest, servletResponse);
                break;
            }
        }

        // 获取Cookie
        Cookie[] cookies = req.getCookies();
        String account = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")) {
                    account = cookie.getValue();
                }
            }
        }

        status = account.equals("");

        // 如果获取不到account则转发到accountError
        if (status) {
            req.setAttribute("account", account); // 设置account
        } else {
            req.getRequestDispatcher("/error/accountError").forward(servletRequest, servletResponse);
            return;
        }

        // 暂时放行
        filterChain.doFilter(servletRequest, servletResponse);
        if (true) {
            return;
        }

        // 验证token
        String token = req.getHeader("token");
        status = userService.testToken(account, token);

        if (!status) {
            req.getRequestDispatcher("/error/tokenError").forward(servletRequest, servletResponse); // 失败则转发到/tokenError
            return;
        }

        if (uri.startsWith("/guide")) {

            // 验证导游身份
            status = guideService.isGuide(account);
            if (!status) {
                req.getRequestDispatcher("/error/identityError").forward(servletRequest, servletResponse); // 失败则转发到/identityError
                return;
            }
        }

        if (uri.startsWith("/admin")) {

            // 验证管理员身份
            status = adminService.isAdmin(account);
            if (!status) {
                req.getRequestDispatcher("/error/identityError").forward(servletRequest, servletResponse); // 失败则转发到/identityError
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse); // 一切OK放行
    }
}
