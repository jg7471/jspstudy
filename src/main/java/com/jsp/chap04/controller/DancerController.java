package com.jsp.chap04.controller;

import com.jsp.chap04.service.ListService;
import com.jsp.chap04.service.DeleteService;
import com.jsp.chap04.service.IDancerService;
import com.jsp.chap04.service.RegistService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿 == 컨트롤러
//클라잉너트의 요청을 파악하고 모델에게 로직을 위임하며
//응답할 view를 결정
//요청이 다 몰림
@WebServlet("*.do")//확장자 패턴 -> 앞이 뭐든간에 .do(관례)로 끝나는 요청이라면 이 서블릿 다 받겠다
// 동작하는 경로 설정(*.do //모두 다 받겠다)
// 웹페이지에 경로+jsp 노출되는거 보안성 취약
// index.jsp 에서 연결
public class DancerController extends HttpServlet {

    //인터페이스 타입의 변수를 선언해서, 상황에 맞게 서비스 객체를 갈아 끼움
    private IDancerService sv;
    private RequestDispatcher rd;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //index에서 이동해 옴
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        try {
            switch(requestURI) {

                case "/register.do":
                    System.out.println("등록 폼으로 이동시켜 달라는 요청이구나!");
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/register.jsp"); //WEB-INF 사용(보안) //객체사용해서 간단히 사용
                    rd.forward(request, response);
                    break;

                case "/regist.do":
                    System.out.println("댄서 등록 요청!");
                    sv = new RegistService(); //sv 인터페이스 객체 //
                    sv.execute(request, response); //사용자의 입력값 호출, 등록, 댄서목록 받아와 리퀘스트에 등록

                    //서비스가 댄서 목록을 request에 담아놓은 상태
                    //디스패처에게 목적지를 알려주면서, request와 response 객체를 가지고 이동하라는 명령을 내림
                    //실제 페이지가 목적지로 이동되면서, request와 respone 객체도 함께 전달됨
                    // -> jsp에서 request를 꺼내서 목록을 화면에 부려서 응답하면 끝!
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

                case "/delete.do":
                    System.out.println("삭제 요청이 들어옴!");
                    sv = new DeleteService();
                    sv.execute(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

                case "/list.do":
                    System.out.println("댄서 목록 요청이 들어옴!");
                    sv = new ListService();
                    sv.execute(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
