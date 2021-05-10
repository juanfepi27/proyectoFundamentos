import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class Tablero{

  public ArrayList<String> palabrasEnTablero = new ArrayList<>();
  
	public void anadirAlTablero(LetterCombinations lc){

		System.out.print("\nEscoge la opción de la palabra que quieres añadir al tablero: ");
		try{
			Scanner scanner = new Scanner(System.in);
			
			int opcionElegida = scanner.nextInt();

            this.palabrasEnTablero.add(lc.puntajes.get(opcionElegida-1).getPalabra());

            scanner.close();
			
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

}