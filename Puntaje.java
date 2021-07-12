/**
 * Esta clase genera objetos los cuales son en realidad palabras con 
 * su respectiva puntuación, esta puntuación será tomada con base los 
 * estándares del juego Scrabble.
 * 
 * @author Gian Paul Sánchez Aristizabal
 * @author Maria Paula Ayala Lizarazo
 * @author Juan Felipe Pinzón Trejo
 * @version 13/05/2021
 */
public class Puntaje{

	private String palabra;
    private int puntos;

	/**
	 * Constructor
	 * Se recibe la palabra de la cual se sacará el puntaje
	 * posteriormente.
	 * 
	 * @param s Palabra a establecer como valor de atributo.
	 */
	public Puntaje(String s){
		this.palabra = s;
		this.puntos = 0;
	}
	
	/**
	 * Este método sirve para tomar el valor del atributo 
	 * privado palabra.
	 * 
	 * @return retorna la palabra del objeto.
	 */
	public String getPalabra(){
		return this.palabra;
	}

	/**
	 * Método para establecer los puntos del objeto.
	 * 
	 * @param puntos valor de los puntos a establecer.
	 */
	public void setPuntos(int puntos){
		this.puntos = puntos;
	}

	/**
	 * Método para tomar el valor del atributo privado puntos.
	 * 
	 * @return retorna los puntos del objeto.
	 */
  	public int getPuntos(){
		return this.puntos;
	}
	
	
}
