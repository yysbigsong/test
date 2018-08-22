package com.test.Filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    private String characterEncoding;//编码方式，配置在web.xml
    private boolean enabled;//是否启用Filter，配置在web.xml
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        characterEncoding= filterConfig.getInitParameter("characterEncoding");
        enabled = "true".equalsIgnoreCase(filterConfig.getInitParameter("enabled").trim());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (enabled &&characterEncoding != null) {
            //如果启用该Filter
            servletRequest.setCharacterEncoding(characterEncoding);
            servletResponse.setCharacterEncoding(characterEncoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);//必须调用此方法,doFilter执行下一个Filter或者Servlet
    }

    @Override
    public void destroy() {
        characterEncoding=null;//销毁时清空资源
    }
}
