package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MemberCheckIdDuplicateAction;
import action.MemberLoginProAction;
import action.MemberLogoutAction;
import action.MemberJoinAction;
import vo.ActionForward;

@WebServlet("*.me")
public class Frontcontroller_me extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	if(command.equals("/login_form.me")) {
    		forward = new ActionForward();
    		forward.setPath("member/mem_loginForm.jsp");
    		forward.setRedirect(false);
    	}else if(command.equals("/login_pro.me")) {
    		action = new MemberLoginProAction();
    		
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/logout.me")) {
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/join_form.me")) {
    		forward = new ActionForward();
    		forward.setPath("member/mem_joinForm.jsp");
    		forward.setRedirect(false);
    	}else if(command.equals("/MemberCheckId.me")) {
			forward = new ActionForward();
			forward.setPath("./member/check_id.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/CheckIdDuplicate.me")) {
			action = new MemberCheckIdDuplicateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/mem_join.me")) {
    		action = new MemberJoinAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}else if(command.equals("/mem_joinSuccess.me")) {
    		forward = new ActionForward();
			forward.setPath("member/mem_joinSuccess.jsp");	
			forward.setRedirect(true);
    	}else if(command.equals("/mypage.me")) {
    		forward = new ActionForward();
    		forward.setPath("member/mem_mypage.jsp");
    		forward.setRedirect(false);
    	}else if(command.equals("/basket.me")) {
    		forward = new ActionForward();
    		forward.setPath("member/mem_basket.jsp");
    		forward.setRedirect(false);
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
