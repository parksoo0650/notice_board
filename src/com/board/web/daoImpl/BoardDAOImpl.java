package com.board.web.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.board.web.dao.BoardDAO;
import com.board.web.domain.ArticleBean;


public class BoardDAOImpl implements BoardDAO{
	public static BoardDAOImpl instance = new BoardDAOImpl();
	
	public static BoardDAOImpl getInstance() {
		return instance;
	}

	public static void setInstance(BoardDAOImpl instance) {
		BoardDAOImpl.instance = instance;
	}
	private BoardDAOImpl() {}

	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost/hanbit";
	private static final String USER="hanbit";
	private static final String PW="hanbit";
	
	@Override
	public void insertArticle(ArticleBean article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countArticles() throws Exception {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String sql="SELECT COUNT(*) AS COUNT FROM Board";
			ResultSet rs=stmt.executeQuery(sql);
			int count=0;
			if(rs.next()){
				count=Integer.parseInt(rs.getString("count"));
			}
		return count;
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
