package model;

public class Song {
	protected int id;
	protected int discid;
	protected int genreid;
	protected String name;
	protected int duration;
	
	public Song() {
		this(-1,-1,-1,"",-1);
	}

	public Song(int id, int discid, int genreid, String name, int duration) {
		this.id = id;
		this.discid = discid;
		this.genreid = genreid;
		this.name = name;
		this.duration = duration;
	}

	public Song(int discid, int genreid, String name, int duration) {
		this.id=-1;
		this.discid = discid;
		this.genreid = genreid;
		this.name = name;
		this.duration = duration;
	}
	
	public Song(Song s) {
		this.id = s.id;
		this.discid = s.discid;
		this.genreid = s.genreid;
		this.name = s.name;
		this.duration = s.duration;
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
		Song other = (Song) obj;
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

	public int getDiscid() {
		return discid;
	}

	public void setDiscid(int discid) {
		this.discid = discid;
	}

	public int getGenreid() {
		return genreid;
	}

	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", discid=" + discid + ", genreid=" + genreid + ", name=" + name + ", duration="
				+ duration + "]";
	}
}
