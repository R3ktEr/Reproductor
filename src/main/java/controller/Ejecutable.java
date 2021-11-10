package controller;

import model.Artist;
import model.DAO.ArtistDAO;

public class Ejecutable {

	public static void main(String[] args) {
		
		ArtistDAO a=new ArtistDAO(new Artist("artista5","japones","foto"));
		
		//System.out.println(a.getArtistById(3));
		//System.out.println(a.getAllArtists().toString());
		System.out.println(a.addArtist());
	}
}
