/**
 * En esta clase se crean tableros, que por el momento
 * son arrays dinámicos, en el tablero creado se 
 * almacenarán las palabras elegidas por los juagadores.
 * 
 * @author Gian Paul Sánchez
 * @author Maria Paula Ayala
 * @author Juan Felipe Pinzón
 * @version 2021 05 28
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;


public class Tablero{
	/**
	 * Arreglo dinámico que contiene las palabras que están en el tablero del juego.
	 */
 	private ArrayList<String> palabrasEnTablero = new ArrayList<>();

	private char[][] tablero = new char[10][10];

	/**
	 * Arreglo dinámico que contiene las letras que están en el tablero del juego.
	 */
	private ArrayList<Character> letrasEnTablero = new ArrayList<Character>();

	/**
	 * Diccionario del cuál sacaremos las palabras que son válidas.
	 */
	private Diccionario diccionario;


	/**
	 * Constructor de la clase tablero que sirve para asignarle valor al atributo diccionario y para rellenar todos los espacios de la matriz tablero con ' '(un esapacio), este caractér representará una hueco vacío el cual puede ser utilizado.
	 * 
	 * @param d diccionario del cuál sacamos las palabras validas del juego.
	 */
	public Tablero(Diccionario d){

		this.diccionario = d;

		for(int i = 0; i<tablero.length;i++){

			for(int j = 0; j<tablero[0].length; j++){

				tablero[i][j] = ' ';

			}

		}

	}



	/**
	 * Con este método se añade al tablero la palabra elegida por el usuario.
	 * 
	 * @param lc Objeto de la clase LetterCombinations.
	 */
	public void anadirAlTablero(LetterCombinations lc){
    
		try{
			Scanner scanner = new Scanner(System.in);
			
			//Si hay mas de una combinacion posible entonces...
			if(lc.palabrasConPuntaje.size()>1){

                System.out.print("\nEscoge la opción de la palabra que quieres añadir al tablero: ");

                int opcionElegida = scanner.nextInt();

                this.palabrasEnTablero.add(lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra());

				this.obtenerLetrasEnTablero(lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra());

				System.out.println("\nSe añadirá la palabra "+lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra().toUpperCase()+ " al tablero.");

				ubicarPalabrasEnTablero(lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra());

				System.out.println("\nSe ha añadido la palabra "+lc.palabrasConPuntaje.get(opcionElegida-1).getPalabra().toUpperCase()+ " al tablero.");
            }

			//Si no, se añade directamente.
            else{
                this.palabrasEnTablero.add(lc.palabrasConPuntaje.get(0).getPalabra());

				this.obtenerLetrasEnTablero(lc.palabrasConPuntaje.get(0).getPalabra());

				System.out.println("\nSe añadirá la palabra "+lc.palabrasConPuntaje.get(0).getPalabra().toUpperCase()+" al tablero.");

				ubicarPalabrasEnTablero(lc.palabrasConPuntaje.get(0).getPalabra());

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


	/**
	 * Método getter para acceder al atributo privado getPalabrasEnTablero.
	 * 
	 * @return retorna el atributo ArrayList<String> palabrasEnTablero de ese objeto.
	 */
	public ArrayList<String> getPalabrasEnTablero(){
		return this.palabrasEnTablero;
	}


	/**
	 * Este método dibuja la matriz tablero en consola, haciendo uso de los datos que se tienen en las posiciones de la matriz.
	 */
	public void dibujarTablero(){
		System.out.print("\n   0 1 2 3 4 5 6 7 8 9 ");
    
    	for(int i = 0; i<10; i++){
			
			System.out.println("\n  ---------------------");
			System.out.print( i + " |");
        	for(int j = 0; j < 10; j++){
         		System.out.print(tablero[i][j] + "|");
        	}
		}

		System.out.println("\n  ---------------------");
	}


	/**
	 * Método que sirve para ubicar las palabras en la matriz tablero, haciendo uso de detalles ingresados por el usuario.
	 * 
	 * @param s palabra que será ubicada en el tablero.
	 */
	public void ubicarPalabrasEnTablero(String s){

		Scanner scanner = new Scanner(System.in);

		System.out.println("\nIntroduzca la orientación de la palabra.");

		System.out.println("\n1. Vertical");
		System.out.println("2. Horizontal");

		System.out.print("\nIngrese el número de la opción deseada: ");

		int respuesta = scanner.nextInt();


		//Condicionales para saber si la orientación deseada es vertical, horizontal o si se ingresó una opción erronéa.
		if(respuesta == 1){

			try{

				System.out.print("\nEscriba la fila en la cual desea colocar la primera letra de su palabra: ");
				int filaElegida = scanner.nextInt();

				System.out.print("\nEscriba la columna en la cual desea colocar la primera letra de su palabra: ");
				int columnaElegida = scanner.nextInt();

				if(this.verificarValidezEnTablero(filaElegida, columnaElegida, respuesta, s)){
					for(int i=0; i<s.length(); i++){
						
						this.tablero[filaElegida + i][columnaElegida] = s.charAt(i);

					}
					this.dibujarTablero();
				}

				else{
					System.out.println("\nLas coordenadas ingresadas son inválidas. Intentelo de nuevo");
					ubicarPalabrasEnTablero(s);
				}

			}
			catch(IndexOutOfBoundsException e){

				System.out.println("\nTu palabra excede los limites del tablero. Vuelve a intentarlo.");

				ubicarPalabrasEnTablero(s);
			}

		}

		else if(respuesta == 2){

			try{

				System.out.print("\nEscriba la fila en la cual desea colocar la primera letra de su palabra: ");
				int filaElegida = scanner.nextInt();

				System.out.print("\nEscriba la columna en la cual desea colocar la primera letra de su palabra: ");
				int columnaElegida = scanner.nextInt();

				if(this.verificarValidezEnTablero(filaElegida, columnaElegida, respuesta, s)){
					for(int i=0; i<s.length(); i++){

						this.tablero[filaElegida][columnaElegida+i] = s.charAt(i);
						
					}
					this.dibujarTablero();
				}

				else{
					System.out.println("\nLas coordenadas ingresadas son inválidas. Inténtelo de nuevo.");
					ubicarPalabrasEnTablero(s);
				}
			}

			catch(IndexOutOfBoundsException e){

				System.out.println("\nTu palabra excede los limites del tablero. Vuelve a intentarlo.");

				ubicarPalabrasEnTablero(s);
			}

		}


		//Si introdujo una opción errónea
		else{
			System.out.println("\nHas introducido una opción incorrecta. Intentelo de nuevo.");

			ubicarPalabrasEnTablero(s);
		}

	}



	/**
	 * Método para preguntar si hay palabras previas al comienzo del programa.
	 */
	 public void hayPalabras(){

		Scanner scanner = new Scanner(System.in);

		System.out.println("\n¿Hay palabras previamente en el tablero?");
		System.out.println("\n1. Si");
		System.out.println("2. No");

		System.out.print("\nIntroduzca la respuesta: ");

		char respuesta = scanner.next().charAt(0);


		//Condicional para saber si hay palabras previamente.
		if(respuesta == '1'){
			
			System.out.print("\n¿Cuantas palabras hay en el tablero?");

			int respuesta2 = scanner.nextInt();

			int i = 1;


			while( i <= respuesta2 ){

				System.out.print("\nEscribe la palabra #"+i+" que se encuentra en el tablero: ");
				String palabra = scanner.next();


				//Condicional para saber si la palabra que se va a colocar en el tablero está o no, en el diccionario.
				if(diccionario.buscarPalabras(palabra)){


					//Condicional para saber si la palabra que se va a colcoar ene l tablero no se ha colocado anteriormente.
					if(!(palabrasEnTablero.contains(palabra))){

						//Lineas para modificar la palabra por una sintaxis "correcta"
						palabra = palabra.replaceAll(" ", "");
						palabra = palabra.toLowerCase();
						palabra = palabra.replaceAll("á", "a");
						palabra = palabra.replaceAll("é", "e");
						palabra = palabra.replaceAll("í", "i");
						palabra = palabra.replaceAll("ó", "o");
						palabra = palabra.replaceAll("ú", "u");
						palabra = palabra.replaceAll("ü", "u");

						
						palabrasEnTablero.add(palabra);
						this.obtenerLetrasEnTablero(palabra);

						ubicarPalabrasEnTablero(palabra);
						
						i++;

					}

					else{
						System.out.println("Esta palabra ya la has colocado en el tablero. Por favor ingresar de nuevo una palabra diferente.");
					}
				}

				else{
					System.out.println("Esta palabra no se encuentra en el diccionario. Por favor ingresar de nuevo una palabra diferente.");
				}
			}

		}

		//Se hace lo siguiente para verificar que no se haya ingresado una opción diferente a 1 y 2.
		else if(respuesta != '1' && respuesta != '2'){

			System.out.println("\nHas introducido una opción incorrecta. Intentelo de nuevo.");

			hayPalabras();
		}

	}

	/**
	 * En este método se verifica si es valida la posición en la cuál el usuasrio quiere colocar la palabra en tablero.
	 * 
	 * @param filaElegida fila elegida por el usuario donde se quiere comenzar a colocar la palabra, servirá en este método para verificar que las coordenadas no presenten errores.
	 * @param columnaElegidafila columna elegida por el usuario donde se quiere comenzar a colocar la palabra, servirá en este método para verificar que las coordenadas no presenten errores.
	 * @param orientacion manera en la cuál se quiere colocar la palabra. 1 es vertical y 2 es horizontal. También elegida por el usuario con anterioridad.
	 * @param palabra la palabra que se quiere colocar en el tablero.
	 * @return retorna boolean para saber si es correcto o no colocar la palabra en esta posición.
	 */
	public boolean verificarValidezEnTablero(int filaElegida, int columnaElegida, int orientacion, String palabra){

		if(this.palabrasEnTablero.size() == 1){

          if(orientacion == 1 && palabra.length() + filaElegida <= 10) {
			  return true;
		  	}

          else if (orientacion == 2 && palabra.length() + columnaElegida <= 10) {
			  return true;
			}

          else {
			  return false; 
		  	}

		}
		

		else{		
			
			boolean cruzado = false;

			if(orientacion == 1){

				for(int i = 0; i<palabra.length(); i++){

					if(this.tablero[filaElegida+i][columnaElegida] == palabra.charAt(i)){
						cruzado = true;
					}

					else if(this.tablero[filaElegida+i][columnaElegida] != palabra.charAt(i) && this.tablero[filaElegida+i][columnaElegida] != ' '){
						cruzado = false;
						break;
					}

				}

			}

			else{

				for(int i = 0; i<palabra.length(); i++){

					if(this.tablero[filaElegida][columnaElegida+i] == palabra.charAt(i)){
						cruzado = true;
					}

					else if(this.tablero[filaElegida+i][columnaElegida+i] != palabra.charAt(i) && this.tablero[filaElegida][columnaElegida+i] != ' '){
						cruzado = false;
						break;
					}

				}

			}

			return cruzado;
		}
	}


	/**
	 * 
	 * Con este método podremos sacar las letras que tenemos en el tablero, para posteriormente usarlas a la hora de crear combinaciones.
	 * 
	 * @param s palabra de la cual sacaremos las letras.
	 */
	public void obtenerLetrasEnTablero(String s){
		
		for(int i = 0; i<s.length() ; i++){

			this.letrasEnTablero.add(s.charAt(i));

		}

	} 


	/**
	 * Método getter para acceder al atributo privado letrasEnTablero.
	 * 
	 * @return retorna el atributo ArrayList<Characater> letrasEnTablero de ese objeto.
	 */
	public ArrayList<Character> getLetrasEnTablero(){
		return this.letrasEnTablero;
	}
}