/**
 * Esta clase genera posibles palabras a partir de las letras
que el usuario tiene en su mano.
 * @author Helmuth Trefftz
 * @author Gian Paul Sánchez Aristizabal
 * @author Maria Paula Ayala Lizarazo
 * @author Juan Felipe Pinzón Trejo
 * @version 03/06/2021
 */

import java.util.ArrayList;

public class LetterCombinations{

	
	/**
	* Arreglo auxiliar que permite ordenar las letras en la mano
	* del jugador en un arreglo de caracteres
	*/
	public UsedLetter [] usedLetters;
	/**
	 * Atributo auxiliar para ayudar a la creación de las variantes 
	 * de diferente longitud que resultan de las letras en la mano
	 * del usuario
	 * 
	 */
	public int longitud;
	
	/**
	 * Objeto de la clase Diccionario
	 */
	public Diccionario diccionario;
	
	/**
	 * Objeto de la clase Tablero
	 */
	public Tablero tablero;

	/**
	 * objeto que se encarga de asiganar el "pantallazo" en el que se encuentra de las palabras con puntaje
	 */
	public int apartadoAMostrarPalablasConPuntaje = 0;
	

	private ArrayList <String> palabrasASugerir = new ArrayList<>();
	
	private ArrayList <Puntaje> palabrasConPuntaje = new ArrayList<>();

	private ArrayList<CombinacionConTablero> combinacionesLetrasTablero = new ArrayList<>();

	private char letraEsp;

	private ArrayList<String> combinacionesPalabrasTablero = new ArrayList<>();

	

	/**
		* En el constructor debe recibir un diccionario con las palabras
		* válidas y el tablero en el que se esté jugando
	*/
	public LetterCombinations(Diccionario diccionario, Tablero tablero) {
		this.diccionario = diccionario;
		this.tablero = tablero;
	}


	/**
		* Recibir las letras que el usuario tiene en la mano 
		* y guardarlas en el arreglo usedLetters
		* 
		* @param s Letras que el usuario tiene en su mano
	*/
	public void init(String s) {

		usedLetters = new UsedLetter[s.length()];

		for(int i = 0; i < s.length(); i++){
			usedLetters[i] = new UsedLetter(s.charAt(i), false);
		}
		
	}


	/**
		* Método recursivo que genera las posibles variaciones con 
		* las letras que el usuario tiene en la mano.
		* Cada variación se busca en el diccionario para saber si
		* es válida.
		* El método es recursivo.
		* 
		* @param s String del cual sacaremos las posibles combinaciones.
	*/
	public void receiveString(String s) {

		if(s.length() == longitud) {

			//Si se encuentra en el diccionario, si no se encuentra en palabrasASugerir y si no se encuentra en palabrasEnTablero, añada la palabra en palabrasASugerir.
			if(diccionario.buscarPalabras(s) && !(palabrasASugerir.contains(s)) && !(tablero.getPalabrasEnTablero().contains(s)) && (tablero.getPalabrasEnTablero().size()==0)) {

				this.palabrasASugerir.add(s);
				
			}

			crearCombinacionesConTablero(s);

		}
		

		for(int i = 0; i < usedLetters.length; i++) {
			if(!usedLetters[i].used) {
				usedLetters[i].used = true;
				receiveString(s+usedLetters[i].letter);
				usedLetters[i].used = false;
			}
		}
	}

	/**
		* Este método invoca el método receiveString para que  
		* genere palabras de longitud 2, 3, ... n (número de 
		* letras que el usuario tiene en su mano).
		* 
		* @param letrasDeMiMano String con las letras que el usuario tiene
		* en la mano.
	*/
	public void crearPalabras(String letrasDeMiMano) {
		String letras = letrasDeMiMano;
		init(letras);

		//Cambiaré la longitud a 1 para poder usar este método con 1 letra y de esta manera poder usar el método combinacionesPalabrasEnTableroYLetrasEnMano(String s) 
		for(longitud = 1; longitud <= letras.length(); longitud++) {
			receiveString("");
		}
	}


