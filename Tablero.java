/**
 * En esta clase se crean tableros, que por el momento
 * son arrays dinámicos, en el tablero creado se 
 * almacenarán las palabras elegidas por los juagadores.
 * 
 * @author Gian Paul Sánchez Aristizabal
 * @author Maria Paula Ayala Lizarazo
 * @author Juan Felipe Pinzón Trejo
 * @version 03/06/2021
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class Tablero{
	
 	private ArrayList<String> palabrasEnTablero = new ArrayList<>();

	private char[][] tablero = new char[10][10];

	private ArrayList<Character> letrasEnTablero = new ArrayList<Character>();

	private Diccionario diccionario;

	public static String nombreArchivoRespaldo;


	/**
	 * Constructor de la clase tablero que sirve para asignarle valor al atributo diccionario y para rellenar todos los espacios de la matriz tablero con ' '(un esapacio), este caractér representará una hueco vacío el cual puede ser utilizado.
	 * 
	 * @param d diccionario del cuál sacamos las palabras validas del juego.
	 */
	public Tablero(Diccionario d){
		this.diccionario = d;

		for(char[] e: tablero){
			Arrays.fill(e, ' ');
		}
	}



	/**
	 * Con este método se añade al tablero la palabra elegida por el usuario.
	 * 
	 * @param lc Objeto de la clase LetterCombinations con el cuál accederemos a los métodos de dicha clase.
	 */
	public void anadirAlTablero(LetterCombinations lc){
    
		try{
			Scanner scanner = new Scanner(System.in);
			
			//Si hay mas de una combinacion posible entonces...
			if(lc.getPalabrasConPuntaje().size()>1){

                System.out.print("\nEscoge la opción de la palabra que quieres añadir al tablero: ");

                int opcionElegida = scanner.nextInt();

                this.palabrasEnTablero.add(lc.getPalabrasConPuntaje().get(opcionElegida-1).getPalabra());

				this.obtenerLetrasEnTablero(lc.getPalabrasConPuntaje().get(opcionElegida-1).getPalabra());

				System.out.println("\nSe añadirá la palabra "+lc.getPalabrasConPuntaje().get(opcionElegida-1).getPalabra().toUpperCase()+ " al tablero.");

				ubicarPalabrasEnTablero(lc.getPalabrasConPuntaje().get(opcionElegida-1).getPalabra(), lc);

            }

			//Si no, se añade directamente.
            else{
                this.palabrasEnTablero.add(lc.getPalabrasConPuntaje().get(0).getPalabra());

				this.obtenerLetrasEnTablero(lc.getPalabrasConPuntaje().get(0).getPalabra());

				System.out.println("\nSe añadirá la palabra "+lc.getPalabrasConPuntaje().get(0).getPalabra().toUpperCase()+" al tablero.");

				ubicarPalabrasEnTablero(lc.getPalabrasConPuntaje().get(0).getPalabra(), lc);

            }
			
		}

		catch(InputMismatchException e){
			System.out.println("\nHas ingresado una opción errónea. Dato");
			anadirAlTablero(lc);
		}
		
		catch(IndexOutOfBoundsException e){
			System.out.println("\nHas ingresado una opción errónea. Bordes");
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
	 * @param lc Objeto de la clase LetterCombinations con el cuál accederemos a los métodos de dicha clase.
	 */
	public void ubicarPalabrasEnTablero(String s, LetterCombinations lc){

		Scanner scanner = new Scanner(System.in);

		System.out.println("\nIntroduzca la orientación de la palabra.");

		System.out.println("\n1. Vertical");
		System.out.println("2. Horizontal");

		System.out.print("\nIngrese el número de la opción deseada: ");

		int orientacion = scanner.nextInt();


		//Condicionales para saber si la orientación deseada es vertical, horizontal o si se ingresó una opción erronéa.
		try{

			if(orientacion == 1){

				System.out.print("\nEscriba la fila en la cual desea colocar la primera letra de su palabra: ");
				int filaElegida = scanner.nextInt();

				System.out.print("\nEscriba la columna en la cual desea colocar la primera letra de su palabra: ");
				int columnaElegida = scanner.nextInt();

				if(this.verificarValidezEnTablero(filaElegida, columnaElegida, orientacion, s, lc)){
					
					for(int i=0; i<s.length(); i++){
						
						this.tablero[filaElegida + i][columnaElegida] = s.charAt(i);

					}
					this.dibujarTablero();
					System.out.println("\nSe ha añadido la palabra "+s.toUpperCase()+" al tablero.");

					//lineas para generar el respaldo

					try {
						FileWriter respaldo = new FileWriter(nombreArchivoRespaldo,true);
						PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);

						escritorDelRespaldo.println("\n\nSe ha añadido la palabra "+s.toUpperCase()+" al tablero en las coordenadas ("+filaElegida+","+columnaElegida+") .");

						escritorDelRespaldo.print("\n\n   0 1 2 3 4 5 6 7 8 9 ");
    
						for(int i = 0; i<10; i++){
							
							escritorDelRespaldo.println("\n  ---------------------");
							escritorDelRespaldo.print( i + " |");
							for(int j = 0; j < 10; j++){
								escritorDelRespaldo.print(tablero[i][j] + "|");
							}
						}

						escritorDelRespaldo.println("\n  ---------------------");

						escritorDelRespaldo.close();


					} catch (IOException e) {
						System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
					}

				}
			

				else{

					System.out.println("\nLas coordenadas ingresadas son inválidas. Inténtelo de nuevo.");

					if(lc.getPalabrasConPuntaje().size()==0){
						this.dibujarTablero();
						ubicarPalabrasEnTablero(s, lc);
					}

					else{

						if(!cambiarOpcion(lc)){
							this.dibujarTablero();
							ubicarPalabrasEnTablero(s, lc);
						}

					}
				}

			}


			else if(orientacion == 2){

				System.out.print("\nEscriba la fila en la cual desea colocar la primera letra de su palabra: ");
				int filaElegida = scanner.nextInt();

				System.out.print("\nEscriba la columna en la cual desea colocar la primera letra de su palabra: ");
				int columnaElegida = scanner.nextInt();

				if(this.verificarValidezEnTablero(filaElegida, columnaElegida, orientacion, s, lc)){
					
					for(int i=0; i<s.length(); i++){

						this.tablero[filaElegida][columnaElegida+i] = s.charAt(i);
						
					}
					this.dibujarTablero();
					System.out.println("\nSe ha añadido la palabra "+s.toUpperCase()+" al tablero.");

					//lineas para generar el respaldo del archivo

					try {
						FileWriter respaldo = new FileWriter(nombreArchivoRespaldo, true);
						PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);

						escritorDelRespaldo.println("\n\nSe ha añadido la palabra "+s.toUpperCase()+" al tablero en las coordenadas ("+filaElegida+","+columnaElegida+") .");

						escritorDelRespaldo.print("\n\n   0 1 2 3 4 5 6 7 8 9 ");
    
						for(int i = 0; i<10; i++){
							
							escritorDelRespaldo.println("\n  ---------------------");
							escritorDelRespaldo.print( i + " |");
							for(int j = 0; j < 10; j++){
								escritorDelRespaldo.print(tablero[i][j] + "|");
							}
						}

						escritorDelRespaldo.println("\n  ---------------------");

						escritorDelRespaldo.close();


					} catch (IOException e) {
						System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
					}

				}

				else{

					System.out.println("\nLas coordenadas ingresadas son inválidas. Inténtelo de nuevo.");

					if(lc.getPalabrasConPuntaje().size()==0){
						this.dibujarTablero();
						ubicarPalabrasEnTablero(s, lc);
					}

					else{
						if(!cambiarOpcion(lc)){
							this.dibujarTablero();
							ubicarPalabrasEnTablero(s, lc);
						}
					}
				}
			
			}


			//Si introdujo una opción errónea
			else{
				System.out.println("\nHas introducido una opción incorrecta. Inténtelo de nuevo.");

				ubicarPalabrasEnTablero(s, lc);
			}
		}

		catch(IndexOutOfBoundsException e){

			System.out.println("\nLas coordenadas ingresadas son inválidas. Inténtelo de nuevo.");

			if(lc.getPalabrasConPuntaje().size()==0){
				this.dibujarTablero();
				ubicarPalabrasEnTablero(s, lc);
			}
			else{
				if(!cambiarOpcion(lc)){
					this.dibujarTablero();
					ubicarPalabrasEnTablero(s, lc);
				}
			}
		}

	}



	/**
	 * Método para preguntar si hay palabras previas al comienzo del programa. Este método luego invoca el método ubicarPalabrasEnTablero para que la palabra ingresada pueda ser ubicada en el tablero.
	 * 
	 * @param lc Objeto de la clase LetterCombinations con el cuál accederemos a los métodos de dicha clase.
	 */
	 public void hayPalabras(LetterCombinations lc){

		Scanner scanner = new Scanner(System.in);

		System.out.println("\n¿Hay palabras previamente en el tablero?");
		System.out.println("\n1. Si");
		System.out.println("2. No");
		System.out.print("\nIntroduzca la respuesta: ");

		char respuesta = scanner.next().charAt(0);


		//Condicional para saber si hay palabras previamente.
		if(respuesta == '1'){

			try {
				FileWriter respaldo = new FileWriter(nombreArchivoRespaldo,true);
				PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);
	
				escritorDelRespaldo.println("\n\nESPACIO PARA PALABRAS PREVIAS\n" );
	
				escritorDelRespaldo.close();
	
	
			} catch (IOException e) {
				System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
			}
			
			try{
				System.out.print("\n¿Cuantas palabras hay en el tablero? ");

				int respuesta2 = scanner.nextInt();

				int i = 1;


				while( i <= respuesta2 ){

					System.out.print("\nEscribe la palabra #" + i + " que se encuentra en el tablero: ");
					String palabra = scanner.next();

					//Lineas para modificar la palabra por una sintaxis "correcta"
					palabra = palabra.replaceAll(" ", "");
					palabra = palabra.toLowerCase();
					palabra = palabra.replaceAll("á", "a");
					palabra = palabra.replaceAll("é", "e");
					palabra = palabra.replaceAll("í", "i");
					palabra = palabra.replaceAll("ó", "o");
					palabra = palabra.replaceAll("ú", "u");
					palabra = palabra.replaceAll("ü", "u");


					//Condicional para saber si la palabra que se va a colocar en el tablero está o no, en el diccionario.
					if(diccionario.buscarPalabras(palabra)){

						//Condicional para saber si la palabra que se va a colcoar ene l tablero no se ha colocado anteriormente.
						if(!(palabrasEnTablero.contains(palabra))){
							
							palabrasEnTablero.add(palabra);
							this.obtenerLetrasEnTablero(palabra);

							ubicarPalabrasEnTablero(palabra, lc);
							
							i++;

						}

						else{
							System.out.println("\nEsta palabra ya la has colocado en el tablero. Por favor ingresar de nuevo una palabra diferente.");
						}
					}

					else{
						System.out.println("\nEsta palabra no se encuentra en el diccionario. Por favor ingresar de nuevo una palabra diferente.");
					}
				}
				
			}
			catch(InputMismatchException e){
				
				System.out.println("\nHas introducido una opción incorrecta. Intentelo de nuevo.");

				hayPalabras(lc);
			}

		}

		//Se hace lo siguiente para verificar que no se haya ingresado una opción diferente a 1 y 2.
		else if(respuesta != '1' && respuesta != '2'){

			System.out.println("\nHas introducido una opción incorrecta. Intentelo de nuevo.");

			hayPalabras(lc);
		}

	}

	/**
	 * En este método se verifica si es valida la posición en la cuál el usuasrio quiere colocar la palabra en tablero.
	 * 
	 * @param filaElegida Fila elegida por el usuario donde se quiere comenzar a colocar la palabra, servirá en este método para verificar que las coordenadas no presenten errores.
	 * @param columnaElegidafila Columna elegida por el usuario donde se quiere comenzar a colocar la palabra, servirá en este método para verificar que las coordenadas no presenten errores.
	 * @param orientacion Manera en la cuál se quiere colocar la palabra. 1 es vertical y 2 es horizontal. También elegida por el usuario con anterioridad.
	 * @param palabra la palabra que se quiere colocar en el tablero.
	 * @param lc Objeto de la clase LetterCombinations con el cuál accederemos a los métodos de dicha clase.
	 * @return Retorna boolean para saber si es correcto o no colocar la palabra en esta posición.
	 * @throws IndexOutOfBoundsException Si las coordenadas elegidas por el usuario hacen que la palabra salga del tablero saltará este error el cuál sera atrapado por un catch en el método ubicarPalabrasEnTablero.
	 */
	public boolean verificarValidezEnTablero(int filaElegida, int columnaElegida, int orientacion, String palabra, LetterCombinations lc)throws IndexOutOfBoundsException{

		//Si es la primera palabra que se va a poner en tablero.
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
		

		//Si no es la primera palabra que se va a poner en tablero.
		else{		
			
			boolean valida = false;

			//Si la orientación es vertical.
			if(orientacion == 1){

				//Si la 
				if(lc.existeCombinacionPalabrasTablero(palabra)){

					String s = "";

					for(int i = 0; i<palabra.length(); i++){

						s += this.tablero[filaElegida+i][columnaElegida];

					}

					s = s.trim();

					if(palabra.contains(s)){

						valida = true;

						for(int i = 0; i< palabra.length(); i++){

							if(this.tablero[filaElegida+i][columnaElegida] != palabra.charAt(i) && this.tablero[filaElegida+i][columnaElegida] != ' '){
	
								return false;
	
							}

						}

						this.getPalabrasEnTablero().remove(this.getPalabrasEnTablero().indexOf(s));
					}

					else{
						return false;
					}
					
				}

				//Si la palabra a elegida está dentro de combinacionesLetrasTablero entonces...
				else if(lc.existeCombinacionLetrasTablero(palabra)){

					for(int i = 0; i<palabra.length(); i++){

						if(this.tablero[filaElegida+i][columnaElegida] == lc.getLetraEsp() && this.tablero[filaElegida+i][columnaElegida] == palabra.charAt(i)){
							valida = true;
						}
	
						else if(this.tablero[filaElegida+i][columnaElegida] != palabra.charAt(i) && this.tablero[filaElegida+i][columnaElegida] != ' '){
	
							return false;
	
						}
	
					}

				}
				else{

					for(int i = 0; i<palabra.length(); i++){

						if(this.tablero[filaElegida+i][columnaElegida] == palabra.charAt(i)){
							valida = true;
						}

						else if(this.tablero[filaElegida+i][columnaElegida] != palabra.charAt(i) && this.tablero[filaElegida+i][columnaElegida] != ' '){

							return false;

						}

					}
					
				}

				//Condicional para verificar si se cruzó correctamente.
				if(valida == false){
					return false;
				}



				//Se  debe verificar que la palabra a cocloar en el tablero está concatenada con otras palabras previamente colocadas en el tablero, y si lo está, entonces se debe verificar que si forme una palabra existente en el diccionario. Más abajo se hace se tiene en cuenta el caso en que dos o más palabras se encuentren una pegada de la otra, verificando que las nuevas combinaciones creadas a partir de está "unión" existan en el diccionario.
				String palabraAVerificarVertical = palabra;
				int indiceVertical = 1; 


				//Se mira si hay letras por encima de la palabra, si hay letras, entonces se agregan como un prefijo a la palabra que queremos añadir.
				while( 0 <= (filaElegida - indiceVertical) && this.tablero[filaElegida - indiceVertical][columnaElegida] != ' '){
					
					palabraAVerificarVertical = this.tablero[filaElegida - indiceVertical][columnaElegida] + palabraAVerificarVertical;
					
					indiceVertical++; 
				}

				indiceVertical = palabra.length();

				//Se mira si hay letras por debajo de la palabra, si hay letras, entonces se agregan como un sufijo a la palabra que queremos añadir.
				while( (filaElegida + indiceVertical) < 10 && this.tablero[filaElegida + indiceVertical][columnaElegida] != ' '){
					
					palabraAVerificarVertical = palabraAVerificarVertical + this.tablero[filaElegida + indiceVertical][columnaElegida];
					
					indiceVertical++; 
				}

				//Por útlimo para verificar que la palabra si existe, se hace uso del método buscarPalabras.
				if(diccionario.buscarPalabras(palabraAVerificarVertical)){
					if(!(palabrasEnTablero.contains(palabraAVerificarVertical))){
						palabrasEnTablero.add(palabraAVerificarVertical);
					}
					valida = true;
				}

				else{
					System.out.println("¡La palabra formada no existe en el diccionario!");
					return false;
				}


				//Se verifica caracter por caracter 
				for(int i = 0; i < palabra.length(); i++){

					String palabraAVerificar = "" + palabra.charAt(i); 
					int indiceHorizontal = 1;

					//Hacia la izquierda
					while(0 <= (columnaElegida - indiceHorizontal) && this.tablero[filaElegida + i][columnaElegida - indiceHorizontal] != ' '){
						
						palabraAVerificar = this.tablero[filaElegida + i][columnaElegida -  indiceHorizontal] + palabraAVerificar;

						indiceHorizontal++;
					}

					indiceHorizontal = 1;

					//Hacia la derecha
					while((columnaElegida + indiceHorizontal) < 10 && this.tablero[filaElegida + i][columnaElegida + indiceHorizontal] != ' '){
						
						palabraAVerificar += this.tablero[filaElegida + i][columnaElegida + indiceHorizontal] ;

						indiceHorizontal++;
					}

					if(palabraAVerificar.length() == 1){
						valida = true;
					}

					//Buscamos en el diccionario la palabra creada con los caracteres de izquierda a derecha.
					else if(diccionario.buscarPalabras(palabraAVerificar)){

						if(!(palabrasEnTablero.contains(palabraAVerificar))){
							palabrasEnTablero.add(palabraAVerificar);
						}
						valida = true;
					}
	
					else{
						System.out.println("\nNo forma una palabra :(");
						return false;
					}
					
				}

			}






			//Si la orientación es horizontal
			else{

				if(lc.existeCombinacionPalabrasTablero(palabra)){

					String s = "";

					for(int i = 0; i<palabra.length(); i++){

						s += this.tablero[filaElegida][columnaElegida+i];

					}

					s = s.trim();

					if(palabra.contains(s)){

						valida = true;

						for(int i = 0; i< palabra.length(); i++){

							if(this.tablero[filaElegida][columnaElegida+i] != palabra.charAt(i) && this.tablero[filaElegida][columnaElegida+i] != ' '){
	
								return false;
	
							}

						}

						this.getPalabrasEnTablero().remove(this.getPalabrasEnTablero().indexOf(s));
					}

					else{
						return false;
					}
					
				}

				else if(lc.existeCombinacionLetrasTablero(palabra)){

					for(int i = 0; i<palabra.length(); i++){

						if(this.tablero[filaElegida][columnaElegida+i] == lc.getLetraEsp() && this.tablero[filaElegida][columnaElegida+i] == palabra.charAt(i)){
							valida = true;
						}
	
						else if(this.tablero[filaElegida][columnaElegida+i] != palabra.charAt(i) && this.tablero[filaElegida][columnaElegida+i] != ' '){
	
							valida = false;
							break;
	
						}
	
					}
				}

				else{

					for(int i = 0; i<palabra.length(); i++){

						if(this.tablero[filaElegida][columnaElegida+i] == palabra.charAt(i)){
							valida = true;
						}

						else if(this.tablero[filaElegida][columnaElegida+i] != palabra.charAt(i) && this.tablero[filaElegida][columnaElegida+i] != ' '){

							valida = false;
							break;

						}

					}

				}

				if(valida == false){
					return false;
				}


				String palabraAVerificarHorizontal = palabra;
				int indiceHorizontal = 1; 

				//Se verifica si hay letras a la izquierda de la palabra a colocar.
				while(0 <= (columnaElegida - indiceHorizontal) && this.tablero[filaElegida][columnaElegida - indiceHorizontal] != ' '){
					
					palabraAVerificarHorizontal = this.tablero[filaElegida][columnaElegida - indiceHorizontal] + palabraAVerificarHorizontal;

					indiceHorizontal++;

				}

				indiceHorizontal = palabra.length();

				//Se verifica si hay letras a la derecha de la palabra a colocar.
				while( (columnaElegida + indiceHorizontal) < 10 && this.tablero[filaElegida][columnaElegida + indiceHorizontal] != ' '){

					palabraAVerificarHorizontal = palabraAVerificarHorizontal + this.tablero[filaElegida][columnaElegida + indiceHorizontal];

					indiceHorizontal++;
					
				}

				if(diccionario.buscarPalabras(palabraAVerificarHorizontal)){

					if(!(palabrasEnTablero.contains(palabraAVerificarHorizontal))){	
						palabrasEnTablero.add(palabraAVerificarHorizontal);
					}
					valida = true;

				}

				else{
					System.out.println("¡La palabra formada no existe en el diccionario!");
					return false;
				}


				for(int i = 0; i<palabra.length(); i++){

					String palabraAVerificar = "" + palabra.charAt(i);
					int indiceVertical = 1;

					//Para arriba
					while(0 <= (filaElegida - indiceVertical) && this.tablero[filaElegida - indiceVertical][columnaElegida+i]!=' '){

						palabraAVerificar = this.tablero[filaElegida-indiceVertical][columnaElegida+i] + palabraAVerificar;

						indiceVertical++;

					}

					indiceVertical = 1;

					//Para abajo
					while((filaElegida + indiceVertical)<10 && this.tablero[filaElegida + indiceVertical][columnaElegida+i]!=' '){

						palabraAVerificar = palabraAVerificar + this.tablero[filaElegida + indiceVertical][columnaElegida+i];

						indiceVertical++;

					}

					if(palabraAVerificar.length() == 1){
						valida = true;
					}

					else if(diccionario.buscarPalabras(palabraAVerificar)){

						if(!(palabrasEnTablero.contains(palabraAVerificar))){
							palabrasEnTablero.add(palabraAVerificar);
						}
						valida = true;
					}

					else{
						System.out.println("\nNo forma una palabra :(");
						return false;
					}

				}

			}

			return valida;
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


	/**
	 * Método utilizado para preguntarle al usuario si desea cambiar la palabra que quiere añadir al tablero. Se llama cuando saltan errores en la colocación de la palabra.
	 * 
	 * @param lc Objeto de la clase LetterCombinations con el cuál accederemos a los métodos de dicha clase.
	 * @return
	 */
	public boolean cambiarOpcion(LetterCombinations lc){

		Scanner entrada = new Scanner(System.in);
		char cambio; 
		boolean quiere = false;

		System.out.println("\n¿Desea hacer algún cambio a su opción?\nSi es el caso eliga una de las siguientes opciones");
		System.out.println("\n1. Cambiar Palabra\n\tCon esta opción aparcerán nuevamente las palabras recomendadas anteriormente y elegir una distinta ");
		System.out.println("2. Cambiar Opciones\n\tCon esta opción aparcerán nuevas combinaciones(si las hay)  y podrás elegir una opción nueva");
		System.out.println("3. Ceder turno\n\tCon esta opción le pasarás el turno al siguiente jugador sin añadir palabra alguna");
		System.out.println("En caso que prefiera simplemente reubicar su palabra elija (0)");
		System.out.print("\nIngrese el número de la opción deseada: ");

		cambio = entrada.next().charAt(0);

		if(cambio == '1'){
			
			quiere = true;

 
			//Ciclo para borrar las letras que fueron agregadas en el arreglo letrasEnTablero luego de agregar la ultima palabra al tablero.
			for(int i = this.palabrasEnTablero.get(this.palabrasEnTablero.size()-1).length(); i>0; i--){
							
				this.letrasEnTablero.remove(this.letrasEnTablero.size()-1);

			}

			//Se borra la palabra que ya había sido agregada al tablero.
			this.palabrasEnTablero.remove(this.palabrasEnTablero.size() - 1);

			this.dibujarTablero();

			System.out.println("\nEstas son tus mejores opciones:\n");
			
			lc.mostrarPalabrasConPuntaje(lc.getPalabrasConPuntaje());	
			this.anadirAlTablero(lc);
		}

		else if(cambio == '2'){

			System.out.println("\n¿Qué opciones desas ver?");
			System.out.println("\n1. Anteriores\n2. Siguientes");
			System.out.print("\nIngrese el número de la opción deseada: ");

			char opcionDeseada = entrada.next().charAt(0);

			if(opcionDeseada=='1'){

				if((lc.apartadoAMostrarPalablasConPuntaje-1)>=0){
					quiere = true;

					lc.apartadoAMostrarPalablasConPuntaje--;
		
					//Ciclo para borrar las letras que fueron agregadas en el arreglo letrasEnTablero luego de agregar la ultima palabra al tablero.
					for(int i = this.palabrasEnTablero.get(this.palabrasEnTablero.size()-1).length(); i>0; i--){
									
						this.letrasEnTablero.remove(this.letrasEnTablero.size()-1);

					}

					//Se borra la palabra que ya había sido agregada al tablero.
					this.palabrasEnTablero.remove(this.palabrasEnTablero.size() - 1);

					this.dibujarTablero();

					System.out.println("\nEstas son tus mejores opciones:\n");
					
					lc.mostrarPalabrasConPuntaje(lc.getPalabrasConPuntaje());	
					this.anadirAlTablero(lc);
				}

				else{

					System.out.println("\nNo hay más opciones previas. Intentalo de nuevo.");
					return cambiarOpcion(lc);
	
				}

			}

			else if(opcionDeseada=='2'){

				if((lc.apartadoAMostrarPalablasConPuntaje+1)<=(lc.getPalabrasConPuntaje().size()/10)){
					quiere = true;

					lc.apartadoAMostrarPalablasConPuntaje++;
		
					//Ciclo para borrar las letras que fueron agregadas en el arreglo letrasEnTablero luego de agregar la ultima palabra al tablero.
					for(int i = this.palabrasEnTablero.get(this.palabrasEnTablero.size()-1).length(); i>0; i--){
									
						this.letrasEnTablero.remove(this.letrasEnTablero.size()-1);

					}

					//Se borra la palabra que ya había sido agregada al tablero.
					this.palabrasEnTablero.remove(this.palabrasEnTablero.size() - 1);

					this.dibujarTablero();

					System.out.println("\nEstas son tus mejores opciones:\n");
					
					lc.mostrarPalabrasConPuntaje(lc.getPalabrasConPuntaje());	
					this.anadirAlTablero(lc);
				}

				else{

					System.out.println("\nNo hay más opciones posteriores. Intentalo de nuevo.");
					return cambiarOpcion(lc);
	
				}

			}
			
			else{

				System.out.println("Has digitado una opción erronea. Intentalo de nuevo.");
				return cambiarOpcion(lc);

			}
			
		}

		else if(cambio == '3'){
			
			quiere = true;

 
			//Ciclo para borrar las letras que fueron agregadas en el arreglo letrasEnTablero luego de agregar la ultima palabra al tablero.
			for(int i = this.palabrasEnTablero.get(this.palabrasEnTablero.size()-1).length(); i>0; i--){
							
				this.letrasEnTablero.remove(this.letrasEnTablero.size()-1);

			}

			//Se borra la palabra que ya había sido agregada al tablero.
			this.palabrasEnTablero.remove(this.palabrasEnTablero.size() - 1);

			this.dibujarTablero();

			System.out.println("\nQue lástima que no se pudo, ya llegará otra ronda\n");

			try {
				FileWriter respaldo = new FileWriter(nombreArchivoRespaldo, true);
				PrintWriter escritorDelRespaldo = new PrintWriter(respaldo);

				escritorDelRespaldo.println("\n\nEn este turno el jugador cedió el turno.");

				escritorDelRespaldo.print("\n\n   0 1 2 3 4 5 6 7 8 9 ");

				for(int i = 0; i<10; i++){
					
					escritorDelRespaldo.println("\n  ---------------------");
					escritorDelRespaldo.print( i + " |");
					for(int j = 0; j < 10; j++){
						escritorDelRespaldo.print(tablero[i][j] + "|");
					}
				}

				escritorDelRespaldo.println("\n  ---------------------");

				escritorDelRespaldo.close();


			} catch (IOException e) {
				System.out.println("ha ocurrido un problema con el archivo, la información no será respaldada"+e.getMessage());
			}
			
		}

		else if (cambio == '0'){
			quiere = false;
		}

		else{
			System.out.println("Has digitado una opción erronea. Intentalo de nuevo.");
			return cambiarOpcion(lc);
		}

		return quiere;
	}


}