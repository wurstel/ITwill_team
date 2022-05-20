package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QNAModifyProService;
import vo.ActionForward;
import vo.QnaDTO;

public class QNAModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QNAModifyProAction");
		ActionForward forward = null;
			
			
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String qna_mem_id = request.getParameter("qna_mem_id");
		String qna_pass = request.getParameter("qna_pass");
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
			
			// 게시물 수정 권한 판별을 위해 전달받은 파라미터 중 패스워드 비교
			// => BoardModifyProService 의 isArticleWriter() 메서드 호출
			QNAModifyProService service = new QNAModifyProService();
			boolean isArticleWriter = service.isArticleWriter(qna_num, qna_pass);
			
			// 수정 가능 여부 판별
			if(!isArticleWriter) { // 패스워드가 일치하지 않을 경우(= 수정 권한 없음)
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 패스워드가 일치할 경우(= 수정 권한 있음)
				// 수정 가능할 경우 BoardModifyProService - modifyArticle() 메서드 호출하여 수정 요청
				// => 파라미터 : BoardDTO 객체(article)
				// => 리턴타입 : boolean(isModifySuccess)
				QnaDTO article = new QnaDTO();
				article.setQna_num(qna_num);
				article.setQna_mem_id(qna_mem_id);
				article.setQna_title(qna_title);
				article.setQna_content(qna_content);
				
				boolean isModifySuccess = service.modifyArticle(article);
				
				if(!isModifySuccess) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정 실패!')");
					out.println("history.back()");
					out.println("</script>");
				} else {
					forward = new ActionForward();
					forward.setPath("QNAList.cu?page=" + request.getParameter("page"));
					forward.setRedirect(true);
				}
			}
			
			return forward;
	}
}

	