	/**
	 * Este método le da un puntaje a las palabras del arreglo palabrasASugerir.
	 * Se hace un ciclo en el cual se crea un objeto Puntaje utilizando la palabra en 
	 * palabrasASugerir y luego a este objeto se le establece un puntaje.
	 * Posteriormente el objeto es agregado a un array llamado palabrasConPuntaje.
	 * 
	 * @param palabrasASugerir Arreglo de combinaciones generadas que se encuentran en el diccionario.
	 */
	public void darPuntaje(ArrayList<String> palabrasASugerir){

		for(int i = 0; i < palabrasASugerir.size(); i++){

			Puntaje palabraPuntaje = new Puntaje (palabrasASugerir.get(i));
			int puntos = 0;
      
			for(int j = 0; j<palabrasASugerir.get(i).length(); j++){

				switch(palabrasASugerir.get(i).charAt(j)){

					case 'a': 
						puntos += 1;
						break;

					case 'b':
						puntos += 3;
						break;

					case 'c': 
          				if((j+1) < (palabrasASugerir.get(i).length()-1)){

          					if(palabrasASugerir.get(i).charAt((j+1)) == 'h'){
								puntos += 5;
								j++;
								break;
							}
							else{
						  		puntos += 3;
          	  					break;
							}		
          				}			
						else{
							puntos += 3;
          					break;
						}

					case 'd': 
							puntos += 2;
							break;

					case 'e': 
							puntos += 1;
							break;

					case 'f': 
							puntos += 4;
							break;

					case 'g': 
							puntos += 2;
							break;

					case 'h': 
							puntos += 4;
							break;

					case 'i': 
							puntos += 1;
							break;
					
					case 'j': 
							puntos += 8;
							break;

					case 'l': 
						if((j+1) < (palabrasASugerir.get(i).length()-1)){

          					if(palabrasASugerir.get(i).charAt((j+1)) == 'l'){
								puntos += 8;
								j++;
								break;
							}
							else{
						  		puntos += 1;
          	  					break;
							}			
          				}
						else{
							puntos += 1;
							break;
					}

					case 'm': 
						puntos += 3;
						break;

					case 'n': 
						puntos += 1;
						break;

					case 'ñ': 
						puntos += 8;
						break;

					case 'o': 
						puntos += 1;
						break;

					case 'p': 
						puntos += 3;
						break;

          			case 'q': 
						puntos += 5;
						break;

          			case 'r': 
         				if((j+1) < (palabrasASugerir.get(i).length()-1)){

          					if(palabrasASugerir.get(i).charAt((j+1)) == 'r'){
								puntos += 8;
								j++;
								break;
							}
							else{
						  		puntos += 1;
          	  					break;
							}				
          				}
						else{
							puntos += 1;
          					break;
						}

          			case 's': 
						puntos += 1;
						break;

          			case 't': 
						puntos += 1;
						break;

          			case 'u': 
						puntos += 1;
						break;

          			case 'v': 
						puntos += 4;
						break;

          			case 'x': 
						puntos += 8;
						break;

          			case 'y': 
						puntos += 4;
						break;

          			case 'z': 
						puntos += 10;
						break;
				}
			}
			palabraPuntaje.setPuntos(puntos);
      		palabrasConPuntaje.add(palabraPuntaje);
    	}

		ordenarPalabrasPorPuntaje(palabrasConPuntaje);
		mostrarPalabrasConPuntaje(palabrasConPuntaje);
		
	}


	/**
	 * El siguiente método toma el atributo puntaje de cada objeto en 
	 * el arreglo palabrasConPuntaje y ordena los objetos de mayor a menor puntaje.
	 * 
	 * @param palabrasConPuntaje Arreglo de objetos de la clase Puntaje.
	 */
	public void ordenarPalabrasPorPuntaje(ArrayList<Puntaje> palabrasConPuntaje){

    	for(int i = 0; i < palabrasConPuntaje.size() - 1; i++){

			for(int j = i+1; j < palabrasConPuntaje.size(); j++){

				if(palabrasConPuntaje.get(i).getPuntos() < palabrasConPuntaje.get(j).getPuntos()){

          			Puntaje temp = palabrasConPuntaje.get(i);

					palabrasConPuntaje.set(i, palabrasConPuntaje.get(j));
                    
          			palabrasConPuntaje.set(j, temp);

				}
			}
		}
		
	}


