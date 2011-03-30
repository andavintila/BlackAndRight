import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Test {
/** Clasa testare proiect sah
 * Clasa Board implementata destul de bine, clasa abstracta Piesa dezvoltata bine,clasele pieselor dezvoltate foarte putin
 * Clasa Cal dezvoltata un pic. Exista o metoda fastMove care genereaza pozitii aleatoare pe care poate fi mutat calul
 * Algoritm inceput-clasa Game. Exista metoda getNextMove() care intoarce un string cu urmatoare metoda pe care o transmite
 * engine-ul.
 * Comunicare cu winboard in stare incipienta
 * Codename ATLAS
*/
	
	public static void main(String[] args) throws IOException {

		//Comunicare c = new Comunicare();
		//c.input();
		Game g=new Game();
		for(int i=1;i<10;i++){
			System.out.println(g.getNextMove());
			//g.showGameBoard();
		}
		/*Scanner s = new Scanner(new File("test.txt"));
		String move;
		while (s.hasNext()){
			move = s.next();
			System.out.println(move);
			MoveSan.Decode(move, g.b);
			g.showGameBoard();
		}*/
	}

}
