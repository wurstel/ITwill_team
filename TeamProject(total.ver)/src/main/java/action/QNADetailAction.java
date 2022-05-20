package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QNADetailService;
import vo.ActionForward;
import vo.QnaDTO;

public class QNADetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QNADetailAction");
		
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		QNADetailService service = new QNADetailService();
		
		QnaDTO article = service.getArticle(qna_num);
		
		request.setAttribute("article", article);
		
		forward = new ActionForward();
		forward.setPath("customerCenter/qna_board_view.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
