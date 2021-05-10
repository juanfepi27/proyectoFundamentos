/**
 * Clase en la que se guarda un caracter y un booleano para saber si ya 
 * ha sido utilizado en la palabra que se está formando.
 * 
 * @author Helmuth Trefftz
 * @version 2021 04 27
 */
public class UsedLetter {
    char letter;
    boolean used;

    /**
     * Constructor.
     * Se recibe la letra y si está siendo usada o no.
     */
    public UsedLetter(char letter, boolean used) {
        this.letter = letter;
        this.used = used;
    }
    
}
