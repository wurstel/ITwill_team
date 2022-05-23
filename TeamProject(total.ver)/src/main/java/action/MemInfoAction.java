package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemInfoEditService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemInfoEditAction");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		System.out.println(session.getAttribute("sId"));
		ActionForward forward = null;
		
		
		MemInfoEditService service = new MemInfoEditService();
		
		boolean isRemainedSession = service.isRemainedSession(id);
		if(isRemainedSession) { //session이 유지되어 있는 경우
			MemberDTO memberDTO = service.isMemberInfo(id);
			session.setAttribute("memberDTO", memberDTO);
			forward = new ActionForward();
			forward.setPath("member/mem_mypage.jsp");
			forward.setRedirect(false);
		}else {					//session이 종료된경우
			forward = new ActionForward();
			forward.setPath("main.jsp");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
