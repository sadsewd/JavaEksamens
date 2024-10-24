package Lietotnes;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JOptionPane;

class Spele extends Lietotne {
	// Atribūti
	private String engine, genre;
	private boolean multiplayer;

	// Konstruktors
	public Spele(int id,double appSize, String name, String[] supportedOS, String[] supportedLang, String logoDir,
			boolean crossPlatform, String engine, String genre, boolean multiplayer) {
		super(id,appSize, name, supportedOS, supportedLang, logoDir, crossPlatform);
		this.engine = engine;
		this.genre = genre;
		this.multiplayer = multiplayer;
	}

	// Getter metodes
	public String getEngine() {
		return engine;
	}

	public String getGenre() {
		return genre;
	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	// Setter metodes
	public void setEngine(String engine) {
		this.engine = engine;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}
	
	//Metodes
	
	public void spelesInfo() {
	    String gameInfo = "Game ID: "+getId()+"\nName: "+getName()+"\nSize: "+getAppSize()+
	    		" MB\nEngine: "+getEngine()+"\nGenre: "+getGenre()+"\nMultiplayer: "+isMultiplayer()+
	    		"\nCross-platform: "+isCrossPlatform()+"\nSupported OS: "+String.join(", ", getSupportedOS())+
	    		"\nSupported Languages: "+String.join(", ", getSupportedLang())+"\nLogo Directory: "+getLogoDir();
	    JOptionPane.showMessageDialog(null, gameInfo, "Spēles informācija", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void ieviestMultiplayer() {
	    if (!multiplayer) {
	        multiplayer = true;
	        JOptionPane.showMessageDialog(null, "Multiplayer funcionalitātes status ir nomainīts","Paziņojums",JOptionPane.INFORMATION_MESSAGE);
	    }else 
	        JOptionPane.showMessageDialog(null, "Multiplayer funcionalitātes status jau ir norādīts kā pastāvošs","Paziņojums",JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Polimorfisms
	public void aptuvenaisLejupieldesLaiks() {
		double speedMbps = Double.parseDouble(JOptionPane.showInputDialog("Ievadiet savu lejupielādes atrumu (Mbps)"));
	    JOptionPane.showMessageDialog(null, "Spēle "+ getName() +" tiks ielādēta " + (getAppSize() / speedMbps) + " sekundēs.");
	}
	

}