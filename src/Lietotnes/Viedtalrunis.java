package Lietotnes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

class Viedtalrunis implements ItemListener {

    static JFrame frame;
    static JButton createB, updateB, delB, callMethodsB;
    static JPanel tablePanel, actionPanel, panelContainer;
    static JComboBox<String> dropdown;
    static JTable table;
    static JScrollPane sp;

    private static String[] initGamesCol = {"Engine", "Genre", "Multiplayer" },
            initSocialCol = { "Video chat", "Group chat", "Max user capacity", "Max file size" },
            initAppCol = {"ID" ,"App size (MB)", "Name", "Supported OS", "Supported languages", "Logo directory", "Cross Platform"},
            cols = { "" };
    private static String[][] data = { { "" } };
    private static ArrayList<Spele> speles = new ArrayList<Spele>();
    private static ArrayList<SazinasLietotne> sazinasL = new ArrayList<SazinasLietotne>();

    public static void main(String[] args) {
    	String[] tipi = { "Spēles", "Sazinas Lietotnes" };
        String[][] initOS = { { "Windows", "Linux", "MacOS" }, { "Windows", "Linux" },
                { "Windows", "Linux", "MacOS" } };
        String[][] initLang = { { "English", "Polish", "Sweedish" },
                { "English", "Czech", "Danish", "Finnish", "Dutch" },
                { "English", "French", "German", "Italian", "Spanish" } };

        speles.add(new Spele(0,869.6, "Crab game", initOS[0], initLang[0], "./Atteli/crabgame.png", true, "Unity",
                "Action", true));
        speles.add(new Spele(1,42327, "Counter Strike 2", initOS[1], initLang[1], "./Atteli/cs2.png", true,
                "Source 2", "FPS", true));
        speles.add(new Spele(2,3259, "Amnesia: The Dark Descent", initOS[2], initLang[2], "./Atteli/Amnesia.png", false,
                "HPL", "Horror", false));
        
        sazinasL.add(new SazinasLietotne(0,136, "Whatsapp", initOS[0], initLang[0], "./Atteli/whatsapp.png", true,true, true, 1024, 16));
        sazinasL.add(new SazinasLietotne(1,36.23, "Telegram", initOS[1], initLang[1], "./Atteli/telegram.png", true,true, true, 200000, 2000));
        sazinasL.add(new SazinasLietotne(2,57.79, "Messenger", initOS[2], initLang[2], "./Atteli/messenger.png", true,true, true, 250, 25));

        frame = new JFrame("Eksamens_Kārlis_Lācītis");
        frame.setSize(800, 640);

        createB = new JButton("Izveidot");
        createB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dataActions("create");
            }
        });

        updateB = new JButton("Mainīt");
        
        updateB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataActions("update");
            }
        });
        
        delB = new JButton("Dzēst");
        delB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	    		int selectedRow = table.getSelectedRow();
	        	if (selectedRow >= 0) {
				String tips = (dropdown.getSelectedItem() == "Sazinas Lietotnes" ? "saz" : "sp");
				for(int i = 0; i < (tips == "saz" ? sazinasL : speles).size(); i++) {
					int id1 = (tips == "saz" ? sazinasL : speles).get(i).getId(),
						id2 =  Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
					if(id1 == id2) {
						(tips == "saz" ? sazinasL : speles).remove(i);
						setTableData(tips);
						break;
					}
				}
				}else if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nav izvēleta rinda kuru dzēst!", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
        
        callMethodsB = new JButton("Izsaukt metodes");
        callMethodsB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	    		int selectedRow = table.getSelectedRow();
	        	if (selectedRow >= 0) {
	        		callMethods();
				}else if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nav izvēlets ieraksts kuram izsaukt metodes!", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
        
        tablePanel = new JPanel();
        actionPanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.add(Box.createVerticalGlue());
        panelContainer = new JPanel();
        dropdown = new JComboBox<String>(tipi);
        Viedtalrunis listener = new Viedtalrunis();
        dropdown.addItemListener(listener);
        
        table = new JTable();
        setTableData("sp");// Iestata tabulas sākuma datus
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Atļauj izvēlēties tikai vienu rindu
        sp = new JScrollPane(table);
        sp.setMinimumSize(new Dimension(1000,480));
        sp.setPreferredSize(new Dimension(1000,480));
        sp.setMaximumSize(new Dimension(1000,480));

        tablePanel.add(sp);
        tablePanel.add(Box.createVerticalGlue());
        actionPanel.add(createB);
        actionPanel.add(updateB);
        actionPanel.add(delB);
        actionPanel.add(callMethodsB);
        actionPanel.add(dropdown);
        panelContainer.add(actionPanel);
        panelContainer.add(tablePanel);
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        
        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Aizverot applikācijas logu tiek apstādināta programma
        frame.setVisible(true);
    }
    
    //Izpildās kad ir veiktas izmaiņas elementiem kuri ir pievienoti pie itemlistener
    public void itemStateChanged(ItemEvent e) {
    	if (e.getSource() == dropdown) {
            if (dropdown.getSelectedItem() == "Spēles") {
            	setTableData("sp");
            } else if (dropdown.getSelectedItem() == "Sazinas Lietotnes") {
            	setTableData("saz");
            } else {
                return;
            }
        }
    }
    
    //Funckija aizpilda tabulu ar izvēlētas lietotnes klases datiem
    public static void setTableData(String tips) {
    	data = new String[(tips == "saz" ? sazinasL : speles ).size()][(initAppCol.length + (tips == "saz" ? initSocialCol : initGamesCol).length)];
    	cols = new String[(initAppCol.length + (tips == "saz" ? initSocialCol : initGamesCol).length)];

    	for (int i = 0; i < initAppCol.length; i++) {
            cols[i] = initAppCol[i];
        }
        for (int i = 0; i < (tips == "saz" ? initSocialCol : initGamesCol ).length; i++) {
            cols[initAppCol.length + i] = (tips == "saz" ? initSocialCol : initGamesCol)[i];
        }
        
    	for (int i = 0; i < (tips == "saz" ? sazinasL : speles).size(); i++) {
    		data[i][0] = Integer.toString((tips == "saz" ? sazinasL : speles).get(i).getId());
    		data[i][1] = String.valueOf((tips == "saz" ? sazinasL : speles).get(i).getAppSize());
    		data[i][2] = (tips == "saz" ? sazinasL : speles).get(i).getName();
    		data[i][3] = "";
            for (int j = 0; j < (tips == "saz" ? sazinasL : speles).get(i).getSupportedOS().length; j++) {
                if (j != (tips == "saz" ? sazinasL : speles).get(i).getSupportedOS().length-1) {
                	data[i][3] += (tips == "saz" ? sazinasL : speles).get(i).getSupportedOS()[j] + ", ";
                } else {
                	data[i][3] += (tips == "saz" ? sazinasL : speles).get(i).getSupportedOS()[j];
                }
            };
            data[i][4] = "";
            for (int j = 0; j < (tips == "saz" ? sazinasL : speles).get(i).getSupportedLang().length; j++) {
                if (j != (tips == "saz" ? sazinasL : speles).get(i).getSupportedLang().length-1) {
                	data[i][4] += (tips == "saz" ? sazinasL : speles).get(i).getSupportedLang()[j] + ", ";
                } else {
                	data[i][4] += (tips == "saz" ? sazinasL : speles).get(i).getSupportedLang()[j];
                }
            };
            data[i][5] = (tips == "saz" ? sazinasL : speles).get(i).getLogoDir();
            data[i][6] = boolToString((tips == "saz" ? sazinasL : speles).get(i).isCrossPlatform());
        }
    	
        if(tips == "saz") {
	    	for (int i = 0; i < sazinasL.size(); i++) {
	            data[i][7] = boolToString(sazinasL.get(i).isVideoChat());
	            data[i][8] = boolToString(sazinasL.get(i).isGroupChat());
	            data[i][9] = Integer.toString(sazinasL.get(i).getMaxUserCap());
	            data[i][10] = String.valueOf(sazinasL.get(i).getMaxFileSize());
	        }
        }else if(tips == "sp") {
        	for (int i = 0; i < speles.size(); i++) {
                data[i][7] = speles.get(i).getEngine();
                data[i][8] = speles.get(i).getGenre();
                data[i][9] = boolToString(speles.get(i).isMultiplayer());
            }
        }
        table.setModel(new javax.swing.table.DefaultTableModel(data, cols));
    }
    
    //Atgriež neizmantotu id jaunam ierakstam
    public static int getNewID(String tips) {
    	int lielakais = 0;
    	for(int i = 0; i < (tips == "Spēle" ? speles : sazinasL).size(); i++) {
    		if((tips == "Spēle" ? speles : sazinasL).get(i).getId() > lielakais) {
    			lielakais = (tips == "Spēle" ? speles : sazinasL).get(i).getId();
    		}
    	}
    	if(lielakais > 0) {
    		return lielakais+1;
    	} else {
    		return lielakais;
    	}
    }
    
    //Funckijas parametrs satur kāda darbība tiks veikta (create, update), 
    //funkciju izsaucot tiek izveidots jauns logs kurš satur ievades laukus un iesniegšanas pogu, 
    //ja parametra vērtība ir norādīta kā update tad ievades lauki tiek automātiski aizpidldīti ar izvēlētajiem datiem
    public static void dataActions(String actionType) {
		String tips = dropdown.getSelectedItem().toString();
        int selectedRow = table.getSelectedRow();
        if(actionType == "update" && selectedRow != -1 || actionType == "create") { //bad
        	JFrame actionsFrame = new JFrame((actionType == "create" ? "Jauns ieraksts" : "Mainīt ierakstu"));
            actionsFrame.setSize(400, 400);
            actionsFrame.setLayout(new BoxLayout(actionsFrame.getContentPane(), BoxLayout.Y_AXIS));
            
            //Lietotnes dati
            JTextField appSizeField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField supportedOSField = new JTextField();
            JTextField supportedLangField = new JTextField();
            JTextField logoDirField = new JTextField();
            JCheckBox crossPlatformC = new JCheckBox("Cross Platform");
            
            //Speles dati
            JTextField engineField = new JTextField();
            JTextField genreField = new JTextField();
            JCheckBox multiplayerC = new JCheckBox("Multiplayer");
            
            //Sazinas lietotnes dati
            JCheckBox videoChatC = new JCheckBox("Video chat");
            JCheckBox groupChatC = new JCheckBox("Group chat");
            JTextField maxUserCapField = new JTextField();
            JTextField maxFileSizeField = new JTextField();
    		
            //Aizpilda laukus ar izvēlētajiem datiem
        	if(actionType == "update") {
                    nameField.setText((String) table.getValueAt(selectedRow, 2));
                    appSizeField.setText((String) table.getValueAt(selectedRow, 1));
                    supportedOSField.setText((String) table.getValueAt(selectedRow, 3));
                    supportedLangField.setText((String) table.getValueAt(selectedRow, 4));
                    logoDirField.setText((String) table.getValueAt(selectedRow, 5));
                    crossPlatformC.setSelected(table.getValueAt(selectedRow, 6).equals("true"));
                    if (tips == "Spēles") {
                        engineField.setText((String) table.getValueAt(selectedRow, 7));
                        genreField.setText((String) table.getValueAt(selectedRow, 8));
                        multiplayerC.setSelected(table.getValueAt(selectedRow, 9).equals("true"));
                    } else if (tips == "Sazinas Lietotnes") {
                        videoChatC.setSelected(table.getValueAt(selectedRow, 7).equals("true"));
                        groupChatC.setSelected(table.getValueAt(selectedRow, 8).equals("true"));
                        maxUserCapField.setText((String) table.getValueAt(selectedRow, 9));
                        maxFileSizeField.setText((String) table.getValueAt(selectedRow, 10));
                    } 
        	}
        	
        	//Pievieno laukus logam
            actionsFrame.add(new JLabel("Name:"));
            actionsFrame.add(nameField);
            actionsFrame.add(new JLabel("App Size (MB):"));
            actionsFrame.add(appSizeField);
            actionsFrame.add(new JLabel("Supported OS (Atdalīti ar kommatu):"));
            actionsFrame.add(supportedOSField);
            actionsFrame.add(new JLabel("Supported Languages (Atdalīti ar kommatu):"));
            actionsFrame.add(supportedLangField);
            actionsFrame.add(new JLabel("Logo Directory:"));
            actionsFrame.add(logoDirField);
            actionsFrame.add(crossPlatformC);
                        
            //Pievieno izvelētajam objektam piederošos laukus
            if(tips == "Spēles"){
            	actionsFrame.add(new JLabel("Engine:"));
            	actionsFrame.add(engineField);
            	actionsFrame.add(new JLabel("Genre:"));
            	actionsFrame.add(genreField);
            	actionsFrame.add(multiplayerC);
            }else if(tips == "Sazinas Lietotnes") {
            	actionsFrame.add(videoChatC);
            	actionsFrame.add(groupChatC);
            	actionsFrame.add(new JLabel("Max user capacity:"));
            	actionsFrame.add(maxUserCapField);
            	actionsFrame.add(new JLabel("Max file size:"));
            	actionsFrame.add(maxFileSizeField);
            }
            
            JButton submitButton = new JButton((actionType == "create" ? "Izveidot" : "Mainīt"));
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                	System.out.println("fdfsdfs");
                        int id = 0;
                        
                        if (actionType != "create") {
                        	id = Integer.parseInt((String) table.getValueAt(selectedRow, 0));
						}
                        
                        int newID = getNewID(tips);
                        double appSize = Double.parseDouble(appSizeField.getText());
                        String name = nameField.getText();
                        String[] supportedOS = supportedOSField.getText().split(",");
                        String[] supportedLang = supportedLangField.getText().split(",");
                        String logoDir = logoDirField.getText();
                        boolean crossPlatform = crossPlatformC.isSelected();

                        if(actionType == "create") {
                        	if (tips == "Spēles") {
                                speles.add(new Spele(newID, appSize, name, supportedOS, supportedLang, logoDir, crossPlatform, engineField.getText(), genreField.getText(), multiplayerC.isSelected()));
                                setTableData("sp");
                            } else if (tips == "Sazinas Lietotnes") {
                                int maxUserCap = Integer.parseInt(maxUserCapField.getText());
                                double maxFileSize = Double.parseDouble(maxFileSizeField.getText());
                                sazinasL.add(new SazinasLietotne(newID, appSize, name, supportedOS, supportedLang, logoDir, crossPlatform, videoChatC.isSelected(), groupChatC.isSelected(), maxUserCap, maxFileSize));
                                setTableData("saz");
                            }
                        }else if(actionType == "update") {
                        	if (tips.equals("Spēles")) {
                                for (Spele spele : speles) {
                                    if (spele.getId() == id) {
                                        spele.setAppSize(appSize);
                                        spele.setName(name);
                                        spele.setSupportedOS(supportedOS);
                                        spele.setSupportedLang(supportedLang);
                                        spele.setLogo(logoDir);
                                        spele.setCrossPlatform(crossPlatform);
                                        spele.setEngine(engineField.getText());
                                        spele.setGenre(genreField.getText());
                                        spele.setMultiplayer(multiplayerC.isSelected());
                                        break;
                                    }
                                }
                                setTableData("sp");
                            } else if (tips.equals("Sazinas Lietotnes")) {
                                int maxUserCap = Integer.parseInt(maxUserCapField.getText());
                                double maxFileSize = Double.parseDouble(maxFileSizeField.getText());
                                for (SazinasLietotne sazinas : sazinasL) {
                                    if (sazinas.getId() == id) {
                                        sazinas.setAppSize(appSize);
                                        sazinas.setName(name);
                                        sazinas.setSupportedOS(supportedOS);
                                        sazinas.setSupportedLang(supportedLang);
                                        sazinas.setLogo(logoDir);
                                        sazinas.setCrossPlatform(crossPlatform);
                                        sazinas.setVideoChat(videoChatC.isSelected());
                                        sazinas.setGroupChat(groupChatC.isSelected());
                                        sazinas.setMaxUserCap(maxUserCap);
                                        sazinas.setMaxFileSize(maxFileSize);
                                        break;
                                    }
                                }
                                setTableData("saz");
                            }
                        }
                        actionsFrame.dispose();
                    } catch (Exception ex) {
                    	System.out.println(ex);
                        JOptionPane.showMessageDialog(actionsFrame, "Ievades kļūda", "Kļūda", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            actionsFrame.add(submitButton);
            actionsFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Nav izvēlēta rinda, kuru mainīt!", "Kļūda", JOptionPane.INFORMATION_MESSAGE);
        }	 
	}
	
    //Funkcija parāda visas pieejamās funkcija izvēlētajam objektam un ļauj veikt objekta funkciju izsaukšanu
    public static void callMethods() {
    	int id = findArrayID(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
    	JFrame methodsFrame = new JFrame("Objekta metodes");
    	methodsFrame.setSize(240,170);
    	methodsFrame.setLayout(new BoxLayout(methodsFrame.getContentPane(), BoxLayout.Y_AXIS));
    	methodsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	//Lietotnes pogas
    	JButton logoB = new JButton("Apskatīt lietotnes logo");
    	logoB.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				(dropdown.getSelectedItem().toString() == "Spēles" ? speles : sazinasL).get(id).raditLogo();
			}
		});
    	JButton downloadB = new JButton("Lietotnes lejupielades ātrums");
    	downloadB.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				(dropdown.getSelectedItem().toString() == "Spēles" ? speles : sazinasL).get(id).aptuvenaisLejupieldesLaiks();
			}
		});
    	JButton OSB = new JButton("Lietotnes saderīgums ar OS");
    	OSB.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				(dropdown.getSelectedItem().toString() == "Spēles" ? speles : sazinasL).get(id).irSaderigsArOS();
			}
		});
    	
    	//Spēles pogas
    	JButton gameInfoB = new JButton("Spēles informācija");
    	gameInfoB.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dropdown.getSelectedItem().toString() == "Spēles") {
					speles.get(id).spelesInfo();
				}
			}
		});
    	JButton enableMultiplayerB = new JButton("Ieviest multiplayer funkcionalitāti");
    	enableMultiplayerB.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dropdown.getSelectedItem().toString() == "Spēles") {
					speles.get(id).ieviestMultiplayer();
					setTableData("sp");
				}
			}
		});
    	
    	//Saziņas lietotnes pogas
    	JButton updateMaxUserCap = new JButton("Mainīt maksimālo lietotāju skaitu");
    	updateMaxUserCap.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println();
				if(dropdown.getSelectedItem().toString() == "Sazinas Lietotnes") {
					sazinasL.get(id).jaunsMaksLietSk();
					setTableData("saz");
				}
			}
		});
    	JButton filesB = new JButton("Vai var sūtīt failus");
    	filesB.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dropdown.getSelectedItem().toString() == "Sazinas Lietotnes") {
					sazinasL.get(id).varSutitFailus();
				}
			}
		});
    	
    	methodsFrame.add(logoB);
    	methodsFrame.add(downloadB);
    	methodsFrame.add(OSB);
    	if (dropdown.getSelectedItem().toString() == "Spēles") {
        	methodsFrame.add(gameInfoB);
        	methodsFrame.add(enableMultiplayerB);
		}else {
	    	methodsFrame.add(updateMaxUserCap);
	    	methodsFrame.add(filesB);
		}
    	
        methodsFrame.setVisible(true);

    }
    
    //Funkcija atrod meklējamā elementa masīvā indeksu, mekelēšana notiek lietojot objekta id atribūtu
    public static int findArrayID(int objectID) {
    	for(int i = 0; i < (dropdown.getSelectedItem().toString() == "Spēles" ? speles : sazinasL).size(); i++) {
    		if ((dropdown.getSelectedItem().toString() == "Spēles" ? speles : sazinasL).get(i).getId() == objectID) {
				return i;
			}
    	}
    	return -1;
    }
    
    //Funkcijai padod boolean vērtību un tā atgriež boolean vērtību ka simbolu virkni (String)
    public static String boolToString(boolean val) {
        if (val == true) {
            return "true";
        } else {
            return "false";
        }
    }
}