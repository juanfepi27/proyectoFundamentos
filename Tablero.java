/**
 * En esta clase se crean tableros que por el momento
 * son arrays dinámicos, en el tablero creado se 
 * almacenarán las palabras elegidas por los juagadores.
 * 
 * @author Gian Paul Sánchez
 * @author Maria Paula Ayala
 * @author Juan Felipe Pinzón
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class Tablero{

  	public ArrayList<String> palabrasEnTablero = new ArrayList<>();

	/**
	 * Con este método se añade al tablero la palabra elegida por el usuario.
	 * 
	 * @param lc Objeto de la clase LetterCombinations.
	 */
	public void anadirAlTablero(LetterCombinations lc){

		System.out.print("\nEscoge la opción de la palabra que quieres añadir al tablero: ");
		
		try{
			Scanner scanner = new Scanner(System.in);
			
			int opcionElegida = scanner.nextInt();

            this.palabrasEnTablero.add(lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra());

            scanner.close();
			
		}

		catch(InputMismatchException e){
			System.out.println("\nHas ingresado una opción errónea.");
			anadirAlTablero(lc);
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("\nHas ingresado una opción errónea.");
			anadirAlTablero(lc);
		}

	}

}