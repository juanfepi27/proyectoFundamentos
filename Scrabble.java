/**
 * Clase principal para el juego de Scrabble.
 * Esta clase crea el diccionario (las palabras se leen de un archivo)
 * Y luego invoca el método crearPalabras 
 * 
 * @author Helmuth Trefftz
 * @author Gian Paul Sánchez
 * @author Maria Paula Alaya
 * @author Juan Felipe Pinzón
 * @version 2021 05 13
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class Scrabble{
	/**
		* Programa principal
		* Se crea el diccionario y luego se invoca el método
		* crearPalabras de la clase LetterCombinations
		* para que imprima palabras que se pueden crear a partir
		* de las letras que el usuario tiene en la mano y que se 
		* encuentren en el diccionario
		*/
	public static void main(String [] args){

		Tablero tablero = new Tablero();

		Diccionario diccionario = new Diccionario();
		diccionario.leerDiccionario("diccionario.txt");

		Scanner entrada = new Scanner(System.in).useDelimiter("\n");
		
		char siNo;

		do{
      
			LetterCombinations lc = new LetterCombinations(diccionario, tablero);

			System.out.print("\nESCRIBA LAS LETRAS QUE TIENE EN SU MANO: ");

			String letrasEnMiMano = entrada.next();


			//Líneas para corregir errores en las letras ingresadas por el usuario.
      		letrasEnMiMano = letrasEnMiMano.replaceAll(" ", "");
			letrasEnMiMano = letrasEnMiMano.toLowerCase();
			letrasEnMiMano = letrasEnMiMano.replaceAll("á", "a");
			letrasEnMiMano = letrasEnMiMano.replaceAll("é", "e");
			letrasEnMiMano = letrasEnMiMano.replaceAll("í", "i");
			letrasEnMiMano = letrasEnMiMano.replaceAll("ó", "o");
			letrasEnMiMano = letrasEnMiMano.replaceAll("ú", "u");
			letrasEnMiMano = letrasEnMiMano.replaceAll("ü", "u");
			

			lc.crearPalabras(letrasEnMiMano);
			
			//Líneas para asegurarnos que las palabras a sugerir son mayor que 0.
			if(lc.palabrasASugerir.size()>0){

				System.out.println("\nEstas son tus mejores opciones:\n");
				lc.darPuntaje(lc.palabrasASugerir);
				
				tablero.anadirAlTablero(lc);

			}
			else System.out.println("\nNo se formó ninguna palabra\n");


			try{

				System.out.println("\n¿Quieres volver a introducir otras letras?");

				System.out.println("\n1. Si");
				System.out.println("2. NO");
		
				System.out.print("\nIngrese el número de la opción deseada: ");

				siNo = entrada.next().charAt(0);

			}
			catch(InputMismatchException e){
				siNo = 0;
			}
			

			while(siNo != '1' && siNo != '2' ){

				try{

				System.out.println("\nHas digitado una opción incorrecta.");

        		System.out.println("\n¿Quieres volver a introducir otras letras?");

				System.out.println("\n1. Si");
				System.out.println("2. NO");
		
				System.out.print("\nIngrese el número de la opción deseada: ");

				entrada.nextLine();
				siNo = entrada.next().charAt(0);

				}
				catch(InputMismatchException e){ 
					continue;
				}

			}

		}
		while(siNo == '1');

		System.out.print("\nFinalizó el programa.");

		entrada.close();
	}
}