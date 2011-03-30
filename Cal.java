import java.util.ArrayList;
import java.util.Random;


public class Cal extends Piesa {
	public Cal(Position po,int clr){
		p=po;
		Color=clr;
	}
	@Override
	boolean canMoveTo(Position po,Board b) {
		if(!po.chessOK()) return false;
		Piesa pi=b.getPieceOnPosition(po);
		if(b.isFree(po)){
			if(Math.abs(this.p.coloana-po.coloana)==2)
				return Math.abs(this.p.linie-po.linie)==1 /*&& !this.possibleCheck(po, b)*/;
			if(Math.abs(this.p.coloana-po.coloana)==1)
				return Math.abs(this.p.linie-po.linie)==2 /*&& !this.possibleCheck(po, b)*/;
			return false;
		}
		else{
			if (b.getPieceOnPosition(po).Color!=Color)
				if(Math.abs(this.p.coloana-po.coloana)==2)
					return Math.abs(this.p.linie-po.linie)==1 && pi.Color!=Color /*&& !this.possibleCheck(po, b)*/;
				if(Math.abs(this.p.coloana-po.coloana)==1)
					return Math.abs(this.p.linie-po.linie)==2 && pi.Color!=Color /*&& !this.possibleCheck(po, b)*/;
			return false;		
		}
	}
	@Override
	ArrayList<Position> genMoves(Board b){
		int[] dl={1,1,-1,-1,2,2,-2,-2};
		int[] dc={2,-2,2,-2,1,-1,1,-1};
		ArrayList<Position>ap=new ArrayList<Position>();
		Position po=new Position(p.linie,p.coloana);
		for(int i=0;i<8;i++){
			po.linie=po.linie+dl[i];
			po.coloana=po.coloana+dc[i];
			if(this.canMoveTo(po, b) && !this.possibleCheck(po,b)){
				ap.add(new Position(po.linie,po.coloana));
			}
			po.linie=p.linie;
			po.coloana=p.coloana;
		}
		return ap;
	}
	
	@Override
	Position fastMove(Board b) {
		int[] dl={1,1,-1,-1,2,2,-2,-2};
		int[] dc={2,-2,2,-2,1,-1,1,-1};
		ArrayList<Position> ap=new ArrayList<Position>();
		Position po=new Position(p.linie,p.coloana);
		for(int i=0;i<8;i++){
			po.linie=po.linie+dl[i];
			po.coloana=po.coloana+dc[i];
			if(this.canMoveTo(po, b) && !this.possibleCheck(po,b)){
				ap.add(new Position(po.linie,po.coloana));
			}
			po.linie=p.linie;
			po.coloana=p.coloana;
		}
		if(ap.size()==0) return null;
		Random r=new Random();
		return ap.get(r.nextInt(ap.size()));
	}
	@Override
	public int hashCode() {
		return 'N';
	}

}
