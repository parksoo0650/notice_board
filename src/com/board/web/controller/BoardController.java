package com.board.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.board.web.domain.ArticleBean;
import com.board.web.service.BoardService;
import com.board.web.service.PaginationService;
import com.board.web.serviceImpl.BoardServiceImpl;
import com.board.web.serviceImpl.PaginationServiceImpl;

@WebServlet("/board.do")
public class BoardController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String VIEW_DIRECTORY = "/WEB-INF/jsp/";
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardServiceImpl.getInstance();
		PaginationService pService = new PaginationServiceImpl();
		HttpSession session = request.getSession();
		String path = request.getServletPath(),
				directory=path.substring(0,path.indexOf(".")),
				action=request.getParameter("action"),
				pageName=request.getParameter("pageName"),
				pageNumber=request.getParameter("no"),
				maxSeq="";
				int count=0;
				pageNumber=(pageNumber==null)?"1":pageNumber;
				System.out.println("서블릿 경로 : " + path);
		String[] params=new String[7];
		int[] rowValues=new int[7];
		Map<String, Object> map = null;
		List<ArticleBean> list = null;
		ArticleBean article = null;
		String writer,title,comment;
		switch (action) {
		case "move":
			int max=service.insertMaxSeq();
			System.out.println("최근 입력 값 : " + max);
			writer = request.getParameter("writer");
			title = request.getParameter("title");
			comment = request.getParameter("comment");
			request.setAttribute("max", max);
			request.setAttribute("writer", writer);
			request.setAttribute("title", title);
			request.setAttribute("comment", comment);
			request.getRequestDispatcher(VIEW_DIRECTORY + directory + "/" + pageName + ".jsp").forward(request, response);
			break;
		case "list":
			System.out.println("action=list 접근");
			list = new ArrayList<>();
			map = new HashMap<>();
			params[0]=pageNumber;
			params[1]=String.valueOf(service.countArticles());
			params[2]="5";
			params[3]="5";
			rowValues=pService.calculateRows(params);
			map.put("startRow", rowValues[0]);
			map.put("endRow", rowValues[1]);
			count=service.countArticles();
			list = service.list(map);
			System.out.println("리스트의 수 : " + count);
			System.out.println("가져온 리스트 : " + list);
			request.setAttribute("pagePerOneblock", params[3]);
			request.setAttribute("rowsPerOnePage", params[2]);
			request.setAttribute("theNumberOfRows", params[1]);
			request.setAttribute("theNumberOfPages", rowValues[6]);
			request.setAttribute("pageNumber", params[0]);
			request.setAttribute("startPage", rowValues[2]);
			request.setAttribute("endPage", rowValues[3]);
			request.setAttribute("startRow", rowValues[0]);
			request.setAttribute("endRow", rowValues[1]);
			request.setAttribute("prevBlock", rowValues[4]);
			request.setAttribute("nextBlock", rowValues[5]);
			request.setAttribute("count", count);
			request.setAttribute("list", list);
			request.setAttribute("action", "list");
			System.out.println("시작 로우 : " + map.get("startRow"));
			System.out.println("엔드 로우 : " + map.get("endRow"));
			request.getRequestDispatcher("/WEB-INF/jsp/board/main.jsp").forward(request, response);
			return;
		case "write":
			article = new ArticleBean();
			System.out.println("action=write 접근");
			writer = request.getParameter("writer");
			title = request.getParameter("title");
			comment = request.getParameter("comment");
			Calendar calendar = Calendar.getInstance();
			String today=calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + "0"+calendar.get(Calendar.DATE)+1;
			System.out.println("받아온 파라미터 값 : " +writer+ ", " +title+", " +comment);
			article.setWriter(writer);
			article.setTitle(title);
			article.setContent(comment);
			article.setRegiDate(today);
			service.insertArticle(article);
			count=service.insertMaxSeq();
			request.setAttribute("writer", writer);
			request.setAttribute("title", title);
			request.setAttribute("content", comment);
			request.setAttribute("seqNo", count);
			request.getRequestDispatcher("/WEB-INF/jsp/board/detail.jsp").forward(request, response);
			return;
		case "detail":
			article = new ArticleBean();
			String no = request.getParameter("no");
			article.setSeqNo(no);
			article=service.selectArticle(article);
			service.updateHitCount(article);
			System.out.println("조회수 증가");
			request.setAttribute("writer", article.getWriter());
			request.setAttribute("title", article.getTitle());
			request.setAttribute("content", article.getContent());
			request.setAttribute("seqNo", article.getSeqNo());
			request.getRequestDispatcher(VIEW_DIRECTORY + directory + "/" + pageName + ".jsp").forward(request, response);
			break;
		case "update":
			article = new ArticleBean();
			writer = request.getParameter("writer");
			title = request.getParameter("title");
			comment = request.getParameter("comment");
			maxSeq = request.getParameter("max");
			article.setSeqNo(maxSeq);
			article.setWriter(writer);
			article.setTitle(title);
			article.setContent(comment);
			service.updateArticle(article);
			System.out.println("수정 되었습니다.");
			list = new ArrayList<>();
			map = new HashMap<>();
			count=service.countArticles();
			params[0]=pageNumber;
			params[1]=String.valueOf(service.countArticles());
			params[2]="5";
			params[3]="5";
			rowValues=pService.calculateRows(params);
			map.put("startRow", rowValues[0]);
			map.put("endRow", rowValues[1]);
			list = service.list(map);
			request.setAttribute("pagePerOneblock", params[3]);
			request.setAttribute("rowsPerOnePage", params[2]);
			request.setAttribute("theNumberOfRows", params[1]);
			request.setAttribute("theNumberOfPages", rowValues[6]);
			request.setAttribute("pageNumber", params[0]);
			request.setAttribute("startPage", rowValues[2]);
			request.setAttribute("endPage", rowValues[3]);
			request.setAttribute("startRow", rowValues[0]);
			request.setAttribute("endRow", rowValues[1]);
			request.setAttribute("prevBlock", rowValues[4]);
			request.setAttribute("nextBlock", rowValues[5]);
			System.out.println("리스트의 수 : " + count);
			System.out.println("가져온 리스트 : " + list);
			request.setAttribute("count", count);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/jsp/board/main.jsp").forward(request, response);
			break;
		case "delete" :
			article = new ArticleBean();
			maxSeq = request.getParameter("max");
			article.setSeqNo(maxSeq);
			service.deleteArticle(article);
			System.out.println("삭제 되었습니다.");
			list = new ArrayList<>();
			map = new HashMap<>();
			count=service.countArticles();
			list = service.list(map);
			System.out.println("리스트의 수 : " + count);
			System.out.println("가져온 리스트 : " + list);
			request.setAttribute("count", count);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/jsp/board/main.jsp").forward(request, response);
			break;
		case "upload":
			request.getRequestDispatcher("/WEB-INF/jsp/board/upload.jsp").forward(request, response);
			break;
		case "search":
			String selectVal=request.getParameter("selectVal");
			String search=request.getParameter("msg");
			if(selectVal != null){
				session.setAttribute("selectVal", selectVal);
				session.setAttribute("search", search);
			}else{
				selectVal=(String) session.getAttribute("selectVal");
				search=(String) session.getAttribute("search");
			}
			System.out.println("넘어온 값 : " + selectVal + ", " + search);
			map = new HashMap<>();
			list = new ArrayList<>();
			count=0;
			if(selectVal.equals("all")){
				map.put("selectVal", selectVal);
				map.put("search", search);
				params[0]=pageNumber;
				params[1]=String.valueOf(service.countArticles());
				params[2]="5";
				params[3]="5";
				rowValues=pService.calculateRows(params);
				map.put("startRow", rowValues[0]);
				map.put("endRow", rowValues[1]);
				list=service.searchByName(map);
				request.setAttribute("pagePerOneblock", params[3]);
				request.setAttribute("rowsPerOnePage", params[2]);
				request.setAttribute("theNumberOfRows", params[1]);
				request.setAttribute("theNumberOfPages", rowValues[6]);
				request.setAttribute("pageNumber", params[0]);
				request.setAttribute("startPage", rowValues[2]);
				request.setAttribute("endPage", rowValues[3]);
				request.setAttribute("startRow", rowValues[0]);
				request.setAttribute("endRow", rowValues[1]);
				request.setAttribute("prevBlock", rowValues[4]);
				request.setAttribute("nextBlock", rowValues[5]);
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				request.setAttribute("action", "search");
			}else if(selectVal.equals("writer")){
				map.put("selectVal", selectVal);
				map.put("search", search);
				params[0]=pageNumber;
				params[1]=String.valueOf(service.searchCount(map));
				params[2]="5";
				params[3]="5";
				rowValues=pService.calculateRows(params);
				map.put("startRow", rowValues[0]);
				map.put("endRow", rowValues[1]);
				list=service.searchByName(map);
				request.setAttribute("pagePerOneblock", params[3]);
				request.setAttribute("rowsPerOnePage", params[2]);
				request.setAttribute("theNumberOfRows", params[1]);
				request.setAttribute("theNumberOfPages", rowValues[6]);
				request.setAttribute("pageNumber", params[0]);
				request.setAttribute("startPage", rowValues[2]);
				request.setAttribute("endPage", rowValues[3]);
				request.setAttribute("startRow", rowValues[0]);
				request.setAttribute("endRow", rowValues[1]);
				request.setAttribute("prevBlock", rowValues[4]);
				request.setAttribute("nextBlock", rowValues[5]);
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				request.setAttribute("action", "search");
			}else{
				map.put("selectVal", selectVal);
				map.put("search", search);
				params[0]=pageNumber;
				params[1]=String.valueOf(service.searchCount(map));
				params[2]="5";
				params[3]="5";
				rowValues=pService.calculateRows(params);
				map.put("startRow", rowValues[0]);
				map.put("endRow", rowValues[1]);
				list = service.searchByTitle(map);
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				request.setAttribute("pagePerOneblock", params[3]);
				request.setAttribute("rowsPerOnePage", params[2]);
				request.setAttribute("theNumberOfRows", params[1]);
				request.setAttribute("theNumberOfPages", rowValues[6]);
				request.setAttribute("pageNumber", params[0]);
				request.setAttribute("startPage", rowValues[2]);
				request.setAttribute("endPage", rowValues[3]);
				request.setAttribute("startRow", rowValues[0]);
				request.setAttribute("endRow", rowValues[1]);
				request.setAttribute("prevBlock", rowValues[4]);
				request.setAttribute("nextBlock", rowValues[5]);
				request.setAttribute("action","search");
			}
			request.getRequestDispatcher("/WEB-INF/jsp/board/main.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}
	
}
