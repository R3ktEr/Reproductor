package model;

import java.sql.Date;
import java.time.LocalDate;

public class Comment {
	protected int id;
	protected int userid;
	protected int listid;
	protected Date date;
	protected String text;
	
	public Comment() {
		this(-1,-1,-1, Date.valueOf(LocalDate.now()), "");
	}
	
	public Comment(int id, int userid, int listid, Date date, String text) {
		this.id = id;
		this.userid = userid;
		this.listid = listid;
		this.date = date;
		this.text = text;
	}

	public Comment(int userid, int listid, Date date, String text) {
		this.id=-1;
		this.userid = userid;
		this.listid = listid;
		this.date = date;
		this.text = text;
	}
	
	public Comment(Comment c) {
		this.id = c.id;
		this.userid = c.userid;
		this.listid = c.listid;
		this.date = c.date;
		this.text = c.text;
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
		Comment other = (Comment) obj;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userid=" + userid + ", listid=" + listid + ", date=" + date + ", text=" + text
				+ "]";
	}
}
