package com.ycx.shenzhou.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "PageFilter")
public class PageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        String display = uri;
        for (int i = uri.length(); i < 30; i++) {
            display += " ";
        }
        for (String attr : servletRequest.getParameterMap().keySet()) {
            display += attr + "=" + servletRequest.getParameter(attr) + " ";
        }
        System.out.println(display);

        String[] pages = {"/", "/home"};
        for (String allowUri : pages) {
            if (uri.equals(allowUri)) {
                req.getRequestDispatcher("/index.html").forward(servletRequest, servletResponse);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
