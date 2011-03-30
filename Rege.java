import java.util.ArrayList;
import java.util.Random;


public class Rege extends Piesa {
	public Rege(Position po,int clr){
		p=po;
		Color=clr;
	}
	
	boolean bigCastling(Board b){
		Position pRege=new Position(this.getLin(),this.getCol()-2);
		Position pTura=new Position(this.getLin(),this.getCol()-1);
		Tura tura = (Tura)b.getPieceOnCoord(this.getLin(),this.getCol()-4);
		b.move(this, pRege);
		b.move(tura, pTura);
		return true;
	}
	boolean smallCastling(Board b){
		Position pRege=new Position(this.getLin(),this.getCol()+2);
		Position pTura=new Position(this.getLin(),this.getCol()+1);
		Tura tura = (Tura)b.getPieceOnCoord(this.getLin(),this.getCol()+3);
		b.move(this, pRege);
		b.move(tura, pTura);
		return true;
	}
	
	@Override
	boolean canMoveTo(Position pos,Board b) {
		if (!pos.chessOK()) return false;
		if (Math.abs(this.p.linie - pos.linie) > 1 || Math.abs(this.p.coloana - pos.coloana) > 1)
				return false;
		if (b.getPieceOnPosition(pos) != null && b.getPieceOnPosition(pos).Color == Color)
				return false;
		return true;
	}
	@Override
	Position fastMove(Board b) {
		ArrayList<Position> pm=new ArrayList<Position>();
		Position po;
		int v[]={0,-1,1};
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(i!=0 || j!=0){
					po=new Position(getLin()+v[i],getCol()+v[j]);
					if(canMoveTo(po, b) && !this.possibleCheck(po, b)){
						pm.add(po);
					}
				}
			}
		}
		if(pm.size()>0){
			Random r=new Random();
			return pm.get(r.nextInt(pm.size()));
		}
		return null;
	}
	@Override
	public int hashCode() {
		return 'K';
	}

	@Override
	ArrayList<Position> genMoves(Board b) {
		ArrayList<Position> pm=new ArrayList<Position>();
		Position po;
		int v[]={0,-1,1};
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(i!=0 || j!=0){
					po=new Position(getLin()+v[i],getCol()+v[j]);
					if(canMoveTo(po, b) && !this.possibleCheck(po, b)){
						pm.add(po);
					}
				}
			}
		}
		return pm;
	}
	
}
