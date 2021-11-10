package model.intefaces;

import java.util.List;

import model.Song;

public interface ISong {
	public boolean addSong();
	public List<Song> getAllSong();
	public Song getSongById(int id);
	public boolean updateSong();
	public boolean deleteSong();
}