	/**
	 * El siguiente método muestra el atributo palabra de cada objeto
	 * en el arreglo palabrasConPuntaje, luego muestra su respectivo puntaje.
	 * 
	 * @param palabrasConPuntaje Arreglo de objetos de la clase Puntaje.
	 */
	public void mostrarPalabrasConPuntaje(ArrayList<Puntaje> palabrasConPuntaje){

		/*if(palabrasConPuntaje.size()<10){

			for(int i = 0; i < palabrasConPuntaje.size(); i++){
				System.out.println((i+1)+". " + palabrasConPuntaje.get(i).getPalabra() + " -> su puntaje es: " + palabrasConPuntaje.get(i).getPuntos());
			}

    	}
		else*/ if(((palabrasConPuntaje.size()-apartadoAMostrarPalablasConPuntaje*10)/10)==0){

			for(int i = (0+apartadoAMostrarPalablasConPuntaje*10); i < palabrasConPuntaje.size(); i++){
				System.out.println((i+1)+". " + palabrasConPuntaje.get(i).getPalabra() + " -> su puntaje es: " + palabrasConPuntaje.get(i).getPuntos());
			}

    	}
		else{

			int inicio = 0+apartadoAMostrarPalablasConPuntaje*10;
			int fin = 9+apartadoAMostrarPalablasConPuntaje*10;

			for(int i = inicio; i <= fin; i++){
				System.out.println((i+1)+". " + palabrasConPuntaje.get(i).getPalabra()+ " -> su puntaje es: " + palabrasConPuntaje.get(i).getPuntos());
			}
			
    	}
	}


	/**
	 * El siguiente método tiene la función de utilizar las palabras en el tablero y unirlas a las diferentes combinaciones de las letras en mi mano, si esta unión da como resultado una palabra entonces la agregamos a palabrasASugerir para que se le calcule el puntaje y sea sugerida al usuario. La unión se hace como prefijo, y luego se hace también como sufijo.
	 * @param s Combinación de las letras en mano.
	 */
	public void crearCombinacionesConTablero(String s){

		String palabraPorDelante, palabraPorDetras; 


		for(int i = 0; i < tablero.getPalabrasEnTablero().size(); i++){

			palabraPorDelante = tablero.getPalabrasEnTablero().get(i) + s;
			palabraPorDetras = s + tablero.getPalabrasEnTablero().get(i);

			if(diccionario.buscarPalabras(palabraPorDelante)){
				
				if(!palabrasASugerir.contains(palabraPorDelante) && !(tablero.getPalabrasEnTablero().contains(palabraPorDelante))){
					palabrasASugerir.add(palabraPorDelante);
					combinacionesPalabrasTablero.add(palabraPorDelante);
				}
			}
			
			if(diccionario.buscarPalabras(palabraPorDetras) && !(tablero.getPalabrasEnTablero().contains(palabraPorDelante))){
				
				if(!palabrasASugerir.contains(palabraPorDetras)){
					palabrasASugerir.add(palabraPorDetras);
					combinacionesPalabrasTablero.add(palabraPorDetras);
				}
			}

		}

	}


	/**
	 * Método getter para acceder al arreglo palabrasASugerir.
	 * 
	 * @return Retorna el ArrayList<> palabrasASugerir.
	 */
	public ArrayList<String> getPalabrasASugerir(){
		return this.palabrasASugerir;
	}



	/**
	 * Método getter para acceder al arreglo palabrasConPuntaje.
	 * 
	 * @return Retorna el ArrayList<> palabrasConPuntaje.
	 */
	public ArrayList<Puntaje> getPalabrasConPuntaje(){
		return this.palabrasConPuntaje;
	}


	/**
	 * Método getter para acceder al arreglo combinacionesLetrasTablero.
	 * 
	 * @return Retorna el ArrayList<> combinacionesLetrasTablero.
	 */
	public ArrayList<CombinacionConTablero> getCombinacionesLetrasTablero(){
		return this.combinacionesLetrasTablero;
	}


