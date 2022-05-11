package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class BestProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BestProductAction");
		ActionForward forward = null;
		forward = new ActionForward();
		forward.setPath("./store/bestProduct.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
