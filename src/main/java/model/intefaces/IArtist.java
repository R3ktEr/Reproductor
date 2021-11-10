package model.intefaces;

import java.util.List;

import model.Artist;

public interface IArtist {
	public boolean addArtist();
	public List<Artist> getAllArtists();
	public Artist getArtistById(int id);
	public boolean updateArtist();
	public boolean deleteArtist();
}
