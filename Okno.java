package zaliczenie;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

import zaliczenie.Matematyka;

public class Okno extends JFrame {
	
	//Tworzy odpowiednie elementy
	Matematyka matematyka = new Matematyka();
	PanelOne mainPanel;
	
	static Toolkit toolkit;
	
	public static Lang nameSpace = new Polski();
	public static int progLanguage = 0;
	
	JMenuItem wczytywanie = new JMenuItem (nameSpace.MenuWczytaj);
	JMenuItem zapisywanie = new JMenuItem (nameSpace.MenuZapisz);
	JLabel wavelength = new JLabel(nameSpace.DlugoscFali + ": ");
	JSlider WavelengthRegulation = new JSlider(350,800,400);
	JLabel napiecie = new JLabel(nameSpace.NapiecieWsteczne + ": ");
	JSlider RegulacjaNapiecia = new JSlider(0,10, 0);
	JComboBox <String> lista = new JComboBox <String>();
	
	String czytaj = "";
	String[] odczytane = null;
	
	String[] danedozapisu = null;
	
	 
	 
	private static final long serialVersionUID = 1L;

	public Okno() throws HeadlessException , SQLException {
		
		BazaMaterialow bazamaterialow = new BazaMaterialow();
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu(nameSpace.MenuOpcje);
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		
		toolkit = Toolkit.getDefaultToolkit();
		
		menu.add(wczytywanie);
		menu.add(zapisywanie);
		
		wczytywanie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser (FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Wybierz plik do odczytania:");
				jfc.showOpenDialog(null);
			 	jfc.getSelectedFile();
			
			try {
				InputStreamReader isr =
						new InputStreamReader(new FileInputStream(jfc.getSelectedFile().getPath()), 
								Charset.forName("UTF-8").newDecoder());
				BufferedReader bufor = new BufferedReader(isr);
				while ((czytaj = bufor.readLine()) != null){
					odczytane = czytaj.split("");
					
					 matematyka.zadanafala.dlugosc = Integer.parseInt(odczytane[0]);
					 Matematyka.napiecieWsteczne = Integer.parseInt(odczytane[1]);
					
					
				}
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
			
			});
		
		zapisywanie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileWriter fw = null;
				try {
					fw = new FileWriter("zapisane_dane");
					danedozapisu[0] = Integer.toString(matematyka.zadanafala.dlugosc);
					danedozapisu[1] = Integer.toString(Matematyka.napiecieWsteczne);
					danedozapisu[0] = Double.toString(matematyka.energiaElektronu);
					
					BufferedWriter bw = new BufferedWriter(fw);
					for (int i = 0; i < danedozapisu.length; i++) { 
						bw.write(danedozapisu[i]);
						bw.newLine();
					}
				}catch (IOException k) {
					k.printStackTrace();
				}
				
				// TODO Auto-generated method stub
				
			}
			
		});
		
		menubar.add(menu);
		this.setJMenuBar(menubar);
		
		JPanel p1 = new JPanel(new GridLayout(6,1));
		mainPanel = new PanelOne(matematyka.obliczEnergie());
		
		WavelengthRegulation.setPaintLabels(true);
		WavelengthRegulation.setPaintTicks(true);
		WavelengthRegulation.setMajorTickSpacing(150);
		
		//RegulacjaNapiecia.setPaintLabels(true);
		RegulacjaNapiecia.setPaintTicks(true);
		RegulacjaNapiecia.setMajorTickSpacing(1);

		p1.add(wavelength);
		p1.add(WavelengthRegulation);
		p1.add(new JPanel());
		p1.add(new JPanel());
		p1.add(napiecie);
		p1.add(RegulacjaNapiecia);
		
		JPanel p2 = new JPanel(new GridLayout(2,1));
		

			lista.addItem(bazamaterialow.materials[1]);
			lista.addItem(bazamaterialow.materials[2]);
			lista.addItem(bazamaterialow.materials[3]);
			lista.addItem(bazamaterialow.materials[4]);
			lista.addItem(bazamaterialow.materials[5]);
		
		lista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				
				String s = (String) lista.getSelectedItem();
				
				switch (s) {
					case "Cs-W=2,14eV":
						matematyka.zadany.energiaWyjscia = Float.parseFloat(bazamaterialow.values[1])/100;
						break;
					case "Pb-W=4,25eV":
						matematyka.zadany.energiaWyjscia = Float.parseFloat(bazamaterialow.values[2])/100;
						break;
					case "Au-W=5,2eV":
						matematyka.zadany.energiaWyjscia = Float.parseFloat(bazamaterialow.values[3])/100;
						break;
					case "Mg-W=3,66eV":
						matematyka.zadany.energiaWyjscia = Float.parseFloat(bazamaterialow.values[4])/100;
						break;
					case "K-W=2,29eV":
						matematyka.zadany.energiaWyjscia = Float.parseFloat(bazamaterialow.values[5])/100;
						break;
				}
						
				
			
				System.out.println(matematyka.zadany.energiaWyjscia);
			}
		});

		
		JLabel l3 = new JLabel(nameSpace.WyborMatrialu + ": ");
		p2.add(l3);
		p2.add(lista);
		
		this.add(p1, BorderLayout.LINE_END);
		this.add(p2, BorderLayout.PAGE_START);
		this.add(mainPanel, BorderLayout.CENTER);
		
		
		matematyka.zadanafala.dlugosc = WavelengthRegulation.getValue();
		wavelength.setText(nameSpace.DlugoscFali + ": " + matematyka.zadanafala.dlugosc);
		
		//Ustawia d³ugoœæ fali w g³ównym panelu
		mainPanel.waveLength = WavelengthRegulation.getValue();
		
		//Listener do slidera d³ugoœci fali
		WavelengthRegulation.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				matematyka.zadanafala.dlugosc = WavelengthRegulation.getValue();
				mainPanel.waveLength = WavelengthRegulation.getValue();
				wavelength.setText(nameSpace.DlugoscFali + ": " + matematyka.zadanafala.dlugosc);
				//System.out.println("Energia elektronów wynosi: " + matematyka.obliczEnergie() + "eV");
				//System.out.println("Czêstotliwoœæ fali wynosi: " + matematyka.zadanafala.czestotliwosc() + "\n");
				mainPanel.energiaElektronu = matematyka.obliczEnergie();
			}
		});
		
		RegulacjaNapiecia.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				matematyka.setNapiecieWsteczne( (double)RegulacjaNapiecia.getValue()/10 );
				napiecie.setText(nameSpace.NapiecieWsteczne + ": " + Matematyka.napiecieWsteczne + "\n");
				System.out.println("Energia elektronów wynosi: " + matematyka.obliczEnergie() + "eV");
				System.out.println("Czêstotliwoœæ fali wynosi: " + matematyka.zadanafala.czestotliwosc() + "\n");
				mainPanel.napiecieWsteczne = ("" + (double)RegulacjaNapiecia.getValue()/10);
				mainPanel.energiaElektronu = matematyka.obliczEnergie();
			}
			
		});
		
	}

	
	
	

	public static void main(String[] args) throws HeadlessException, SQLException {
		
		String [] languageSelectorButtons = {"Polski", "English"};
		progLanguage = JOptionPane.showOptionDialog(null, "Select Language: ", "Welcome", JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.PLAIN_MESSAGE, null, languageSelectorButtons, languageSelectorButtons[1]);
		
		if(progLanguage == 1) nameSpace = new Angielski();
		
		Okno okienko = new Okno();
		okienko.setSize(800, 600);
		
		ExecutorService exec = Executors.newFixedThreadPool(1);
		exec.execute(okienko.mainPanel);
		
		okienko.setVisible(true);
	}

}
