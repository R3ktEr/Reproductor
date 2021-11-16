package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Subscription;
import model.intefaces.ISubscription;
import utils.DBConection;

public class SubscriptionDAO extends Subscription implements ISubscription{
	
	private static final String GETALLSUBSCRIPTIONS = "SELECT * FROM subcription";
	private static final String GETSUBCRIPTIONSBYUSER = "SELECT * FROM subscription WHERE userid=?";
	private static final String INSERTSUBSCRIPTION = "INSERT INTO subscription (userid, listid) VALUES (?,?)";
	private static final String DELETESUBSCRIPTION = "DELETE FROM subscription WHERE userid=? AND listid=?";

	private Connection con = null;

	public SubscriptionDAO() {
		super();
	}
	
	public SubscriptionDAO(Subscription s) {
		super(s);
	}
	
	@Override
	public boolean addSubscription(int userid, int listid) {
		if(userid>0&&listid>0) {
			if (this.id == -1) {
				con = DBConection.getConection();
				
				if (con != null) {
					PreparedStatement ps = null;
					ResultSet rs = null;
					try {
						ps = con.prepareStatement(INSERTSUBSCRIPTION, Statement.RETURN_GENERATED_KEYS);
						
						ps.setInt(1, userid);
						ps.setInt(2, listid);
						
						ps.executeUpdate();
						// Solo lo puedes ejecutar si has puesto RETURN_GENERATED_KEYS
						rs = ps.getGeneratedKeys();
						if (rs.next()) {
							this.id = rs.getInt(1);
							this.userid=userid;
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
		}
		
		return false;
	}
	
	@Override
	public java.util.List<Subscription> getAllSubscriptions() {

		java.util.List<Subscription> subscriptions = new ArrayList<Subscription>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALLSUBSCRIPTIONS);
				rs = ps.executeQuery();
				
				ListDAO ldao=new ListDAO();

				while (rs.next()) {
					Subscription s=new Subscription(rs.getInt("id"), rs.getInt("userid"), rs.getInt("listid"), 
							ldao.getListById(rs.getInt("listid")));
					subscriptions.add(s);
				}
				
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return subscriptions;
	}

	@Override
	public java.util.List<Subscription> getSubscriptionsByUser(int userid) {
		java.util.List<Subscription> userSubscriptions = new ArrayList<Subscription>();

		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETSUBCRIPTIONSBYUSER);
				
				ps.setInt(1, userid);
				
				rs = ps.executeQuery();
				
				ListDAO ldao=new ListDAO();

				while (rs.next()) {
					Subscription s = new Subscription(rs.getInt("id"), rs.getInt("userid"), rs.getInt("listid"), 
							ldao.getListById(rs.getInt("listid")));
					userSubscriptions.add(s);
				}
				
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return userSubscriptions;
	}

	@Override
	public boolean deleteSubscription(int userid, int listid) {
		con = DBConection.getConection();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(DELETESUBSCRIPTION);
				ps.setInt(1, userid);
				ps.setInt(2, listid);
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
