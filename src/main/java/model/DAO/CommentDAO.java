package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Comment;
import model.intefaces.IComment;
import utils.DBConection;

public class CommentDAO extends Comment implements IComment {

	private static final String GETALLUSERCOMMENTS = "SELECT * FROM comment WHERE userid=?";
	private static final String GETALLLISTCOMMENTS = "SELECT * FROM comment WHERE listid=?";
	private static final String INSERTCOMMENT = "INSERT INTO comment (userid, listid, date, text) VALUES (?,?,?,?)";
	private static final String GETCOMMENTBYID = "SELECT * FROM comment WHERE id=?";
	private static final String UPDATECOMMENTBYID = "UPDATE comment SET userid=?, listid=?, date=?, text=? WHERE id=?";
	private static final String DELETECOMMENTBYID = "DELETE FROM comment WHERE id=?";

	private Connection con = null;

	public CommentDAO() {
		super();
	}

	public CommentDAO(Comment c) {
		super(c);
	}

	@Override
	public boolean addComment() {

		if (this.id == -1) {
			con = DBConection.getConection();

			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTCOMMENT, Statement.RETURN_GENERATED_KEYS);

					ps.setInt(1, this.userid);
					ps.setInt(2, this.listid);
					ps.setDate(3, this.date);
					ps.setString(4, this.text);

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
	public Comment getCommentById(int id) {
		Comment comment = null;

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETCOMMENTBYID);

				ps.setInt(1, id);

				rs = ps.executeQuery();

				if (rs.next()) {
					comment = new Comment(rs.getInt("id"), rs.getInt("userid"), rs.getInt("listid"), rs.getDate("date"), rs.getString("text"));
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				comment = null;
			}
		}

		return comment;
	}

	@Override
	public java.util.List<Comment> getAllUserComments(int userid) {
		java.util.List<Comment> resultado = new ArrayList<Comment>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLUSERCOMMENTS);
				
				ps.setInt(1, userid);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					Comment a=new Comment(rs.getInt("id"), rs.getInt("userid"), rs.getInt("listid"), rs.getDate("date"), rs.getString("text"));
					resultado.add(a);
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
	public java.util.List<Comment> getAllListComments(int listid) {
		java.util.List<Comment> resultado = new ArrayList<Comment>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLLISTCOMMENTS);
				
				ps.setInt(1, listid);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					Comment a=new Comment(rs.getInt("id"), rs.getInt("userid"), rs.getInt("listid"), rs.getDate("date"), rs.getString("text"));
					resultado.add(a);
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
	public boolean updateComment() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATECOMMENTBYID);

				ps.setInt(1, this.userid);
				ps.setInt(2, this.listid);
				ps.setDate(3, this.date);
				ps.setString(4, this.text);
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
	public boolean deleteComment() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETECOMMENTBYID);
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
