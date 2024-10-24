package Lietotnes;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JOptionPane;

class SazinasLietotne extends Lietotne {
	// Atributi
	private boolean videoChat, groupChat; // Funckiju atbalsts
	private int maxUserCap; // Maksimalais lietotāju skaits grupā
	private double maxFileSize; // 0 = Nav iespējams sūtīt failus

	// Konstruktors
	public SazinasLietotne(int id,double appSize, String name, String[] supportedOS, String[] supportedLang, String logoDir,
			boolean crossPlatform, boolean videoChat, boolean groupChat, int maxUserCap, double maxFileSize) {
		super(id,appSize, name, supportedOS, supportedLang, logoDir, crossPlatform);
		this.videoChat = videoChat;
		this.groupChat = groupChat;
		this.maxUserCap = maxUserCap;
		this.maxFileSize = maxFileSize;
	}

	// Getter metodes
	public boolean isVideoChat() {
		return videoChat;
	}

	public boolean isGroupChat() {
		return groupChat;
	}

	public int getMaxUserCap() {
		return maxUserCap;
	}

	public double getMaxFileSize() {
		return maxFileSize;
	}

	// Setter metodes
	public void setVideoChat(boolean videoChat) {
		this.videoChat = videoChat;
	}

	public void setGroupChat(boolean groupChat) {
		this.groupChat = groupChat;
	}

	public void setMaxUserCap(int maxUserCap) {
		this.maxUserCap = maxUserCap;
	}

	public void setMaxFileSize(double maxFileSize) {
		this.maxFileSize = maxFileSize;
	}
	
	//Metodes
	
	public void jaunsMaksLietSk() {
		int newCap = -1;
		
		try {
			 newCap = Integer.parseInt(JOptionPane.showInputDialog("Ievadiet jauno maksimālo lietotaju skaitu"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lūdzu ievadiet derīgu skaitli","Kļūda",JOptionPane.ERROR_MESSAGE);
		}
		
	    if (newCap > 0) {
	        this.maxUserCap = newCap;
	        JOptionPane.showMessageDialog(null, "Maksimālais lietotāju skaits ir nomainīts","",JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Lietotāju skaitam ir jabūt vismaz 0","",JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void varSutitFailus() {
	    if (getMaxFileSize() > 0) 
			JOptionPane.showMessageDialog(null, "Lietotnē ir iespējams sūtīt failus, maksimālais failu lielums ir " + getMaxFileSize() + " MB");
		else
			JOptionPane.showMessageDialog(null, "Lietotnē nav iespējams sūtīt failus");
	}

}