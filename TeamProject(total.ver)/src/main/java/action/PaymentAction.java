package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.PaymentService;
import vo.ActionForward;
import vo.Order_padDTO;
import vo.payInfoDTO;

public class PaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; 
		System.out.println("PaymentAction");
		HttpSession session = request.getSession();
		String mem_id = (String)session.getAttribute("sId");
		String pd_code = request.getParameter("pd_code");
		payInfoDTO payInfoDTO = new payInfoDTO();
		Order_padDTO padDTO = new Order_padDTO();
		padDTO.setOrder_mem_id(mem_id);
		padDTO.setOrder_qty(request.getParameter("sub_qty"));
		padDTO.setOrder_pd_code(pd_code);
		padDTO.setOrder_postcode(request.getParameter("postcode"));
		padDTO.setOrder_address(request.getParameter("address") + request.getParameter("address_detail"));
		padDTO.setOrder_phoneNum(request.getParameter("phoneNum"));
		
		PaymentService service = new PaymentService();
		int insertCount = service.insertOrderPad(padDTO);		//결제정보 기입
		payInfoDTO = service.getPayInfo(mem_id,pd_code);			//결제정보 불러오기
		System.out.println(payInfoDTO);
		if(insertCount > 0) {

		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('결제실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		if(payInfoDTO != null) {			//정보불러오기 성공
			session.setAttribute("payInfoDTO", payInfoDTO);
			forward = new ActionForward();
			forward.setPath("payment/payment.jsp");
			forward.setRedirect(false);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('결제실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
