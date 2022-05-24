package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BasketService;
import vo.ActionForward;
import vo.BasketListDTO;

public class BasketAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		BasketService service = new BasketService(); 
		ArrayList<BasketListDTO> basketlist = service.basketlist(id); 
		
		
		if(basketlist != null) {		//장바구니가 있는경우
			session.setAttribute("basketlist", basketlist);
		}
		
		forward = new ActionForward();
		forward.setPath("member/mem_basket.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
