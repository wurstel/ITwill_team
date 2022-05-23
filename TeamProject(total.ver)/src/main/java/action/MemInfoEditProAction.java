package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemInfoEditProService;
import vo.ActionForward;

public class MemInfoEditProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemInfoEditProAction");
		ActionForward forward = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id= (String)session.getAttribute("sId");
		String email = request.getParameter("mem_email")+"@"+request.getParameter("domain");
		String postcode = request.getParameter("postcode");
		String phoneNum = request.getParameter("phone");
		String address = request.getParameter("address") + request.getParameter("add_detail");
		
		
		
		MemInfoEditProService service = new MemInfoEditProService(); 
		
		int memInfoEditSuccess = service.memInfoEditSuccess(id,email,phoneNum,address,postcode);
		
		if(memInfoEditSuccess > 0) {		//업데이트 성공
			forward = new ActionForward();
			forward.setPath("mypage.me");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		
		return forward;
	}

}
