import jason.environment.grid.Location;
import net.sourceforge.jFuzzyLogic.FIS;
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
	
	public int countCarros(int rua){
		int carros = 0;
		if(rua == 1){
			for(int cont = 0; cont < rua1.length; cont++){
				if(smodel.hasObject(StreetModel.CARRO, rua1[cont]))
					carros++;
			}	
		} else if(rua == 2){
			for(int cont = 0; cont < rua2.length; cont++){
				if(smodel.hasObject(StreetModel.CARRO, rua2[cont]))
					carros++;
			}
		}
		return carros;
	}
	
	public int fuzzy(){
        String fileName = "fcl/fuzzyTraffic.fcl";
        FIS fis = FIS.load(fileName,true);
        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '"+fileName+"'");
            return 0;
        }
        
        if(aberto1){
        	fis.setVariable("qtd", countCarros(2));
            fis.setVariable("qtddir", countCarros(1));
            fis.setVariable("qtdindir", 6);
        } else {
        	fis.setVariable("qtd", countCarros(1));
            fis.setVariable("qtddir", countCarros(2));
            fis.setVariable("qtdindir", 6);
        }

        fis.evaluate();
        
        double value = fis.getVariable("ajuste").defuzzify();
        
        return (int)value;
	}
	
	//TIMER TASK
		class OpenTask extends TimerTask{
			public void run(){
				timer.cancel();
				System.out.println("[SINAL "+location+"] - T1:"+tempo1+" T2:"+tempo2+ " [PRE]");
				int fuz = fuzzy();
				if(aberto1){
					tempo2 += fuz;
				} else {
					tempo1 += fuz;
				}
				System.out.println("[SINAL "+location+"] - T1:"+tempo1+" T2:"+tempo2+ " [POS]   - FUZZY: "+fuz+" count1: "+countCarros(1)+" cont2: "+countCarros(2));
				
				aberto1 = !aberto1;
				
				timer = new Timer();
				timer.schedule(new OpenTask(), (aberto1 ? tempo1 : tempo2)*1000);
			}
		}
}
