package com.jsp.chap01;

import javax.lang.model.SourceVersion;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

//역할 : http 요청 응답 처리에서 필요한 공통적인 부분을 쉽게 해결해 주는 자바 API를 이용한 클래스
@WebServlet("/hello")//우리 웹 서버에 /hello라는 URL로 요청이 오면 이 서블릿을 실행시켜라
public class HelloServlet extends HttpServlet { //1, httpServlet 상속

    //기본 생성자
    public HelloServlet() {
        //요청이 들어오면 서블릿 객체가 서버 내에서 자동 생성됨
        System.out.println("\n\n\n Hello 서블릿 작동 시작! \n\n\n");
    }

    //요청 정보 얻어보기


    @Override//요청에 관련된건 request //응답에 관련된건 response
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //오버라이딩 틀을 바꾸지마라 : 리턴/메서드/타입 바꾸지마라, 변수명 변경 괜찮

        //요청이 들어오면 자동으로 실행되는 메서도
        //매개값으로 요청에 대한 정보가 담긴 HttpServletRequest와
        //응답에 대한 정보 및 기능이 담긴 HttpServletResponse를 전달 받습니다

        //크라이언트 요청 방식
        String method = request.getMethod();

        //요청 URL
        String requestURI = request.getRequestURI();

        //요청 헤더 읽기
        String header = request.getHeader("Cache-control");

        System.out.println("method = " + method);
        System.out.println("requestURI = " + requestURI);
        System.out.println("header = " + header);
        //main실행 후 -> f12 -> network -> headers 에서 디테일 확인 가능

        //요청과 함께 넘어온 데이터 (쿼리 파라미터) 읽기
        String nick = request.getParameter("nick");
        String age = request.getParameter("age");
        System.out.println("nick = " + nick);
        System.out.println("age = " + age);

        //응답 메세지에 HTML 문서 생성해서 응답하기
        //nick 님은 xxxx년생 입니다!

        //출생 년도를 구하는 비즈니스 로직
        int year = LocalDateTime.now().getYear();
        int birthYear = year - Integer.parseInt(age); //사용자의 입력값 int 타입으로 변환

        //서블릿 클래스에서 HTML 파일 생성 방법
        response.setContentType("text/html"); //응답 객체 출력방식 세팅
        response.setCharacterEncoding("UTF-8"); //문자열 인코딩 방식 세팅

        //HTML 문서를 작성할 writer 객체를 생성
        PrintWriter w = response.getWriter(); //객체 생성

        w.write("<!DOCTYPE html>\n");
        w.write("<html>\n");
        w.write("<head>\n");
        w.write("</head>\n");

        w.write("<body>\n");
        w.write("<h1>\n");
        w.write(String.format("%s님은 %d년생입니다.\n", nick, birthYear));
        w.write("</h1>\n");
        w.write("</body>\n");

        w.write("</html>\n");

        w.flush();//지금까지 작성한 내용들을 브라우저로 전송 후 메모리 공간을 비우기
        w.close();

    }
}
