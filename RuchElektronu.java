// Alex

package zaliczenie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



class Animation extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 750;
	int y = 500;
	

	public Animation() {
		
		super();
		setPreferredSize(new Dimension(1000, 1000));
		// TODO Auto-generated constructor stub
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.blue);
		g.fillOval(x, y, 50, 50);

	}

	@Override
	public void run() {
		
		while (x > 250) {
			x--;
			try
			{
				Thread.sleep(10);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			repaint();
		}
		// TODO Auto-generated method stub
		
	}

}

public class RuchElektronu {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame f = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(1000, 1000);
				f.setLocation(750,500);
				Animation panel = new Animation();
				f.add(panel);
				f.setVisible(true);

				ExecutorService exec = Executors.newFixedThreadPool(1);
				exec.execute(panel);
				exec.shutdown();

				// new Thread(panel).start();

			}
		});
		// TODO Auto-generated method stub

	}
	
}


