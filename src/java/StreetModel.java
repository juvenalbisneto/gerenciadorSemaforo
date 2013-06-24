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
	public static final int TIMER = 300;
	public static final int MAX_CARROS = 3;
	
	public static final int RUA = 8;
	public static final int CARRO = 16;
	
	public Location ORIGIN;
	public Location END;
	
	private HashMap<Location, Semaforo> semaforos;
	private HashMap<Location, Integer[]> direcoes;
	private HashMap<Integer, Location> carros;
	private Integer[] waitList;
	
	private Timer timer;
	
	public StreetModel(int gridSize) {
		super(gridSize,gridSize,6);
		this.gridSize = gridSize;
		
		semaforos = new HashMap<Location, Semaforo>();
		direcoes = new HashMap<Location, Integer[]>();
		carros = new HashMap<Integer, Location>();
		waitList = new Integer[0];
		
		mapGenerator();
		
		timer = new Timer();
		timer.schedule(new MoveTask(), TIMER);
	}
	
	
	//MAP GENERATOR
	public void mapGenerator(){
		// MAPA GALT
		ORIGIN = new Location(8,0);
		END = new Location(0,7);
		
		addSemaforo(0, new Location(6,0), new Location(7,0), new Location(6,1));
		addSemaforo(1, new Location(3,0), new Location(4,0), new Location(2,0));
		addSemaforo(2, new Location(5,2), new Location(4,2), new Location(5,3));
		addSemaforo(3, new Location(3,4), new Location(3,3), new Location(2,4));
		addSemaforo(4, new Location(3,6), new Location(3,5), new Location(4,6));
		addSemaforo(5, new Location(6,6), new Location(6,5), new Location(7,6));
		
		Integer[] esq   = new Integer[1];   esq[0] = Direcao.ESQUERDA;
		Integer[] dir   = new Integer[1];   dir[0] = Direcao.DIREITA;
		Integer[] cima  = new Integer[1];  cima[0] = Direcao.CIMA;
		Integer[] baixo = new Integer[1]; baixo[0] = Direcao.BAIXO;
		Integer[] dirBa = new Integer[2]; dirBa[0] = Direcao.DIREITA;   dirBa[1] = Direcao.BAIXO;
		Integer[] dirCi = new Integer[2]; dirCi[0] = Direcao.DIREITA;   dirCi[1] = Direcao.CIMA;
		Integer[] cimBa = new Integer[2]; cimBa[0] = Direcao.CIMA;      cimBa[1] = Direcao.BAIXO;
		
		direcoes.put(new Location(0,0), dir);
		direcoes.put(new Location(1,0), dir);
		direcoes.put(new Location(2,0), dir);
		direcoes.put(new Location(3,0), baixo);
		direcoes.put(new Location(4,0), esq);
		direcoes.put(new Location(5,0), esq);
		direcoes.put(new Location(6,0), esq);
		direcoes.put(new Location(7,0), esq);
		direcoes.put(new Location(8,0), esq);
		direcoes.put(new Location(0,1), cima);
		direcoes.put(new Location(3,1), baixo);
		direcoes.put(new Location(6,1), cima);
		direcoes.put(new Location(0,2), cima);
		direcoes.put(new Location(3,2), dirBa);
		direcoes.put(new Location(4,2), dir);
		direcoes.put(new Location(5,2), dir);
		direcoes.put(new Location(6,2), dirCi);
		direcoes.put(new Location(7,2), dir);
		direcoes.put(new Location(8,2), baixo);
		direcoes.put(new Location(0,3), cima);
		direcoes.put(new Location(3,3), baixo);
		direcoes.put(new Location(5,3), cima);
		direcoes.put(new Location(8,3), baixo);
		direcoes.put(new Location(0,4), dirCi);
		direcoes.put(new Location(1,4), dir);
		direcoes.put(new Location(2,4), dir);
		direcoes.put(new Location(3,4), dirBa);
		direcoes.put(new Location(4,4), dir);
		direcoes.put(new Location(5,4), dirCi);
		direcoes.put(new Location(6,4), baixo);
		direcoes.put(new Location(8,4), baixo);
		direcoes.put(new Location(0,5), cima);
		direcoes.put(new Location(3,5), baixo);
		direcoes.put(new Location(6,5), baixo);
		direcoes.put(new Location(8,5), baixo);
		direcoes.put(new Location(0,6), cimBa);
		direcoes.put(new Location(1,6), esq);
		direcoes.put(new Location(2,6), esq);
		direcoes.put(new Location(3,6), esq);
		direcoes.put(new Location(4,6), esq);
		direcoes.put(new Location(5,6), esq);
		direcoes.put(new Location(6,6), esq);
		direcoes.put(new Location(7,6), esq);
		direcoes.put(new Location(8,6), esq);
		direcoes.put(new Location(0,7), baixo);
		
		//MAPA SIMPLES
//		ORIGIN = new Location(5,1);
//		END = new Location(1,5);
//		Integer[] esq = new Integer[1];   esq[0] = Direcao.ESQUERDA;
//		Integer[] baixo = new Integer[1]; baixo[0] = Direcao.BAIXO;
//		Integer[] baixoEsq = new Integer[2]; baixoEsq[0] = Direcao.BAIXO; baixoEsq[1] = Direcao.ESQUERDA;
//		direcoes.put(new Location(1,1), baixo);
//		direcoes.put(new Location(1,2), baixo);
//		direcoes.put(new Location(1,3), baixo);
//		direcoes.put(new Location(1,4), baixo);
//		direcoes.put(new Location(1,5), baixo);
//		direcoes.put(new Location(4,2), baixo);
//		direcoes.put(new Location(4,3), baixo);
//		direcoes.put(new Location(2,1), esq);
//		direcoes.put(new Location(3,1), esq);
//		direcoes.put(new Location(5,1), esq);
//		direcoes.put(new Location(2,4), esq);
//		direcoes.put(new Location(3,4), esq);
//		direcoes.put(new Location(4,4), esq);
//		
//		direcoes.put(new Location(4,1), baixoEsq);
		
		//ALL GRID
//		for(int contX = 0; contX < gridSize; contX++){
//			for(int contY = 0; contY < gridSize; contY++){
//				direcoes.put(new Location(contX,contY), direcao);
//			}
//		}
		
		paintRuas();
	}
	private void paintRuas(){
		for(int contX = 0; contX < gridSize; contX++){
			for(int contY = 0; contY < gridSize; contY++){
				Integer[] dir = direcoes.get(new Location(contX,contY));
				if(dir != null)
					add(RUA,new Location(contX,contY));
			}
		}
	}
	public void carGenerator(){
		if(!hasObject(CARRO,ORIGIN)){
			if(waitList.length > 0){
				addCarro(waitList[0], ORIGIN);
				Integer[] aux = new Integer[waitList.length-1];
				for(int cont = 1; cont < this.waitList.length; cont++)
    				aux[cont-1] = new Integer(this.waitList[cont]);
				this.waitList = aux;
			} else if (carros.size() < MAX_CARROS){
				addCarro(carros.size(),ORIGIN);
			}
		}
	}
	public String direcao(int x, int y){
		String retorno = "";
		Integer[] dir = direcoes.get(new Location(x,y));
		for(int cont = 0; cont < dir.length; cont++){
			switch(dir[cont]){
			case Direcao.BAIXO:
				retorno +=  ".";
				break;
			case Direcao.CIMA:
				retorno +=  "^";
				break;
			case Direcao.ESQUERDA:
				retorno +=  "<";
				break;
			case Direcao.DIREITA:
				retorno +=  ">";
				break;
			}
		}
		
		
		return retorno;
	}
	
	
	//SEMAFORO
	public void addSemaforo(int index, Location loc, Location origem1, Location origem2){
		this.setAgPos(index, loc);
		semaforos.put(loc, new Semaforo(loc, origem1, origem2, 4, 4));
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
		Integer[] direcoes = this.direcoes.get(location);
		
		if(direcoes == null)
			return Direcao.NADA;
		
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
    	
    	if(!hasObject(CARRO,auxLocation) && inGrid(auxLocation.x, auxLocation.y)){
    		if(auxLocation.x == END.x && auxLocation.y == END.y){
    			removeCarro(index);
    			Integer[] waitAux = new Integer[this.waitList.length+1];
    			for(int cont = 0; cont < this.waitList.length; cont++)
    				waitAux[cont] = this.waitList[cont];
    			waitAux[waitAux.length-1] = index;
    			this.waitList = waitAux;
    		} else {
    			Semaforo aux = this.semaforos.get(auxLocation);
    			if(aux != null){
    				boolean can =aux.canPass(carros.get(index));
    				System.out.println("Semaforo: "+aux.location+" | "+can);
    	    		if(can){
    	    			removeCarro(index);
    	    			addCarro(index, auxLocation);
    	    		}
    	    	} else {
    	    		removeCarro(index);
    	    		addCarro(index, auxLocation);
    	    	}
    		}
    	}
    }

    
    //TIMER TASK
	class MoveTask extends TimerTask{
		public void run(){
			moveCarros();
			carGenerator();
			
			timer.cancel();
			timer = new Timer();
			timer.schedule(new MoveTask(), TIMER);
		}
	}
}
