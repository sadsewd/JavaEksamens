package Lietotnes;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

class Lietotne {
	// Atribūti
	private int id;
	private double appSize;
	private String name, logoDir;
	private String[] supportedOS, supportedLang;
	private boolean crossPlatform;

	// Konstruktors
	public Lietotne(int id,double appSize, String name, String[] supportedOS, String[] supportedLang, String logoDir,
			boolean crossPlatform) {
		this.id = id;
		this.appSize = appSize;
		this.name = name;
		this.supportedOS = supportedOS;
		this.supportedLang = supportedLang;
		this.logoDir = logoDir;
		this.crossPlatform = crossPlatform;
	}

	// Getter metodes
	public int getId() {
		return id;
	}
	
	public double getAppSize() {
		return appSize;
	}

	public String getName() {
		return name;
	}

	public String getLogoDir() {
		return logoDir;
	}

	public String[] getSupportedLang() {
		return supportedLang;
	}

	public String[] getSupportedOS() {
		return supportedOS;
	}

	public boolean isCrossPlatform() {
		return crossPlatform;
	}

	// Setter metodes
	public void setId(int id) {
		this.id = id;
	}
	
	public void setAppSize(double appSize) {
		this.appSize = appSize;
	}

	public void setLogo(String logoDir) {
		this.logoDir = logoDir;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSupportedLang(String[] supportedLang) {
		this.supportedLang = supportedLang;
	}

	public void setSupportedOS(String[] supportedOS) {
		this.supportedOS = supportedOS;
	}

	public void setCrossPlatform(boolean crossPlatform) {
		this.crossPlatform = crossPlatform;
	}
	
	//Metodes
	
	public void raditLogo() {
	    ImageIcon appLogo = new ImageIcon(logoDir);
	    //Pārbauda vai ir ielādēts attēls
	    if (appLogo.getIconWidth() > 0) {
	        Image scaledLogo = appLogo.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(scaledLogo);
	        JOptionPane.showMessageDialog(null, "", getName() + " Logo", JOptionPane.INFORMATION_MESSAGE, scaledIcon);
	    } else {
	        JOptionPane.showMessageDialog(null, "Nevarēja ielādēt attēlu no: " + logoDir, "Kļūda", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void aptuvenaisLejupieldesLaiks() {
		double speedMbps = Double.parseDouble(JOptionPane.showInputDialog("Ievadiet savu lejupielādes atrumu (Mbps)"));
	    JOptionPane.showMessageDialog(null, "Lietotne tiks ielādēta " + (appSize / speedMbps) + " sekundēs.");
	}
	
	public void irSaderigsArOS() {
		boolean atrasts = false;
		String inputOS = JOptionPane.showInputDialog("Ievadiet OS");
	    for (String OS : supportedOS) {
	        if (OS.equalsIgnoreCase(inputOS)) {
	        	atrasts = true;
	        	inputOS = OS;
	        	break;
	        }
	    }
	    if(atrasts) 
	    	JOptionPane.showMessageDialog(null, ("Lietotne ir saderīga ar " + inputOS + " operētājsistēmu") , "Saderīgums", JOptionPane.INFORMATION_MESSAGE);
	    else
	    	JOptionPane.showMessageDialog(null, "Lietotne nav saderīga ar jūsu operētājsistēmu", "Saderīgums", JOptionPane.INFORMATION_MESSAGE);
	}

}