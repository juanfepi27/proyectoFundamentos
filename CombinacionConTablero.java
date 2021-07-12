/**
 * La funcionalidad de esta clase es la de crear objetos los cuales son combinaciones resultado de usar letras en el tablero.
 * @author Juan Felipe Pinzón Trejo
 * @author Maria Paula Ayala Lizarazo
 * @author Gian Paul Sánchez Aristizabal
 * @version 02/06/2021
 */
public class CombinacionConTablero {
    
    private String combinacion;

    private char letraEspecial;

    /**
     * Constructor el cual me sirve para rellenar los dos atributos.
     * @param comb Combinación recibida.
     * @param letra Letra en tablero recibida.
     */
    public CombinacionConTablero(String comb, char letra){
        this.combinacion = comb;
        this.letraEspecial = letra;
    }

    /**
     * Método getter para obtener la combianción mencionada previamente.
     * @return Retorna el String correspondiente a la combianción.
     */
    public String getCombinacion(){
        return this.combinacion;
    }

    /**
     * Método getter para obtener el valor del atributo letraEspecial mencionado previamente.
     * @return Retorna el char correspondiente a la letra.
     */
    public char getLetraEspecial(){
        return this.letraEspecial;
    }


}
