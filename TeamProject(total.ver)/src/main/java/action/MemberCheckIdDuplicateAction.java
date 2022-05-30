package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberCheckIdDuplicateService;
import vo.ActionForward;

public class MemberCheckIdDuplicateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberCheckIdDuplicateAction");
		ActionForward forward = null;
		
		String id = request.getParameter("mem_id");

		MemberCheckIdDuplicateService service = new MemberCheckIdDuplicateService();
		boolean isDuplicate = service.isDuplicateId(id);
		
		forward = new ActionForward();
		forward.setPath("MemberCheckId.me?mem_id=" + id + "&isDuplicate=" + isDuplicate);
		forward.setRedirect(true);
		return forward;
	}

}
