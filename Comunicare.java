
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


class Comunicare {
	
	
	public static void input() throws IOException{
		System.out.println("feature san=1");		//format SAN pt mutari
		System.out.println("feature usermove=1");	//append username to move command received from winboard
		System.out.println("feature done=1");
		
		BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
		
		Game g=null;
		String comanda;
		String first_move=null;
		boolean two_eng=false;
		do{
			try {
			comanda=in.readLine();
			if(!comanda.isEmpty()){
				if(comanda.equals("computer")) two_eng=true;
				else if(comanda.equals("quit")){
					
					break;
				}
				else if(comanda.equals("new")){
					// setare/resetare tabla, piese, STARE JOC, etc.
					g=new Game(); 
				}
				else if(comanda.equals("white")){
					//functie_din_game_init_joc(cul_white);
					//cul_white=culoarea noastra
					g=new Game(ChessColors.White);
				}
				else if(comanda.equals("black")){
					//functie_din_game_init_joc(cul_black);
					//cul_black=culoarea noastra
					g=new Game(ChessColors.Black);
				}
				else if(comanda.equals("go")){
					if(two_eng==true){
					//functie_din_game_mutare_adversar(first_move);
						if(Game.getMyColor()==Game.getActivePlayer())
							output("move "+g.getNextMove());
						else{
							g.processMove(first_move);
							output("move "+g.getNextMove());
						}
						two_eng=false;
					}
					else {//culoarea a fost setata anterior
						output("move "+g.getNextMove());
					}
					
				}
				else if(comanda.startsWith("usermove")){
					//trimite unei fc mutarea
					String[] mutare = comanda.split(" ");
					if(two_eng==true) first_move=mutare[1];
					
					else{
						g.processMove(mutare[1]);//Pasez mutarea in format SAN unei functii
						output("move "+g.getNextMove());
					}
				}
				else if(comanda.equals("xboard")){
					System.out.println("Ready"); //practic nu fac nimic
				}
				else if(comanda.equals("force")){
					//dezactivarea oricarei fc a motorului, mai putin verificarea validitatii mutarilor
					Game.setState(comanda);								  
					//ceasurile se opresc
					//inca se primesc mutari si se realizeaza pe tabla interna
				}
				else if(comanda.startsWith("time")){
					String[] time=comanda.split(" ");
					float t=Float.valueOf(time[1]).floatValue();
					Game.setMyTime(t);
					
				}
				else if(comanda.startsWith("otime")){
					String[] time=comanda.split(" ");
					float t=Float.valueOf(time[1]).floatValue();
					Game.setOTime(t);
					
				}
				
			}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}while(true);
	}
	
	//id se foloseste pentru diferite tipuri de iesiri (mutare, mutare ilegala, resign, etc)
	public static void output(String s){
		
		System.out.println(s);
		
	}
	
}
