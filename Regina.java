import java.util.ArrayList;
import java.util.Random;


public class Regina extends Piesa {
	public Regina(Position po,int clr){
		p=po;
		Color=clr;
	}
	  boolean canMoveToRook(Position p,Board b) {
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
	boolean canMoveToBish(Position p,Board b) {
		if (!p.chessOK()) return false;//pozitia este in limitele tablei
		int sum=this.getLin()+this.getCol();
		int dif=this.getLin()-this.getCol();
		int posum=p.coloana+p.linie;
		int podif=p.linie-p.coloana;
		if (sum!=posum && dif!=podif)//mutarea se face pe diagonala
			return false;
		Piesa piece = b.getPieceOnPosition(p);
		if (piece!=null)
			if (piece.Color == this.Color)//exista o piesa de aceeasi culoare pe pozitia de destinatie
				return false;
		//cautare pe path
		int d1;
		if(sum==posum){
			//diagonala 1
			if(p.linie>this.getLin()){
				//mergem in sus pe diagonala 1
				d1=p.linie-this.getLin();
				for(int i=1;i<d1;i++){
					if(!b.isFree(this.getLin()+i, this.getCol()-i)) return false;
				}
			}
			else{
				//mergem in jos pe diagonala 1
				d1=this.getLin()-p.linie;
				for(int i=1;i<d1;i++){
					if(!b.isFree(this.getLin()-i, this.getCol()+i)) return false;
				}
			}
		}
		else{
			//diagonala 2
			if(p.linie>this.getLin()){
				//mergem in sus pe diagonala 2
				d1=p.linie-this.getLin();
				for(int i=1;i<d1;i++){
					if(!b.isFree(this.getLin()+i, this.getCol()+i)) return false;
				}
			}
			else{
				//mergem in jos pe diagonala 2
				d1=this.getLin()-p.linie;
				for(int i=1;i<d1;i++){
					if(!b.isFree(this.getLin()-i, this.getCol()-i)) return false;
				}
			}
		}

		return true;//se respecta toate conditiile
	}
	@Override
	boolean canMoveTo(Position p,Board b) {
		return this.canMoveToRook(p, b) || this.canMoveToBish(p, b);
	}
	@Override
	Position fastMove(Board b) {
		ArrayList<Position> pm=new ArrayList<Position>();
		Random r=new Random();
		Position p;
		int v[]={0,1,-1};
		for(int k=1;k<9;k++){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(i!=0 || j!=0){
						p=new Position(getLin()+v[i]*k,getCol()+v[j]*k);
						if(canMoveTo(p, b) && !this.possibleCheck(p, b)){
							pm.add(p);
						}
					}
				}
			}
			if(pm.size()>5){
				break;
			}
		}
		if(pm.size()>0){
			return pm.get(r.nextInt(pm.size()));
		}
		return null;
	}
	@Override
	public int hashCode() {
		return 'Q';
	}
	@Override
	ArrayList<Position> genMoves(Board b) {
		ArrayList<Position> pm=new ArrayList<Position>();
		Random r=new Random();
		Position p;
		int v[]={0,1,-1};
		for(int k=1;k<9;k++){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(i!=0 || j!=0){
						p=new Position(getLin()+v[i]*k,getCol()+v[j]*k);
						if(canMoveTo(p, b) && !this.possibleCheck(p, b)){
							pm.add(p);
						}
					}
				}
			}
			if(pm.size()>5){
				break;
			}
		}
		return pm;
	}

}
