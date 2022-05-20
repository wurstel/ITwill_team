package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QNADetailService;
import vo.ActionForward;
import vo.QnaDTO;

public class QNAModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QNAModifyFormAction");
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		// 글 수정에 필요한 데이터를 조회하기 위해
		// 이미 만들어진 BoardDetailService 클래스의 getArticle() 메서드를 호출하여
		// 게시물 상세 정보를 리턴받아 qna_board_modify.jsp 페이지로 포워딩
		// => 단, 조회수 증가 작업은 수행하지 않음
		QNADetailService service = new QNADetailService();
		QnaDTO article = service.getArticle(qna_num);
		
		// request 객체에 BoardDTO 객체 저장
		request.setAttribute("article", article);
		
		// board/qna_board_modify.jsp 페이지로 포워딩 설정 => Dispatcher 방식
		forward = new ActionForward();
		forward.setPath("./customerCenter/qna_board_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	
	}

}
