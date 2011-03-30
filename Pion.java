import java.util.ArrayList;
import java.util.Random;


public class Pion extends Piesa{
	public Pion(Position po,int clr){
		p=po;
		Color=clr;
	}

	@Override
	boolean canMoveTo(Position pn, Board b) {
		if(!pn.chessOK()) return false;
		if(!b.isFree(pn)){//daca e ocupata pozitia verificam culoarea
			if(pn.coloana==p.coloana) return false; //verific sa nu incerce captura pe linia de mers
			if(b.getPieceOnPosition(pn).Color==this.Color)
				return false;
			else
				if (pn.linie == p.linie + this.Color && Math.abs(pn.coloana-p.coloana) == 1)
						return true;
				else
					return false;
		}
		
		else{
			if(pn.coloana!=p.coloana){//daca miscarea nu e pe aceeasi coloana
				return false;
			}
			else{
				if (pn.linie == p.linie + this.Color)
						return true;
				else{
					if ((this.Color == ChessColors.Black && p.linie == 7) || (this.Color == ChessColors.White && p.linie == 2))
						if ((pn.linie == p.linie + 2 * this.Color) && (b.isFree(p.linie + this.Color, p.coloana)))
								return true;
						else
							return false;
					else
						return false;
				}
			}
		}
	}

	@Override
	Position fastMove(Board b) {
		ArrayList<Position> ap= new ArrayList<Position>();
		Position po=new Position(p.linie,p.coloana);
		int[] dl={this.Color,this.Color,this.Color, 2*this.Color};
		int[] dc={-1, +1, 0, 0};
		Random r=new Random();
		for(int i=0;i<dl.length;i++){
			po.linie=po.linie+dl[i];
			po.coloana=po.coloana+dc[i];
			if(this.canMoveTo(po, b) && !this.possibleCheck(po, b)){
				ap.add(new Position(po.linie, po.coloana));
			}
			po.linie=p.linie;
			po.coloana=p.coloana;
		}
		if(ap.size()>0)
			return ap.get(r.nextInt(ap.size()));
		return null;
			
	}

	@Override
	public int hashCode() {
		return 'p';
	}

	@Override
	ArrayList<Position> genMoves(Board b) {
		ArrayList<Position> ap= new ArrayList<Position>();
		Position po=new Position(p.linie,p.coloana);
		int[] dl={this.Color,this.Color,this.Color, 2*this.Color};
		int[] dc={-1, +1, 0, 0};
		Random r=new Random();
		for(int i=0;i<dl.length;i++){
			po.linie=po.linie+dl[i];
			po.coloana=po.coloana+dc[i];
			if(this.canMoveTo(po, b) && !this.possibleCheck(po, b)){
				ap.add(new Position(po.linie, po.coloana));
			}
			po.linie=p.linie;
			po.coloana=p.coloana;
		}
		return ap;
	}
	
}
