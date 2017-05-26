package com.board.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board.do")
public class BoardController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		switch (action) {
		case "list":
			request.getRequestDispatcher("/WEB-INF/jsp/board/main.jsp").forward(request, response);
			return;
		case "write":
			request.getRequestDispatcher("/WEB-INF/jsp/board/write.jsp").forward(request, response);
			return;
		case "update":
			request.getRequestDispatcher("/WEB-INF/jsp/board/update.jsp").forward(request, response);
			return;
		case "detail":
			request.getRequestDispatcher("/WEB-INF/jsp/board/detail.jsp").forward(request, response);
			return;
		case "upload":
			request.getRequestDispatcher("/WEB-INF/jsp/board/upload.jsp").forward(request, response);
			return;
		default:
			break;
		}
	}
	
}
