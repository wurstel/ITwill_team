package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction.java");
		ActionForward forward = null;
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMem_id(request.getParameter("id"));
		memberDTO.setMem_password(request.getParameter("id"));
		memberDTO.setMem_name(request.getParameter("id"));
		memberDTO.setMem_birth(request.getParameter("id"));
		memberDTO.setMem_gender(request.getParameter("id"));
		memberDTO.setMem_email(request.getParameter("id"));
		memberDTO.setMem_phoneNum(request.getParameter("id"));
		memberDTO.setMem_postcode(request.getParameter("id"));
		memberDTO.setMem_address(request.getParameter("id"));
		memberDTO.setMem_grade(request.getParameter("id"));
		memberDTO.setMem_point(request.getParameter("id"));
		memberDTO.setMem_paymethod(request.getParameter("id"));
		
		
		
		
		return forward;
	}

}
