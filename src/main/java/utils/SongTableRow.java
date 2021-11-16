package utils;

public class SongTableRow {
	private int songid;
	private int artistid;
	private String song;
	private String artist;
	private String disc;
	private String genre;
	private int plays;
	
	public SongTableRow() {
		this(-1,-1,"","","","",0);
	}

	public SongTableRow(int songid, int artistid, String song, String artist, String disc, String genre, int plays) {
		super();
		this.songid = songid;
		this.artistid = artistid;
		this.song = song;
		this.artist = artist;
		this.disc = disc;
		this.genre = genre;
		this.plays = plays;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artistid;
		result = prime * result + songid;
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
		SongTableRow other = (SongTableRow) obj;
		if (artistid != other.artistid)
			return false;
		if (songid != other.songid)
			return false;
		return true;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getDisc() {
		return disc;
	}

	public void setDisc(String disc) {
		this.disc = disc;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getArtistid() {
		return artistid;
	}

	public void setArtistid(int artistid) {
		this.artistid = artistid;
	}

	public int getSongid() {
		return songid;
	}

	public void setSongid(int songid) {
		this.songid = songid;
	}

	public int getPlays() {
		return plays;
	}

	public void setPlays(int plays) {
		this.plays = plays;
	}

	@Override
	public String toString() {
		return "SongTableRow [songid=" + songid + ", artistid=" + artistid + ", song=" + song + ", artist=" + artist
				+ ", disc=" + disc + ", genre=" + genre + ", plays=" + plays + "]";
	}
}
