package zaliczenie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LanguageSelect extends JOptionPane implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Boolean isPolish = true;

	public LanguageSelect() {
		this.setSize(400,400);
		this.setLayout(new BorderLayout());
		JButton b1 = new JButton("Polish");
		JButton b2 = new JButton("English");
		JLabel jta = new JLabel("Select Language");
		JPanel centerJP = new JPanel();
		centerJP.setLayout(new FlowLayout());
		
		b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			isPolish = true;
			}});
		b2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			isPolish = false;
			}});
		
		centerJP.add(b1);
		centerJP.add(b2);
		this.add(jta, BorderLayout.PAGE_START);
		this.add(centerJP, BorderLayout.CENTER);
		this.setVisible(true);

		
		
	
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
