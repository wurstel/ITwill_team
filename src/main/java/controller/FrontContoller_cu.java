package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.CustomerCenterAction;
import action.QNADeleteProAction;
import action.QNADetailAction;
import action.QNAModifyFormAction;
import action.QNAModifyProAction;
import action.QNAReplyFormAction;
import action.QNAReplyProAction;
import action.QNAWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class frontContoller
 */
@WebServlet("*.cu")
public class FrontContoller_cu extends HttpServlet { protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	
	String command = request.getServletPath();				//servlet 주소 -> command
	
	System.out.println("서블릿 주소(command) : " + command);
	
	Action action = null;
	ActionForward forward = null;
	
	/*
	 * .cu : Customer Center
	 * .st : store
	 * .sub : subscribe
	 * .me : member
	 * .ad : admin
	 * .pm : payment
	 */
	if(command.equals("/CustomerCenter.cu")) {
		action = new CustomerCenterAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAWriteForm.cu")) {
		// board 디렉토리의 qna_board_write.jsp 페이지로 포워딩
		// => 포워딩 대상이 뷰페이지(*.jsp)일 경우 Dispatcher 방식 포워딩
		//    (ActionForward 객체 생성 및 URL 과 포워딩 방식(Dispatcher)을 저장)
		forward = new ActionForward();
		forward.setPath("./customerCenter/qna_board_write.jsp");
		forward.setRedirect(false); // Dispatcher 방식(생략 가능)
		// => Dispatcher 방식이므로 jsp 페이지 주소가 노출되지 않고
		//    이전에 요청된 서블릿 주소(BoardWriteForm.bo)가 그대로 유지됨
	} else if(command.equals("/QNAWritePro.cu")) {
		// 비즈니스 로직 처리를 위한 Action 클래스에 접근
		// => 글쓰기 작업 요청을 위해 BoardWriteProAction 인스턴스 생성 후 execute() 메서드 호출
		// => 생성된 인스턴스를 부모 타입인 Action 타입으로 업캐스팅하여 다루기
		action = new QNAWriteProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNADetail.cu")) {
		// 비즈니스 로직 처리를 위해 BoardDetailAction 클래스의 execute() 메서드 호출
		action = new QNADetailAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNADeleteForm.cu")) {
		forward = new ActionForward();
		forward.setPath("./customerCenter/qna_board_delete.jsp");
		forward.setRedirect(false); // Dispatcher 방식(생략 가능)
	} else if(command.equals("/QNADeletePro.cu")) {
		// 비즈니스 로직 처리를 위해 BoardDeleteProAction 클래스의 execute() 메서드 호출
		action = new QNADeleteProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAModifyForm.cu")) {
		// 비즈니스 로직 처리를 위해 BoardModifyFormAction 클래스의 execute() 메서드 호출
		action = new QNAModifyFormAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAModifyPro.cu")) {
		// 비즈니스 로직 처리를 위해 BoardModifyProAction 클래스의 execute() 메서드 호출
		action = new QNAModifyProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAReplyForm.cu")) {
		action = new QNAReplyFormAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAReplyPro.cu")) {
		// 비즈니스 로직 처리를 위해 BoardModifyProAction 클래스의 execute() 메서드 호출
		action = new QNAReplyProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	
	
	
	//forward에서 isRedirect가 true -> redirect, false -> dispatcher
	if(forward != null) {
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}
	

}



protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doProcess(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doProcess(request, response);
}

}
