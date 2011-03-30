import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Clasa ce implementeaza algoritmul Negamax.
 * @author Matei
 *
 */
public class Negamax {
/**
 * Functia de evaluare pentru algoritmul NegaMax. Pe baza acestei functii calculeaza ce mutari sunt mai avantajoase.
 * In prezent, calculeaza doar cine e in avantaj pe baza pieselor pe care le are fiecare.
 * TODO: o functie de evaluare mai buna. Sa calculeze in functie de piesele vulnerabile, pozitiile pe tabla de sah
 * @param b - tabla de joc 
 * @param player - jucatorul care trebuie sa mute
 * @return valoarea pozitiei respective
 */
	public static int pieceEval(Piesa p){
		switch ((char)(p.hashCode())){
		case 'p': return 100;
		case 'N': return 320;
		case 'B': return 325;
		case 'R': return 500;
		case 'Q': return 975;
		case 'K': return 20000;
		default: return 0;
		}
	}
	
	public static int evalPos(char code, int color, int x, int y){
		x--;
		y--;
		switch (code){
		case 'p':
			if (color == ChessColors.White)
				return Evaluators.pawnW[x][y];
			else
				return Evaluators.pawnB[x][y];
		case 'N':
			if (color == ChessColors.White)
				return Evaluators.knightW[x][y];
			else
				return Evaluators.knightB[x][y];
		case 'B':
			if (color == ChessColors.White)
				return Evaluators.bishopW[x][y];
			else
				return Evaluators.bishopB[x][y];
		case 'Q':
			return Evaluators.queen[x][y];
		case 'R':
			if (color == ChessColors.White)
				return Evaluators.rookW[x][y];
			else
				return Evaluators.rookB[x][y];
		case 'K':
			if (color == ChessColors.White)
				return Evaluators.kingW[x][y];
			else
				return Evaluators.kingB[x][y];
		default: return 0;
		}
	}
	
	public static int evaluate(Board b, int player){
		int eval = 0;
		int i, j;
		Piesa p;
		for (i = 1; i <= 8; i++)
			for (j = 1; j <= 8; j++){
				p = b.getPieceOnCoord(i, j);
				if (p != null){
					if (p.Color == player){
						switch ((char)(p.hashCode())){
						case 'Q': eval += 975; break;
						case 'R': eval += 500; break;
						case 'N': eval += 320; break;
						case 'B': eval += 325; break;
						case 'p': eval += 100; break;
						case 'K': eval += 20000; break;
						default: break;
						}
						eval += evalPos((char)p.hashCode(), p.Color, i, j);
					}
					else{
						switch ((char)(p.hashCode())){
						case 'Q': eval -= 975; break;
						case 'R': eval -= 500; break;
						case 'N': eval -= 320; break;
						case 'B': eval -= 325; break;
						case 'p': eval -= 100; break;
						case 'K': eval -= 20000; break;
						default: break;
						}
						eval -= evalPos((char)p.hashCode(), p.Color, i, j);
					}
				}
			}
		return eval;
	}
	
