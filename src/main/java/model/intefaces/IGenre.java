package model.intefaces;

import java.util.List;

import model.Genre;

public interface IGenre {
	public boolean addGenre();
	public List<Genre>getAllGenres();
	public Genre getGenreById(int id);
	public boolean updateGenre();
	public boolean deleteGenre();
}
