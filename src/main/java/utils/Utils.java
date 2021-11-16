package utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.Artist;
import model.Disc;
import model.Genre;
import model.Song;
import model.Subscription;
import model.DAO.ArtistDAO;
import model.DAO.DiscDAO;
import model.DAO.GenreDAO;
import model.DAO.SubscriptionDAO;

public class Utils {
	
	public static void popInfo(String content) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void popError(String content) {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static boolean popConfirmation(String content) {
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(content);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		    return false;
		}
	}
	
	public static void popWarning(String content) {
		Alert alert=new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Alerta");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static LocalDate dateToLocalDate(Date date) {
        LocalDate localDate = date.toLocalDate();
        
        return localDate;
	}
	
	public static Date localDateToDate(LocalDate date) {
		Date sqlDate=Date.valueOf(date);
		
		return sqlDate;
	}
	
	public static List<SongTableRow> songsToRow(List<Song> songsList) {
		List<SongTableRow> songsRows=new ArrayList<SongTableRow>();
		
		for (Song s : songsList) {
			Disc d=new DiscDAO().getDiscById(s.getDiscid());
			Artist a=new ArtistDAO().getArtistById(d.getArtistid());
			Genre g=new GenreDAO().getGenreById(s.getGenreid());
			
			SongTableRow row=new SongTableRow(s.getId(), a.getId(), s.getName(),a.getName(),d.getName(),g.getName(), s.getN_reproductions());
			
			songsRows.add(row);
		}
		
		return songsRows;
	}
	
	public static List<UsersListTableRow> usersListsToRows(List<model.List> playlistList, int userid) {
		List<UsersListTableRow> playlistRows=new ArrayList<UsersListTableRow>();
		
		boolean subscribed;
		
		SubscriptionDAO sdao=new SubscriptionDAO();
		
		for (model.List l : playlistList) {
			List<Subscription> userSubscriptions=sdao.getSubscriptionsByUser(userid);
			
			subscribed=false;
			
			for (Subscription s: userSubscriptions) {
				if(s.getListid()==l.getId()) {
					subscribed=true;
				}
			}
			
			UsersListTableRow row=new UsersListTableRow(l.getId(), l.getName(), l.getDescription(), l.getN_subscribers(), subscribed);
			
			playlistRows.add(row);
		}
		
		return playlistRows;
	}
}
