package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Song;
import model.intefaces.ISong;
import utils.DBConection;

public class SongDAO extends Song implements ISong {

	private static final String GETALLSONGS = "SELECT * FROM song";
	private static final String INSERTSONG = "INSERT INTO song (discid, genreid, name, duration, n_reproductions) VALUES (?,?,?,?,?)";
	private static final String GETSONGBYID = "SELECT * FROM song WHERE id=?";
	private static final String GETSONGSBYDISC = "SELECT * FROM song WHERE discid=?";
	private static final String GETSONGSBYGENRE = "SELECT * FROM song WHERE genreid=?";
	private static final String UPDATESONGBYID = "UPDATE song SET discid=?, genreid=?, name=?, duration=?, n_reproductions=? WHERE id=?";
	private static final String DELETESONGBYID = "DELETE FROM song WHERE id=?";

	private Connection con = null;

	public SongDAO() {
		super();
	}

	public SongDAO(Song s) {
		super(s);
	}

	@Override
	public boolean addSong() {
		if (this.id == -1) {
			con = DBConection.getConection();

			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTSONG, Statement.RETURN_GENERATED_KEYS);

					ps.setInt(1, this.discid);
					ps.setInt(2, this.genreid);
					ps.setString(3, this.name);
					ps.setInt(4, this.duration);
					ps.setInt(5, this.n_reproductions);

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
	public List<Song> getAllSong() {
		List<Song> resultado = new ArrayList<Song>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLSONGS);
				rs = ps.executeQuery();

				while (rs.next()) {
					Song s = new Song(rs.getInt("id"), rs.getInt("discid"), rs.getInt("genreid"), rs.getString("name"),
							rs.getInt("duration"), rs.getInt("n_reproductions"));
					resultado.add(s);
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
	public Song getSongById(int songid) {
		Song song = null;

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETSONGBYID);

				ps.setInt(1, songid);

				rs = ps.executeQuery();

				if (rs.next()) {
					song = new Song(rs.getInt("id"), rs.getInt("discid"), rs.getInt("genreid"), rs.getString("name"),
							rs.getInt("duration"), rs.getInt("n_reproductions"));
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				song = null;
			}
		}

		return song;
	}
	
	@Override
	public List<Song> getSongsByDisc(int discid) {
		List<Song> resultado = new ArrayList<Song>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETSONGSBYDISC);
				
				ps.setInt(1, discid);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					Song s = new Song(rs.getInt("id"), rs.getInt("discid"), rs.getInt("genreid"), rs.getString("name"),
							rs.getInt("duration"), rs.getInt("n_reproductions"));
					resultado.add(s);
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
	public List<Song> getSongsByGenre(int genreid) {
		List<Song> resultado = new ArrayList<Song>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETSONGSBYGENRE);
				
				ps.setInt(1, genreid);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					Song s = new Song(rs.getInt("id"), rs.getInt("discid"), rs.getInt("genreid"), rs.getString("name"),
							rs.getInt("duration"), rs.getInt("n_reproductions"));
					resultado.add(s);
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
	public boolean updateSong() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATESONGBYID);

				ps.setInt(1, this.discid);
				ps.setInt(2, this.genreid);
				ps.setString(3, this.name);
				ps.setInt(4, this.duration);
				ps.setInt(5, this.n_reproductions);
				ps.setFloat(6, this.id);
				
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
	public boolean deleteSong() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETESONGBYID);
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
