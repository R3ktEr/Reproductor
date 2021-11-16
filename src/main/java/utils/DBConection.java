package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class DBConection {
	@XmlTransient
	private static Connection con;
	@XmlTransient
	private static DBConection _DBConection;

	@XmlElement(name = "server")
	private String server;
	@XmlElement(name = "database")
	private String database;
	@XmlElement(name = "user")
	private String user;
	@XmlElement(name = "password")
	private String password;

	private DBConection() {
		this.server = "";
		this.database = "";
		this.user = "";
		this.password = "";
	}
	
	public static DBConection getDBConection() {
		if(_DBConection==null) {
			_DBConection=new DBConection();
		}
		
		return _DBConection;
	}

	private void setSettings(String server, String database, String user, String password) {
		this.server = server;
		this.database = database;
		this.user = user;
		this.password = password;
	}

	public String getServer() {
		return server;
	}

	public String getDatabase() {
		return database;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
	public static Connection getConection() {
		if(con==null) {
			if(conect()) {
				return con;
			}else {
				return null;
			}
		}
		
		return con;
	}

	public static boolean conect() {
		try {
			con = DriverManager.getConnection(
					DBConection.getDBConection().getServer() + "/" + DBConection.getDBConection().getDatabase(),
					DBConection.getDBConection().getUser(), DBConection.getDBConection().getPassword());
			return true;
		} catch (SQLException e) {
			Utils.popError(
					"Error: El servidor no responde\n\nDebido a la severidad de este error, la aplicacion se cerrará");
			return false;
		}
	}
	
	public boolean saveSettings()
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(DBConection.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(DBConection.getDBConection(), new File("settings.xml"));
			result=true;
		} catch (JAXBException e) {
			System.out.println("Error al guardar los ajustes de conexion de la base de datos");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean loadSettings()
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(DBConection.class);
			Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
			DBConection conect=((DBConection) unmarshaller.unmarshal(new File("settings.xml")));
			setSettings(conect.getServer(), conect.getDatabase(), conect.getUser(), conect.getPassword());
			result=true;
		} catch (JAXBException e) {
			System.out.println("Error al cargar los ajustes de conexion de la base de datos");
			e.printStackTrace();
		}
		
		return result;
	}
}
