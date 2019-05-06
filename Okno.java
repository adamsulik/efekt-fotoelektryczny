package Interfejs;

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

public class Okno extends JFrame {
	
	JMenuItem wczytywanie = new JMenuItem ("Wczytaj...");
	JMenuItem zapisywanie = new JMenuItem ("Zapisz jako");
	JLabel wavelength = new JLabel("Długość Fali (nm):");
	JSlider WavelengthRegulation = new JSlider(0,1000,20);
	JLabel napiecie = new JLabel("Napięcie wsteczne (V):");
	JSlider RegulacjaNapiecia = new JSlider(0,20,5);
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
		
		WavelengthRegulation.setPaintTicks(true);
		WavelengthRegulation.setMajorTickSpacing(100);
		
		RegulacjaNapiecia.setPaintLabels(true);
		RegulacjaNapiecia.setMajorTickSpacing(4);
		JTextField t1 = new JTextField("Długość Fali");
		
		JTextField t2 = new JTextField("Wartość napięcia wstecznego");
		p1.add(wavelength);
		p1.add(WavelengthRegulation);
		p1.add(t1);
		p1.add(napiecie);
		p1.add(RegulacjaNapiecia);
		p1.add(t2);
		
		JPanel p2 = new JPanel(new GridLayout(2,1));
		
		lista.addItem("Cs - W = 2,14 eV");
		lista.addItem("Pb - W = 4,25 eV");
		lista.addItem("Au - W = 5,2 eV");
		lista.addItem("K - W = 2,29 eV");
		lista.addItem("Mg - W = 3,66 eV");
		JLabel l3 = new JLabel("Wybór Materiału:");
		p2.add(l3);
		p2.add(lista);
		
		this.add(p1, BorderLayout.LINE_END);
		this.add(p2, BorderLayout.PAGE_START);
		this.add(animacja, BorderLayout.CENTER);
		
	}

	

	public static void main(String[] args) {
		Okno okienko = new Okno();
		okienko.setSize(800, 800);
		okienko.setVisible(true);
	}

}
