/**
 * En esta clase se maneja el diccionario con palabras válidas para el
 * juego Scrabble.
 * Las palabras se leen de un archivo texto, en el cual cada palabra
 * está en una línea diferente
 * 
 * @author Helmuth Trefftz
 * @author Gian Paul Sánchez Aristizabal
 * @author Maria Paula Ayala Lizarazo
 * @author Juan Felipe Pinzón Trejo
 * @version 13/05/2021
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Diccionario
{
  private ArrayList <String> diccionario = new ArrayList<>();
    
	/**
		* Constructor
		* Se recibe el nombre del archivo del cual se van a leer las
		* palabras válidas para el juego.
		* 
		* @param nombreArchivo Nombre del archivo a leer.
		*/
	public void leerDiccionario(String nombreArchivo){
		int cont = 0;
		try {
			Scanner in = new Scanner(new File(nombreArchivo));
			while(in.hasNext()) {
				String s = in.next();

                //Si en el diccionario se coloca alguna palabra con tilde, entonces las siguientes lineas de código reemplazan esas tildes por los mismos caracteres sin tildes.

				s = s.toLowerCase();
				
				s = s.replaceAll("á", "a");
                s = s.replaceAll("é", "e");
                s = s.replaceAll("í", "i");
                s = s.replaceAll("ó", "o");
                s = s.replaceAll("ú", "u");
				s = s.replaceAll("ü", "u");

				diccionario.add(s);


				cont++;
			}
		} catch(FileNotFoundException e) {
				System.out.println("Ese archivo no se encuentra");
		}

		this.ordenarDiccionario();
		System.out.println("Lei " + cont + " palabras");
	}
	
	
	/**
		* Este método busca una palabra en el diccionario
		* 
		* @param palabraBuscada palabra que se va a buscar en el diccionario
		* @return true si la palabra está en el diccionario, false de lo
		* contrario.
		*/
	public boolean buscarPalabras(String palabraBuscada) {

		int inicio = 0;
		int fin = (this.diccionario.size() - 1);
		int posicionActual;
		boolean resultado = false;

		while(inicio <= fin){

			posicionActual = (inicio+fin)/2;

			if(palabraBuscada.equals(this.diccionario.get(posicionActual))){

				resultado = true;
				break;

			}

			else if( palabraBuscada.compareTo(this.diccionario.get(posicionActual)) < 0){

				fin = posicionActual-1;

			}
			else{
				
				inicio = posicionActual+1;

			}
		}

		return resultado;
		
	}


	/**
	 * Este método utiliza el ordenamiento Select Sort para organizar
	 * de manera alfabética el arreglo de palabras en el diccionario.
	 */
    public void ordenarDiccionario(){
		
	    for(int i = 0; i < this.diccionario.size() - 1; i++){

		    for(int j = i+1; j < this.diccionario.size(); j++){

                if(this.diccionario.get(i).compareTo(this.diccionario.get(j)) > 0){

                    String temp = this.diccionario.get(i);

                    this.diccionario.set(i, this.diccionario.get(j));
                        
                    this.diccionario.set(j, temp);

                }

			}

		}

	}
}