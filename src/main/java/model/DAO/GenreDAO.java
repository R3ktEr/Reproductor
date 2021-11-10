package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Genre;
import model.intefaces.IGenre;
import utils.DBConection;

public class GenreDAO extends Genre implements IGenre {

	private static final String GETALLGENRES = "SELECT * FROM genre";
	private static final String INSERTGENRE = "INSERT INTO genre (name) VALUES (?)";
	private static final String GETGENREBYID = "SELECT * FROM genre WHERE id=?";
	private static final String UPDATEGENREBYID = "UPDATE genre SET name=? WHERE id=?";
	private static final String DELETEGENREBYID = "DELETE FROM genre WHERE id=?";

	private Connection con = null;

	public GenreDAO() {
		super();
	}

	public GenreDAO(Genre g) {
		super(g);
	}

	@Override
	public boolean addGenre() {
		if (this.id == -1) {
			con = DBConection.getConection();

			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTGENRE, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, this.name);

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
	public List<Genre> getAllGenres() {
		List<Genre> resultado = new ArrayList<Genre>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLGENRES);
				rs = ps.executeQuery();

				while (rs.next()) {
					Genre g = new Genre(rs.getInt("id"), rs.getString("name"));
					resultado.add(g);
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
	public Genre getGenreById(int id) {
		Genre genre = null;

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETGENREBYID);

				ps.setInt(1, id);

				rs = ps.executeQuery();

				if (rs.next()) {
					genre = new Genre(rs.getInt("id"), rs.getString("name"));
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				genre = null;
			}
		}

		return genre;
	}

	@Override
	public boolean updateGenre() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATEGENREBYID);

				ps.setString(1, this.name);
				ps.setFloat(2, this.id);
				
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
	public boolean deleteGenre() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETEGENREBYID);
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
