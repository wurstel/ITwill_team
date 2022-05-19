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
import action.QNAListAction;
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
	
	String command = request.getServletPath();
	
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
		forward = new ActionForward();
		forward.setPath("./customerCenter/qna_board_write.jsp");
		forward.setRedirect(false);
	} else if(command.equals("/QNAWritePro.cu")) {
		action = new QNAWriteProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNADetail.cu")) {
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
		action = new QNADeleteProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAModifyForm.cu")) {
		action = new QNAModifyFormAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAModifyPro.cu")) {
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
		action = new QNAReplyProAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(command.equals("/QNAList.cu")) {
		action = new QNAListAction();
		
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