	/**
	 * Método getter para acceder al valor del atributo privado letraEsp.
	 * 
	 * @return Retorna el valor almacenado como char en letraEsp.
	 */
	public char getLetraEsp(){
		return this.letraEsp;
	}


	/**
	 * Método que invoca al método receiveStringConLetrasTablero para crear diversas combinaciones con las letras que tengo en mano añadiendo individualmente cada una de las letras del tablero.
	 * 
	 * @param letrasDeMiMano String que corresponde a las letras que tiene el ususario en mano.
	 * @param letraEnTablero char que corresponde al caracter del tablero que se utilizará en la formación de posibles combinaciones.
	 */
	public void crearPalabrasConLetrasTablero(String letrasDeMiMano, char letraEnTablero){
		
		String s = letrasDeMiMano + letraEnTablero;

		init(s);
		
		for(longitud = 2; longitud <= s.length(); longitud++){
			receiveStringConLetrasTablero("", letraEnTablero);
		}
		
	}


	/**
	 * Método que hace las diversas combinaciones entre las letrasEnMano y letrasEnTablero. Su principal función es sacar solamente las combinaciones que hagan uso de letras en tablero.
	 * 
	 * @param s String que corresponde a las letras que tiene el ususario en mano.
	 * @param letraEnTablero char que corresponde al caracter del tablero que se utilizará en la formación de posibles combinaciones.
	 */
	public void receiveStringConLetrasTablero(String s, char letraEnTablero){
		
		if(s.length() == longitud && s.contains(letraEnTablero+"")) {

			//Si se encuentra en el diccionario, si no se encuentra en palabrasASugerir y si no se encuentra en palabrasEnTablero, añada la palabra en palabrasASugerir.
			if(diccionario.buscarPalabras(s) && !(palabrasASugerir.contains(s)) && !(tablero.getPalabrasEnTablero().contains(s))) {
				
				this.palabrasASugerir.add(s);

				CombinacionConTablero comb = new CombinacionConTablero(s, letraEnTablero);
				combinacionesLetrasTablero.add(comb);
			}

		}
		

		for(int i = 0; i < usedLetters.length; i++) {
			if(!usedLetters[i].used) {
				usedLetters[i].used = true;
				receiveStringConLetrasTablero(s+usedLetters[i].letter, letraEnTablero);
				usedLetters[i].used = false;
			}
		}
	}



	/**
     * Método para saber si la palabra elegida está dentro de las combinaciones hechas con letras en tablero.
     * 
     * @param s Palabra a comparar con cada elemento del arreglo combinacionesLetrasTablero.
     * @return Retorna si existe o no existe esta combinacion dentro del arreglo combinacionesLetrasTablero(true o false).
     */
    public boolean existeCombinacionLetrasTablero(String s){

        boolean existe = false;

        for(int i = 0; i<this.getCombinacionesLetrasTablero().size(); i++){

            if((this.getCombinacionesLetrasTablero().get(i).getCombinacion()).equals(s)){
                letraEsp = this.getCombinacionesLetrasTablero().get(i).getLetraEspecial();
				return true;
            }

        }

        return existe;

    }


	/**
	 * Méotod para saber si la palabra elegida está dentro de las combinaciones creadas con las palabras que ya existen en tablero.
	 * 
	 * @param s Palabra a comparar con cada elemento del arreglo combinacionesPalabrasTablero.
	 * @return Retorna si existe o no combinacion dentro del arreglo combinacionesPalabrasTablero(true o false).
	 */
	public boolean existeCombinacionPalabrasTablero(String s){

        boolean existe = false;

        for(int i = 0; i<this.getCombinacionesPalabrasTablero().size(); i++){

            if((this.getCombinacionesPalabrasTablero().get(i)).equals(s)){
				return true;
            }

        }
        return existe;
    }

	/**
	 * Método getter utilizado para acceder al arreglo combiancionesPalabrasTablero.
	 * 
	 * @return Retorna el ArrayList<> combinacionesPalabrasTablero.
	 */
	public ArrayList<String> getCombinacionesPalabrasTablero(){
		return this.combinacionesPalabrasTablero;
	}

}