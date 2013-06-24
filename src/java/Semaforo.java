import jason.environment.grid.Location;
import java.util.Timer;
import java.util.TimerTask;

public class Semaforo{
	public StreetModel smodel;
	public Location location;
	//Origens
	public Location origem1;
	public Location origem2;
	//Ruas
	public Location[] rua1;
	public Location[] rua2;
	//Fila de carros
	public int fila;
	//Aberto
	private boolean aberto1;
	//Tempo de abertura (em segundos)
	public int tempo1;
	public int tempo2;
	//Timer (contador)
	private Timer timer;
	
	
	public Semaforo(StreetModel model, Location location, Location origem1, Location origem2, int tempo1, int tempo2, Location[] rua1, Location[] rua2){
		this.smodel = model;
		this.location = location;
		this.origem1 = origem1;
		this.origem2 = origem2;
		this.tempo1 = tempo1;
		this.tempo2 = tempo2;
		this.rua1 = rua1;
		this.rua2 = rua2;
		
		this.aberto1 = true;
		this.fila = countCarros();
		
		timer = new Timer();
		timer.schedule(new OpenTask(), tempo1*1000);
	}
	
	public boolean canPass(Location origin){
		if(origin.x == origem1.x && origin.y == origem1.y){
			return aberto1;
		}
		if(origin.x == origem2.x && origin.y == origem2.y){
			return !aberto1;
		}
		
		return false;
	}
	
	public int countCarros(){ //Conta os carros da rua parada
		int carros = 0;
		if(aberto1){
			for(int cont = 0; cont < rua2.length; cont++)
				if(smodel.hasObject(StreetModel.CARRO, rua2[cont]))
					carros++;
		} else {
			for(int cont = 0; cont < rua1.length; cont++)
				if(smodel.hasObject(StreetModel.CARRO, rua1[cont]))
					carros++;
		}
		System.out.println("[SINAL "+location+"] count: "+carros);
		return carros;
	}
	
	//TIMER TASK
		class OpenTask extends TimerTask{
			public void run(){
				timer.cancel();
				
				aberto1 = !aberto1;
				
				fila = countCarros();
				
				timer = new Timer();
				timer.schedule(new OpenTask(), (aberto1 ? tempo1 : tempo2)*1000);
			}
		}
}
