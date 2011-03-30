import java.io.FileWriter;
import java.io.IOException;


public class MoveSan {
	public static int uniquePiece(Board b,int color, Position pos, int lin, char type){
		Piesa piesa = null;
		int variants = 0;
		Piesa orig = b.getPieceOnPosition(pos);
		b.placa_piese[pos.linie][pos.coloana] = null;
		for (int j = 1; j <= 8; j++)
			if (!b.isFree(lin, j) && b.placa_piese[lin][j].hashCode() == type && b.placa_piese[lin][j].Color == color && b.placa_piese[lin][j].canMoveTo(pos, b)){
				variants++;
				piesa = b.placa_piese[lin][j];
			}
		b.placa_piese[pos.linie][pos.coloana] = orig;
		return variants;
	}
	public static int uniquePiece(Board b, int col, int color, Position pos, char type){
		Piesa piesa = null;
		int variants = 0;
		Piesa orig = b.getPieceOnPosition(pos);
		b.placa_piese[pos.linie][pos.coloana] = null;
		for (int i = 1; i <= 8; i++)
			if (!b.isFree(i, col) && b.placa_piese[i][col].hashCode() == (int)type && b.placa_piese[i][col].Color == color && b.placa_piese[i][col].canMoveTo(pos, b)){
				variants++;
				piesa = b.placa_piese[i][col];
			}
		b.placa_piese[pos.linie][pos.coloana] = orig;
		return variants;
	}
	public static int uniquePiece(Board b, Position pos, int color, char type){
		Piesa p;
		Piesa orig = b.getPieceOnPosition(pos);
		b.placa_piese[pos.linie][pos.coloana] = null;
		int variants = 0;
		int i, j;
		for (i = 1; i <= 8; i++)
			for (j = 1; j<= 8; j++){
				p = b.getPieceOnCoord(i, j);
				if (p != null && p.hashCode() == type && p.Color == color && p.canMoveTo(pos, b)){
					variants++;
				}
			}
		b.placa_piese[pos.linie][pos.coloana] = orig;
		return variants;
	}
	public static Piesa findPiece(Board b, Position pos, int lin, int col, int color, char type){
		Piesa p = null;
		int i, j;
		int ln,cl;
		for (i = 1; i <= 8; i++)
			for (j = 1; j <= 8; j++){
				p = b.getPieceOnCoord(i, j);
				if (p != null && (char)(p.hashCode()) == type && p.Color == color){
					ln = (lin!= 0) ? lin : i;
					cl = (col!= 0) ? col : j;
					if (p.getLin() == ln && p.getCol() == cl && p.canMoveTo(pos, b))
						return p;
				}
			}
		return null;
	}
	public static void Decode(String code, Board b){//decodifica o mutare primita de la xboard
		char []move = code.toCharArray();
		int lung = code.length();
		char tip;
		Piesa p = null;
		Position pos;
		/*if(move[lung-1]=='+'){
			((Rege)Rules.getKing(b, Game.getActivePlayer() * (-1))).isInCheck=true;
		}
		else 
			((Rege)Rules.getKing(b, Game.getActivePlayer() * (-1))).isInCheck=false;*/
		if (move[lung - 1] == '#'){
			try {
				Comunicare.input();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (Character.isLowerCase(move[0])){//pion
			if (move[1] == 'x'){//pionul captureaza o piesa
				pos = new Position(move[3] - '0', move[2] - 'a' + 1);
				p = findPiece(b, pos, 0, move[0] - 'a' + 1, Game.getActivePlayer(), 'p');
				if (p == null){
					//nu a fost gasit pionul.
					Game.write("Illegal move: " + code);
				}
				else
					b.move(p,pos);
				if (lung>4&&move[4] == '='){
					p = b.getPieceOnPosition(pos);
					b.promote(p, Game.getActivePlayer(), move[5]);
				}
			}
			else{
				pos = new Position(move[1] - '0', move[0] - 'a' + 1);
				p = findPiece(b, pos, 0, 0, Game.getActivePlayer(), 'p');
				if (p != null){
					b.move(p,pos);
				}
				else{
					Game.write("Illegal move: " +code);
					//mutare invalida
				}
				if (lung >= 4 && move[2] == '='){
					p = b.getPieceOnPosition(pos);
					b.promote(p,Game.getActivePlayer(),move[3]);
				}
			}
		}
		else{//alta piesa
			if (code.compareTo("O-O-O") == 0)
				((Rege)Rules.getKing(b, Game.getActivePlayer())).bigCastling(b);
			else
				if (code.compareTo("O-O") == 0)
					((Rege)Rules.getKing(b, Game.getActivePlayer())).smallCastling(b);
				else{
					tip = move[0];
					if (code.contains("x")){//mutare care captureaza o piesa
						if (move[1] == 'x'){//mutare neambigua
							pos = new Position(move[3] - '0', move[2] - 'a' + 1);
							p = findPiece(b,pos, 0, 0, Game.getActivePlayer(),tip);
							if (p != null)
								b.move(p,pos);
							else
								Game.write("Illegal move: " +code);
						}
						else if (lung > 3 && move[2] == 'x'){//se specifica coloana sau linia piesei
							pos = new Position(move[4] - '0', move[3] - 'a' + 1);
							if (Character.isLowerCase(move[1])){//se specifica coloana
								p = findPiece(b, pos, 0, move[1] - 'a' + 1, Game.getActivePlayer(), tip);
								if (p != null)
									b.move(p,pos);
								else
									Game.write("Illegal move: " +code);
							}
							else if (Character.isDigit(move[1])){//se specifica linia
								p = findPiece(b, pos, move[1] - '0', 0, Game.getActivePlayer(), tip);
								if (p != null)
									b.move(p,pos);
								else
									Game.write("Illegal move: " +code);
							}
						}
						else if (lung > 4 && move[3] == 'x'){//se specifica coloana si linia piesei
							pos = new Position(move[5] - '0', move[4] - 'a' + 1);
							p = b.getPieceOnCoord(move[2] - '0', move[1] - 'a' + 1);
							if (p != null)
								b.move(p,pos);
							else{
								Game.write("Illegal move: " +code);
								//piesa inexistenta
							}
						}
					}
					else{//nu se captureaza nimic
						if (Character.isLetter(move[1]))
								if (Character.isLetter(move[2])){//se specifica coloana pe care se afla piesa
									pos = new Position(move[3] - '0', move[2] - 'a' + 1);
									p = findPiece(b, pos, 0, move[1] - 'a' + 1, Game.getActivePlayer(), tip);
									if (p != null)
										b.move(p,pos);
									else
										Game.write("Illegal move: " +code);
								}
								else if (lung >= 4 && Character.isLetter(move[3])){//se specifica linia si coloana pe care se afla piesa
									pos = new Position(move[4] - '0', move[3] - 'a' + 1);
									p = b.getPieceOnCoord(move[2] - '0', move[1] - 'a' + 1);
									if (p != null)
										b.move(p,pos);
									else
										Game.write("Illegal move: " +code);
								}
								else{//mutare neambigua
									pos = new Position(move[2] - '0', move[1] - 'a' + 1);
									p = findPiece(b, pos, 0, 0, Game.getActivePlayer(), tip);
									if (p != null)
										b.move(p,pos);
									else
										Game.write("Illegal move: " +code);
								}
						else{//se specifica linia pe care se afla piesa
							pos = new Position(move[3] - '0', move[2] - 'a' + 1);
							p = findPiece(b, pos, move[1] - '0', 0, Game.getActivePlayer(), tip);
							if (p != null)
								b.move(p,pos);
							else
								Game.write("Illegal move: " +code);
						}
					}
				}
		}
		Game.changeActivePlayer();
	}
	public static String Code(Board b, Piesa p, Position prev, boolean captures){//codifica o mutare pe care o trimite catre xboard
		char mov[] = new char[10];
		int l = 0;
		if (p.hashCode() == 'p'){//pion
			if (captures){//adauga coloana si 'x'
				mov[l++] = (char)(prev.coloana + 'a' -1);
				mov[l++] = 'x';
			}
			mov[l++] = (char)(p.getCol() + 'a' - 1);
			mov[l++] = (char)(p.getLin() + '0');
			if (Rules.isInCheck(b, Rules.getKing(b, p.Color * (-1)))){
				mov[l++] = '+';//pionul da sah
			}
		}
		else{
			mov[l++] = (char)p.hashCode();
			int c,lin,g;
			
			c = uniquePiece(b, prev.coloana, p.Color, p.p,(char)p.hashCode());
			lin = uniquePiece(b, p.Color, p.p, prev.linie, (char)p.hashCode());
			g = uniquePiece(b, p.p, p.Color, (char)p.hashCode());
			if ( g != 0){
				if ((c == 0) || lin != 0)//trebuie adaugata coloana
					mov[l++] = (char)(prev.coloana + 'a' - 1);
				if (c != 0)//trebuie adaugata linia
					mov[l++] = (char)(prev.linie + '0');
			}
			if (captures)
				mov[l++] = 'x';
			mov[l++] = (char)(p.getCol() + 'a' - 1);
			mov[l++] = (char)(p.getLin() + '0');
			if (Rules.isInCheck(b, Rules.getKing(b, p.Color * (-1)))){
				mov[l++] = '+';//piesa da sah
			}
		}
		String s = new String();
		for (int i = 0; i < l; i++)
			s += Character.toString(mov[i]);
		Game.changeActivePlayer();
		return s;
	}
}
