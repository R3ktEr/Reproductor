package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Artist;
import model.Disc;
import model.Genre;
import model.List;
import model.Song;
import model.Subscription;
import model.DAO.ArtistDAO;
import model.DAO.DiscDAO;
import model.DAO.GenreDAO;
import model.DAO.ListDAO;
import model.DAO.SongDAO;
import model.DAO.Songs_ListDAO;
import model.DAO.SubscriptionDAO;
import model.DAO.UserDAO;
import utils.DBConection;
import utils.SongTableRow;
import utils.UsersListTableRow;
import utils.Utils;

public class MainController implements Initializable {

	@FXML
	private TabPane tpSongs;

	@FXML
	private Tab tabSongs;

	@FXML
	private Tab tabPlaylists;

	@FXML
	private Tab tabAdmin;

	@FXML
	private TabPane tpUserTabs;

	@FXML
	private Tab tabAllSongs;

	@FXML
	private Tab tabPlaylist;

	@FXML
	private TitledPane tlpSearchSongs;

	@FXML
	private TitledPane tlpSongs;

	@FXML
	private TableView<SongTableRow> tvAllSongs;

	@FXML
	private TableColumn<SongTableRow, String> tcAllSongsName;

	@FXML
	private TableColumn<SongTableRow, String> tcAllSongsArtist;

	@FXML
	private TableColumn<SongTableRow, String> tcAllSongsDisc;

	@FXML
	private TableColumn<SongTableRow, String> tcAllSongsGenre;

	@FXML
	private TableColumn<SongTableRow, String> tcAllSongsPlays;

	@FXML
	private TextField tSearchName;

	@FXML
	private TextField tSearchArtist;

	@FXML
	private TextField tSearchDisc;

	@FXML
	private TextField tSearchGenre;

	@FXML
	private ImageView ivSong;

	@FXML
	private Label rlName;

	@FXML
	private Label rlDisc;

	@FXML
	private Label rlArtist;

	@FXML
	private Button bPrevSong;

	@FXML
	private Button bPlay;

	@FXML
	private Button bNextSong;

	@FXML
	private Slider slider;

	@FXML
	private TableView<SongTableRow> tvPlaylist;

	@FXML
	private TableColumn<SongTableRow, String> tcPlaylistName;

	@FXML
	private TableColumn<SongTableRow, String> tcPlaylistArtist;

	@FXML
	private TableColumn<SongTableRow, String> tcPlaylistDisc;

	@FXML
	private TableColumn<SongTableRow, String> tcPlaylistGenre;

	@FXML
	private TableColumn<SongTableRow, String> tcPlaylistPlays;

	@FXML
	private Tab tabCreatedPlaylists;

	@FXML
	private Tab tabAllUserPlaylists;

	@FXML
	private Tab tabAllPlaylists;

	@FXML
	private TableView<List> tvUserCreatedPlaylists;

	@FXML
	private TableColumn<List, String> tcTUCPName;

	@FXML
	private TableColumn<List, String> tcTUCPDescription;

	@FXML
	private TableColumn<List, Integer> tcTUCPSubscribers;

	@FXML
	private Button bCreatePlaylist;

	@FXML
	private Button bDeletePlaylist;

	@FXML
	private Button bEditPlaylist;

	@FXML
	private TableView<List> tvAllUserPlaylists;

	@FXML
	private TableColumn<List, String> tcTAUPName;

	@FXML
	private TableColumn<List, String> tcTAUPDescription;

	@FXML
	private TableColumn<List, Integer> tcTAUPSubscribers;

	@FXML
	private TableView<UsersListTableRow> tvUsersPlaylists;

	@FXML
	private TableColumn<UsersListTableRow, String> tcUPName;

	@FXML
	private TableColumn<UsersListTableRow, String> tcUPDescription;

	@FXML
	private TableColumn<UsersListTableRow, String> tcUPSubscribers;

	@FXML
	private TableColumn<UsersListTableRow, String> tcUPSubscribe;

	@FXML
	private TitledPane tlpAddArtist;

	@FXML
	private TitledPane tlpAddDisc;

	@FXML
	private TitledPane tlpAddSong;

