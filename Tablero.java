/**
 * En esta clase se crean tableros, que por el momento
 * son arrays dinámicos, en el tablero creado se 
 * almacenarán las palabras elegidas por los juagadores.
 * 
 * @author Gian Paul Sánchez
 * @author Maria Paula Alaya
 * @author Juan Felipe Pinzón
 * @version 2021 05 13
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class Tablero{
/**
	 * Arreglo dinámico que contiene las palabras que están en el tablero del juego
	 */
  public ArrayList<String> palabrasEnTablero = new ArrayList<>();

	/**
	 * Con este método se añade al tablero la palabra elegida por el usuario.
	 * 
	 * @param lc Objeto de la clase LetterCombinations.
	 */
	public void anadirAlTablero(LetterCombinations lc){
    
		try{
			Scanner scanner = new Scanner(System.in);
			
			if(lc.palabrasConPuntaje.size()>1){

                System.out.print("\nEscoge la opción de la palabra que quieres añadir al tablero: ");

                int opcionElegida = scanner.nextInt();

                this.palabrasEnTablero.add(lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra());

				System.out.println("\nSe ha añadido la palabra "+lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra().toUpperCase()+ " al tablero.");
            }

            else{
                this.palabrasEnTablero.add(lc.palabrasConPuntaje.get(0).getPalabra());

				System.out.println("\nSe ha añadido la palabra "+lc.palabrasConPuntaje.get(0).getPalabra().toUpperCase()+" al tablero.");
            }
			
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