package com.board.web.serviceImpl;

import java.util.ArrayList;
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
		dao.insertArticle(article);
	}

	@Override
	public int countArticles(){
		int count=0;
		try {
			count=dao.countArticles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}

	@Override
	public ArticleBean selectArticle(ArticleBean article) {
		return dao.selectArticle(article);
	}

	@Override
	public List<ArticleBean> list(Map<String, Object> map) {
		List<ArticleBean> list = new ArrayList<>();
		list = dao.list(map);
		return list;
	}

	@Override
	public List<ArticleBean> searchByName(Map<String, Object> map) {
		return 	dao.searchByName(map);
	}

	@Override
	public List<ArticleBean> searchByTitle(Map<String, Object> map) {
		return dao.searchByTitle(map);
	}

	@Override
	public void updateArticle(ArticleBean article) {
		dao.updateArticle(article);
		
	}

	@Override
	public void deleteArticle(ArticleBean article) {
		dao.deleteArticle(article);
	}

	@Override
	public int insertMaxSeq() {
		return dao.insertMaxSeq();
	}

	@Override
	public void updateHitCount(ArticleBean article) {
		dao.updateHitCount(article);
	}

}
