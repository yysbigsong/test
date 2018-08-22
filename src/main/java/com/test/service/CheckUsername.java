package com.test.service;

import com.test.dao.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/checkUsername")
public class CheckUsername extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        boolean b = new DBConnect().checkUsername(username);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        String check="{\"exists\":\"" + b+"\"}";
        PrintWriter out = resp.getWriter();
        out.println(check);
        out.close();
    }
}
