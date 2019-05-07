package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import analiza.Matematyka;

public class Okno extends JFrame {
	
	Matematyka matematyka = new Matematyka();
	JMenuItem wczytywanie = new JMenuItem ("Wczytaj...");
	JMenuItem zapisywanie = new JMenuItem ("Zapisz jako");
	JLabel wavelength = new JLabel("D³ugoœæ Fali (nm):");
	JSlider WavelengthRegulation = new JSlider(10,800,400);
	JLabel napiecie = new JLabel("Napiêcie wsteczne (V):");
	JSlider RegulacjaNapiecia = new JSlider(0,5, 1);
	JComboBox <String> lista = new JComboBox <String>();
	JPanel animacja = new JPanel();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Okno() throws HeadlessException {
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Opcje");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		
		
		menu.add(wczytywanie);
		menu.add(zapisywanie);
		
		menubar.add(menu);
		this.setJMenuBar(menubar);
		
		JPanel p1 = new JPanel(new GridLayout(6,1));
		
		WavelengthRegulation.setPaintLabels(true);
		WavelengthRegulation.setPaintTicks(true);
		WavelengthRegulation.setMajorTickSpacing(150);
		
		RegulacjaNapiecia.setPaintLabels(true);
		RegulacjaNapiecia.setPaintTicks(true);
		RegulacjaNapiecia.setMajorTickSpacing(1);

		p1.add(wavelength);
		p1.add(WavelengthRegulation);
		p1.add(new JPanel());
		p1.add(new JPanel());
		p1.add(napiecie);
		p1.add(RegulacjaNapiecia);
		
		JPanel p2 = new JPanel(new GridLayout(2,1));
		
		lista.addItem("Cs - W = 2,14 eV");
		lista.addItem("Pb - W = 4,25 eV");
		lista.addItem("Au - W = 5,2 eV");
		lista.addItem("K - W = 2,29 eV");
		lista.addItem("Mg - W = 3,66 eV");
		JLabel l3 = new JLabel("Wybór Materia³u:");
		p2.add(l3);
		p2.add(lista);
		
		this.add(p1, BorderLayout.LINE_END);
		this.add(p2, BorderLayout.PAGE_START);
		this.add(animacja, BorderLayout.CENTER);
		
		
		matematyka.zadanafala.dlugosc = WavelengthRegulation.getValue();
		wavelength.setText("D³ugoœæ Fali(nm): " + matematyka.zadanafala.dlugosc);
		animacja.setBackground( LenghtToRGB.returnRGB(matematyka.zadanafala.dlugosc) );
		
		WavelengthRegulation.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				matematyka.zadanafala.dlugosc = WavelengthRegulation.getValue();
				wavelength.setText("D³ugoœæ Fali(nm): " + matematyka.zadanafala.dlugosc);
				animacja.setBackground( LenghtToRGB.returnRGB(matematyka.zadanafala.dlugosc) );
				System.out.println("Energia elektronów wynosi: " + matematyka.obliczEnergie() + "eV");
				System.out.println("Czêstotliwoœæ fali wynosi: " + matematyka.zadanafala.czestotliwosc() + "\n");
			}
		});
		
		RegulacjaNapiecia.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				matematyka.setNapiecieWsteczne( RegulacjaNapiecia.getValue() );
				napiecie.setText("Napiêcie wsteczne (V):" + Matematyka.napiecieWsteczne + "\n");
				System.out.println("Energia elektronów wynosi: " + matematyka.obliczEnergie() + "eV");
				System.out.println("Czêstotliwoœæ fali wynosi: " + matematyka.zadanafala.czestotliwosc() + "\n");
			}
			
		});
		
	}

	
	
	

	public static void main(String[] args) {
		Okno okienko = new Okno();
		okienko.setSize(800, 800);
		okienko.setVisible(true);
	}

}
