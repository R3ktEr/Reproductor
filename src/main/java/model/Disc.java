package model;

import java.util.ArrayList;

public class Disc {
	protected int id;
	protected int artistid;
	protected String name;
	protected int publish_date;
	protected String photo;
	protected int n_reproductions;
	protected java.util.List<Song> discSongs;
	
	public Disc() {
		this(-1,-1,"",-1,"",-1, new ArrayList<Song>());
	}

	public Disc(int id, int artistid, String name, int publish_date, String photo, int n_reproductions, java.util.List<Song> discSongs) {
		this.id = id;
		this.artistid = artistid;
		this.name = name;
		this.publish_date = publish_date;
		this.photo = photo;
		this.n_reproductions = n_reproductions;
		this.discSongs = discSongs;
	}
	
	public Disc(int artistid, String name, int publish_date, String photo, int n_reproductions) {
		this.id = -1;
		this.artistid = artistid;
		this.name = name;
		this.publish_date = publish_date;
		this.photo = photo;
		this.n_reproductions = n_reproductions;
	}
	
	public Disc(Disc d) {
		this.id = d.id;
		this.artistid = d.artistid;
		this.name = d.name;
		this.publish_date = d.publish_date;
		this.photo = d.photo;
		this.n_reproductions = d.n_reproductions;
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
		Disc other = (Disc) obj;
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

	public int getArtistid() {
		return artistid;
	}

	public void setArtistid(int artistid) {
		this.artistid = artistid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(int publish_date) {
		this.publish_date = publish_date;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getN_reproductions() {
		return n_reproductions;
	}

	public void setN_reproductions(int n_reproductions) {
		this.n_reproductions = n_reproductions;
	}

	public java.util.List<Song> getDiscSongs() {
		return discSongs;
	}

	public void setDiscSongs(java.util.List<Song> discSongs) {
		this.discSongs = discSongs;
	}

	@Override
	public String toString() {
		return "Disc [id=" + id + ", artistid=" + artistid + ", name=" + name + ", publish_date=" + publish_date
				+ ", photo=" + photo + ", n_reproductions=" + n_reproductions + "]";
	}
}
