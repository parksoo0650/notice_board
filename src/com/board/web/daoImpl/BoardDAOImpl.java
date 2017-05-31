package com.board.web.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
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
		
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String sql="INSERT INTO Board(writer, title, content,regidate) VALUES"+
						"('"+article.getWriter()+"','"+article.getTitle()+"',"
								+ "'"+article.getContent()+"','"+article.getRegiDate()+"')";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public int countArticles() {
		int count=0;
			try {
				Class.forName(DRIVER);
				Connection connection = DriverManager.getConnection(URL,USER,PW);
				Statement stmt = connection.createStatement();
				String sql="SELECT COUNT(*) AS COUNT FROM Board";
				ResultSet rs=stmt.executeQuery(sql);
				
				if(rs.next()){
					count=Integer.parseInt(rs.getString("count"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return count;
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		int count=0;
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String sql = "SELECT COUNT(*) AS selectCount FROM Board WHERE "+map.get("selectVal")+"='"+map.get("search")+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				count=rs.getInt("selectCount");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return count;
	}

	@Override
	public ArticleBean selectArticle(ArticleBean article) {
		
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM Board WHERE seq_no ='"+article.getSeqNo()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				article = new ArticleBean();
				article.setSeqNo(rs.getString("seq_no"));
				article.setWriter(rs.getString("writer"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setRegiDate(rs.getString("regidate"));
				article.setHitCount(rs.getString("hit_count"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public List<ArticleBean> list(Map<String, Object> map) {
		List<ArticleBean> list = new ArrayList<>();
		ArticleBean article =null;
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String s = String.valueOf(map.get("startRow"));
			String e = String.valueOf(map.get("endRow"));
			String sql="SELECT * FROM (SELECT @NO := @NO + 1 AS ROWNUM, A.* "
					+ "FROM ( SELECT * FROM Board) "
					+ "A,( SELECT @NO := 0 ) B ) C WHERE C.ROWNUM BETWEEN "+s+" AND "+e+"";
			System.out.println("페이지 네이션 쿼리 : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				article = new ArticleBean();
				article.setSeqNo(rs.getString("seq_no"));
				article.setWriter(rs.getString("writer"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setRegiDate(rs.getString("regidate"));
				article.setHitCount(rs.getString("hit_count"));
				list.add(article);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ArticleBean> searchByName(Map<String, Object> map) {
		ArticleBean article =null;
		List<ArticleBean> list = new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String s = String.valueOf(map.get("startRow"));
			String e = String.valueOf(map.get("endRow"));
			String sql="SELECT * FROM (SELECT @NO := @NO + 1 AS ROWNUM, A.* "
					+ "FROM ( SELECT * FROM Board WHERE writer='"+map.get("search")+"') "
					+ "A,( SELECT @NO := 0 ) B ) C WHERE C.ROWNUM BETWEEN "+s+" AND "+e+"";
			System.out.println("검색 쿼리문 : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				article = new ArticleBean();
				article.setSeqNo(rs.getString("seq_no"));
				article.setWriter(rs.getString("writer"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setRegiDate(rs.getString("regidate"));
				article.setHitCount(rs.getString("hit_count"));
				list.add(article);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ArticleBean> searchByTitle(Map<String, Object> map) {
		ArticleBean article =null;
		List<ArticleBean> list = new ArrayList<>();
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String s = String.valueOf(map.get("startRow"));
			String e = String.valueOf(map.get("endRow"));
			String sql = "SELECT * FROM (SELECT @NO := @NO + 1 AS ROWNUM, A.* "
					+ "FROM ( SELECT * FROM Board WHERE title='"+map.get("search")+"') "
					+ "A,( SELECT @NO := 0 ) B ) C WHERE C.ROWNUM BETWEEN "+s+" AND "+e+"";
			System.out.println("제목으로 검색 쿼리문 : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				article = new ArticleBean();
				article.setSeqNo(rs.getString("seq_no"));
				article.setWriter(rs.getString("writer"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setRegiDate(rs.getString("regidate"));
				article.setHitCount(rs.getString("hit_count"));
				list.add(article);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateArticle(ArticleBean article) {
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String sql="UPDATE Board SET title='"+article.getTitle()+"', content='"+article.getContent()+
					"' WHERE seq_no="+article.getSeqNo()+"";
			System.out.println("업데이트 sql문 : " + sql);
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteArticle(ArticleBean article) {
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String sql="DELETE FROM Board WHERE seq_no='"+article.getSeqNo()+"'";
			System.out.println("삭제 sql문 : " + sql);
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int insertMaxSeq() {
		int count=0;
		try {
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,USER,PW);
			Statement stmt = connection.createStatement();
			String sql = "SELECT MAX(seq_no) AS max FROM Board"; 
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				count = Integer.parseInt(rs.getString("max"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

}
