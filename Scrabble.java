/**
 * Clase principal para el juego de Scrabble.
 * Esta clase crea el diccionario (las palabras se leen de un archivo)
 * Y luego invoca el método crearPalabras 
 * 
 * @author Helmuth Trefftz
 * @author Gian Paul Sánchez
 * @author Maria Paula Ayala
 * @author Juan Felipe Pinzón
 * @version 2021 05 27
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

		Diccionario diccionario = new Diccionario();
		diccionario.leerDiccionario("diccionario.txt");

		Tablero tablero = new Tablero(diccionario);

		Scanner entrada = new Scanner(System.in).useDelimiter("\n");
		
		char siNo = '1';


		System.out.print("\nIntroduzca la cantidad de jugadores: ");
		char cantJugadoresChar = entrada.next().charAt(0);
		int cantJugadoresInt = Character.getNumericValue(cantJugadoresChar);

		int numJugador = 1;

		int ronda = 1;

		
		//Ciclo para verificar que no se introduzcan cantidades de jugadores equivocados.
		while(cantJugadoresInt <= 0 || cantJugadoresInt > 4){
			System.out.println("Cantidad de jugadores no permitida.");

			System.out.print("\nIntroduzca la cantidad de jugadores: ");
			cantJugadoresChar = entrada.next().charAt(0);
			cantJugadoresInt = Character.getNumericValue(cantJugadoresChar);
		}

		do{

			if(ronda == 1 && numJugador == 1){

				tablero.dibujarTablero();

				tablero.hayPalabras();
			}

			LetterCombinations lc = new LetterCombinations(diccionario, tablero);


			System.out.println("\nES EL TURNO DEL JUGADOR "+numJugador);

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
			

			//Las siguientes lineas sirven para sacar las combinaciones seún las letras que ya existen en el tablero. Si las letras en el tablero son inexistentes entonces se utilizan solo las letras en la mano.
			if(tablero.getLetrasEnTablero().size() == 0){
				lc.crearPalabras(letrasEnMiMano);
			}

			else{

				for(int i = 0; i<tablero.getLetrasEnTablero().size(); i++){

					lc.crearPalabras(letrasEnMiMano + tablero.getLetrasEnTablero().get(i));

				}

			}
			

			//Líneas para asegurarnos que las palabras a sugerir son mayor que 0.
			if(lc.palabrasASugerir.size()>0){

				System.out.println("\nEstas son tus mejores opciones:\n");
				lc.darPuntaje(lc.palabrasASugerir);
				
				tablero.anadirAlTablero(lc);

			}
			else System.out.println("\nNo se formó ninguna palabra\n");
		
			

			//Condicional para saber si se debe preguntar si van a jugar otra ronda. Se ejecuta si el numero del jugador es igual a la cantidad de jugadores en la partida.
			
			if(numJugador == cantJugadoresInt){
				
				try{

					System.out.println("\n¿Quieren volver a jugar otra ronda?");

					System.out.println("\n1. Si");
					System.out.println("2. No");
			
					System.out.print("\nIngrese el número de la opción deseada: ");

					siNo = entrada.next().charAt(0);

				}
				catch(InputMismatchException e){
					siNo = '0';
				}
				

				while(siNo != '1' && siNo != '2' ){

					try{

					System.out.println("\nHas digitado una opción incorrecta.");

					System.out.println("\n¿Quieren volver a jugar otra ronda?");

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

			if(numJugador < cantJugadoresInt){
				numJugador ++;
			}

			else{
				numJugador = 1;
				ronda++;
			}

		}
		while(siNo == '1');
		

		tablero.dibujarTablero();

		System.out.print("\nFinalizó el programa.");

		entrada.close();
	}
}