package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberManageService;
import vo.ActionForward;
import vo.MemberDTO;
import vo.PageInfo;

public class MemberManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
				
		// URL 로 전달받은 파라미터 중 "page" 파라미터가 있을 경우 해당 값을 pageNum 값으로 저장
		if (request.getParameter("page")!=null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		
		MemberManageService service = new MemberManageService();
		ArrayList<MemberDTO> memberList = service.loadMember(pageNum,listLimit);
		int listCount = service.getListCount();

		// java.lang.Math 클래스의 ceil() 메서드를 사용하여 반올림 가능
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		// 2. 현재 페이지에서 보여줄 시작 페이지 번호(1, 11, 21 등의 시작 번호) 계산
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
				
		// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10, 20, 30 등의 끝 번호) 계산
		int endPage = startPage + pageLimit - 1;
				
		// 4. 만약, 끝 페이지(endPage)가 현재 페이지에서 표시할 총 페이지 수(maxPage)보다 클 경우
		//	    끝 페이지 번호를 총 페이지 수로 대체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
				
		// 페이지 처리 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		
		session.setAttribute("pageInfo", pageInfo);
		session.setAttribute("memberList", memberList);
		
		
		forward = new ActionForward();
		forward.setPath("./admin/memberManage.jsp");
		forward.setRedirect(false); // Dispatcher 방식(생략 가능)
		return forward;
	}

}
