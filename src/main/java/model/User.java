package model;

public class User {
	protected int id;
	protected String name;
	protected String photo;
	protected String mail;
	
	public User() {
		this(-1,"","","");
	}
	
	public User(int id, String name, String photo, String mail) {
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.mail = mail;
	}

	public User(String name, String photo, String mail) {
		this.id=-1;
		this.name = name;
		this.photo = photo;
		this.mail = mail;
	}
	
	public User(User u) {
		this.id = u.id;
		this.name = u.name;
		this.photo = u.photo;
		this.mail = u.mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", photo=" + photo + ", mail=" + mail + "]";
	}
}
