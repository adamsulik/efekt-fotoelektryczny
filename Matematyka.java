package analiza;
//Kod przygotowany przez Adama

import java.lang.Math;
import java.util.Scanner;


public class Matematyka {
	
	
	public Matematyka() {
		zadanafala = new Fala();
		zadany = new Material();
		napiecieWsteczne = 0;
	}

	/*public static void main(String[] args) {
		int cykl = 0;
		Matematyka matematyka = new Matematyka();
		Scanner sc = new Scanner(System.in);
		while(true) {
			cykl++;
			System.out.println("" + cykl + "\n");
			System.out.println("Info o materiale:");
			materialInfo();
			System.out.println("Podaj wartosc napiecia wstecznego\n");
			napiecieWsteczne = sc.nextInt();
			System.out.println("Wprowadzone napiêcie: " + napiecieWsteczne + "V");
			System.out.println("Wartosc energii elektronów: " + matematyka.obliczEnergie());
		}

	}*/
	
	public void setNapiecieWsteczne(int setter) {
		napiecieWsteczne = setter;
	}
	
	public static void materialInfo() {
		System.out.println("Nazwa: " + zadany.nazwa + "\n" + "Energia Wyjœcia: " + zadany.energiaWyjscia);
	}
	
	public double obliczEnergie() {
		double energia = 0;
		energia = zadanafala.czestotliwosc() * 4.13 * Math.pow(10, -15) ;//energia w eV
		System.out.println("Energia: " + energia + "\n");
		energia -= zadany.energiaWyjscia;
		energia -= napiecieWsteczne;
		
		if(energia < 0) { energia = 0; }
		
		return energia;
	}
	
	
	
	public static int napiecieWsteczne = 0;
	static Material zadany;
	public Fala zadanafala;
	double energiaElektronu;
	
	public class Fala{
		public int dlugosc = 400;
		int wykladnik = -9; //nanometry
		public double czestotliwosc() {return 3*Math.pow(10, 8) / (dlugosc * Math.pow((double)10, (double)wykladnik)); }
	}
	class Material{
		String nazwa = "Nazwa Materialu";
		float energiaWyjscia = (float) 2.14;
	}
}
