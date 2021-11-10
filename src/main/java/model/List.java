package model;

public class List {
	protected int id;
	protected int userid;
	protected String name;
	protected String description;
	protected int n_subscribers;
	
	public List() {
		this(-1,-1,"","",-1);
	}
	
	public List(int id, int userid, String name, String description, int n_subscribers) {
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.description = description;
		this.n_subscribers = n_subscribers;
	}

	public List(int userid, String name, String description, int n_subscribers) {
		this.id=-1;
		this.userid = userid;
		this.name = name;
		this.description = description;
		this.n_subscribers = n_subscribers;
	}
	
	public List(List l) {
		this.id = l.id;
		this.userid = l.userid;
		this.name = l.name;
		this.description = l.description;
		this.n_subscribers = l.n_subscribers;
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
		List other = (List) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getN_subscribers() {
		return n_subscribers;
	}

	public void setN_subscribers(int n_subscribers) {
		this.n_subscribers = n_subscribers;
	}

	@Override
	public String toString() {
		return "List [id=" + id + ", userid=" + userid + ", name=" + name + ", description=" + description
				+ ", n_subscribers=" + n_subscribers + "]";
	}
}
