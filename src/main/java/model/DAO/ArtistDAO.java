package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Artist;
import model.intefaces.IArtist;
import utils.DBConection;

public class ArtistDAO extends Artist implements IArtist {

	private static final String GETALLARTISTS = "SELECT * FROM artist";
	private static final String INSERTARTIST = "INSERT INTO artist (name, nationality, photo) VALUES (?,?,?)";
	private static final String GETARTISTBYID = "SELECT * FROM artist WHERE id=?";
	private static final String UPDATEARTISTBYID = "UPDATE artist SET name=?, nationality=?, photo=? WHERE id=?";
	private static final String DELETEARTISTBYID = "DELETE FROM artist WHERE id=?";

	private Connection con = null;

	public ArtistDAO() {
		super();
	}

	public ArtistDAO(Artist a) {
		super(a);
	}

	@Override
	public boolean addArtist() {
		if (this.id == -1) {
			con = DBConection.getConection();

			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTARTIST, Statement.RETURN_GENERATED_KEYS);

					ps.setString(1, this.name);
					ps.setString(2, this.nationality);
					ps.setString(3, this.photo);

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
	public List<Artist> getAllArtists() {

		List<Artist> resultado = new ArrayList<Artist>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLARTISTS);
				rs = ps.executeQuery();

				while (rs.next()) {
					Artist a=new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("nationality"), rs.getString("photo"),
							new DiscDAO().getDiscsByArtist(rs.getInt("id")));
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
	public Artist getArtistById(int artistid) {
		Artist artist=null;
		
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETARTISTBYID);
				
				ps.setInt(1, artistid);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					artist=new Artist(rs.getInt("id"), rs.getString("name"), rs.getString("nationality"), rs.getString("photo"),
							new DiscDAO().getDiscsByArtist(rs.getInt("id")));					
				}
				
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				artist=null;
			}
		}
		
		return artist;
	}

	@Override
	public boolean updateArtist() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATEARTISTBYID);

				ps.setString(1, this.name);
				ps.setString(2, this.nationality);
				ps.setString(3, this.photo);
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
	public boolean deleteArtist() {	
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETEARTISTBYID);
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
