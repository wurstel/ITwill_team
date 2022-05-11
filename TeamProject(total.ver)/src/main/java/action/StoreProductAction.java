package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class StoreProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("StoreProductAction");
		ActionForward forward = null;
		forward = new ActionForward();
		forward.setPath("./store/store.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
