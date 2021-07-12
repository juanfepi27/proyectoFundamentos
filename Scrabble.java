/**
 * Clase principal para el juego de Scrabble.
 * Esta clase crea el diccionario (las palabras se leen de un archivo)
 * Y luego invoca el método crearPalabras 
 * 
 * @author Helmuth Trefftz
 * @author Gian Paul Sánchez Aristizabal
 * @author Maria Paula Ayala Lizarazo
 * @author Juan Felipe Pinzón Trejo
 * @version 02/06/2021
 */
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

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


		do{

			System.out.println("Introduzca el nombre del juego ('Recuerde que no puede ser diccionario')");
			Tablero.nombreArchivoRespaldo= entrada.nextLine();
			Tablero.nombreArchivoRespaldo= Tablero.nombreArchivoRespaldo+".txt";

			//System.out.println("el nombre del archivo es: _"+Tablero.nombreArchivoRespaldo);
		
		}while(Tablero.nombreArchivoRespaldo.equals("diccionario.txt"));
		
		File archivoRespaldo = new File(Tablero.nombreArchivoRespaldo);
		

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


		try {
			FileWriter respaldo = new FileWriter(Tablero.nombreArchivoRespaldo,true);
			PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);

		escritorDelRespaldo.println("\n\n--------------------------------------------------------------------------\nHa comenzado el juego: "+ Tablero.nombreArchivoRespaldo.substring( 0 , (Tablero.nombreArchivoRespaldo.length()-4) )+"\nCon "+cantJugadoresInt+" jugadores\n" );

			escritorDelRespaldo.close();


		} catch (IOException e) {
			System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
		}


		//Ciclo principal de juego.
		do{

			LetterCombinations lc = new LetterCombinations(diccionario, tablero);

			if(ronda == 1 && numJugador == 1){

				tablero.dibujarTablero();

				tablero.hayPalabras(lc);
			}


			try {
				FileWriter respaldo = new FileWriter(Tablero.nombreArchivoRespaldo,true);
				PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);
	
			escritorDelRespaldo.println("\n\nRONDA "+ronda+" JUGADOR "+numJugador+"\n" );
	
				escritorDelRespaldo.close();
	
	
			} catch (IOException e) {
				System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
			}
			
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
			
			//Siempre se hacen las combinaciones con las letrasEnMiMano
			lc.crearPalabras(letrasEnMiMano);
			

			//Pero, si letrasEnTablero es mayor a 0 tambien debemos hacer las combinaciones con estas letras.
			if(tablero.getLetrasEnTablero().size() > 0){

				for(int i = 0; i<tablero.getLetrasEnTablero().size(); i++){

					//lc.crearPalabrasConLetrasTablero(letrasEnMiMano, tablero.getLetrasEnTablero().get(i));
					lc.crearPalabrasConLetrasTablero(letrasEnMiMano, tablero.getLetrasEnTablero().get(i));

				}

			}
			

			//Líneas para asegurarnos que las palabras a sugerir son mayor que 0.
			if(lc.getPalabrasASugerir().size()>0){

				System.out.println("\nEstas son tus mejores opciones:\n");
				lc.darPuntaje(lc.getPalabrasASugerir());
				
				tablero.anadirAlTablero(lc);

			}
			else {
				System.out.println("\nNo se formó ninguna palabra\n");

				try {
					FileWriter respaldo = new FileWriter(Tablero.nombreArchivoRespaldo,true);
					PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);
		
					escritorDelRespaldo.println("\n\nNO SE FORMARON PALABRAS\n" );
		
					escritorDelRespaldo.close();
		
		
				} catch (IOException e) {
					System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
				}
			}
		
			

			//Condicional para saber si se debe preguntar si van a jugar otra ronda. Se ejecuta si el numero del jugador es igual a la cantidad de jugadores en la partida.
			
			if(numJugador == cantJugadoresInt){
				
				System.out.println("\n¿Quieren volver a jugar otra ronda?");
				System.out.println("\n1. Si");
				System.out.println("2. No");
				System.out.print("\nIngrese el número de la opción deseada: ");

				siNo = entrada.next().charAt(0);


				while(siNo != '1' && siNo != '2' ){

					try{

					System.out.println("\nHas digitado una opción incorrecta.");

					System.out.println("\n¿Quieren volver a jugar otra ronda?");

					System.out.println("1. Si");
					System.out.println("2. NO");
			
					System.out.print("Ingrese el número de la opción deseada: ");

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

		try {
			FileWriter respaldo = new FileWriter(Tablero.nombreArchivoRespaldo,true);
			PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);

			escritorDelRespaldo.println("\n\nFIN DEL JUEGO\n--------------------------------------------------------------------------" );

			escritorDelRespaldo.close();


		} catch (IOException e) {
			System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
		}

		entrada.close();
	}
}