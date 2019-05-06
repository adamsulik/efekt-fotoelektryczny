package GUI;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
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

	private static final long serialVersionUID = 1L;

	public Okno() throws HeadlessException {
		
		JMenuBar m1 = new JMenuBar();
		JMenu menu = new JMenu("Opcje");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		JMenuItem i1 = new JMenuItem ("Wczytaj...");
		JMenuItem i2 = new JMenuItem ("Zapisz jako");
		
		menu.add(i1);
		menu.add(i2);
		m1.add(menu);
		this.setJMenuBar(m1);
		
		JPanel p1 = new JPanel(new GridLayout(6,1));
		
		JLabel l1 = new JLabel("D³ugoœæ Fali (nm):");
		JSlider s1 = new JSlider(380,780,450);
		s1.setPaintTicks(true);
		s1.setPaintLabels(true);
		
		s1.setMajorTickSpacing(80);
		JLabel l2 = new JLabel("Napiêcie wsteczne (V):");
		JSlider s2 = new JSlider(0,20,5);
		s2.setPaintLabels(true);
		s2.setMajorTickSpacing(4);
		
		JTextField t1 = new JTextField("D³ugoœæ Fali");
		JTextField t2 = new JTextField("Wartoœæ napiêcia wstecznego");
		p1.add(l1);
		p1.add(s1);
		p1.add(t1);
		p1.add(l2);
		p1.add(s2);
		p1.add(t2);
		JPanel p2 = new JPanel(new GridLayout(2,1));
		JComboBox <String> lista = new JComboBox <String>();
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
		
	}

	public Okno(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public Okno(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public Okno(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	

	public static void main(String[] args) {
		Okno okienko = new Okno();
		okienko.setSize(900, 500);
		okienko.setVisible(true);
	}

}
