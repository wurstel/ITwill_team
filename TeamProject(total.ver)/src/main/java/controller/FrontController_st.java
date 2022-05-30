package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.swing.Action;
import action.Action;
import action.BestProductAction;
import action.NewProductAction;
import action.ProductCartAction;
import action.ProductDetailAction;
import action.ProductListAction;
import vo.ActionForward;

@WebServlet("*.st")
public class FrontController_st extends HttpServlet {
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
    	if(command.equals("/storeMain.st")) {

    		action = new ProductListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/newProduct.st")) {

    		action = new NewProductAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/bestProduct.st")) {

    		action = new BestProductAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} else if(command.equals("/productDetail.st")) {

    		action = new ProductDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	} else if(command.equals("/productCart.st")) {

    		action = new ProductCartAction();
			
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
