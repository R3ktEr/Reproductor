package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Song;
import model.Songs_List;
import model.intefaces.ISongs_List;
import utils.DBConection;

public class Songs_ListDAO extends Songs_List implements ISongs_List{
	
	private static final String GETALLSONGSFROMLIST = "SELECT songid FROM songs_list WHERE listid=?";
	private static final String INSERTSONGLIST = "INSERT INTO songs_list (songid, listid) VALUES (?,?)";
	private static final String UPDATESONGLIST = "UPDATE songs_list SET songid=?, listid=? WHERE id=?";
	private static final String GETLISTOFSONG = "SELECT listid FROM songs_list WHERE songid=?";
	private static final String DELETESONGSOFLIST = "DELETE FROM songs_list WHERE listid=?";

	private Connection con = null;

	public Songs_ListDAO() {
		super();
	}
	
	public Songs_ListDAO(Songs_List sl) {
		super(sl);
	}
	
	@Override
	public boolean addSongToList(int songid, int listid) {
		if(songid>0&&listid>0) {	
			con = DBConection.getConection();
			
			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTSONGLIST, Statement.RETURN_GENERATED_KEYS);
					
					ps.setInt(1, songid);
					ps.setInt(2, listid);
					
					ps.executeUpdate();
					// Solo lo puedes ejecutar si has puesto RETURN_GENERATED_KEYS
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
						this.songid=songid;
						this.listid=listid;
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
	public List<Song> getSongsFromList(int listid) {
		List<Song> resultado = new ArrayList<Song>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLSONGSFROMLIST);
				
				ps.setInt(1, listid);
				
				rs = ps.executeQuery();
				
				SongDAO sdao=new SongDAO();

				while (rs.next()) {
					Song s=new Song(sdao.getSongById(rs.getInt("songid")));
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
	public List<Song> getListOfTheSong(int songid) {
		List<Song> resultado = new ArrayList<Song>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETLISTOFSONG);
				
				ps.setInt(1, songid);
				
				rs = ps.executeQuery();
				
				SongDAO sdao=new SongDAO();

				while (rs.next()) {
					Song s=new Song(sdao.getSongById(rs.getInt("songid")));
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
	public boolean updateSongOfList(int songid, int listid, int id) {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATESONGLIST);

				ps.setInt(1, songid);
				ps.setInt(2, listid);
				ps.setFloat(4, id);
				
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
	public boolean deleteSongsOfList(int listid) {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETESONGSOFLIST);
				ps.setInt(1, listid);
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
