package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberjoinService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction.java");
		ActionForward forward = null;
		request.setCharacterEncoding("UTF-8");
		String birth =  request.getParameter("birth").replace("-", "");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMem_id(request.getParameter("id"));
		memberDTO.setMem_password(request.getParameter("pass"));
		memberDTO.setMem_name(request.getParameter("name"));
		memberDTO.setMem_birth(birth);
		memberDTO.setMem_gender(request.getParameter("gender"));
		memberDTO.setMem_email(request.getParameter("email")+"@"+request.getParameter("domain"));
		memberDTO.setMem_phoneNum(request.getParameter("phone"));
		memberDTO.setMem_postcode(request.getParameter("postcode"));
		memberDTO.setMem_address(request.getParameter("address")+request.getParameter("add_detail"));
		
		MemberjoinService service = new MemberjoinService();
		int insertMember = service.insertMember(memberDTO);
		
		if(insertMember > 0) {		//회원가입 성공
			forward = new ActionForward();
			forward.setPath("mem_joinSuccess.me");	
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
