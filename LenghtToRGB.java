//Kod przygotowany przez Adama

package GUI;

import java.awt.Color;

import javax.swing.JOptionPane;

public class LenghtToRGB {

	public LenghtToRGB() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Color returnRGB(float waveLength) {
		float r = 1;
		float g = 1;
		float b = 1;
		float nasycenie = 1; //nasycenie
		
		//tworzy biny do interpretacji kolorów
		final float bins[] = {380, 420, 440, 490, 510, 580, 645, 700, 780, Float.MAX_VALUE};
		int bin = 0;
		
		if(waveLength > 1100 || waveLength < 0) {
			System.out.println("Zadano nieprawid³ow¹ wartoœæ d³ugoœci fali\n");
			return new Color(0,0,0);
		}
		
		//umiejscawia zadan¹ d³ugoœæ fali w odpowiednim binie
		for(int i = 0; i < bins.length; i++) {
			if(waveLength <= bins[i]) {
				bin = i;
				break;
			}
		}
		
		//oblicza wartoœæ rgb
		 switch ( bin )
         {
         case 0:
             //d³ugoœæ fali niewidzialnej dla ludzkiego oka - poni¿ej 380nm
             r = 1;
             g = 0;
             b = 0;
             nasycenie = .1f;
             break;
         case 1:
             //380-420
             r = ( 440 - waveLength ) / ( 440 - 380 );
             g = 0;
             b = 1;
             nasycenie = (float)1 * ( waveLength - 380 ) / ( 420 - 380 );
             break;
         case 2:
             //420-440
             r = ( 440 - waveLength ) / ( 440 - 380 );
             g = 0;
             b = 1;
             break;
         case 3:
             //440-490
             r = 0;
             g = ( waveLength - 440 ) / ( 490 - 440 );
             b = 1;
             break;
         case 4:
             //490-510
             r = 0;
             g = 1;
             b = ( 510 - waveLength ) / ( 510 - 490 );
             break;
         case 5:
             //510 - 580
             r = ( waveLength - 510 ) / ( 580 - 510 );
             g = 1;
             b = 0;
             break;
         case 6:
             //580 - 645
             r = 1;
             g = ( 645 - waveLength ) / ( 645 - 580 );
             b = 0;
             break;
         case 7:
            //645-700
             r = 1;
             g = 0;
             b = 0;
             break;
         case 8:
             // 700 - 780
             r = 1;
             g = 0;
             b = 0;
             nasycenie = (float)1 * ( 780 - waveLength ) / ( 780 - 700 );
             break;
         case 9:
            //d³ugoœæ fali niewidzialnej dla ludzkiego oka - powy¿ej 780nm
             r = 0;
             g = 0;
             b = 0;
             nasycenie = 0;
             break;
         }
		 
		 //uwzglêdnienie nasycenia
		 r *= nasycenie;
		 g *= nasycenie;
		 b *= nasycenie;
		 
		
		return new Color( r, g, b );
	}

}
