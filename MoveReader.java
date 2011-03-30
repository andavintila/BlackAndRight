import java.io.*;
import java.util.*;


/* - fiecare deschidere e scrisa pe o linie, cu spatii intre mutari;
 * - vor fi 2*nrOp linii (nr de linii poate fi citit din fisier eventual)
 * - primele nrOp deschideri sunt pentru alb
 * - se ia random o linie in functie de culoare
 */
public class MoveReader {
	BufferedReader br;
	List<String> moves=new LinkedList<String>();
	final static int nrOp =  3; //10 linii in fisier(5 pt fiecare culoare)
	
	public MoveReader(String file){
		try {
			br=new BufferedReader(new FileReader(new File(file)));
			this.moves = ReadNextOpening();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<String> ReadNextOpening(){
		try {
			String[] s = null;
			int j;
			Random r = new Random();
			if (Game.getMyColor() == ChessColors.White){
				int nr = r.nextInt(nrOp);
				System.out.println(nr);
				j = 0;
				while (j != nr){
					br.readLine();
					j++;
				}
			}
			else{
				int nr = nrOp + r.nextInt(nrOp);
				j = 0;
				while (j != nr){
					br.readLine();
					j++;
				}
			}
			s = br.readLine().split(" ");
			for (int i = 0; i < s.length; i++)
				moves.add(i, s[i]);
			return moves;
		} catch (IOException e) {
			return null;
		}
	}
	

	public boolean hasNextMove(){
		return moves.size()>0;
	}
	public String getNextMove(){
		String s = null;
	
		if(hasNextMove()){
			s=moves.get(0);
			moves.remove(0);
			return s;
		}
		return null;
	}
	
}
