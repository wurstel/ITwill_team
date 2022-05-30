package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BasketService;
import vo.ActionForward;

public class BasketUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String bk_qty = request.getParameter("bk_qty");
		String bk_mem_id = request.getParameter("bk_mem_id");
		String bk_order_num = request.getParameter("bk_order_num");
		String bk_pd_code = request.getParameter("bk_pd_code");
		System.out.println("BasketUpdateAction-"+bk_qty);
		BasketService service = new BasketService();
		int updateSuccess = service.qtyUpdate(bk_qty,bk_mem_id,bk_order_num,bk_pd_code);
		
		if(updateSuccess > 0) {		//업데이트 성공
		
		forward = new ActionForward();
		forward.setPath("basket.me");
		forward.setRedirect(true);
		} else {					//업데이트 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
