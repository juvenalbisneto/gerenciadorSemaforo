import java.util.Random;
import java.util.HashMap;
//import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;
//import jason.environment.grid.Area;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class StreetModel extends GridWorldModel {
	public int gridSize;
	public static final int TIMER = 1000;
	
	public static final int SEMAFORO = 8;
	public static final int CARRO = 16;
	
//	Location lcar1 = new Location(GridSize-1, GridSize-1);
//	Location lcar2 = new Location(GridSize-1, GridSize-2);
	Location origin = new Location(0, 0);
	
	private HashMap<Integer, Location> inicio;
	private HashMap<Location, Integer[]> direcoes;
	private HashMap<Integer, Location> carros;
	
	private Timer timer;
	
//	Area areaSem0;
//	Area areaSem1;
	
	public StreetModel(int gridSize) {
		super(gridSize,gridSize,0);
		this.gridSize = gridSize;
		
		inicio = new HashMap<Integer, Location>();
		direcoes = new HashMap<Location, Integer[]>();
		carros = new HashMap<Integer, Location>();
		
		mapGenerator();
		
		addCarro(0, origin);
		addCarro(1, new Location(2,0));
//		for(int cont = 0; cont < 10; cont ++)
//			moveCarros();

		
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
		if(view != null) System.out.println("TEM VIEW");
		Integer[] direcao = new Integer[2];
		direcao[0] = Direcao.BAIXO;
		direcao[1] = Direcao.DIREITA;
		for(int contX = 0; contX < gridSize; contX++){
			for(int contY = 0; contY < gridSize; contY++){
				direcoes.put(new Location(contX,contY), direcao);
			}
		}
	}
	
	
	//CARRO
	public boolean addCarro(int index, Location loc){
		if(isFree(CARRO, loc)){
			add(CARRO, loc);
			carros.put(index,loc);
			return true;
		}
		return false;
	}
	public boolean removeCarro(int index){
		remove(CARRO, carros.get(index));
		carros.remove(index);
		return true;
	}
	public void moveCarros(){
		for(int cont = 0; cont < carros.size(); cont++){
			moveCarro(cont);
		}
	}
    public boolean moveCarro(int index){
    	int sentido = escolheSentido(carros.get(index));
    	switch(sentido){
    		case Direcao.ESQUERDA:
    			moveCarroTo(index,-1,0);
    			break;
    		case Direcao.DIREITA:
    			moveCarroTo(index,+1,0);
    			break;
    		case Direcao.CIMA:
    			moveCarroTo(index,0,-1);
    			break;
    		case Direcao.BAIXO:
    			moveCarroTo(index,0,+1);
    			break;
    		default:
    			return false;
    	}
        
        return true;
    }
	public int escolheSentido(Location location){
		System.out.println("escolhe");
		Integer[] direcoes = this.direcoes.get(location);
		
		if(direcoes.length == 1){
			return direcoes[0];
		} else if(direcoes.length > 0){
			Random generator = new Random(); 
			int direcao = generator.nextInt(direcoes.length);
			return direcoes[direcao];
		} else
			return Direcao.NADA;
	}
    public void moveCarroTo(int index, int deslocamentoX, int deslocamentoY){
    	Location auxLocation = new Location(carros.get(index).x, carros.get(index).y);
    	auxLocation.x += deslocamentoX;
    	auxLocation.y += deslocamentoY;
    	
    	if(isFree(auxLocation) && inGrid(auxLocation.x, auxLocation.y)){
    		removeCarro(index);
    		addCarro(index, auxLocation);
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
			timer.cancel();
			
			timer = new Timer();
			timer.schedule(new MoveTask(), TIMER);
		}
	}
}
