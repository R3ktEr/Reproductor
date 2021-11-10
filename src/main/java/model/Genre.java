package model;

public class Genre {
	protected int id;
	protected String name;
	
	public Genre() {
		this(-1,"");
	}
	
	public Genre(int id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
	}
}
