import java.io.*;
import java.util.*;


/* - fiecare deschidere e scrisa pe o linie, cu spatii intre mutari;
 * - vor fi 2*nrOp linii (nr de linii poate fi citit din fisier eventual)
 * - primele nrOp deschideri sunt pentru alb
 * - se ia random o linie in functie de culoare
 */
public class Opening {
	BufferedReader in;
	ArrayList<String> openings; //lista cu toate deschiderile
	List<String> moves = new LinkedList<String>();
	String history = null;
	int index = 0;
	final static int nrOp =  3; //
	

	public Opening(String file) throws IOException{
		openings = new ArrayList<String>();
		try {
			String[] s = null;
			String l;
			
			in = new BufferedReader(new FileReader(new File(file)));
			while ( (l = in.readLine()) != null){
				openings.add(l);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public boolean hasNextMove(){
		return openings.size()>0;
	}
	
	public String getNextMove(){
		String move = null;
		int i = 0;
		if (Game.oHistory.isEmpty()){
			move = openings.get(0).split(" ")[0];
			history = move.concat(" ");
			index++;
			return move;
		}
		else {
			if (history == null) {
				history = Game.oHistory.get(0).concat(" ");
				index++;
			}
			else {
			history = history.concat(Game.oHistory.get(Game.oHistory.size()-1).concat(" "));
			index++;
			}
			for (i = 0; i < openings.size(); ){
				if (openings.get(i).startsWith(history) != true){
					openings.remove(i);
				}
				else i++;
			}
		}
		if(hasNextMove()){
			move = openings.get(0).split(" ")[index];
			history = history.concat(move.concat(" "));
			index++;
			
			return move;
		}
		
		return null;
	}
	
}
