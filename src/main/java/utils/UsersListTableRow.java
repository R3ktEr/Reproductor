package utils;

public class UsersListTableRow {
	private int listid;
	private String name;
	private String description;
	private int subscribers;
	private boolean subscribed;
	private String subscribedYesNo;
	
	public UsersListTableRow() {
		this(-1,"","",0,false);
	}

	public UsersListTableRow(int listid, String name, String description, int subscribers, boolean subscribed) {
		super();
		this.listid = listid;
		this.name = name;
		this.description = description;
		this.subscribers = subscribers;
		this.subscribed = subscribed;
		
		if(this.subscribed==false) {
			this.subscribedYesNo="NO";
		}else {
			this.subscribedYesNo="SI";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + listid;
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
		UsersListTableRow other = (UsersListTableRow) obj;
		if (listid != other.listid)
			return false;
		return true;
	}

	public int getListid() {
		return listid;
	}

	public void setListid(int listid) {
		this.listid = listid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
		
		if(this.subscribed==false) {
			this.subscribedYesNo="NO";
		}else {
			this.subscribedYesNo="SI";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubscribedYesNo() {
		return subscribedYesNo;
	}

	public void setSubscribedYesNo(String subscribedYesNo) {
		this.subscribedYesNo = subscribedYesNo;
	}

	@Override
	public String toString() {
		return "UsersListTableRow [listid=" + listid + ", name=" + name + ", description=" + description
				+ ", subscribers=" + subscribers + ", subscribed=" + subscribed + "]";
	}
}
