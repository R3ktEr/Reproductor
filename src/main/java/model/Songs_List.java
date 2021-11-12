package model;

public class Songs_List {
	protected int id;
	protected int songid;
	protected int listid;
	
	public Songs_List() {
		this(-1,-1,-1);
	}
	
	public Songs_List(int id, int songid, int listid) {
		this.id = id;
		this.songid = songid;
		this.listid = listid;
	}

	public Songs_List(Song song, List list) {
		this.id=-1;
		this.songid = song.id;
		this.listid = list.id;
	}
	
	public Songs_List(List list) {
		this.id=-1;
		this.songid = -1;
		this.listid = list.id;
	}
	
	public Songs_List(Songs_List sl) {
		this.id = sl.id;
		this.songid = sl.songid;
		this.listid = sl.listid;
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
		Songs_List other = (Songs_List) obj;
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

	public int getSongid() {
		return songid;
	}

	public void setSongid(int songid) {
		this.songid = songid;
	}

	public int getListid() {
		return listid;
	}

	public void setListid(int listid) {
		this.listid = listid;
	}

	@Override
	public String toString() {
		return "Songs_List [id=" + id + ", songid=" + songid + ", listid=" + listid + "]";
	}
}
