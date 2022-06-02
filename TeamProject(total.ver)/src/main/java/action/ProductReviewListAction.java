package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ReviewDTO;

public class ProductReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductReviewListAction");
		ActionForward forward = null;

		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수

		// URL 로 전달받은 파라미터 중 "page" 파라미터가 있을 경우 해당 값을 pageNum 값으로 저장
		if (request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}

//		String pd_code = request.getParameter("pd_code");
		String re_pd_code = request.getParameter("re_pd_code");
		System.out.println("re_pd_code : " + re_pd_code);

		// 리뷰 갯수 판별
		ProductReviewListService service = new ProductReviewListService();
		int listCount = service.getListCount(re_pd_code);

		ArrayList<ReviewDTO> articleList = null;

		// java.lang.Math 클래스의 ceil() 메서드를 사용하여 반올림 가능
		int maxPage = (int) Math.ceil((double) listCount / listLimit);

		// 2. 현재 페이지에서 보여줄 시작 페이지 번호(1, 11, 21 등의 시작 번호) 계산
		int startPage = ((int) ((double) pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;

		// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10, 20, 30 등의 끝 번호) 계산
		int endPage = startPage + pageLimit - 1;

		// 4. 만약, 끝 페이지(endPage)가 현재 페이지에서 표시할 총 페이지 수(maxPage)보다 클 경우
		// 끝 페이지 번호를 총 페이지 수로 대체
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 페이징 처리 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);

		String select = "평점순";
		if (request.getParameter("select") != null) {
			select = request.getParameter("select");
		}

		System.out.println("ProductReviewListAction - select: " + select);

		if (select.equals("평점순") || select.equals("")) {
			// 리뷰 평점순
			articleList = service.getReviewHighList(pageNum, listLimit, re_pd_code);
		} else if (select.equals("최신순")) {
			// 리뷰 최신 등록순
			articleList = service.getReviewRecentList(pageNum, listLimit, re_pd_code);
		}

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("re_pd_code", re_pd_code);
		request.setAttribute("articleList", articleList);

		forward = new ActionForward();
		forward.setPath("./store/reviewList.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
