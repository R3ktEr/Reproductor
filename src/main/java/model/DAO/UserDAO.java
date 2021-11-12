package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.intefaces.IUser;
import utils.DBConection;

public class UserDAO extends User implements IUser {

	private static final String GETALLUSERS = "SELECT * FROM user";
	private static final String INSERTUSER = "INSERT INTO user (name, photo, mail) VALUES (?,?,?)";
	private static final String GETUSERBYID = "SELECT * FROM user WHERE id=?";
	private static final String UPDATEUSERBYID = "UPDATE user SET name=?, photo=?, mail=? WHERE id=?";
	private static final String DELETEUSERBYID = "DELETE FROM user WHERE id=?";

	private Connection con = null;

	public UserDAO() {
		super();
	}

	public UserDAO(User u) {
		super(u);
	}

	@Override
	public boolean addUser() {
		if (this.id == -1) {
			con = DBConection.getConection();

			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTUSER, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, this.name);
					ps.setString(2, this.photo);
					ps.setString(3, this.mail);

					ps.executeUpdate();
					// Solo lo puedes ejecutar si has puesto RETURN_GENERATED_KEYS
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
					}
					// fin de extraer el id generado automaticamente en la db

					ps.close();
					rs.close();

					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}

		return false;
	}

	@Override
	public User getUserById(int userid) {
		User user = null;

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETUSERBYID);

				ps.setInt(1, userid);

				rs = ps.executeQuery();

				if (rs.next()) {
					user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("photo"), rs.getString("mail"),
							new ListDAO().getListsByUser(rs.getInt("id")), new SubscriptionDAO().getSubscriptionsByUser(rs.getInt("id")),
							new CommentDAO().getAllUserComments(rs.getInt("id")));
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				user = null;
			}
		}

		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> usersList = new ArrayList<User>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLUSERS);
				rs = ps.executeQuery();

				while (rs.next()) {
					User u=new User(rs.getInt("id"), rs.getString("name"), rs.getString("photo"), rs.getString("mail"),
							new ListDAO().getListsByUser(rs.getInt("id")), new SubscriptionDAO().getSubscriptionsByUser(rs.getInt("id")),
							new CommentDAO().getAllUserComments(rs.getInt("id")));
					usersList.add(u);
				}
				
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return usersList;
	}

	@Override
	public boolean updateUser() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATEUSERBYID);

				ps.setString(1, this.name);
				ps.setString(2, this.photo);
				ps.setString(3, this.mail);
				ps.setFloat(4, this.id);
				
				ps.executeUpdate();
				ps.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteUser() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETEUSERBYID);
				ps.setInt(1, this.id);
				ps.executeUpdate();
				this.id=-1;
				ps.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}
}
