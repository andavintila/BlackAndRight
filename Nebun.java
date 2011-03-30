import java.util.ArrayList;
import java.util.Random;


public class Nebun extends Piesa{
	public Nebun(Position po,int clr){
		p=po;
		Color=clr;
	}
	@Override
	boolean canMoveTo(Position pos,Board b) {
		if (!pos.chessOK()) return false;//pozitia este in limitele tablei
		int sum=this.getLin()+this.getCol();
		int dif=this.getLin()-this.getCol();
		int posum=pos.coloana+pos.linie;
		int podif=pos.linie-pos.coloana;
		if (sum!=posum && dif!=podif)//mutarea se face pe diagonala
			return false;
		Piesa piece = b.getPieceOnPosition(pos);
		if (piece!=null)
			if (piece.Color == this.Color){//exista o piesa de aceeasi culoare pe pozitia de destinatie
				return false;
			}
		//cautare pe path
		int d1;
		if(sum == posum){
			//diagonala 1
			if(pos.linie>this.getLin()){
				//mergem in sus pe diagonala 1
				d1=pos.linie-this.getLin();
				for(int i=1;i<d1;i++){
					if(!b.isFree(this.getLin()+i, this.getCol()-i)) return false;
				}
			}
			else{
				//mergem in jos pe diagonala 1
				d1=this.getLin()-pos.linie;
				for(int i = 1; i < d1; i++){
					if(!b.isFree(this.getLin()-i, this.getCol()+i)) return false;
				}
			}
		}
		else{
			//diagonala 2
			if(pos.linie>this.getLin()){
				//mergem in sus pe diagonala 2
				d1=pos.linie-this.getLin();
				for(int i=1;i<d1;i++){
					if(!b.isFree(this.getLin()+i, this.getCol()+i)) return false;
				}
			}
			else{
				//mergem in jos pe diagonala 2
				d1=this.getLin()-pos.linie;
				for(int i=1;i<d1;i++){
					if(!b.isFree(this.getLin()-i, this.getCol()-i)) return false;
				}
			}
		} 
		return true;//se respecta toate conditiile
	}
	@Override
	Position fastMove(Board b) {
		ArrayList<Position> possible_moves=new ArrayList<Position>();
		//caut mutari posibile pe diagonala 1 in sus
		int i;
		Random r=new Random();
		
		for(i=1;Position.chessOK(getLin()+i, getCol()+i) && this.canMoveTo(new Position(getLin()+i,getCol()+i), b);i++){
			if (!this.possibleCheck(new Position(getLin()+i, getCol()+i), b))
				possible_moves.add(new Position(getLin()+i,getCol()+i));			
		}
		
		for(i=1;Position.chessOK(getLin()-i, getCol()-i) && this.canMoveTo(new Position(getLin()-i,getCol()-i), b);i++){
			if (!this.possibleCheck(new Position(getLin()-i, getCol()-i), b))
				possible_moves.add(new Position(getLin()-i,getCol()-i));			
		}
		
		for(i=1;Position.chessOK(getLin()-i, getCol()+i) && this.canMoveTo(new Position(getLin()-i,getCol()+i), b);i++){
			if (!this.possibleCheck(new Position(getLin()-i, getCol()+i), b))
				possible_moves.add(new Position(getLin()-i,getCol()+i));
		}
		
		for(i=1;Position.chessOK(getLin()+i, getCol()-i) && this.canMoveTo(new Position(getLin()+i,getCol()-i), b);i++){
			if (!this.possibleCheck(new Position(getLin()+i, getCol()-i), b))
				possible_moves.add(new Position(getLin()+i,getCol()-i));			
		}
		
		if(possible_moves.size()>0){
			return possible_moves.get(r.nextInt(possible_moves.size()));
		}
		return null;
	}
	@Override
	public int hashCode() {
		return 'B';
	}
	@Override
	ArrayList<Position> genMoves(Board b) {
		ArrayList<Position> possible_moves=new ArrayList<Position>();
		
		//caut mutari posibile pe diagonala 1 in sus
		int i;
		Random r=new Random();

		for(i=1;Position.chessOK(getLin()+i, getCol()+i) && this.canMoveTo(new Position(getLin()+i,getCol()+i), b);i++){
			if (!this.possibleCheck(new Position(getLin()+i, getCol()+i), b))
				possible_moves.add(new Position(getLin()+i,getCol()+i));			
		}
		
		for(i=1;Position.chessOK(getLin()-i, getCol()-i) && this.canMoveTo(new Position(getLin()-i,getCol()-i), b);i++){
			if (!this.possibleCheck(new Position(getLin()-i, getCol()-i), b))
				possible_moves.add(new Position(getLin()-i,getCol()-i));			
		}
		
		for(i=1;Position.chessOK(getLin()-i, getCol()+i) && this.canMoveTo(new Position(getLin()-i,getCol()+i), b);i++){
			if (!this.possibleCheck(new Position(getLin()-i, getCol()+i), b))
				possible_moves.add(new Position(getLin()-i,getCol()+i));
		}
	
		for(i=1;Position.chessOK(getLin()+i, getCol()-i) && this.canMoveTo(new Position(getLin()+i,getCol()-i), b);i++){
			if (!this.possibleCheck(new Position(getLin()+i, getCol()-i), b))
				possible_moves.add(new Position(getLin()+i,getCol()-i));			
		}
		return possible_moves;
	}

}
