import java.util.*;


public class Tura extends Piesa{
	public Tura(Position po,int clr){
		p=po;
		Color=clr;
	}
	@Override
	  boolean canMoveTo(Position p,Board b) {
        if (!p.chessOK()) return false;//pozitia este in limitele tablei
        if (this.getLin() != p.linie && this.getCol() != p.coloana)//mutarea se face pe aceeasi linie sau coloana
            return false;
        Piesa piece = b.getPieceOnPosition(p);
        if (piece!=null)
            if (piece.Color == this.Color)//exista o piesa de aceeasi culoare pe pozitia de destinatie
                return false;
        int i,j;
        if (this.getLin() == p.linie){//verifica daca nu este blocata calea pana la destinatie
            if (this.getCol() < p.coloana)//ma uit pe coloana
                j = 1;
            else
                j = -1;
            for (i = this.getCol() + j; j * i <= j * (p.coloana - j); i += j)
                if (!b.isFree(p.linie, i))
                    return false;
        }
        if (this.getCol() == p.coloana){//verifica daca nu este blocata calea pana la destinatie
            if (this.getLin() < p.linie)//ma uit pe linie
                j = 1;
            else
                j = -1;
            for (i = this.getLin() + j; j * i <= j * (p.linie - j); i += j)
                if (!b.isFree(i, p.coloana))
                    return false;
        }

        return true;//se respecta toate conditiile
    }
	@Override
	Position fastMove(Board b) {
		ArrayList<Position> ap=new ArrayList<Position>();
		Random r=new Random();
		int nrLSt=0,nrLDr=0;
		int nrCSus=0,nrCJos=0;
		for(int i=1;i<9;i++){
			Position po=new Position(i,p.coloana);
			if(this.canMoveTo(po, b) && !this.possibleCheck(po, b))
				ap.add(po);
		}
		for(int j=1;j<9;j++){
			Position po=new Position(p.linie,j);
			if(this.canMoveTo(po, b) && !this.possibleCheck(po, b))
				ap.add(po);
		}
		if(ap.size()>0)
			return ap.get(r.nextInt(ap.size()));
		return null;
	}
	@Override
	public int hashCode() {
		return 'R';
	}
	@Override
	ArrayList<Position> genMoves(Board b) {
		ArrayList<Position> ap=new ArrayList<Position>();
		Random r=new Random();
		int nrLSt=0,nrLDr=0;
		int nrCSus=0,nrCJos=0;
		for(int i=1;i<9;i++){
			Position po=new Position(i,p.coloana);
			if(this.canMoveTo(po, b) && !this.possibleCheck(po, b))
				ap.add(po);
		}
		for(int j=1;j<9;j++){
			Position po=new Position(p.linie,j);
			if(this.canMoveTo(po, b) && !this.possibleCheck(po, b))
				ap.add(po);
		}
		return ap;
	}

}
