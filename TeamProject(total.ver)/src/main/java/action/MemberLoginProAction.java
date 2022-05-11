package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginProAction");
		ActionForward forward = null;
		
		// 폼 파라미터 가져와서 MemberDTO 객체에 저장
		System.out.println(request.getParameter("mem_id"));
		
//		MemberDTO member = new MemberDTO();
//		member.setMem_id(request.getParameter("mem_id"));
//		member.setMem_password(request.getParameter("mem_password"));
//				System.out.println(member.toString());
		String id = request.getParameter("mem_id");
		String pass = request.getParameter("mem_password");
		// MemberLoginProService 클래스의 loginMember() 메서드를 호출하여 로그인 판별 요청
		// => 파라미터 : id,pass    리턴타입 : boolean(isMember)
		MemberLoginProService service = new MemberLoginProService();
		boolean isMember = service.loginMember(id,pass);
		System.out.println(isMember);
		// 로그인 판별 작업 요청 결과에 따른 판별 작업 수행
		if(!isMember) { // 로그인 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 또는 패스워드 틀림!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 로그인 성공
			// request 객체의 getSession() 메서드를 호출하여 HttpSesson 객체를 얻어오기
			HttpSession session = request.getSession();
			// HttpSession 객체의 setAttribute() 메서드를 호출하여 세션 아이디값 저장(속성명 : sId)
			session.setAttribute("sId", request.getParameter("mem_id"));
			
			// ActionForward 객체를 통해 메인페이지 포워딩 설정
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true); 
		}
		
		
		
		
		return forward;
	}

}
