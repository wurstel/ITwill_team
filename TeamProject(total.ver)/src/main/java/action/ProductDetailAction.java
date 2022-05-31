package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductDetailService;
import vo.ActionForward;
import vo.ProductDTO;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductDetailAction");
		ActionForward forward = null;
		
		String pd_code = request.getParameter("pd_code");
		System.out.println(pd_code);
		ProductDetailService service = new ProductDetailService();
		ProductDTO articleDetail = service.getArticle(pd_code);
		
		request.setAttribute("articleDetail", articleDetail);
		
		forward = new ActionForward();
		forward.setPath("./store/detailProduct.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}


