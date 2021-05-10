
/**
 * Clase principal para el juego de Scrabble.
 * Esta clase crea el diccionario (las palabras se leen de un archivo)
 * Y luego invoca el método crearPalabras 
 * 
 * @author Helmuth Trefftz
 * @version 2021 04 27
 */
import java.util.Scanner;
public class Scrabble
{
	/**
		* Programa principal
		* Se crea el diccionario y luego se invoca el método
		* crearPalabras de la clase LetterCombinations
		* para que imprima palabras que se pueden crear a partir
		* de las letras que el usuario tiene en la mano y que se 
		* encuentren en el diccionario
		*/
	public static void main(String [] args){

		Diccionario diccionario = new Diccionario();
		diccionario.leerDiccionario("diccionario.txt");

		LetterCombinations lc = new LetterCombinations(diccionario);

        Scanner entrada = new Scanner(System.in);
        System.out.println("Escriba las letras que tiene en su mano sin espacios");
		String letrasEnMiMano = entrada.next();

        letrasEnMiMano = letrasEnMiMano.toLowerCase();
		
		lc.crearPalabras(letrasEnMiMano);
        System.out.println("Estas son tus mejores opciones:");
		// lc.mostrarListaValida();//OJO
		lc.darPuntaje(lc.palabrasValidadas);

        entrada.close();
    }
}