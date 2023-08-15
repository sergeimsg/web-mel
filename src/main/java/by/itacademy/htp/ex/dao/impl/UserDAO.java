package by.itacademy.htp.ex.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import by.itacademy.htp.ex.bean.NewUserInfo;
import by.itacademy.htp.ex.dao.UserDaoException;
import by.itacademy.htp.ex.dao.conpool.ConnectionPool;
import by.itacademy.htp.ex.dao.conpool.Exception.ConnectionPoolException;
import by.itacademy.htp.ex.dao.IUserDAO;

public class UserDAO implements IUserDAO {

	private static final ConnectionPool pool = ConnectionPool.getInstance();

	private static final String CHECK_LOGIN = "SELECT * FROM users JOIN users_has_role ON "
			+ " users.id=users_has_role.users_id  WHERE users.login = ?";

	private static final String GET_ROLE_ID = "SELECT * FROM users_has_role WHERE 	users_id = ?";

	@Override
	public NewUserInfo logination(String loginFromWeb, String passwordFromWeb) throws UserDaoException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		NewUserInfo user = null;

		try {

			con = pool.takeConnection();
			ps = con.prepareStatement(CHECK_LOGIN);

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

		} 
		catch (SQLException e) {
			throw new UserDaoException("No such user, check the data or register",e);
		} 
		catch (ConnectionPoolException e) {

			throw new UserDaoException("Pool doesn't work in logination",e);

		}

		finally {

			try {
				pool.closeConnection(con, st, rs, ps);
			} catch (ConnectionPoolException e) {


				throw new UserDaoException(e);
			}

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

			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_USER);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new UserDaoException("Data not saved in DB, check fields!", e);
		} catch (ConnectionPoolException e) {

			throw new UserDaoException("Pool doesn't work", e);

		}

		finally {

			try {
				pool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				
				throw new UserDaoException(e);
				
			}

		}

		return true;
	}

	private static final String SQL_GET_LIST_OF_USERS = "SELECT * FROM user_details JOIN users ON "
			+ "user_details.users_id=users.id JOIN users_has_role ON users.id=users_has_role.users_id ";

	@Override
	public List<NewUserInfo> getUsersList() throws UserDaoException {

		List<NewUserInfo> userList = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SQL_GET_LIST_OF_USERS);

			while (rs.next()) {

				userList.add(new NewUserInfo(rs.getInt("users_id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("login"), rs.getString("email"), rs.getString("roles_id")));

			}


		} catch (SQLException e) {

			throw new UserDaoException("Problem with DB", e);
		} catch (ConnectionPoolException e) {

			throw new UserDaoException("Problem with connection pool", e);
		}

		finally {

			try {
				pool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {


				throw new UserDaoException(e);
			}

		}

		return userList;
	}

	private static final String SQL_GET_USER_BY_ID = "SELECT * FROM user_details JOIN users ON "
			+ "user_details.users_id=users.id JOIN users_has_role ON users.id=users_has_role.users_id ";

	@Override
	public NewUserInfo getUserDetail(int userId) throws UserDaoException {

		NewUserInfo userEdit = null;

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SQL_GET_USER_BY_ID);

			while (rs.next()) {

				if (rs.getInt("users_id") == userId) {

					userEdit = new NewUserInfo(rs.getInt("users_id"), rs.getString("name"), rs.getString("surname"),
							rs.getString("login"), rs.getString("email"), rs.getString("roles_id"));

					break;
				}

				else {

					userEdit = null;

				}

			}

		} catch (SQLException e) {
			throw new UserDaoException("Data not saved in DB, check fields!", e);
		} catch (ConnectionPoolException e) {

			throw new UserDaoException("No user available ", e);

		}

		finally {

			try {
				pool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				
				throw new UserDaoException(e);
			}

		}

		return userEdit;

	}

//	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user_details JOIN users ON user_details.users_id = users.id "
//       	+ "JOIN users_has_role ON users.id=users_has_role.users_id "
//			+ "SET name =?, surname = ?, email = ?, login = ? WHERE users_id = ?";
	
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user_details JOIN users ON user_details.users_id = users.id "
				+ "SET name =?, surname = ?, email = ?, login = ? WHERE users_id = ?";


	


	@Override
	public boolean editUserDetail(NewUserInfo userUpdate) throws UserDaoException {

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = pool.takeConnection();

			ps = con.prepareStatement(SQL_UPDATE_USER_BY_ID);
			ps.setString(1, userUpdate.getName());
			ps.setString(2, userUpdate.getSurname());
			ps.setString(3, userUpdate.getEmail());
		    ps.setString(4, userUpdate.getLogin());
			ps.setInt(5, userUpdate.getUserID());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new UserDaoException("Data not saved in DB, check fields!", e);
		} catch (ConnectionPoolException e) {

			throw new UserDaoException("Pool doesn't work", e);

		}

		finally {

			try {
				pool.closeConnection(con, st, rs, ps);
			} catch (ConnectionPoolException e) {

				throw new UserDaoException(e);
				
			}

		}

		return true;
	}

}
