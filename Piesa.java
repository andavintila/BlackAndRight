import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


@SuppressWarnings("unchecked")
public abstract class Piesa implements Comparable {
	Position p;
	int Color;
	int Value;//va fi implementat pentru algoritm
	/* Declaratii Abstracte */
	abstract boolean canMoveTo(Position p,Board b);
	abstract ArrayList<Position> genMoves(Board b);
	abstract Position fastMove(Board b);
	public abstract int hashCode();
	
	int getCol(){
		return p.coloana;
	}
	int getLin(){
		return p.linie;
	}
	void changePosition(Position p1){//schimba pozitia direct
		p=p1;
	}
	void spawn(Position p1,Board b){//reinvie o piesa intr-o pozitie
		if(b.isFree(p1)){
			b.placa_piese[p1.linie][p1.coloana]=this;
		}
	}
	void respawn(Position p1,Board b){//reinvie o piesa intr-o pozitie
		if(b.isFree(p1)){
			this.p=p1;
			b.placa_piese[p1.linie][p1.coloana]=this;
			if (this.Color == ChessColors.White)
				b.pieseAlbe.add(this);
			else
				b.pieseNegre.add(this);
		}
	}
	
	public int compareTo(Object o){
		return this.Value-((Piesa)o).Value;
	}
	
	public static Piesa createPiece(Position pos, int color, char tip){
		switch (tip){
		case 'R': return new Tura(pos, color);
		case 'K': return new Cal(pos, color);
		case 'B': return new Nebun(pos, color);
		case 'Q': return new Regina(pos, color);
		case 'N': return new Cal(pos, color);
		default: return null;
		}
	}
	boolean wasTaken(Board b){
		if(this.Color==ChessColors.Black){
			return b.pieseNegre.contains(this);
		}
		else{
			return b.pieseAlbe.contains(this);
		}
	}
	boolean canBeTaken(Board b){
		Iterator<Piesa> it;
		if (this.Color==ChessColors.Black){
			it=b.pieseAlbe.iterator();
			while(it.hasNext()){
				if(it.next().canMoveTo(this.p, b)){
					return true;
				}
			}
		}
		else{
			it=b.pieseNegre.iterator();
			while(it.hasNext()){
				if(it.next().canMoveTo(this.p, b)){
					return true;
				}
			}
		}
		return false;
	}
	HashSet<Piesa> getPossibleTakers(Board b){
		HashSet<Piesa> hs=new HashSet<Piesa>();
		Iterator<Piesa> it;
		Piesa cp;
		if (this.Color==ChessColors.Black){
			it=b.pieseAlbe.iterator();
			while(it.hasNext()){
				cp=it.next();
				if(cp.canMoveTo(this.p, b)){
					hs.add(cp);
				}
			}
		}
		else{
			it=b.pieseNegre.iterator();
			while(it.hasNext()){
				cp=it.next();
				if(cp.canMoveTo(this.p, b)){
					hs.add(cp);
				}
			}
		}
		return hs;
	}
	boolean possibleCheck(Position p,Board b){
		Piesa captura = b.getPieceOnPosition(p);//simuleaza mutarea piesei
		b.placa_piese[p.linie][p.coloana] = null;
		Position poz_curent = this.p;//apoi verifica daca din noua pozitie
		b.move(this, p);
		//regele de aceeasi culoare intra in sah
		if (Rules.isInCheck(b, Rules.getKing(b, this.Color))){//prin mutarea piesei, regele intra in sah
			//revine la pozitia initiala
			b.move(this, poz_curent);
			if (captura != null)
				captura.spawn(p, b);//revine la pozitia initiala
			return true;
		}
		b.move(this, poz_curent);//revine la pozitia initiala
		if (captura != null)
			captura.spawn(p, b);//revine la pozitia initiala
		return false;
	}
}
