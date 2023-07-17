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
import by.itacademy.htp.ex.dao.conPool.ConnectionPool;
import by.itacademy.htp.ex.dao.conPool.ConnectionPoolException;

public class NewsDAO implements INewsDAO {

	private static final ConnectionPool poolNews = ConnectionPool.getInstance();

	private static final String SQL_GET_LATEST_NEWS = "SELECT * FROM news WHERE NOT status = 0 ORDER BY news_date DESC";
	private static final String SQL_GET_LIST_OF_NEWS = "SELECT * FROM news WHERE NOT status = 0 ORDER BY news_date DESC";
	private static final String SQL_SELECT_FROM_NEWS = "SELECT * FROM news";
	private static final String SQL_UPDATE_NEWS_BY_NEWS_ID = "UPDATE news SET title =?, brief_news = ?, content = ?,"
			+ " news_date =?, status = ?, news_img_path = ? WHERE id = ?";

	private static final String SQL_INSERT_NEWS = "SELECT * FROM news ";

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int i = 0;

		try {
//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
//					"root", "Irina1983");

			con = poolNews.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SQL_GET_LATEST_NEWS);

			while (rs.next() && i < count) {

				i++;
//			System.out.println(rs.getInt("id")+"  "+rs.getString("title") + " "+ rs.getString("brief_news") +
//					rs.getString("content") + rs.getString("news_date")+  rs.getString("newscol"));

				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
						rs.getString("content"), rs.getString("news_date"), rs.getString("news_img_path"),
						rs.getInt("status")));

//				for (News news : result) {
//					
//					System.out.println("Status from latest " + news.getStatus());
//				}

			}
		} catch (SQLException e) {

			throw new NewsDAOException("Bad connection with DB!");
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("Connection pool problem");
		}

		finally {

			poolNews.closeConnection(con, st, rs);

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
//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
//					"root", "Irina1983");
			st = con.createStatement();
			rs = st.executeQuery(SQL_GET_LIST_OF_NEWS);

			while (rs.next()) {

				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
						rs.getString("content"), rs.getString("news_date"), rs.getString("news_img_path"),
						rs.getInt("status")));

				for (News news : result) {

					// System.out.println("Status from holly list " + news.getStatus());
				}

			}
		} catch (SQLException e) {

			throw new NewsDAOException("Problem with DB");
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			throw new NewsDAOException("Problem with connection pool");
		}

		finally {

			poolNews.closeConnection(con, st, rs);

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

					System.out.println("Status  from fetch " + news.getStatus());
				}
			}

		} catch (SQLException | ConnectionPoolException e) {

			throw new NewsDAOException("No connection in fetch by ID");
		}

		finally {

			poolNews.closeConnection(con, st, rs);

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
			System.out.println("news.getIdNews() --" + news.getIdNews());
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

			poolNews.closeConnection(con, st, rs);

			try {

				if (ps != null) {

					ps.close();
				}
			} catch (Exception e2) {
				throw new NewsDAOException("ps problem to close");
			}
		}
		return true;

	}

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
			System.out.println("news.getIdNews() --" + news.getIdNews());
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

			poolNews.closeConnection(con, st, rs);

			try {

				if (ps != null) {

					ps.close();
				}
			} catch (Exception e2) {
				throw new NewsDAOException("ps problem to close");
			}
		}
		return true;

	}

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

}
