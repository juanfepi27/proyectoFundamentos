public class Puntaje{

	private String palabra;
    private int puntos;

	public Puntaje(String s){
		this.palabra = s;
		this.puntos = 0;
	}
	
	public String getPalabra(){
		return this.palabra;
	}

	public void setPuntos(int n){
		this.puntos = n;
	}

  public int getPuntos(){
		return this.puntos;
	}
	
	
}
