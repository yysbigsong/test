package com.test.service;


import com.test.dao.DBConnect;
import com.test.utils.PasswordMD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirm", urlPatterns = "/confirm")
@MultipartConfig
public class ConfirmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //允许跨域的主机地址
        resp.setHeader("Access-Control-Allow-Origin","*");
        //允许跨域的请求方式GET，POST，HEAD等
        resp.setHeader("Access-Control-Allow-Methods","*");
        //重新预检验跨域的缓存时间
        resp.setHeader("Access-Control-Max-Age","3600");
        //是否携带cookie
        resp.setHeader("Access-Control-Allow-Credentials","false");
        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter("name");
        if (type.equals("login")) {
            String username = req.getParameter("username");
            String pwd = PasswordMD5.md5Password(req.getParameter("password"));
            boolean b = new DBConnect().check(username, pwd);
            if (b) {
                req.getServletContext().removeAttribute("id");
                /*req.getRequestDispatcher("/WEB-INF/html/index.html").forward(req,resp);*/
                req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);
            } else {
                resp.sendRedirect("fail.html");
            }
        } else if (type.equals("register")) {
            String username = req.getParameter("username");
            String pwd =PasswordMD5.md5Password(req.getParameter("password"));
            new DBConnect().login(username, pwd);
            resp.sendRedirect("index.html");
        }
    }
}
