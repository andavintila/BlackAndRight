import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class Board {
	Piesa[][] placa_piese=new Piesa[9][9];
	int[][] st_value=new int[9][9];
	HashSet<Piesa>pieseAlbe = new HashSet<Piesa>();
	HashSet<Piesa>pieseNegre = new HashSet<Piesa>();
	boolean isFree(Position p){
		return placa_piese[p.linie][p.coloana]==null;
	}
	boolean isFree(int x, int y){
		return placa_piese[x][y]==null;
	}
	private void updateBoard(){
		Piesa p;
		Iterator<Piesa> it = pieseAlbe.iterator();
		while(it.hasNext()){
			p = it.next();
			placa_piese[p.getLin()][p.getCol()] = p;
		}
		it = pieseNegre.iterator();
		while(it.hasNext()){
			p = it.next();
			placa_piese[p.getLin()][p.getCol()] = p;
		}
	}
	void initBoard(){
		pieseAlbe.add(new Rege(new Position(1,5),ChessColors.White));
		pieseNegre.add(new Rege(new Position(8,5),ChessColors.Black));
		pieseAlbe.add(new Regina(new Position(1,4),ChessColors.White));
		pieseNegre.add(new Regina(new Position(8,4),ChessColors.Black));
		pieseAlbe.add(new Nebun(new Position(1,6),ChessColors.White));
		pieseAlbe.add(new Nebun(new Position(1,3),ChessColors.White));
		pieseNegre.add(new Nebun(new Position(8,6),ChessColors.Black));
		pieseNegre.add(new Nebun(new Position(8,3),ChessColors.Black));
		pieseAlbe.add(new Cal(new Position(1,7),ChessColors.White));
		pieseAlbe.add(new Cal(new Position(1,2),ChessColors.White));
		pieseNegre.add(new Cal(new Position(8,7),ChessColors.Black));
		pieseNegre.add(new Cal(new Position(8,2),ChessColors.Black));
		pieseAlbe.add(new Tura(new Position(1,8),ChessColors.White));
		pieseAlbe.add(new Tura(new Position(1,1),ChessColors.White));
		pieseNegre.add(new Tura(new Position(8,8),ChessColors.Black));
		pieseNegre.add(new Tura(new Position(8,1),ChessColors.Black));
		for(int i=8;i<16;i++){
			pieseNegre.add(new Pion(new Position(7,i-8+1),ChessColors.Black));
			pieseAlbe.add(new Pion(new Position(2,i-8+1),ChessColors.White));
		}
		updateBoard();
	}
	Piesa getPieceOnPosition(Position p){//intorce referinta spre piesa din pozitia p
		if(p.chessOK()){
			return placa_piese[p.linie][p.coloana];
		}
		return null;
	}
	Piesa getPieceOnCoord(int x,int y){
		if (x < 1 || x > 8 || y < 1 || y > 8)
			return null;
		return placa_piese[x][y];
	}
	void move(Piesa pi,Position p){
			if(isFree(p)){
				//simple move
				placa_piese[pi.getLin()][pi.getCol()]=null; //ridic piesa
				placa_piese[p.linie][p.coloana]=pi;//mut piesa
				pi.changePosition(p);
			}
			else{
				//try take move
				Piesa ps=placa_piese[p.linie][p.coloana];
				if(pi.Color!=ps.Color){//verific sa aiba culori diferite
					take(ps);
					ps = null;
					placa_piese[pi.getLin()][pi.getCol()]=null; //ridic piesa
					placa_piese[p.linie][p.coloana]=pi;//mut piesa
					pi.changePosition(p);
				}
			}
	}
	void take(Piesa p){
		if(p.Color==ChessColors.Black){
			pieseNegre.remove(p); 
		}
		else{
			pieseAlbe.remove(p);
		}
		this.placa_piese[p.getLin()][p.getCol()]=null;
	}
	void printBoard(String code){
		try {
			FileWriter fw = new FileWriter("out2.txt",true);
			fw.write(code + "\n");
			Iterator<Piesa>it;
			it = pieseAlbe.iterator();
			while (it.hasNext())
				fw.write((char)(it.next().hashCode()));
			fw.write("\n");
			it = pieseNegre.iterator();		
			while (it.hasNext())
				fw.write((char)(it.next().hashCode()));
			fw.write("\n");
			for(int i=8;i>=1;i--){
				for(int j=1;j<=8;j++){
					if(placa_piese[i][j]==null){
						fw.write(" . ");
					}
					else{
						if(placa_piese[i][j].Color==ChessColors.Black){
							fw.write("b");
						}
						else{
							fw.write("w");
						}
						if(placa_piese[i][j] instanceof Rege){
							fw.write("K ");
						}
						if(placa_piese[i][j] instanceof Regina){
							fw.write("Q ");
						}
						if(placa_piese[i][j] instanceof Pion){
							fw.write("p ");
						}
						if(placa_piese[i][j] instanceof Cal){
							fw.write("N ");
						}
						if(placa_piese[i][j] instanceof Nebun){
							fw.write("B ");
						}
						if(placa_piese[i][j] instanceof Tura){
							fw.write("R ");
						}
					}
				}
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	HashSet<Piesa> validWhites(){
		return pieseAlbe;
	}
	HashSet<Piesa> validBlacks(){
		return pieseNegre;
	}
	Piesa promote(Piesa p, int color, char rang){//promoveaza un pion
		Position pos = p.p;
		Piesa noua = null;
		if (color == ChessColors.White){
				take(p);
				noua = Piesa.createPiece(pos, color, rang);
				pieseAlbe.add(noua);
				placa_piese[pos.linie][pos.coloana] = noua;
		}
		if (color == ChessColors.Black){
				take(p);
				noua = Piesa.createPiece(pos, color, rang);
				pieseNegre.add(noua);
				placa_piese[pos.linie][pos.coloana] = noua;
		}
		return noua;
	}
	ArrayList<Piesa> selectPiecesOfType(int h,int color){
		ArrayList<Piesa> ar = new ArrayList<Piesa>();
		Iterator<Piesa> it;
		Piesa p;
		if (color == ChessColors.Black){
			it = pieseNegre.iterator();
		}
		else
			it = pieseAlbe.iterator();
		while (it.hasNext()){
			p = it.next();
			if (p.hashCode() == h)
				ar.add(p);
		}
		return ar;
	}
}
