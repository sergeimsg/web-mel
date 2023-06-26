package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.UserDaoException;
import jakarta.faces.view.ActionSource2AttachedObjectTarget;
import by.htp.ex.dao.IUserDAO;

public class UserDAO implements IUserDAO {

	@Override
	public boolean logination(String login, String password) throws UserDaoException {

		/*
		 * Random rand = new Random(); int value = rand.nextInt(1000);
		 * 
		 * if(value % 3 == 0) { try { throw new SQLException("stub exception");
		 * }catch(SQLException e) { throw new DaoException(e); } }else if (value % 2 ==
		 * 0) { return true; }else { return false; }
		 */
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sqlLogin = "SELECT * FROM users";
		boolean flag = false;

		try {

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
					"root", "Irina1983");
			st = con.createStatement();
			rs = st.executeQuery(sqlLogin);
			while (rs.next()) {

				if (rs.getString("login").equals(login) && rs.getString("password").equals(password)) {

					flag = true;
					break;
				} else {
					flag = false;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return flag;

	}

	public String getRole(String login, String password) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		return "user";
	}

	@Override
	public boolean registrationDB(NewUserInfo user) throws UserDaoException {

		final String ADD_USER = "INSERT INTO users(login,password) VALUES(?,?)";

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
					"root", "Irina1983");

			ps = con.prepareStatement(ADD_USER);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			int i = ps.executeUpdate();
			// System.out.println("i = " + i + "done ");

		} catch (Exception e) {
			// TODO: handle exception
		}

		finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}

				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return true;
	}

}
