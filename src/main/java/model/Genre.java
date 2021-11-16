package model;

import java.util.ArrayList;

public class Genre {
	protected int id;
	protected String name;
	protected java.util.List<Song> songsOfGenre;
	
	public Genre() {
		this(-1,"", new ArrayList<Song>());
	}
	
	public Genre(int id, String name, java.util.List<Song> songsOfGenre) {
		this.id = id;
		this.name = name;
		this.songsOfGenre = songsOfGenre;
	}

	public Genre(String name) {
		this.id=-1;
		this.name = name;
	}

	public Genre(Genre g) {
		this.id = g.id;
		this.name = g.name;
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
		Genre other = (Genre) obj;
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

	public java.util.List<Song> getSongsOfGenre() {
		return songsOfGenre;
	}

	public void setSongsOfGenre(java.util.List<Song> songsOfGenre) {
		this.songsOfGenre = songsOfGenre;
	}

	@Override
	public String toString() {
		return name;
	}
}
