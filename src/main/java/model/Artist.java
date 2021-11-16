package model;

import java.util.ArrayList;

public class Artist {
	protected int id;
	protected String name;
	protected String nationality;
	protected String photo;
	protected java.util.List<Disc> artistDiscs;

	public Artist() {
		this(-1,"","","", new ArrayList<Disc>());
	}

	public Artist(int id, String name, String nationality, String photo, java.util.List<Disc> artistDiscs) {
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.photo = photo;
		this.artistDiscs = artistDiscs;
	}
	
	public Artist(String name, String nationality, String photo) {
		this.id = -1;
		this.name = name;
		this.nationality = nationality;
		this.photo = photo;
	}
	
	public Artist(Artist a) {
		this.id = a.id;
		this.name = a.name;
		this.nationality = a.nationality;
		this.photo = a.photo;
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
		Artist other = (Artist) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public java.util.List<Disc> getArtistDiscs() {
		return artistDiscs;
	}

	public void setArtistDiscs(java.util.List<Disc> artistDiscs) {
		this.artistDiscs = artistDiscs;
	}

	@Override
	public String toString() {
		return name;
	}
}
