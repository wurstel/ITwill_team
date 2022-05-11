package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class NewProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NewProductAction");
		ActionForward forward = null;
		forward = new ActionForward();
		forward.setPath("./store/newProduct.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