	@FXML
	private TitledPane tlpAddGenre;

	@FXML
	private TextField tAddArtistName;

	@FXML
	private TextField tAddArtistNationality;

	@FXML
	private Button bSaveNewArtist;

	@FXML
	private TextField tAddDiscName;

	@FXML
	private TextField tAddDiscPublishDate;

	@FXML
	private ComboBox<Artist> cbAddArtist;

	@FXML
	private Button bSaveNewDisc;

	@FXML
	private TextField tAddSongName;

	@FXML
	private TextField tAddSongDuration;

	@FXML
	private ComboBox<Disc> cbAddDisc;

	@FXML
	private ComboBox<Genre> cbAddGenre;

	@FXML
	private Button bSaveNewSong;

	@FXML
	private TextField tAddGenreName;

	@FXML
	private Button bSaveNewGenre;

	@FXML
	private Pane pBackgroundPane;

	@FXML
	private Pane pEditPlaylistPane;

	@FXML
	private TitledPane tlpPlaylist;

	@FXML
	private TitledPane tlpAddSongsToPlaylist;

	@FXML
	private TableView<SongTableRow> tvEditPlaylist;

	@FXML
	private TableColumn<SongTableRow, String> tcEPName;

	@FXML
	private TableColumn<SongTableRow, String> tcEPArtist;

	@FXML
	private TableColumn<SongTableRow, String> tcEPDisc;

	@FXML
	private TableColumn<SongTableRow, String> tcEPGenre;

	@FXML
	private TableView<SongTableRow> tvEditPlaylistSongs;

	@FXML
	private TableColumn<SongTableRow, String> tcEPSName;

	@FXML
	private TableColumn<SongTableRow, String> tcEPSArtist;

	@FXML
	private TableColumn<SongTableRow, String> tcEPSDisc;

	@FXML
	private TableColumn<SongTableRow, String> tcEPSGenre;

	@FXML
	private TextField tSearchName2;

	@FXML
	private TextField tSearchArtist2;

	@FXML
	private TextField tSearchDisc2;

	@FXML
	private TextField tSearchGenre2;

	@FXML
	private Button bSaveEditedPlaylist;

	@FXML
	private Button bCancelPlaylistEdit;

	@FXML
	private Button bSubscribe;

	@FXML
	private TabPane tpPlaylists;

	private String newPlaylistName;
	private String listDescription;
	private int userid = 3;
	private boolean editPlaylist;
	private List selectedListForEdit;
	private List currentPlaylist;
	private Song selectedSong;

	private ObservableList<SongTableRow> allSongs;
	private ObservableList<SongTableRow> editPlaylistSongs;
	private ObservableList<List> userCreatedPlaylist;
	private ObservableList<UsersListTableRow> allUsersLists;
	private ObservableList<List> allUserPlaylists;
	private ObservableList<SongTableRow> playlistSongs;

	private ObservableList<Artist> artistsList;
	private ObservableList<Disc> discList;
	private ObservableList<Genre> genreList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DBConection.getDBConection().loadSettings();
		
		this.allSongs = FXCollections.observableArrayList();
		this.editPlaylistSongs = FXCollections.observableArrayList();
		this.userCreatedPlaylist = FXCollections.observableArrayList();
		this.allUsersLists = FXCollections.observableArrayList();
		this.allUserPlaylists = FXCollections.observableArrayList();
		this.playlistSongs = FXCollections.observableArrayList();

		this.artistsList = FXCollections.observableArrayList();
		this.discList = FXCollections.observableArrayList();
		this.genreList = FXCollections.observableArrayList();

		setAllSongsTable();
		setEditPlaylistSongsTable();
		setEditPlaylistTable();
		setUserCreatedPlaylistsTable();
		setUsersPlaylistTable();
		setAllUserPlaylistsTable();
		setPlaylistSongsTable();

		setSongTabPanelOnChange();

		loadUserCreatedPlaylists();
		loadAllUsersPlaylists();
		loadAllUserPlaylists();

		setComboboxes();

