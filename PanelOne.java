/****************************************
 * Kod utworzony przez Adama Sulika
 * Panel z animacj¹ do projektu obrazuj¹cego
 * dzia³anie efektu fotoelektrycznego
****************************************/

package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelOne extends JPanel implements Runnable{

	int width = 640,
		height = 480;
	
	//Do zmiany koloru ¿arówki i jej umiejscowienia
	int waveLength = 0;
	int bulbX = 420,
		bulbY = 30;
	BasicStroke bs1 = new BasicStroke(3);
	
	//Dane do pola do usuniêcia
	int xBorderCoordinates = 78,
		yBorderCoordinates = 36;
	
	int borderHeight = 127,
		borderWidth = 276;
	
	//prêdkoœæ elektronu i jego promieñ
	int vel = 5,
		radius = 35;
	//wspolrzedne srodka elektronu
	static final int startx0 = 400;
	int x0 = startx0,
		y0 = 100;
	
	//Obrazy buforowe
	BufferedImage mainImg,
				blueImg,
				electronImg;
	BufferedImage backgroundImage = null;

	//Dane do pliku z obrazkiem na t³o
	File backgroundFile = null;
	String backgroundImgPath = "C:\\Users\\Adam\\Pictures\\EfektFotoelektryczny\\tlo.jpg";
	
	
	
	//Konstruktor
	public PanelOne() {
			
		electronImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		mainImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		importImage();
		generateBlueImage();
	}
	
	
	//repaint
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		paintBackground();
		g.drawImage(mainImg, 0, 0, width, height, Color.white, null);
		
		Font f = new Font("Comic Sans MS", Font.PLAIN, 40);
		g.setFont(f);
		//g.drawString("Text example", 40, 40);
		
		//Rysuje ¿arówkê
		g.setColor(LenghtToRGB.returnRGB(waveLength));
		g.fillOval(bulbX, bulbY, 100, 115);
		g.setColor(Color.black);
		g.drawOval(bulbX, bulbY, 100, 115);
		g.drawOval(bulbX, bulbY, 99, 114);
		g.drawOval(bulbX, bulbY, 98, 113);


		/*g.setColor(Color.black);
		g2d.draw(new Line2D.Float(456, 135, 484, 135));
		g.drawLine(456, 135, 484, 135);*/
	}

		
	
	
	//funkcja pobieraj¹ca obraz z pliku
	public void importImage() {
		//Za³adowanie do programu odpowiedniej grafiki
		backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try{
			
			backgroundFile = new File(backgroundImgPath);
			backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			backgroundImage = ImageIO.read(backgroundFile);
			System.out.println("Reading complete.");
			
		}catch(IOException e){
		      System.out.println("Error: "+e);
		 }
	}
	
	//Tworzy niebieski obraz z którego kolory pobiera elektron
	public void generateBlueImage() {
		blueImg = new BufferedImage(borderWidth, borderHeight, BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < borderWidth; x++) {
			for(int y = 0; y < borderHeight; y++) {
				int b = (int)(Math.random()*256);
				int p = (255<<24)| b;
				
				blueImg.setRGB(x, y, p);
			} 				
		}
	}
	
	//Rysuje poruszaj¹cy siê elektron
	public void paintElectron() {
		for(int x = xBorderCoordinates; x < xBorderCoordinates+borderWidth; x++ ) {
			for(int y = yBorderCoordinates; y < yBorderCoordinates+borderHeight; y++ ) {
				if( (x-x0)*(x-x0)+(y-y0)*(y-y0) <= (radius*radius) ) {
					electronImg.setRGB(x, y, blueImg.getRGB(x-xBorderCoordinates, y-yBorderCoordinates));
				}
				else {
					electronImg.setRGB(x, y, 0);
				}
			}
		}
		x0 -= vel;
		if (x0 < (xBorderCoordinates-radius) ) { x0 = startx0; }
	}
	
	
	//Rysowanie obrazka na panelu
	public void paintBackground() {
		
		//Ustawianie pixeli docelowego obrazu
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				//przestrzeñ dla animacji
				if(x <= xBorderCoordinates+borderWidth && x >= xBorderCoordinates && y >= yBorderCoordinates && y <= yBorderCoordinates+borderHeight) {
					mainImg.setRGB(x, y, electronImg.getRGB(x, y));
				}
				
				//przestrzeñ dla obrazku
				else {
					mainImg.setRGB(x, y, backgroundImage.getRGB(x,y));
				}
			}
		}
	}

	
	@Override
	public void run() {
		int pauza = 30;
		boolean czynny = true;
		
		while(czynny) {
			paintElectron();
			repaint();
			try {
				Thread.sleep(pauza);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
