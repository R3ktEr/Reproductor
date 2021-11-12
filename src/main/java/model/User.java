package model;

import java.util.ArrayList;

public class User {
	protected int id;
	protected String name;
	protected String photo;
	protected String mail;
	protected java.util.List<List> userCreatedLists;
	protected java.util.List<Subscription> userListsSubscriptions;
	protected java.util.List<Comment> userComments;
	
	public User() {
		this(-1,"","","", new ArrayList<List>(), new ArrayList<Subscription>(), new ArrayList<Comment>());
	}
	
	public User(int id, String name, String photo, String mail, java.util.List<List> userCreatedLists, 
			java.util.List<Subscription> userListsSubscriptions, java.util.List<Comment> userComments) {
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.mail = mail;
		this.userCreatedLists = userCreatedLists;
		this.userListsSubscriptions = userListsSubscriptions;
		this.userComments = userComments;
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

	public java.util.List<List> getUserCreatedLists() {
		return userCreatedLists;
	}

	public void setUserCreatedLists(java.util.List<List> userCreatedLists) {
		this.userCreatedLists = userCreatedLists;
	}

	public java.util.List<Subscription> getUserListsSubscriptions() {
		return userListsSubscriptions;
	}

	public void setUserListsSubscriptions(java.util.List<Subscription> userListsSubscriptions) {
		this.userListsSubscriptions = userListsSubscriptions;
	}

	public java.util.List<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(java.util.List<Comment> userComments) {
		this.userComments = userComments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", photo=" + photo + ", mail=" + mail + "]";
	}
}
