package com.ycx.shenzhou.filter;

import com.ycx.shenzhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "AccountFilter")
public class AccountFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();

        // 如果是这些路径就直接放行
        String[] allowUris = {"/public", "/index.html", "/login", "/register", "/error"};
        for(String allowUri:allowUris) {
            if (uri.startsWith(allowUri)) {
                filterChain.doFilter(servletRequest, servletResponse);
                break;
            }
        }

        // 获取Cookie
        Cookie[] cookies = req.getCookies();
        String account = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("account")) {
                account = cookie.getValue();
            }
        }

        // 如果获取不到account则转发到accountError
        if (account.equals("")) {
            req.getRequestDispatcher("/error/accountError").forward(servletRequest, servletResponse);
        } else {
            req.setAttribute("account", account); // 设置account
        }

        filterChain.doFilter(servletRequest, servletResponse); // 放行
        if (true) {
            return;
        }

        // 其它路径需要验证token
        String token = req.getHeader("token");
        if (userService.testToken(account, token)) {
            filterChain.doFilter(servletRequest, servletResponse); // 放行
        } else {
            req.getRequestDispatcher("/error/tokenError").forward(servletRequest, servletResponse); // 失败则转发到/tokenError
        }
    }

    @Override
    public void destroy() {

    }
}
