package by.itacademy.htp.ex.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import by.itacademy.htp.ex.bean.NewUserInfo;
import by.itacademy.htp.ex.dao.UserDaoException;
import by.itacademy.htp.ex.dao.conpool.ConnectionPool;
import by.itacademy.htp.ex.dao.conpool.ConnectionPoolException;
import by.itacademy.htp.ex.dao.IUserDAO;

public class UserDAO implements IUserDAO {

	private static final ConnectionPool pool = ConnectionPool.getInstance();
	// private static final String CHECK_LOGIN = "SELECT * FROM users WHERE login =
	// ?";
	private static final String CHECK_LOGIN = "SELECT * FROM users JOIN users_has_role ON "
			+ " users.id=users_has_role.users_id  WHERE users.login = ?";


	private static final String GET_ROLE_ID = "SELECT * FROM users_has_role WHERE 	users_id = ?";

	@Override
	public NewUserInfo logination(String loginFromWeb, String passwordFromWeb) throws UserDaoException {

	
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rsRole = null;

		NewUserInfo user = null;

		try {


			con = pool.takeConnection();
			PreparedStatement ps = con.prepareStatement(CHECK_LOGIN);

			ps.setString(1, loginFromWeb);

			rs = ps.executeQuery();


			if (rs.next() && BCrypt.checkpw(passwordFromWeb, rs.getString("password"))) {

				int idRole = rs.getInt("roles_id");

				String role = "guest";

				if (idRole == 1) {
					role = "user";
				}
				if (idRole == 2) {

					role = "admin";

				}

				user = new NewUserInfo(rs.getInt("id"), rs.getString("login"), role);

			}

			else {
				throw new UserDaoException("No such user, check the data or register");
			}

		} catch (SQLException e) {
			throw new UserDaoException("No such user, check the data or register");
		} catch (ConnectionPoolException e) {

			throw new UserDaoException("Pool doesn't work in logination");

		}

		finally {

			pool.closeConnection(con, st, rs);

		}

		return user;

	}

	public String getRole(String login, String password) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		return "user";
	}

	final String ADD_USER = "INSERT INTO users(login,password) VALUES(?,?)";

	@Override
	public boolean registrationDB(NewUserInfo user) throws UserDaoException {

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
//					"root", "Irina1983");

			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_USER);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			

		} catch (SQLException e) {
			throw new UserDaoException("Data not saved in DB, check fields!");
		} catch (ConnectionPoolException e) {

			throw new UserDaoException("Pool doesn't work");

		}

		finally {

			pool.closeConnection(con, st, rs);

			// pool.clearConnectionQueue();

//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				throw new UserDaoException("DB problem");
//
//			}
//			try {
//				if (st != null) {
//					st.close();
//				}
//
//			} catch (SQLException e) {
//				throw new UserDaoException("DB problem");
//
//			}
//
//			try {
//
//				if (ps != null) {
//					ps.close();
//				}
//			} catch (SQLException e) {
//				throw new UserDaoException("DB problem");
//			}
//
//			try {
//				if (con != null) {
//					con.close();
//				}
//			}
//
//			catch (SQLException e) {
//				throw new UserDaoException("DB problem");
//			}
		}

		return true;
	}

}
