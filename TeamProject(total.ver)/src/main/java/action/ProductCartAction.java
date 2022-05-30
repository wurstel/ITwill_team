package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import svc.ProductCartService;
import vo.ActionForward;
import vo.CartDTO;

public class ProductCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductCartAction");
		
		ActionForward forward = null;
		
		String pd_code = request.getParameter("pd_code");
		String mem_id = request.getParameter("mem_id");
		String choiceCheck = request.getParameter("choiceCheck");
		String bk_qty = request.getParameter("bk_qty");
		ProductCartService service = new ProductCartService();
		String orderNumIs = service.searchBasket(mem_id,pd_code);
		
		CartDTO cart = new CartDTO();
		cart.setBk_mem_id(mem_id);
		cart.setBk_pd_code(pd_code);
		cart.setBk_qty(bk_qty);
		if(orderNumIs != null) {
			cart.setBk_order_num(orderNumIs);
		}
		
		service.insertCart(cart);
		
		if(choiceCheck.equals("list")) {		// 파업창에서 쇼핑 계속하기 클릭 시
			forward = new ActionForward();
			forward.setPath("./storeMain.st");
			forward.setRedirect(true); 
			
		} else if(choiceCheck.equals("cart")) {	// 팝업창에서 장바구니로 이동 클릭 시 
			forward = new ActionForward();
			forward.setPath("./basket.me");
			forward.setRedirect(true);
			
		} else if(choiceCheck.equals("close")) { // 팝업창에서 close 클릭 시
			forward = new ActionForward();
			forward.setPath("./productDetail.st?pd_code=" + pd_code);
			forward.setRedirect(true);
		}
		
		return forward;
		
	}

}
