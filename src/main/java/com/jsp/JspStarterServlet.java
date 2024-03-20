package com.jsp;

import org.apache.coyote.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(//서버 객체 : 서블릿
        name = "hello",
        urlPatterns = "/start"
)
public class JspStarterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Welcome to my web-app");
        resp.sendRedirect("index.jsp");//요청들어온거 index에 응답해줘


    }
}
