package model.intefaces;

import model.List;
import model.Subscription;

public interface ISubscription {
	public boolean addSubscription(int userid, int listid);
	public java.util.List<Subscription> getAllSubscriptions();
	public java.util.List<List> getSubscriptionsByUser(int userid);
	public boolean deleteSubscription();
}
