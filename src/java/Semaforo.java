import jason.environment.grid.Location;
import java.util.Timer;
import java.util.TimerTask;

public class Semaforo{
	public Location location;
	//Origens
	public Location origem1;
	public Location origem2;
	//Aberto
	private boolean aberto1;
	//Tempo de abertura (em segundos)
	public int tempo1;
	public int tempo2;
	//Timer (contador)
	private Timer timer;
	
	
	public Semaforo(Location location, Location origem1, Location origem2, int tempo1, int tempo2){
		this.location = location;
		this.origem1 = origem1;
		this.origem2 = origem2;
		this.tempo1 = tempo1;
		this.tempo2 = tempo2;
		this.aberto1 = true;
		
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
	
	//TIMER TASK
		class OpenTask extends TimerTask{
			public void run(){
				timer.cancel();
				
				aberto1 = !aberto1;
				
				timer = new Timer();
				timer.schedule(new OpenTask(), (aberto1 ? tempo1 : tempo2)*1000);
			}
		}
}
