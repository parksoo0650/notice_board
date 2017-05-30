package com.board.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.web.domain.ArticleBean;
import com.board.web.service.BoardService;
import com.board.web.serviceImpl.BoardServiceImpl;

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
		String path = request.getServletPath(),
				directory=path.substring(0,path.indexOf(".")),
				action=request.getParameter("action"),
				pageName=request.getParameter("pageName"),
				maxSeq="";
		int count=0;
				System.out.println("서블릿 경로 : " + path);
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
			count=service.countArticles();
			list = service.list(map);
			System.out.println("리스트의 수 : " + count);
			System.out.println("가져온 리스트 : " + list);
			request.setAttribute("count", count);
			request.setAttribute("list", list);
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
			list = service.list(map);
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
			System.out.println("넘어온 값 : " + selectVal + ", " + search);
			map = new HashMap<>();
			list = new ArrayList<>();
			count=0;
			if(selectVal.equals("writer")){
				map.put("selectVal", selectVal);
				map.put("search", search);
				list=service.searchByName(map);
				count=service.searchCount(map);
				request.setAttribute("list", list);
				request.setAttribute("count", count);
			}else{
				map.put("selectVal", selectVal);
				map.put("search", search);
				list = service.searchByTitle(map);
				count=service.searchCount(map);
				request.setAttribute("list", list);
				request.setAttribute("count", count);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/board/main.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}
	
}
