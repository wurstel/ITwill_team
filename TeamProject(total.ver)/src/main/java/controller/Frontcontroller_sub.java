package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

@WebServlet("*.sub")
public class Frontcontroller_sub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String command = request.getServletPath();				//servlet 주소 -> command
    	System.out.println("서블릿 주소(command) : " + command);
    	Action action = null;
    	ActionForward forward = null;
    	HttpSession session = request.getSession();
    	/*
    	 * .cu : Customer Center
    	 * .st : store
    	 * .sub : subscribe
    	 * .me : member
    	 * .ad : admin
    	 * .pm : payment
    	 */
    	if(command.equals("/subscribePage.sub")) {		//구독페이지로 이동
    		forward = new ActionForward();
    		forward.setPath("subscribe/sub_level.jsp");
    		forward.setRedirect(false);
    	} else if(command.equals("/sub_determine_st.sub")) {
    		forward = new ActionForward();
    		if(session.getAttribute("sId") == null || session.getAttribute("sId") == "") {	//로그인안되있으면 *현재는 로그인폼이 없어 반대로 설정되어있음
    			System.out.println(session.getAttribute("sId"));
    			forward.setPath("member/mem_loginForm.jsp");
    			forward.setRedirect(true);
    		} else {									//로그인시
    			forward.setPath("subscribe/subType/sub_standard.jsp");
    			forward.setRedirect(false);
    		}
    	} else if(command.equals("/sub_determine_pre.sub")) {
    		forward = new ActionForward();
    		if(session.getAttribute("sId") == null || session.getAttribute("sId") == "") {	//로그인안되있으면 *현재는 로그인폼이 없어 반대로 설정되어있음
    			forward.setPath("member/mem_loginForm.jsp");
    			forward.setRedirect(true);
    		} else {									//로그인시
    			forward.setPath("subscribe/subType/sub_premium.jsp");
    			forward.setRedirect(false);
    		}
    	} else if(command.equals("/sub_volume.sub")) {
    		forward = new ActionForward();
    		forward.setPath("subscribe/sub_volume.jsp");
    		forward.setRedirect(false);
    	} else if(command.equals("/sub_destination.sub")) {
    		forward = new ActionForward();
    		forward.setPath("subscribe/sub_destination.jsp");
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
