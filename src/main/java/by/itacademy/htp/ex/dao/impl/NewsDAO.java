package by.itacademy.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import by.itacademy.htp.ex.bean.News;
import by.itacademy.htp.ex.dao.INewsDAO;
import by.itacademy.htp.ex.dao.NewsDAOException;
import by.itacademy.htp.ex.dao.conpool.ConnectionPool;
import by.itacademy.htp.ex.dao.conpool.Exception.ConnectionPoolException;

public class NewsDAO implements INewsDAO {

	private static final ConnectionPool poolNews = ConnectionPool.getInstance();

	private static final String SQL_GET_LIST_OF_NEWS = "SELECT * FROM news WHERE NOT status = 0 ORDER BY news_date DESC";
	private static final String SQL_SELECT_FROM_NEWS = "SELECT * FROM news";

	private static final String SQL_GET_LATEST_NEWS_LIMIT = "SELECT * FROM news WHERE NOT status = 0 ORDER BY news_date DESC LIMIT ?";

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {

		List<News> result = new ArrayList<News>();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		
		try {

			con = poolNews.takeConnection();
			st = con.createStatement();
			ps = con.prepareStatement(SQL_GET_LATEST_NEWS_LIMIT);
			ps.setInt(1, count);

			rs = ps.executeQuery();

			while (rs.next()) {

				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
						rs.getString("content"), rs.getString("news_date"), rs.getString("news_img_path"),
						rs.getInt("status")));

			}



		} catch (SQLException | ConnectionPoolException e ) {

			throw new NewsDAOException("Bad connection with DB!", e );
		} 

		finally {

			try {
				poolNews.closeConnection(con, st, rs, ps);
			} 
			catch (ConnectionPoolException e) {
				
				throw new NewsDAOException(e);

		}
		}

		return result;
	}

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			con = poolNews.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SQL_GET_LIST_OF_NEWS);

			while (rs.next()) {

				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
						rs.getString("content"), rs.getString("news_date"), rs.getString("news_img_path"),
						rs.getInt("status")));



			}
		} 		
		catch (SQLException | ConnectionPoolException e) {

			throw new NewsDAOException("Problem with DB", e );
		} 

		finally {

			try {
				
				poolNews.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {

				throw new NewsDAOException(e);
				
			}

		}

		return result;
	}

	@Override
	public News fetchById(int idNews) throws NewsDAOException {

		News news = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			con = poolNews.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SQL_SELECT_FROM_NEWS);
			while (rs.next()) {
				if (rs.getInt("id") == idNews) {
					news = new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
							rs.getString("content"), rs.getString("news_date"), rs.getString("news_img_path"),
							rs.getInt("status"));

					
				}
			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new NewsDAOException("No connection in fetch by ID", e);
		}

		finally {

			try {
				poolNews.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				
				throw new NewsDAOException(e);
				
			}

		}

		return news;

	}

	@Override
	public boolean addNews(News news) throws NewsDAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			con = poolNews.takeConnection();
			st = con.createStatement();

			ps = con.prepareStatement(
					"INSERT INTO news( title, brief_news,content, news_date,status, news_img_path, users_id) "
							+ "VALUES(?,?,?,?,?,?,?) ");
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBriefNews());
			ps.setString(3, news.getContent());
			ps.setString(4, news.getNewsDate());
			ps.setInt(5, news.getStatus());
			ps.setString(6, news.getImagePath());
			ps.setInt(7, news.getUserID());

			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new NewsDAOException("Error in DB edit news query", e);
		}

		finally {

			try {
				poolNews.closeConnection(con, st, rs, ps);
			} catch (ConnectionPoolException e) {


				throw new NewsDAOException(e);
				
			}

			
		}
		return true;

	}

	private static final String SQL_UPDATE_NEWS_BY_NEWS_ID = "UPDATE news SET title =?, brief_news = ?, content = ?,"
			+ " news_date =?, status = ?, news_img_path = ? WHERE id = ?";

	@Override
	public boolean updateNews(News news) throws NewsDAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			con = poolNews.takeConnection();
			st = con.createStatement();

			ps = con.prepareStatement(SQL_UPDATE_NEWS_BY_NEWS_ID);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBriefNews());
			ps.setString(3, news.getContent());
			ps.setString(4, news.getNewsDate());
			ps.setInt(5, news.getStatus());
			ps.setString(6, news.getImagePath());

			ps.setInt(7, news.getIdNews());

			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {

			throw new NewsDAOException("Error in DB edit news query", e);
		}

		finally {

			try {
				poolNews.closeConnection(con, st, rs, ps);
			} catch (ConnectionPoolException e) {


				throw new NewsDAOException(e);
			}

			}
		return true;

	}

	@Override
	public boolean deleteNewses(String[] idNewsBox) throws NewsDAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		for (int i = 0; i < idNewsBox.length; i++) {

			try {

				con = poolNews.takeConnection();

				st = con.createStatement();
				ps = con.prepareStatement("UPDATE news SET status = ? WHERE id = ?");
				ps.setInt(1, 0);
				ps.setInt(2, Integer.parseInt(idNewsBox[i]));

				ps.executeUpdate();

			} catch (SQLException | ConnectionPoolException e) {

				throw new NewsDAOException("Error in DB delete news query", e);
			}

			finally {

				try {
					poolNews.closeConnection(con, st, rs, ps);
				} catch (ConnectionPoolException e) {
					
					throw new NewsDAOException(e);
				}

					}

		}
		return true;

	}

}
