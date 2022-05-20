package action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QNADeleteProService;
import vo.ActionForward;

public class QNADeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QNADeleteProAction");
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String pageNum = request.getParameter("page");
		
		QNADeleteProService service = new QNADeleteProService();
		
		boolean isDeleteSuccess = service.removeArticle(qna_num);
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
		forward = new ActionForward();
		forward.setPath("QNAList.cu?page=" + pageNum);
		forward.setRedirect(true);
		}
		return forward;
	}

}
