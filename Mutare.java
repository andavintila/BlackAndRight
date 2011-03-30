
/**  Clasa auxiliara ce retine o piesa si o pozitie unde va fi mutata piesa.
*    Utila pentru a trimite clasei Game urmatoarea mutare.
*    @author Matei
 */
 
class Mutare{
	Piesa p;
	Position pos;
	public Mutare(Piesa piesa, Position position){
		p = piesa;
		pos = position;
	}
}