		this.editPlaylist = false;
	}

	public void setAllSongsTable() {

		// Listener on click
		this.tvAllSongs.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					if (tvAllSongs.getSelectionModel().getSelectedItem() != null) {
						selectSong(tvAllSongs);
						enablePlayButtons();
					}
				}
			}
		});

		java.util.List<Song> songsList;

		SongDAO sdao = new SongDAO();

		songsList = sdao.getAllSong();

		this.allSongs.setAll(Utils.songsToRow(songsList));

		this.tcAllSongsName.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("song"));
		this.tcAllSongsArtist.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("artist"));
		this.tcAllSongsDisc.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("disc"));
		this.tcAllSongsGenre.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("genre"));
		this.tcAllSongsPlays.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("plays"));

		this.tvAllSongs.setItems(this.allSongs);
		this.tvAllSongs.getSortOrder().add(this.tcAllSongsName);
	}

	public void setPlaylistSongsTable() {
		this.tvPlaylist.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					if (tvPlaylist.getSelectionModel().getSelectedItem() != null) {
						selectSong(tvPlaylist);
						enablePlayButtons();
					}
				}
			}
		});

		this.tcPlaylistName.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("song"));
		this.tcPlaylistArtist.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("artist"));
		this.tcPlaylistDisc.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("disc"));
		this.tcPlaylistGenre.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("genre"));
		this.tcPlaylistPlays.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("plays"));

		this.tvPlaylist.setItems(this.playlistSongs);
		this.tvPlaylist.getSortOrder().add(this.tcAllSongsName);
	}

	public void setEditPlaylistSongsTable() {
		this.tvEditPlaylistSongs.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					addSongToPlaylist(tvEditPlaylistSongs.getSelectionModel().getSelectedItem());
				}
			}
		});

		java.util.List<Song> songsList;

		SongDAO sdao = new SongDAO();

		songsList = sdao.getAllSong();

		this.allSongs.setAll(Utils.songsToRow(songsList));

		this.tcEPSName.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("song"));
		this.tcEPSArtist.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("artist"));
		this.tcEPSDisc.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("disc"));
		this.tcEPSGenre.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("genre"));

		this.tvEditPlaylistSongs.setItems(this.allSongs);
		this.tvEditPlaylistSongs.getSortOrder().add(this.tcEPSName);
	}

	public void setEditPlaylistTable() {

		this.tvEditPlaylist.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					removeSongFromPlaylist(tvEditPlaylist.getSelectionModel().getSelectedIndex());
				}
			}
		});

		this.tcEPName.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("song"));
		this.tcEPArtist.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("artist"));
		this.tcEPDisc.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("disc"));
		this.tcEPGenre.setCellValueFactory(new PropertyValueFactory<SongTableRow, String>("genre"));

		this.tvEditPlaylist.setItems(this.editPlaylistSongs);
		this.tvEditPlaylist.getSortOrder().add(this.tcEPName);
	}

	public void setUsersPlaylistTable() {

		this.tvUsersPlaylists.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					setSubscribeButton();
				}

				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					UsersListTableRow l = getTableListRow(tvUsersPlaylists);

					Songs_ListDAO sldao = new Songs_ListDAO();

					loadPlaylistSongs(sldao.getSongsFromList(l.getListid()));

					ListDAO ldao = new ListDAO();

					setCurrentPlaylist(ldao.getListById(l.getListid()));
					navigateToPlaylist();
				}
			}
		});

		this.tcUPName.setCellValueFactory(new PropertyValueFactory<UsersListTableRow, String>("name"));
		this.tcUPDescription.setCellValueFactory(new PropertyValueFactory<UsersListTableRow, String>("description"));
		this.tcUPSubscribers.setCellValueFactory(new PropertyValueFactory<UsersListTableRow, String>("subscribers"));
		this.tcUPSubscribe.setCellValueFactory(new PropertyValueFactory<UsersListTableRow, String>("subscribedYesNo"));

		this.tvUsersPlaylists.setItems(this.allUsersLists);
		this.tvUsersPlaylists.getSortOrder().add(this.tcUPName);
	}

	public void setUserCreatedPlaylistsTable() {

		this.tvUserCreatedPlaylists.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					setEditAndDeleteButtons();
				}

				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					List l = getTableRow(tvUserCreatedPlaylists);

					Songs_ListDAO sldao = new Songs_ListDAO();

					loadPlaylistSongs(sldao.getSongsFromList(l.getId()));

					setCurrentPlaylist(l);
					navigateToPlaylist();
				}
			}
		});

		this.tcTUCPName.setCellValueFactory(new PropertyValueFactory<List, String>("name"));
		this.tcTUCPDescription.setCellValueFactory(new PropertyValueFactory<List, String>("description"));
		this.tcTUCPSubscribers.setCellValueFactory(new PropertyValueFactory<List, Integer>("n_subscribers"));

		this.tvUserCreatedPlaylists.setItems(this.userCreatedPlaylist);
		this.tvUserCreatedPlaylists.getSortOrder().add(this.tcTUCPName);
	}

	public List getTableRow(TableView<List> table) {
		return table.getSelectionModel().getSelectedItem();
	}

	public UsersListTableRow getTableListRow(TableView<UsersListTableRow> table) {
		return table.getSelectionModel().getSelectedItem();
	}

	public void navigateToPlaylist() {
		this.tpSongs.getSelectionModel().select(tabSongs);
		this.tpUserTabs.getSelectionModel().select(tabPlaylist);
	}

	public void setAllUserPlaylistsTable() {
		this.tvAllUserPlaylists.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					List l = getTableRow(tvAllUserPlaylists);

					Songs_ListDAO sldao = new Songs_ListDAO();

					loadPlaylistSongs(sldao.getSongsFromList(l.getId()));

					setCurrentPlaylist(l);
					navigateToPlaylist();
				}
			}
		});

		this.tcTAUPName.setCellValueFactory(new PropertyValueFactory<List, String>("name"));
		this.tcTAUPDescription.setCellValueFactory(new PropertyValueFactory<List, String>("description"));
		this.tcTAUPSubscribers.setCellValueFactory(new PropertyValueFactory<List, Integer>("n_subscribers"));

		this.tvAllUserPlaylists.setItems(this.allUserPlaylists);
		this.tvAllUserPlaylists.getSortOrder().add(this.tcTAUPName);
	}

	public void loadUserCreatedPlaylists() {
		ListDAO ldao = new ListDAO();

		this.userCreatedPlaylist.addAll(ldao.getListsByUser(this.userid));
	}

	public void loadAllUsersPlaylists() {
		ListDAO ldao = new ListDAO();

		java.util.List<List> allLists = ldao.getAllLists();
		java.util.List<List> otherUsersLists = new ArrayList<List>();

		for (List list : allLists) {
			if (list.getUserid() != this.userid) {
				otherUsersLists.add(list);
			}
		}

		this.allUsersLists.setAll(Utils.usersListsToRows(otherUsersLists, this.userid));
	}

	public void loadAllUserPlaylists() {
		ListDAO ldao = new ListDAO();
		UserDAO udao = new UserDAO();

		java.util.List<Subscription> uSubscriptions = udao.getUserById(this.userid).getUserListsSubscriptions();
		java.util.List<List> uSubsLists = new ArrayList<List>();

		for (Subscription subscription : uSubscriptions) {
			List l = ldao.getListById(subscription.getListid());
			uSubsLists.add(l);
		}

		this.allUserPlaylists.setAll(ldao.getListsByUser(this.userid));
		this.allUserPlaylists.addAll(uSubsLists);
	}

	public void loadPlaylistSongs(java.util.List<Song> songsList) {
		this.playlistSongs.setAll(Utils.songsToRow(songsList));
	}

	public void loadAllSongs(java.util.List<Song> songsList) {
		this.allSongs.setAll(Utils.songsToRow(songsList));
	}

	public void setSongTabPanelOnChange() {
		tpSongs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				setEditPlaylistSongsTable();
			}
		});

		tpPlaylists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				setUsersPlaylistTable();
			}
		});
	}

	public void addSongToPlaylist(SongTableRow row) {
		if (!this.editPlaylistSongs.contains(row)) {
			this.editPlaylistSongs.add(row);

			if (this.editPlaylistSongs.isEmpty()) {
				this.bSaveEditedPlaylist.setDisable(true);
			} else {
				this.bSaveEditedPlaylist.setDisable(false);
			}
		}

	}

	public void removeSongFromPlaylist(int row) {
		this.editPlaylistSongs.remove(row);

		if (this.editPlaylistSongs.isEmpty()) {
			this.bSaveEditedPlaylist.setDisable(true);
		} else {
			this.bSaveEditedPlaylist.setDisable(false);
		}
	}

	@FXML
	public void createPlayList(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
			Parent root = loader.load();
			SecondaryController controller = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();

			if (controller.getName() != null && controller.getDescription() != null) {
				this.newPlaylistName = controller.getName();
				this.listDescription = controller.getDescription();

				this.pBackgroundPane.setVisible(true);
				this.pEditPlaylistPane.setVisible(true);
			}

			controller.setFieldsToNull();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void savePlaylist(ActionEvent event) {
		if (this.editPlaylist == false) {
			List list = new List(this.userid, this.newPlaylistName, this.listDescription, 0);

			Songs_ListDAO sldao = new Songs_ListDAO();
			ListDAO ldao = new ListDAO(list);
			ldao.addList();

			list.setId(ldao.getId());

			for (SongTableRow row : this.editPlaylistSongs) {
				sldao.addSongToList(row.getSongid(), ldao.getId());
			}

			this.userCreatedPlaylist.add(list);

		} else {
			Songs_ListDAO sldao = new Songs_ListDAO();
			ListDAO ldao = new ListDAO(this.selectedListForEdit);
			ldao.updateList();

			sldao.deleteSongsOfList(this.selectedListForEdit.getId());

			for (SongTableRow row : this.editPlaylistSongs) {
				sldao.addSongToList(row.getSongid(), this.selectedListForEdit.getId());
			}

			this.editPlaylist = false;
		}

		this.tSearchName2.setText("");
		this.tSearchArtist2.setText("");
		this.tSearchDisc2.setText("");
		this.tSearchGenre2.setText("");

		searchSong2(null);

		this.editPlaylistSongs.clear();

		this.pEditPlaylistPane.setVisible(false);
		this.pBackgroundPane.setVisible(false);

		this.allUsersLists.clear();
		this.allUserPlaylists.clear();
		loadAllUsersPlaylists();
		loadAllUserPlaylists();
	}

	@FXML
	public void cancelPlaylistEdit(ActionEvent event) {
		this.tSearchName2.setText("");
		this.tSearchArtist2.setText("");
		this.tSearchDisc2.setText("");
		this.tSearchGenre2.setText("");

		searchSong2(null);

		this.editPlaylistSongs.clear();
		this.pEditPlaylistPane.setVisible(false);
		this.pBackgroundPane.setVisible(false);

		this.bSaveEditedPlaylist.setDisable(true);
	}

	@FXML
	public void editPlaylist(ActionEvent event) {
		this.pBackgroundPane.setVisible(true);
		this.pEditPlaylistPane.setVisible(true);

		java.util.List<Song> songsList;

		selectedListForEdit = this.tvUserCreatedPlaylists.getSelectionModel().getSelectedItem();

		Songs_ListDAO sldao = new Songs_ListDAO();

		songsList = sldao.getSongsFromList(selectedListForEdit.getId());

		this.editPlaylistSongs.clear();
		this.editPlaylistSongs.addAll(Utils.songsToRow(songsList));

		this.tvEditPlaylist.setItems(this.editPlaylistSongs);

		this.editPlaylist = true;
	}

	public void setEditAndDeleteButtons() {
		if ((this.tvUserCreatedPlaylists.getSelectionModel().getSelectedItem() != null)
				&& (!this.userCreatedPlaylist.isEmpty())) {
			this.bEditPlaylist.setDisable(false);
			this.bDeletePlaylist.setDisable(false);
		} else {
			this.bEditPlaylist.setDisable(true);
			this.bDeletePlaylist.setDisable(true);
		}
	}

	public void setSubscribeButton() {
		if (this.tvUsersPlaylists.getSelectionModel().getSelectedItem() != null) {
			this.bSubscribe.setDisable(false);

			if (this.tvUsersPlaylists.getSelectionModel().getSelectedItem().isSubscribed()) {
				this.bSubscribe.setText("Darse de baja");
			} else {
				this.bSubscribe.setText("Subscribirse");
			}
		} else {
			this.bSubscribe.setDisable(true);
		}
	}

	@FXML
	public void deletePlaylist() {
		List listToDelete;

		listToDelete = this.tvUserCreatedPlaylists.getSelectionModel().getSelectedItem();

		ListDAO ldao = new ListDAO(listToDelete);

		this.userCreatedPlaylist.remove(listToDelete);

		ldao.deleteList();

		this.allUsersLists.clear();
		this.allUserPlaylists.clear();
		loadAllUsersPlaylists();
		loadAllUserPlaylists();
	}

	@FXML
	public void subscribeToList(ActionEvent event) {
		ListDAO ldao = new ListDAO();
		List l = ldao.getListById(this.tvUsersPlaylists.getSelectionModel().getSelectedItem().getListid());

		if (!this.tvUsersPlaylists.getSelectionModel().getSelectedItem().isSubscribed()) {
			SubscriptionDAO sdao = new SubscriptionDAO();

			sdao.addSubscription(this.userid, l.getId());
			l.setN_subscribers(l.getN_subscribers() + 1);

			ldao = new ListDAO(l);
			ldao.updateList();

			loadAllUserPlaylists();
			loadAllUsersPlaylists();

			setSubscribeButton();
		} else {
			SubscriptionDAO sdao = new SubscriptionDAO();

			sdao.deleteSubscription(this.userid, l.getId());
			l.setN_subscribers(l.getN_subscribers() - 1);

			ldao = new ListDAO(l);
			ldao.updateList();

			loadAllUserPlaylists();
			loadAllUsersPlaylists();

			setSubscribeButton();
		}
	}

	public void selectSong(TableView<SongTableRow> table) {
		SongTableRow song = table.getSelectionModel().getSelectedItem();

		if (song != null) {
			SongDAO sdao = new SongDAO();

			this.selectedSong = sdao.getSongById(song.getSongid());

			this.rlName.setText(song.getSong());
			this.rlArtist.setText(song.getArtist());
			this.rlDisc.setText(song.getDisc());
		}
	}

	@FXML
	public void playSong(ActionEvent event) {
		if (this.selectedSong != null) {
			SongDAO sdao = new SongDAO();

			this.selectedSong.setN_reproductions(this.selectedSong.getN_reproductions() + 1);
			sdao = new SongDAO(this.selectedSong);
			sdao.updateSong();
			Songs_ListDAO sldao = new Songs_ListDAO();
			loadAllSongs(sdao.getAllSong());
			if (this.currentPlaylist != null) {
				loadPlaylistSongs(sldao.getSongsFromList(this.currentPlaylist.getId()));
			}

			searchSong(null);
		}
	}

	public void setCurrentPlaylist(List l) {
		this.currentPlaylist = l;
	}

	@FXML
	public void addArtist(ActionEvent event) {
		String name;
		String nationality;

		name = this.tAddArtistName.getText();
		nationality = this.tAddArtistNationality.getText();

		if (!name.isEmpty() && !nationality.isEmpty()) {
			ArtistDAO adao = new ArtistDAO(new Artist(name, nationality, ""));
			adao.addArtist();
		}

		this.tAddArtistName.setText("");
		this.tAddArtistNationality.setText("");
		
		setComboboxes();

		Utils.popInfo("Artista insertado con exito");
	}

	@FXML
	public void addDisc(ActionEvent event) {
		if (this.tAddDiscPublishDate.getText().matches("\\d+")) {
			String name;
			String publish_date;
			Artist a = new Artist();

			name = this.tAddDiscName.getText();
			publish_date = this.tAddDiscPublishDate.getText();

			a = this.cbAddArtist.getValue();

			DiscDAO ddao = new DiscDAO(new Disc(a.getId(), name, Integer.parseInt(publish_date), "", 0));
			ddao.addDisc();

			this.tAddDiscName.setText("");
			this.tAddDiscPublishDate.setText("");
			this.cbAddArtist.setValue(null);
			
			setComboboxes();

			Utils.popInfo("Disco insertado con exito");
		} else {
			Utils.popError("Por favor, introduzca un año valido");
			this.tAddDiscPublishDate.setText("");
			this.bSaveNewDisc.setDisable(true);
		}

	}

	@FXML
	public void addSong(ActionEvent event) {
		if (this.tAddSongDuration.getText().matches("\\d+")) {
			String name;
			String duration;
			Disc d = new Disc();
			Genre g = new Genre();

			name = tAddSongName.getText();
			duration = tAddSongDuration.getText();
			d = cbAddDisc.getValue();
			g = cbAddGenre.getValue();

			SongDAO sdao = new SongDAO(new Song(d.getId(), g.getId(), name, Integer.parseInt(duration), 0));

			sdao.addSong();

			this.tAddSongName.setText("");
			this.tAddSongDuration.setText("");
			this.cbAddDisc.setValue(null);
			this.cbAddGenre.setValue(null);

			loadAllSongs(sdao.getAllSong());
			setComboboxes();

			Utils.popInfo("Cancion insertada con éxito");
		} else {
			Utils.popError("Por favor, introduzca una duracion valida\nEj: 136");
			this.tAddSongDuration.setText("");
			this.bSaveNewSong.setDisable(true);
		}
	}

	@FXML
	public void enableAdminSaveButtons(KeyEvent event) {
		checkAdminFields();
	}

	@FXML
	public void addGenre(ActionEvent event) {
		String name;

		name = tAddGenreName.getText();

		GenreDAO gdao = new GenreDAO(new Genre(name));

		gdao.addGenre();

		this.tAddGenreName.setText("");
		
		setComboboxes();

		Utils.popInfo("Genero insertado con exito");
	}

	public void checkAdminFields() {
		if ((this.tAddArtistName.getText().isEmpty()) || (this.tAddArtistNationality.getText().isEmpty())) {
			this.bSaveNewArtist.setDisable(true);
		} else {
			this.bSaveNewArtist.setDisable(false);
		}

		if ((this.tAddDiscName.getText().isEmpty()) || (this.tAddDiscPublishDate.getText().isEmpty())
				|| (checkAddArtistComboBox())) {
			this.bSaveNewDisc.setDisable(true);
		} else {
			this.bSaveNewDisc.setDisable(false);
		}

		if ((this.tAddSongName.getText().isEmpty()) || (this.tAddSongDuration.getText().isEmpty())
				|| (checkAddDiscComboBox()) || (checkAddGenreComboBox())) {
			this.bSaveNewSong.setDisable(true);
		} else {
			this.bSaveNewSong.setDisable(false);
		}

		if (this.tAddGenreName.getText().isEmpty()) {
			this.bSaveNewGenre.setDisable(true);
		} else {
			this.bSaveNewGenre.setDisable(false);
		}
	}

	public boolean checkAddArtistComboBox() {
		if (this.cbAddArtist.getValue() != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkAddDiscComboBox() {
		if (this.cbAddDisc.getValue() != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkAddGenreComboBox() {
		if (this.cbAddGenre.getValue() != null) {
			return false;
		} else {
			return true;
		}
	}

	public void setComboboxes() {
		ArtistDAO adao = new ArtistDAO();
		DiscDAO ddao = new DiscDAO();
		GenreDAO gdao = new GenreDAO();

		this.cbAddArtist.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			checkAdminFields();
		});

		this.cbAddDisc.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			checkAdminFields();
		});

		this.cbAddGenre.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			checkAdminFields();
		});

		this.artistsList.setAll(adao.getAllArtists());
		this.discList.setAll(ddao.getAllDiscs());
		this.genreList.setAll(gdao.getAllGenres());

		this.cbAddArtist.setItems(this.artistsList);
		this.cbAddDisc.setItems(this.discList);
		this.cbAddGenre.setItems(this.genreList);
	}

	@FXML
	public void searchSong(KeyEvent event) {
		ObservableList<SongTableRow> sortedSongs = FXCollections.observableArrayList(this.allSongs);

		String name;
		String artist;
		String disc;
		String genre;

		name = this.tSearchName.getText();
		artist = this.tSearchArtist.getText();
		disc = this.tSearchDisc.getText();
		genre = this.tSearchGenre.getText();

		Iterator<SongTableRow> i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!name.isEmpty()) {
				if (!row.getSong().contains(name)) {
					i.remove();
				}
			}
		}

		i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!artist.isEmpty()) {
				if (!row.getArtist().contains(artist)) {
					i.remove();
				}
			}
		}

		i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!disc.isEmpty()) {
				if (!row.getDisc().contains(disc)) {
					i.remove();
				}
			}
		}

		i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!genre.isEmpty()) {
				if (!row.getGenre().contains(genre)) {
					i.remove();
				}
			}
		}

		this.tvAllSongs.setItems(sortedSongs);
		this.tvAllSongs.refresh();

		if (name.isEmpty() && artist.isEmpty() && disc.isEmpty() && genre.isEmpty()) {
			this.tvAllSongs.setItems(this.allSongs);
		}
	}

	@FXML
	public void searchSong2(KeyEvent event) {
		ObservableList<SongTableRow> sortedSongs = FXCollections.observableArrayList(this.allSongs);

		String name;
		String artist;
		String disc;
		String genre;

		name = this.tSearchName2.getText();
		artist = this.tSearchArtist2.getText();
		disc = this.tSearchDisc2.getText();
		genre = this.tSearchGenre2.getText();

		Iterator<SongTableRow> i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!name.isEmpty()) {
				if (!row.getSong().contains(name)) {
					i.remove();
				}
			}
		}

		i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!artist.isEmpty()) {
				if (!row.getArtist().contains(artist)) {
					i.remove();
				}
			}
		}

		i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!disc.isEmpty()) {
				if (!row.getDisc().contains(disc)) {
					i.remove();
				}
			}
		}

		i = sortedSongs.iterator();

		while (i.hasNext()) {
			SongTableRow row = i.next();
			if (!genre.isEmpty()) {
				if (!row.getGenre().contains(genre)) {
					i.remove();
				}
			}
		}

		this.tvEditPlaylistSongs.setItems(sortedSongs);
		this.tvEditPlaylistSongs.refresh();

		if (name.isEmpty() && artist.isEmpty() && disc.isEmpty() && genre.isEmpty()) {
			this.tvEditPlaylistSongs.setItems(this.allSongs);
		}
	}

	@FXML
	public void nextSong() {
		if (this.tpUserTabs.getSelectionModel().getSelectedItem().getText().equals("Todas las Canciones")) {
			if (this.tvAllSongs.getSelectionModel().getSelectedItem() != null) {
				this.tvAllSongs.getSelectionModel().selectNext();
				selectSong(this.tvAllSongs);
			}
		} else if (this.tpUserTabs.getSelectionModel().getSelectedItem().getText().equals("Lista de Reproduccion")) {
			if (this.tvPlaylist.getSelectionModel().getSelectedItem() != null) {
				this.tvPlaylist.getSelectionModel().selectNext();
				selectSong(this.tvPlaylist);
			}
		}
	}

	@FXML
	public void previousSong() {
		if (this.tpUserTabs.getSelectionModel().getSelectedItem().getText().equals("Todas las Canciones")) {
			if (this.tvAllSongs.getSelectionModel().getSelectedItem() != null) {
				this.tvAllSongs.getSelectionModel().selectPrevious();
				selectSong(this.tvAllSongs);
			}
		} else if (this.tpUserTabs.getSelectionModel().getSelectedItem().getText().equals("Lista de Reproduccion")) {
			if (this.tvPlaylist.getSelectionModel().getSelectedItem() != null) {
				this.tvPlaylist.getSelectionModel().selectPrevious();
				selectSong(this.tvPlaylist);
			}
		}
	}

	public void enablePlayButtons() {
		this.bPlay.setDisable(false);
		this.bPrevSong.setDisable(false);
		this.bNextSong.setDisable(false);
	}
}
