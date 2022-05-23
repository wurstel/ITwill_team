package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QNAReplyProService;
import vo.ActionForward;
import vo.QnaDTO;

public class QNAReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QNAReplyProAction");
		ActionForward forward = null;
		QnaDTO article = new QnaDTO();
		article.setQna_num(Integer.parseInt(request.getParameter("qna_num")));
		article.setQna_mem_id(request.getParameter("qna_mem_id"));
		article.setQna_pass(request.getParameter("qna_pass"));
		article.setQna_title(request.getParameter("qna_title"));
		article.setQna_content(request.getParameter("qna_content"));
		article.setQna_re_ref(Integer.parseInt(request.getParameter("qna_re_ref")));
		article.setQna_re_lev(Integer.parseInt(request.getParameter("qna_re_lev")));
		article.setQna_re_seq(Integer.parseInt(request.getParameter("qna_re_seq")));
//		System.out.println(article);
		
		// BoardReplyProService 의 replyArticle() 메서드를 호출하여 답글 등록 작업 요청
		// => 파라미터 : BoardDTO 객체(article)   리턴타입 : boolean(isReplySuccess)
		QNAReplyProService service = new QNAReplyProService();
		boolean isReplySuccess = service.replyArticle(article);
		
		// 답글 등록 작업 요청 처리 결과 판별
		// => 실패 시 자바스크립트를 사용하여 "답글 등록 실패!" 출력 후 이전페이지
		// => 성공 시 BoardList.bo 페이지로 포워딩(페이지 번호 전달)
		if(!isReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("QNAList.cu?page=" + request.getParameter("page"));
			forward.setRedirect(true); // Redirect 방식
		}
		
		return forward;
	}

}
