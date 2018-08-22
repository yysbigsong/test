package com.test.Filter;

import com.test.dao.DBConnect;
import com.test.utils.PasswordMD5;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String type = request.getParameter("name");
        if (type.equals("login")) {
            String username = request.getParameter("username");
            String pwd = PasswordMD5.md5Password(request.getParameter("password"));
            boolean b = new DBConnect().check(username, pwd);
            if (b) {
                /*req.getRequestDispatcher("/WEB-INF/html/index.html").forward(req,resp);*/
                request.getRequestDispatcher("jsp/index.jsp").forward(request,response);
            } else {
                response.sendRedirect("fail.html");
            }
        } else if (type.equals("register")) {
            String username = request.getParameter("username");
            String pwd =PasswordMD5.md5Password(request.getParameter("password"));
            new DBConnect().login(username, pwd);
            response.sendRedirect("index.html");
        }
    }

    @Override
    public void destroy() {

    }
}
