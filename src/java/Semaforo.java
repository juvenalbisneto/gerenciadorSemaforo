import jason.environment.grid.Location;

public class Semaforo{
	private Location location;
	//Direcao
	private int direcao;
	//Aberto
	private boolean aberto;
	//Tempo de abertura
	private int tempo;
	//Semaforo irmao
	private Semaforo brother;
	
	public Semaforo(Location location, int direcao, boolean aberto, int tempo, Semaforo brother){
		this.location = location;
		this.direcao = direcao;
		this.aberto = aberto;
		this.tempo = tempo;
		this.brother = brother;
	}
	public Semaforo(){
		
	}
	
	// GETTERS E SETTERS
	public Location getLocation(){
		return this.location;
	}
	public void setLocation(int x, int y){
		this.location = new Location(x,y);
	}
	
	public int getDirecao() {
		return direcao;
	}
	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}
	
	public boolean getAberto(){
		return aberto;
	}
	public void setAberto(boolean aberto){
		this.aberto = aberto;
		this.brother.aberto = !this.aberto;
	}
	
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	public Semaforo getBrother(){
		return brother;
	}
	public void setBrother(Semaforo brother){
		this.brother = brother;
	}
}
