/*
1. Tablero - Alberto
2. Faldero - Alfredo
3. Galena - Angela
4. Lacra - Clara
5. Deudora - Eduardo
6. Regador - Gerardo
7. Trecho - Hector
8. Arido - Dario
9. Bailes - Basile
10. Reclamo - Carmelo
11. Trama - Marta
12. Meato - Mateo
13. Camión - Mónica
14. Oscilan - Nicolás
15. Corrida - Ricardo
16. Saunas - Susana 
17. Aretes - Teresa
18. Invocare - Veronica
19. Riesgo - Sergio
20. Ebanistas - Sebastian
 */

/**
 * Esta clase genera posibles palabras a partir de las letras que
 * el usario tiene en su mano
 * @author Helmuth Trefftz
 */

import java.util.ArrayList;
import java.util.Scanner;

public class LetterCombinations{

	UsedLetter [] usedLetters;
	int longitud;
	Diccionario diccionario;
	ArrayList <String> palabrasValidadas = new ArrayList<>();
	ArrayList <Puntaje> puntajes = new ArrayList<>();
	
	/**
		* En el constructor debe recibir un diccionario con las palabras
		* válidas
	*/
	public LetterCombinations(Diccionario diccionario) {
		this.diccionario = diccionario;
	}

	/**
		* Recibir las letras que el usuario tiene en la mano 
		* y guardarlas en el arreglo usedLetters
		* 
		* @param s Letras que el usuario tiene en su mano
	*/
	public void init(String s) {

		usedLetters = new UsedLetter[s.length()];

		for(int i = 0; i < s.length(); i++) {
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
		* @param s String que se va conformando hasta el momento.
	*/
	public void receiveString(String s) {

		if(s.length() == longitud) {

			if(diccionario.buscarPalabras(s)) {
				if(!(palabrasValidadas.contains(s))) {
					this.palabrasValidadas.add(s);
				}
			}

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
		for(longitud = 2; longitud <= letras.length(); longitud++) {
			receiveString("");
		}
	}


	public void mostrarListaValida(){//OJO
		for(int i = 0; i<this.palabrasValidadas.size(); i++){
			System.out.println(this.palabrasValidadas.get(i));
		}
	}


	public void darPuntaje(ArrayList<String> palabrasValidadas){

		for(int i = 0; i < palabrasValidadas.size(); i++){

			Puntaje palabraPuntaje = new Puntaje (palabrasValidadas.get(i));
			int puntos = 0;
      
			for(int j = 0; j<palabrasValidadas.get(i).length(); j++){

				switch(palabrasValidadas.get(i).charAt(j)){

					case 'a': 
                        puntos += 1;
                        break;

                    case 'b':
                        puntos += 3;
                        break;

					case 'c': 
                        if((j+1) != (palabrasValidadas.get(i).length()-1)){

          	                if(palabrasValidadas.get(i).charAt((j+1)) == 'h'){
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
					    if((j+1) != (palabrasValidadas.get(i).length()-1)){

          	                if(palabrasValidadas.get(i).charAt((j+1)) == 'l'){
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
         	            if((j+1) != (palabrasValidadas.get(i).length()-1)){

          	                if(palabrasValidadas.get(i).charAt((j+1)) == 'r'){
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
            puntajes.add(palabraPuntaje);
        }

		ordenarPuntajes();
		mostrarPuntajes();
		
	}


	public void ordenarPuntajes(){

        for(int i = 0; i < this.puntajes.size() - 1; i++){

			for(int j = i+1; j < this.puntajes.size(); j++){

				if(this.puntajes.get(i).getPuntos() < this.puntajes.get(j).getPuntos()){

                    Puntaje temp = this.puntajes.get(i);

				    this.puntajes.set(i, this.puntajes.get(j));
                    
                    this.puntajes.set(j, temp);

				}
			}
		}
		
	}


	public void mostrarPuntajes(){

		if(this.puntajes.size()<=10){

			for(int i = 0; i < this.puntajes.size(); i++){
				System.out.println((i+1)+". ' " + this.puntajes.get(i).getPalabra()+ " '" + " -> su puntaje es: " + this.puntajes.get(i).getPuntos());
			}

        }
        else{

			for(int i = 0; i < 10; i++){
				System.out.println((i+1)+". ' " + this.puntajes.get(i).getPalabra()+ " '" + " -> su puntaje es: " + this.puntajes.get(i).getPuntos());
			}
			
        }
	}

}