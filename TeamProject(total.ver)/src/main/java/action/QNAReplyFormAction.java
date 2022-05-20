package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QNADetailService;
import vo.ActionForward;
import vo.QnaDTO;

public class QNAReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QNAReplyFormAction");
		ActionForward forward = null;
		// 전달받은 파라미터(글번호) 가져오기
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
				
				// BoardDetailService 클래스의 getArticle() 메서드를 호출하여
				// 원본 게시물 1개 정보 가져오기
				// => 파라미터 : 글번호(board_num)   리턴타입 : BoardDTO(article)
		QNADetailService service = new QNADetailService();
		QnaDTO article = service.getArticle(qna_num);
				
				// request 객체에 전달할 객체 지정
		request.setAttribute("article", article);
				
				// ActionForward 객체를 통해 board/qna_board_reply.jsp 페이지 설정
		forward = new ActionForward();
		forward.setPath("./customerCenter/qna_board_reply.jsp");
		forward.setRedirect(false); // Dispatcher 방식
		return forward;
	}

}
