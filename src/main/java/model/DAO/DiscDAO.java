package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Disc;
import model.intefaces.IDisc;
import utils.DBConection;

public class DiscDAO extends Disc implements IDisc {

	private static final String GETALLDISCS = "SELECT * FROM disc";
	private static final String GETDISCSBYARTIST = "SELECT * FROM disc WHERE artistid=?";
	private static final String INSERTDISC = "INSERT INTO disc (artistid, name, publish_date, photo, n_reproductions) VALUES (?,?,?,?,?)";
	private static final String GETDISCBYID = "SELECT * FROM disc WHERE id=?";
	private static final String UPDATEDISCBYID = "UPDATE disc SET artistid=?, name=?, publish_date=?, photo=?, n_reproductions=? WHERE id=?";
	private static final String DELETEDISCBYID = "DELETE FROM disc WHERE id=?";

	private Connection con = null;

	public DiscDAO() {
		super();
	}

	public DiscDAO(Disc d) {
		super(d);
	}

	@Override
	public boolean addDisc() {
		if (this.id == -1) {
			con = DBConection.getConection();

			if (con != null) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = con.prepareStatement(INSERTDISC, Statement.RETURN_GENERATED_KEYS);

					ps.setInt(1, this.artistid);
					ps.setString(2, this.name);
					ps.setInt(3, this.publish_date);
					ps.setString(4, this.photo);
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
	public List<Disc> getAllDiscs() {
		List<Disc> resultado = new ArrayList<Disc>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLDISCS);
				rs = ps.executeQuery();

				while (rs.next()) {
					Disc d = new Disc(rs.getInt("id"), rs.getInt("artistid"), rs.getString("name"),
							rs.getInt("publish_date"), rs.getString("photo"), rs.getInt("n_reproductions"),
							new SongDAO().getSongsByDisc(rs.getInt("id")));
					resultado.add(d);
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
	public Disc getDiscById(int discid) {
		Disc disc = null;

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETDISCBYID);

				ps.setInt(1, discid);

				rs = ps.executeQuery();

				if (rs.next()) {
					disc = new Disc(rs.getInt("id"), rs.getInt("artistid"), rs.getString("name"),
							rs.getInt("publish_date"), rs.getString("photo"), rs.getInt("n_reproductions"),
							new SongDAO().getSongsByDisc(rs.getInt("id")));
				}

				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				disc = null;
			}
		}

		return disc;
	}

	@Override
	public List<Disc> getDiscsByArtist(int artistid) {
		List<Disc> resultado = new ArrayList<Disc>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETDISCSBYARTIST);
				
				ps.setInt(1, artistid);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					Disc d = new Disc(rs.getInt("id"), rs.getInt("artistid"), rs.getString("name"),
							rs.getInt("publish_date"), rs.getString("photo"), rs.getInt("n_reproductions"), 
							new SongDAO().getSongsByDisc(rs.getInt("id")));
					resultado.add(d);
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
	public boolean updateDisc() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(UPDATEDISCBYID);

				ps.setInt(1, this.artistid);
				ps.setString(2, this.name);
				ps.setInt(3, this.publish_date);
				ps.setString(4, this.photo);
				ps.setInt(5, this.n_reproductions);
				ps.setInt(6, this.id);
				
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
	public boolean deleteDisc() {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETEDISCBYID);
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