	static ArrayList<Position> BubbleSort(Piesa p, ArrayList<Position> moves, Board b){
		int i, k, gata;
		int v1 = 0, v2 = 0;
		Position aux, pos;
		Piesa captura;
		k = moves.size() - 1;
		do{
			gata = 1;
			for (i = 0; i < k; i++){
				pos = moves.get(i);
				captura = b.getPieceOnPosition(pos);
				if (captura != null && captura.Color != p.Color)
					v1 = pieceEval(captura);
				captura = null;
				pos = moves.get(i+1);
				captura = b.getPieceOnPosition(pos);
				if (captura != null && captura.Color != p.Color)
					v2 = pieceEval(captura);
				if (v1 < v2){
					aux = moves.get(i);
					moves.set(i, moves.get(i+1));
					moves.set(i+1, moves.get(i));
					gata = 0;
				}
			}
			k--;
		}
		while (gata == 0);
		return moves;
	}
/**
 * Functia care furnizeaza mutarea urmatoare engine-ului. Efectueaza primul pas din algoritm si apeleaza mai departe
 * functia negaMax sa calculeze cu o adancime de 3. 
 * @param b - tabla de sah
 * @return - o instanta a clasei Mutare, cu piesa ce urmeaza a fi mutata si pozitia pe care va fi mutata
 */
	public static Mutare nextMove(Board b){
		int score, max = Integer.MIN_VALUE;
		Piesa p = null, captura = null, pmax = null;
		Position pos = null, prev, posmax = null;
		Random r=new Random();
		int i, j, k;
		for (i = 1; i <= 8; i++)
			for (j = 1; j <= 8; j++){
				p = b.getPieceOnCoord(i, j);//pentru fiecare piesa
				if (p != null && p.Color == Game.myColor){//daca e o piesa proprie la pozitia respectiva
					ArrayList<Position>moves = p.genMoves(b);//genereaza toate mutarile posibile pentru piesa
					moves = BubbleSort(p, moves, b);
					k = 0;
					while (k < moves.size()){//apeleaza negamax pentru fiecare mutare
						pos = moves.get(k++);
						captura = b.getPieceOnPosition(pos);
						prev = p.p;
						b.move(p, pos);
						score = (-1) * Negamax.negaMax(b, 6, (-1 * Game.myColor),Integer.MIN_VALUE,-max);//adancime de 3 in afara de nivelul curent (in total 4)
						if (score == max){
							if (r.nextInt(4) == 1){
								pmax = p;
								posmax = pos;
							}
						}
						else
							if (score > max){
								max = score;
								pmax = p;
								posmax = pos;
							}
						b.move(p, prev);
						if (captura != null)
							captura.respawn(pos, b);
					}
				}
			}
		return new Mutare(pmax, posmax);
	}

/**
 * Functia recursiva care implementeaza algoritmul NegaMax.
 * @param b - tabla de sah
 * @param depth - adancimea curenta a parcurgerii mutarilor in adancime; cand depth e 0, se opreste
 * @param player - jucatorul care trebuie sa faca mutarea la pasul curent
 * @return - maximul/minimul dintre pozitiile evaluate
 */
	public static int negaMax(Board b, int depth, int player,int alpha,int betha){
		if (depth == 0){
			return evaluate(b, player);
		}
		int score, promotion = 0;
		Piesa p = null, captura = null;
		Position pos = null, prev;
		int i, j, k,be=betha,alfa=alpha;
		for (i = 1; i <= 8; i++)
			for (j = 1; j <= 8; j++){
				p = b.getPieceOnCoord(i, j);
				if (p != null && p.Color == player){
					ArrayList<Position> moves = p.genMoves(b);
					moves = BubbleSort(p, moves, b);
					k = 0;
					while (k < moves.size()){
						pos = moves.get(k++);
						captura = b.getPieceOnPosition(pos);
						prev = p.p;
						b.move(p, pos);
						if ((char)(p.hashCode()) == 'p' && ((p.p.linie == 8 && p.Color == ChessColors.White) || (p.p.linie == 1 && p.Color == ChessColors.Black))){
							p = b.promote(p, p.Color, 'Q');
							promotion = 1;
						}
						else
							promotion = 0;
						score = (-1) * Negamax.negaMax(b, depth - 1, -1 * player,-be,alfa);
						if (score > alfa){
							alfa = score;
						}
						if(alfa>=betha) {
							b.move(p, prev);
							if (captura != null)
								captura.respawn(pos, b);
							if (promotion == 1){
								int col = p.Color;
								b.take(p);
								p = new Pion(pos, col);
								p.respawn(pos, b);
							}							
							return alfa;
						}
						if(alfa>=be){
							alfa=-Negamax.negaMax(b, depth-1, -player, -betha, -alfa);
							if(alfa>=betha){
								b.move(p, prev);
								if (captura != null)
									captura.respawn(pos, b);
								if (promotion == 1){
									int col = p.Color;
									b.take(p);
									p = new Pion(pos, col);
									p.respawn(pos, b);
								}
								return alfa;
							}
						}
						be=alfa+1;
						b.move(p, prev);
						if (captura != null)
							captura.respawn(pos, b);
						if (promotion == 1){
							int col = p.Color;
							b.take(p);
							p = new Pion(pos, col);
							p.respawn(pos, b);
						}
					}
				}
			}
		return alfa;
	}
}
