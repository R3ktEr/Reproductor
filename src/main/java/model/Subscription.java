package model;

public class Subscription {
	protected int id;
	protected int userid;
	protected int listid;
	
	public Subscription() {
		this(-1,-1,-1);
	}
	
	public Subscription(int id, int userid, int listid) {
		this.id = id;
		this.userid = userid;
		this.listid = listid;
	}

	public Subscription(int userid, int listid) {
		this.id=-1;
		this.userid = userid;
		this.listid = listid;
	}
	
	public Subscription(Subscription s) {
		this.id = s.id;
		this.userid = s.userid;
		this.listid = s.listid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getListid() {
		return listid;
	}

	public void setListid(int listid) {
		this.listid = listid;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", userid=" + userid + ", listid=" + listid + "]";
	}
}
