
/**
 * Clase principal para el juego de Scrabble.
 * Esta clase crea el diccionario (las palabras se leen de un archivo)
 * Y luego invoca el método crearPalabras 
 * 
 * @author Helmuth Trefftz
 * @version 2021 04 27
 */
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

	Scanner entrada = new Scanner(System.in);
		
	int siNo;

		do{
		
			LetterCombinations lc = new LetterCombinations(diccionario, tablero);

			System.out.println("\nEscriba las letras que tiene en su mano sin espacios\n");

			String letrasEnMiMano = entrada.next();

			letrasEnMiMano = letrasEnMiMano.toLowerCase();
			letrasEnMiMano = letrasEnMiMano.replaceAll("á", "a");
			letrasEnMiMano = letrasEnMiMano.replaceAll("é", "e");
			letrasEnMiMano = letrasEnMiMano.replaceAll("í", "i");
			letrasEnMiMano = letrasEnMiMano.replaceAll("ó", "o");
			letrasEnMiMano = letrasEnMiMano.replaceAll("ú", "u");
			letrasEnMiMano = letrasEnMiMano.replaceAll("ü", "u");
			
			lc.crearPalabras(letrasEnMiMano);
			System.out.println("\nEstas son tus mejores opciones:\n");
			lc.darPuntaje(lc.palabrasValidadas);
			
			tablero.anadirAlTablero(lc);

			System.out.println("¿Quieres seguir?");

			System.out.println("\n1. SÍ");
			System.out.println("2. NO");

			System.out.print("\nIngrese el número de la opción deseada: ");
			siNo = entrada.nextInt();

			while(siNo != 1 && siNo != 2 ){

				System.out.print("\nHas digitado una opción incorrecta, vuelve a intentarlo: ");

				siNo = entrada.nextInt();

			}
		
		}
		while(siNo == 1);

		System.out.print("\nFinalizó el programa.");

		entrada.close();
	}
}