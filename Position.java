
class Position {
	int linie;
	int coloana;
	static private String s="abcdefgh";
	public Position(int l,int c){
		linie=l;
		coloana=c;
	}
	public boolean chessOK(){//verifica daca se incadreaza in limitele indicilor
		if(this.coloana<1 || this.coloana>8 || this.linie<1 || this.linie>8){
			return false;
		}
		return true;
	}
	static public boolean chessOK(Position p){
		if(p.coloana<1 || p.coloana>8 || p.linie<1 || p.linie>8){
			return false;
		}
		return true;
	}
	static public boolean chessOK(int linie,int coloana){
		if(coloana<1 || coloana>8 || linie<1 || linie>8){
			return false;
		}
		return true;
	}
	public String toAlgNotation(){//genereaza notatia de tip "c7" din indicii de pe tabla
		if(chessOK()){
		return Character.toString(s.charAt(coloana-1))+linie;
		}
		return null;
	}
	static Position NewPosFromNotation(String nt){//creeaza un obiect de tip pozitie de la un string algebric notation
		char col=nt.charAt(0);
		char lin=nt.charAt(1);
		return new Position((lin-'0'),col-'a');
	}
	static Position reverseBoard(Position p){
		//transforma coordonatele de la AlbeJos la NegreJos si invers;
			return new Position(8-p.linie + 1,p.coloana);
	}
}
