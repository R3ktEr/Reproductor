package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.List;
import model.intefaces.IList;
import utils.DBConection;

public class ListDAO extends List implements IList {

	private static final String GETALLLISTS = "SELECT * FROM list";
	private static final String INSERTLIST = "INSERT INTO list (userid, name, description, n_subscribers) VALUES (?,?,?,?)";
	private static final String GETLISTBYID = "SELECT * FROM list WHERE id=?";
	private static final String GETLISTSBYUSER = "SELECT * FROM list WHERE userid=?";
	private static final String UPDATELISTBYID = "UPDATE list SET userid=?, name=?, description=?, n_subscribers=? WHERE id=?";
	private static final String DELETELISTBYID = "DELETE FROM list WHERE id=?";

	private Connection con = null;

	public ListDAO() {
		super();
	}

	public ListDAO(List l) {
		super(l);
	}

	@Override
	public boolean addList() {
		if (this.id == -1) {
			con = DBConection.getConection();

			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTLIST, Statement.RETURN_GENERATED_KEYS);

					ps.setInt(1, this.userid);
					ps.setString(2, this.name);
					ps.setString(3, this.description);
					ps.setInt(4, this.n_subscribers);

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
	public java.util.List<List> getAllLists() {
		java.util.List<List> resultado = new ArrayList<List>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLLISTS);
				rs = ps.executeQuery();

				while (rs.next()) {
					List l = new List(rs.getInt("id"), rs.getInt("userid"), rs.getString("name"),
							rs.getString("description"), rs.getInt("n_subscribers"), new Songs_ListDAO().getSongsFromList(rs.getInt("id")));
					resultado.add(l);
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public List getListById(int listid) {
		List list = null;

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETLISTBYID);

				ps.setInt(1, listid);

				rs = ps.executeQuery();

				if (rs.next()) {
					list = new List(rs.getInt("id"), rs.getInt("userid"), rs.getString("name"),
							rs.getString("description"), rs.getInt("n_subscribers"), new Songs_ListDAO().getSongsFromList(rs.getInt("id")));
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				list = null;
			}
		}

		return list;
	}
	
	@Override
	public java.util.List<List> getListsByUser(int userid) {
		java.util.List<List> resultado = new ArrayList<List>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETLISTSBYUSER);
				
				ps.setInt(1, userid);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					List l = new List(rs.getInt("id"), rs.getInt("userid"), rs.getString("name"),
							rs.getString("description"), rs.getInt("n_subscribers"), new Songs_ListDAO().getSongsFromList(rs.getInt("id")));
					resultado.add(l);
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public boolean updateList() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATELISTBYID);

				ps.setInt(1, this.userid);
				ps.setString(2, this.name);
				ps.setString(3, this.description);
				ps.setInt(4, this.n_subscribers);
				ps.setInt(5, this.id);
				
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
	public boolean deleteList() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETELISTBYID);
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
