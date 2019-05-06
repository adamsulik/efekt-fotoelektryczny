package analiza;
import java.lang.Math;
import java.util.Scanner;


public class Matematyka {
	
	
	public Matematyka() {
		zadanafala = new Fala();
		zadany = new Material();
		napiecieWsteczne = 0;
	}

	public static void main(String[] args) {
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

	}
	
	public void setNapiecieWsteczne(int setter) {
		napiecieWsteczne = setter;
	}
	
	public static void materialInfo() {
		System.out.println("Nazwa: " + zadany.nazwa + "\n" + "Energia Wyjœcia: " + zadany.energiaWyjscia);
	}
	
	public double obliczEnergie() {
		double energia = 0;
		energia = zadanafala.czestotliwosc() * Math.pow(6.63, -34);
		energia -= zadany.energiaWyjscia;
		energia -= napiecieWsteczne * Math.pow(1.6, -19);
		
		if(energia < 0) { energia = 0; }
		
		return energia;
	}
	
	
	
	static int napiecieWsteczne = 0;
	static Material zadany;
	Fala zadanafala;
	double energiaElektronu;
	
	class Fala{
		int dlugosc = 400;
		int wykladnik = -9;
		double czestotliwosc() {return Math.pow((double)dlugosc, (double)wykladnik) / 300000; }
	}
	class Material{
		String nazwa = "Nazwa Materialu";
		float energiaWyjscia = 0;
	}
}
