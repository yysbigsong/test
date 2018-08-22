package com.test.Filter;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@MultipartConfig//servlet3.0上传文件需要添加的注解
public class UploadFilter implements Filter {
    private String savePath;//上传文件存储路径

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        savePath = filterConfig.getInitParameter("savePath");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Collection<Part> parts = request.getParts();
        if (parts.size() == 1) {
            Part part = request.getPart("file");
            String header = part.getHeader("content-disposition");
            String fileName = getFileName(header);
            part.write(savePath + File.separator + fileName);
        } else {
            for (Part part : parts) {
                String header = part.getHeader("content-disposition");
                String fileName = getFileName(header);
                part.write(savePath+File.separator+fileName);
            }
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('上传成功');history.go(-1);</script>");
    }

    @Override
    public void destroy() {

    }
    public String getFileName(String header) {
        String[] tempArr1 = header.split(";");
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        return fileName;
    }


}
