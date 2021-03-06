package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.QNAWriteProService;
import vo.ActionForward;
import vo.QnaDTO;

public class QNAWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QNAWriteProAction");
		ActionForward forward = null;
		
		QnaDTO qna = new QnaDTO();
		qna.setQna_mem_id(request.getParameter("qna_mem_id"));
		qna.setQna_pass(request.getParameter("qna_pass"));
		qna.setQna_title(request.getParameter("qna_title"));
		qna.setQna_content(request.getParameter("qna_content"));
		
		QNAWriteProService service = new QNAWriteProService();
		boolean isWriteSuccess = service.registArticle(qna);
		
		if(!isWriteSuccess) { // 글쓰기 실패 시(결과값이 false 일 경우)
			// 자바스크립트를 통해 "글쓰기 실패!" 출력하고 이전페이지로 돌아가기
			// => 자바 클래스에서 웹브라우저를 통해 HTML 코드 등을 출력하려면
			//    response 객체를 통해 문서 타입 설정 및 PrintWriter() 객체를 통해 태그 출력하기
			// 1) response 객체의 setContentType() 메서드를 호출하여 문서 타입(ContentType) 지정
			//    => jsp 파일 맨 위에 page 디렉티브 내의 contentType=XXXX 항목과 동일
			response.setContentType("text/html; charset=UTF-8");
			
			// 2) response 객체의 getWrite() 메서드를 호출하여 출력스트림 PrintWriter 객체 얻어오기
			PrintWriter out = response.getWriter();
			
			// 3) PrintWriter 객체의 println() 메서드를 호출하여 출력할 태그 작성
			out.println("<script>");
			out.println("alert('글쓰기 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 글쓰기 성공 시(결과값이 true 일 경우)
			// ActionForward 객체를 통해 "BoardList.bo" 서블릿 주소 요청
			// => 새로운 요청이므로 서블릿 주소 변경을 위해 Redirect 방식으로 포워딩 설정
			forward = new ActionForward();
			forward.setPath("CustomerCenter.cu");
			forward.setRedirect(true);
		}
		return forward;
	}

}
