package com.board.web.serviceImpl;

import java.util.List;
import java.util.Map;

import com.board.web.dao.BoardDAO;
import com.board.web.daoImpl.BoardDAOImpl;
import com.board.web.domain.ArticleBean;
import com.board.web.service.BoardService;

public class BoardServiceImpl implements BoardService{
	private BoardDAO dao = BoardDAOImpl.getInstance();
	
	private static BoardServiceImpl instance = new BoardServiceImpl();
	
	
	public static BoardServiceImpl getInstance() {
		return instance;
	}
	
	private BoardServiceImpl() {}

	@Override
	public void insertArticle(ArticleBean article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countArticles() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArticleBean selectArticle(ArticleBean article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleBean> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleBean> searchByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleBean> searchByTitle(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateArticle(ArticleBean article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArticleBean deleteArticle(ArticleBean article) {
		// TODO Auto-generated method stub
		return null;
	}

}
