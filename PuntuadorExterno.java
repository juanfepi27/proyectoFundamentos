import java.util.Scanner;

public class PuntuadorExterno {

    public static void darPuntaje(String palabra) {

        int puntos = 0;

        for (int j = 0; j < palabra.length(); j++) {

            switch (palabra.charAt(j)) {

                case 'a':
                    puntos += 1;
                break;

                case 'b':
                    puntos += 3;
                break;

                case 'c':
                    if ((j + 1) < (palabra.length() - 1)) {

                        if (palabra.charAt((j + 1)) == 'h') {
                            puntos += 5;
                            j++;
                            break;
                        } else {
                            puntos += 3;
                            break;
                        }
                    } else {
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
                    if ((j + 1) < (palabra.length() - 1)) {

                        if (palabra.charAt((j + 1)) == 'l') {
                            puntos += 8;
                            j++;
                            break;
                        } else {
                            puntos += 1;
                            break;
                        }
                    } else {
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
                    if ((j + 1) < (palabra.length() - 1)) {

                        if (palabra.charAt((j + 1)) == 'r') {
                            puntos += 8;
                            j++;
                            break;
                        } else {
                            puntos += 1;
                            break;
                        }
                    } else {
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

        System.out.println("El puntaje de la palabra es: "+puntos);

    }

    public static void main(String[] args) {
        Scanner ingreso = new Scanner(System.in);
        char siguiente;

        do{

            siguiente='0';

            System.out.print("\nIngrese la palabra a puntuar: ");
            String palabra = ingreso.next();
            ingreso.nextLine();

            PuntuadorExterno.darPuntaje(palabra);

            System.out.print("\n\n¿Desea puntuar otra palabra?\n\nSi desea seguir marque 1\nSi no lo desea ingrese cualquier otro digito y pulse enter\n\nIngrese su respuesta: ");
            siguiente = ingreso.next().charAt(0);
            ingreso.nextLine();
        
        }while(siguiente=='1');

    }
}
