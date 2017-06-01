package com.board.web.dao;

import java.util.List;
import java.util.Map;

import com.board.web.domain.ArticleBean;

public interface BoardDAO {
	public void insertArticle(ArticleBean article);
	public int insertMaxSeq();
	public int countArticles() throws Exception;
	public int searchCount(Map<String, Object>map);
	public ArticleBean selectArticle(ArticleBean article);
	public List<ArticleBean> list(Map<String, Object>map);
	public List<ArticleBean> searchByName(Map<String, Object>map);
	public List<ArticleBean> searchByTitle(Map<String, Object>map);
	public void updateArticle(ArticleBean article);
	public void deleteArticle(ArticleBean article);
	public void updateHitCount(ArticleBean article);
	
	
}
