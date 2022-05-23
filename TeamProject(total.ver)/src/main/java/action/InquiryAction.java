package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.InquiryService;
import vo.ActionForward;
import vo.Order_checkDTO;

public class InquiryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		InquiryService service = new InquiryService();
		String isInquiry = service.isInquiry(id);
		ArrayList<Order_checkDTO> list = null;
		if(isInquiry != null) {	//주문내역이 없을떄 
			session.setAttribute("comment", isInquiry);
		} else {			//주문내역이 있을때
			list = service.loadInquiry(id);
			session.setAttribute("list", list);
		}
		
		forward = new ActionForward();
		forward.setPath("member/mem_orderInquiry.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
