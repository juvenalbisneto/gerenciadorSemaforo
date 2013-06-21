import java.util.Random;
import java.util.HashMap;
import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;
//import jason.environment.grid.Area;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class StreetModel extends GridWorldModel {
	public static final int GridSize = 10;
	public static final int TIMER = 2000;
	
	public static final int SEMAFORO = 8;
	public static final int CARRO = 16;
	
//	Location lcar1 = new Location(GridSize-1, GridSize-1);
//	Location lcar2 = new Location(GridSize-1, GridSize-2);
	Location origin = new Location(0, 0);
	
	private HashMap<Integer, Location> inicio;
	private HashMap<Location, Integer[]> direcoes;
	private Vector<Location> carros;
	
	private Timer timer;
	
//	Area areaSem0;
//	Area areaSem1;
	
	public StreetModel(int w, int h, int nbAgs) {
		super(GridSize,GridSize, nbAgs);
		
		inicio = new HashMap<Integer, Location>();
		direcoes = new HashMap<Location, Integer[]>();
		carros = new Vector<Location>();
		
		mapGenerator();
		
		addCarro(origin);
		addCarro(new Location(2,0));
		addCarro(new Location(4,0));
//		moveCarros();
//		moveCarros();
		
		timer = new Timer();
		timer.schedule(new MoveTask(), TIMER);
		
//		Integer[] dir = new Integer[2];
//		dir[0]=Direcao.ESQUERDA;
//		dir[1]=Direcao.BAIXO;
//		direcoes.put(new Location(0,0), dir);
		
//		addWall(1, 1, 1, 1); // obstaculo em (1,1) 
//		setAgPos(0, GridSize-1, 0);
//		setAgPos(1, 0, GridSize-1);
//		add(SEMAFORO, lsem1);
//		add(SEMAFORO, lsem2);
		
//		areaSem0 = new Area(new Location(0, 0), new Location(0, 1));
//		areaSem1 = new Area(new Location(2, 1), new Location(2, 2));
//		inicio.put(0, new Location(GridSize-1, 0));
//		inicio.put(1, new Location(0, GridSize-1));
	}
	
	
	//MAP GENERATOR
	public void mapGenerator(){
		Integer[] direcaoParaBaixo = new Integer[1];
		direcaoParaBaixo[0] = Direcao.BAIXO;
		for(int contX = 0; contX < GridSize; contX++){
			for(int contY = 0; contY < GridSize; contY++){
				direcoes.put(new Location(contX,contY), direcaoParaBaixo);
			}
		}
	}
	
	public boolean sinalVermelho(){
		//TODO
		return true;
	}
	
	
	//CARRO
	public boolean addCarro(Location loc){
		if(isFree(CARRO, loc)){
			add(CARRO, loc);
			carros.add(loc);
			return true;
		}
		return false;
	}
	public boolean removeCarro(Location loc){
		remove(CARRO, loc);
		carros.removeElement(loc);
		return true;
	}
	public void moveCarros(){
		System.out.println("QTD Carros: "+carros.size());
		for(int cont = 0; cont < carros.size(); cont++){
			moveCarroAt(carros.get(cont));
			System.out.println("[carro] at ["+carros.get(cont).x+"]["+carros.get(cont).y+"]");
		}
		timer.schedule(new MoveTask(), TIMER);
	}
    public boolean moveCarroAt(Location location){
    	int sentido = escolheSentido(location);
    	
    	switch(sentido){
    		case Direcao.ESQUERDA:
    			moveCarroFromTo(location,-1,0);
    			break;
    		case Direcao.DIREITA:
    			moveCarroFromTo(location,+1,0);
    			break;
    		case Direcao.CIMA:
    			moveCarroFromTo(location,0,-1);
    			break;
    		case Direcao.BAIXO:
    			moveCarroFromTo(location,0,+1);
    			break;
    		default:
    			return false;
    	}
        
        return true;
    }
	public int escolheSentido(Location location){
		Integer[] direcoes = this.direcoes.get(location);
		
		if(direcoes.length == 1){
			return direcoes[0];
		} else if(direcoes.length > 0){
			Random generator = new Random(); 
			int direcao = generator.nextInt(direcoes.length);
			return direcoes[direcao];
		} else
			return 0;
	}
    public void moveCarroFromTo(Location location, int deslocamentoX, int deslocamentoY){
    	Location auxLocation = location;
    	auxLocation.x += deslocamentoX;
    	auxLocation.y += deslocamentoY;
    	
    	if(auxLocation.x >= GridSize || auxLocation.y >= GridSize)
    		return;
    	
    	if(!hasObject(CARRO,auxLocation)){
    		removeCarro(location);
    		addCarro(auxLocation);
    	}
    }
    
    
    //????
    public void setPosInicial (int ag){
		 setAgPos(ag, inicio.get(ag));
		 
	}

    
    //GETTERS E SETTERS
	public HashMap<Integer, Location> getInicio() {
		return inicio;
	}
	public void setInicio(HashMap<Integer, Location> inicio) {
		this.inicio = inicio;
	}
	
	public HashMap<Location, Integer[]> getDirecoes() {
		return direcoes;
	}
	public void setDirecoes(HashMap<Location, Integer[]> direcoes) {
		this.direcoes = direcoes;
	}
    
    //TIMER TASK
	class MoveTask extends TimerTask{
		public void run(){
			moveCarros();
		}
	}
}
