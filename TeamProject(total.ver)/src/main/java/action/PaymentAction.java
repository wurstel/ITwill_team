package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;
import vo.Order_padDTO;

public class PaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; 
		System.out.println("PaymentAction");
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("sId");
		String pd_code = request.getParameter("pd_code");
		Order_padDTO padDTO = new Order_padDTO();
		padDTO.setOrder_mem_id(mem_id);
		padDTO.setOrder_qty(request.getParameter("sub_qty"));
		padDTO.setOrder_pd_code(pd_code);
		padDTO.setOrder_postcode(request.getParameter("postcode"));
		padDTO.setOrder_address(request.getParameter("address") + request.getParameter("address_detail"));
		padDTO.setOrder_phoneNum(request.getParameter("phoneNum"));
		System.out.println(padDTO);
		
		
		
		forward = new ActionForward();
		forward.setPath("payment/payment.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
