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
	public static final int TIMER = 150;
	public static final int MAX_CARROS = 50;
	
	public static final int RUA = 8;
	public static final int CARRO = 16;
	
	public Location ORIGIN;
//	public Location END;
	
	private HashMap<Location, Semaforo> semaforos;
	private HashMap<Location, Integer[]> direcoes;
	public HashMap<Integer, Location> carros;
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
		//mapaGaltI();
		mapaGaltII();
		
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
	
	private void mapaGaltI(){
		ORIGIN = new Location(8,0);
		
		Location[] rua01 = new Location[2];
		rua01[0] = new Location(7,0);
		rua01[1] = new Location(8,0);
		Location[] rua02 = new Location[2]; 
		rua02[0] = new Location(6,1);
		rua02[1] = new Location(6,2);
		addSemaforo(0, new Location(6,0), new Location(7,0), new Location(6,1), rua01, rua02);
		
		Location[] rua11 = new Location[3];
		rua11[0] = new Location(4,0); 
		rua11[1] = new Location(5,0); 
		rua11[2] = new Location(6,0);
		Location[] rua12 = new Location[6]; 
		rua12[0] = new Location(2,0); 
		rua12[1] = new Location(1,0); 
		rua12[2] = new Location(0,0);
		rua12[3] = new Location(0,1);
		rua12[4] = new Location(0,2);
		rua12[5] = new Location(0,3);
		addSemaforo(1, new Location(3,0), new Location(4,0), new Location(2,0), rua11, rua12);
		
		Location[] rua21 = new Location[2]; 
		rua21[0] = new Location(4,2); 
		rua21[1] = new Location(3,2);
		Location[] rua22 = new Location[2]; 
		rua22[0] = new Location(5,3); 
		rua22[1] = new Location(5,4);
		addSemaforo(2, new Location(5,2), new Location(4,2), new Location(5,3), rua21, rua22);
		
		Location[] rua31 = new Location[3]; 
		rua31[0] = new Location(3,3); 
		rua31[1] = new Location(3,2); 
		rua31[2] = new Location(3,1);
		Location[] rua32 = new Location[3]; 
		rua32[0] = new Location(2,4); 
		rua32[1] = new Location(1,4); 
		rua32[2] = new Location(0,4);
		addSemaforo(3, new Location(3,4), new Location(3,3), new Location(2,4), rua31, rua32);
		
		Location[] rua41 = new Location[3]; 
		rua41[0] = new Location(3,5); 
		rua41[1] = new Location(3,4); 
		rua41[2] = new Location(3,3);
		Location[] rua42 = new Location[3]; 
		rua42[0] = new Location(4,6); 
		rua42[1] = new Location(5,6); 
		rua42[2] = new Location(6,6);
		addSemaforo(4, new Location(3,6), new Location(3,5), new Location(4,6), rua41, rua42);
		
		Location[] rua51 = new Location[3]; 
		rua51[0] = new Location(6,5); 
		rua51[1] = new Location(6,4); 
		rua51[2] = new Location(5,4);
		Location[] rua52 = new Location[7]; 
		rua52[0] = new Location(7,6); 
		rua52[1] = new Location(8,6); 
		rua52[2] = new Location(8,5);
		rua52[3] = new Location(8,4);
		rua52[4] = new Location(8,3);
		rua52[5] = new Location(8,2);
		rua52[6] = new Location(7,2);
		addSemaforo(5, new Location(6,6), new Location(6,5), new Location(7,6), rua51, rua52);
		
		semaforos.get(new Location(6,0)).setIndireto(semaforos.get(new Location(5,2)));
		semaforos.get(new Location(3,0)).setIndireto(semaforos.get(new Location(6,0)));
		semaforos.get(new Location(5,2)).setIndireto(semaforos.get(new Location(6,6)));
		semaforos.get(new Location(3,4)).setIndireto(semaforos.get(new Location(3,0)));
		semaforos.get(new Location(3,6)).setIndireto(semaforos.get(new Location(3,4)));
		semaforos.get(new Location(6,6)).setIndireto(semaforos.get(new Location(3,6)));
		
		Integer[] esq   = new Integer[1];   esq[0] = Direcao.ESQUERDA;
		Integer[] dir   = new Integer[1];   dir[0] = Direcao.DIREITA;
		Integer[] cima  = new Integer[1];  cima[0] = Direcao.CIMA;
		Integer[] baixo = new Integer[1]; baixo[0] = Direcao.BAIXO;
		Integer[] dirBa = new Integer[2]; dirBa[0] = Direcao.BAIXO;     dirBa[1] = Direcao.DIREITA;
		Integer[] dirCi = new Integer[2]; dirCi[0] = Direcao.CIMA;      dirCi[1] = Direcao.DIREITA;
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
		direcoes.put(new Location(0,6), cima);
		direcoes.put(new Location(1,6), esq);
		direcoes.put(new Location(2,6), esq);
		direcoes.put(new Location(3,6), esq);
		direcoes.put(new Location(4,6), esq);
		direcoes.put(new Location(5,6), esq);
		direcoes.put(new Location(6,6), esq);
		direcoes.put(new Location(7,6), esq);
		direcoes.put(new Location(8,6), esq);
	}
	private void mapaGaltII(){
		int cont;
		
		// ESCOLHE A ORIGEM
		ORIGIN = new Location(24,0);
		
		// CONFIGURA A LOCALIZACAO E AS RUAS DOS SEMAFOROS
		// Semaforo 1
		Location[] rua01 = new Location[10];
		for(cont = 0; cont <= 9; cont++)
			rua01[cont] = new Location(7+cont,0);
		Location[] rua02 = new Location[10];
		for(cont = 0; cont <= 5; cont++)
			rua02[cont] = new Location(7+cont,0);
		rua02[6] = new Location(0,0);
		rua02[7] = new Location(0,1);
		rua02[8] = new Location(0,2);
		rua02[9] = new Location(0,3);
		addSemaforo(0, new Location(6,0), new Location(7,0), new Location(5,0), rua01, rua02);
		semaforos.get(new Location(6,0)).setIndireto(semaforos.get(new Location(19,0)));
		
		// Semaforo 2
		Location[] rua11 = new Location[5];
		for(cont = 0; cont <= 4; cont++)
			rua11[cont] = new Location(20+cont,0);
		Location[] rua12 = new Location[8];
		for(cont = 0; cont <= 7; cont++)
			rua12[cont] = new Location(19,1+cont);
		addSemaforo(1, new Location(19,0), new Location(20,0), new Location(19,1), rua11, rua12);
		semaforos.get(new Location(19,0)).setIndireto(semaforos.get(new Location(18,9)));
		
		// Semaforo 3
		Location[] rua21 = new Location[8];
		for(cont = 0; cont <= 7; cont++)
			rua21[cont] = new Location(10+cont,9);
		Location[] rua22 = new Location[8];
		for(cont = 0; cont <= 7; cont++)
			rua22[cont] = new Location(18,10+cont);
		addSemaforo(2, new Location(18,9), new Location(17,9), new Location(18,10), rua21, rua22);
		semaforos.get(new Location(18,9)).setIndireto(semaforos.get(new Location(6,0)));
		
		// Semaforo 4
		Location[] rua31 = new Location[8];
		for(cont = 0; cont <= 7; cont++)
			rua31[cont] = new Location(6,10+cont);
		Location[] rua32 = new Location[5];
		for(cont = 0; cont <= 4; cont++)
			rua32[cont] = new Location(1+cont,18);
		addSemaforo(3, new Location(6,18), new Location(6,17), new Location(5,18), rua31, rua32);
		semaforos.get(new Location(6,18)).setIndireto(semaforos.get(new Location(6,0)));
		
		// Semaforo 5
		Location[] rua41 = new Location[5];
		for(cont = 0; cont <= 4; cont++)
			rua41[cont] = new Location(6,19+cont);
		Location[] rua42 = new Location[5];
		for(cont = 0; cont <= 4; cont++)
			rua42[cont] = new Location(7+cont,24);
		addSemaforo(4, new Location(6,24), new Location(6,23), new Location(7,24), rua41, rua42);
		semaforos.get(new Location(6,24)).setIndireto(semaforos.get(new Location(6,18)));
		
		// Semaforo 6
		Location[] rua51 = new Location[9];
		rua51[0] = new Location(23,24);
		rua51[1] = new Location(24,24);
		for(cont = 2; cont <= 8; cont++)
			rua51[cont] = new Location(24,17+cont);
		Location[] rua52 = new Location[9];
		for(cont = 0; cont <= 5; cont++)
			rua52[cont] = new Location(22,18+cont);
		rua52[6] = new Location(21,18);
		rua52[7] = new Location(20,18);
		rua52[8] = new Location(19,18);
		addSemaforo(5, new Location(22,24), new Location(23,24), new Location(22,23), rua51, rua52);
		semaforos.get(new Location(22,24)).setIndireto(semaforos.get(new Location(6,18)));
		
		// DIZ AS LIGACOES DOS SINAIS
		
		
		// CRIA AS RUAS
		Integer[] esq   = new Integer[1];   esq[0] = Direcao.ESQUERDA;
		Integer[] dir   = new Integer[1];   dir[0] = Direcao.DIREITA;
		Integer[] cima  = new Integer[1];  cima[0] = Direcao.CIMA;
		Integer[] baixo = new Integer[1]; baixo[0] = Direcao.BAIXO;
		Integer[] dirBa = new Integer[2]; dirBa[0] = Direcao.BAIXO;     dirBa[1] = Direcao.DIREITA;
		Integer[] dirCi = new Integer[2]; dirCi[0] = Direcao.CIMA;      dirCi[1] = Direcao.DIREITA;
		Integer[] cimBa = new Integer[2]; cimBa[0] = Direcao.CIMA;      cimBa[1] = Direcao.BAIXO;
		
		for(cont = 7; cont <= 24; cont++)
			direcoes.put(new Location(cont,0), esq);
		for(cont = 1; cont <= 24; cont++)
			direcoes.put(new Location(cont,24), esq);
		for(cont = 0; cont <= 5; cont++)
			direcoes.put(new Location(cont,0), dir);
		for(cont = 1; cont <= 5; cont++)
			direcoes.put(new Location(cont,18), dir);
		for(cont = 7; cont <= 18; cont++)
			direcoes.put(new Location(cont,9), dir);
		for(cont = 20; cont <= 23; cont++)
			direcoes.put(new Location(cont,9), dir);
		for(cont = 7; cont <= 17; cont++)
			direcoes.put(new Location(cont,18), dir);
		for(cont = 19; cont <= 21; cont++)
			direcoes.put(new Location(cont,18), dir);
		for(cont = 0; cont <= 8; cont++)
			direcoes.put(new Location(6,cont), baixo);
		for(cont = 10; cont <= 17; cont++)
			direcoes.put(new Location(6,cont), baixo);
		for(cont = 19; cont <= 23; cont++)
			direcoes.put(new Location(6,cont), baixo);
		for(cont = 9; cont <= 23; cont++)
			direcoes.put(new Location(24,cont), baixo);
		for(cont = 18; cont <= 23; cont++)
			direcoes.put(new Location(22,cont), baixo);
		for(cont = 1; cont <= 17; cont++)
			direcoes.put(new Location(0,cont), cima);
		for(cont = 19; cont <= 24; cont++)
			direcoes.put(new Location(0,cont), cima);
		for(cont = 1; cont <= 8; cont++)
			direcoes.put(new Location(19,cont), cima);
		for(cont = 10; cont <= 17; cont++)
			direcoes.put(new Location(18,cont), cima);
		direcoes.put(new Location(6,9), dirBa);
		direcoes.put(new Location(6,18), dirBa);
		direcoes.put(new Location(19,9), dirCi);
		direcoes.put(new Location(18,18), dirCi);
		direcoes.put(new Location(0,18), dirCi);
		
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
				case Direcao.ESQUERDA:
					retorno +=  "<";
					break;
				case Direcao.BAIXO:
					retorno +=  ".";
					break;
				case Direcao.CIMA:
					retorno +=  "^";
					break;
				case Direcao.DIREITA:
					retorno +=  ">";
					break;
			}
		}
		
		
		return retorno;
	}
	
	
	//SEMAFORO
	public void addSemaforo(int index, Location loc, Location origem1, Location origem2, Location[] rua1, Location[] rua2){
		this.setAgPos(index, loc);
		semaforos.put(loc, new Semaforo(this, loc, origem1, origem2, 4, 4, rua1, rua2));
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
//    		if(auxLocation.x == END.x && auxLocation.y == END.y){
//    			removeCarro(index);
//    			Integer[] waitAux = new Integer[this.waitList.length+1];
//    			for(int cont = 0; cont < this.waitList.length; cont++)
//    				waitAux[cont] = this.waitList[cont];
//    			waitAux[waitAux.length-1] = index;
//    			this.waitList = waitAux;
//    		} else {
    			Semaforo aux = this.semaforos.get(auxLocation);
    			if(aux != null){
    				boolean can = aux.canPass(carros.get(index));
    	    		if(can){
    	    			removeCarro(index);
    	    			addCarro(index, auxLocation);
    	    		}
    	    	} else {
    	    		removeCarro(index);
    	    		addCarro(index, auxLocation);
    	    	}
//    		}
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
