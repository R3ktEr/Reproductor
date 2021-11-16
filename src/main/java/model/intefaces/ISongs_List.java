package model.intefaces;

import java.util.List;

import model.Song;

public interface ISongs_List {
	public boolean addSongToList(int songid, int listid);
	public List<Song> getSongsFromList(int listid);
	public List<Song> getListOfTheSong(int songid);
	public boolean updateSongOfList(int songid, int listid, int id);
	public boolean deleteSongsOfList(int songid);
}
