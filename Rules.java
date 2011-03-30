import java.io.FileWriter;
import java.io.IOException;


public class Rules {
	//aici ar trebui puse functii care verifica respectarea regulilor si care nu tin strict de piesa
	//Board.searchPiece(...) ar putea veni aici
	public static Piesa getKing(Board b, int color){
		Piesa p;
		int i, j;
		for (i = 1; i <= 8; i++)
			for (j = 1; j <= 8; j++){
				p = b.getPieceOnCoord(i, j);
				if (p != null && p.hashCode() == 'K' && p.Color == color)
					return p;
			}
		return null;
	}
	public static boolean isInCheck(Board b, Piesa king){
		Piesa p;
		int i, j;
		for (i = 1; i <= 8; i++)
			for (j = 1; j <= 8; j++){
				p = b.getPieceOnCoord(i, j);
				if (p != null && p.Color != king.Color && p.canMoveTo(king.p, b))
					return true;
			}
		return false;
	}
	public static void printOutput(String code){
		FileWriter fw;
		try {
			fw = new FileWriter("out2.txt",true);
			fw.write(code + "\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
