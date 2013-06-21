import java.util.Random;
import java.util.HashMap;
import jason.environment.grid.Area;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class StreetModel extends GridWorldModel {
	public static final int GridSize = 10;
	
//	Location lcar1 = new Location(GridSize-1, GridSize-1);
//	Location lcar2 = new Location(GridSize-1, GridSize-2);
	Location lsem1 = new Location(GridSize-1, GridSize-3);
	Location lsem2 = new Location(GridSize-3, GridSize-1);
	
	private HashMap<Integer, Location> inicio;
	private HashMap<Location, Integer[]> direcoes;
	
	Area areaSem0;
	Area areaSem1;
	
	public StreetModel(int w, int h, int nbAgs) {
		super(GridSize,GridSize, 2);
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
	
	public boolean sinalVermelho(){
		//TODO
		return true;
	}

	public int escolheSentido(int ag){
		Location location = getAgPos(ag);
		Integer[] direcoes = this.direcoes.get(location);
		
		if(direcoes.length > 0){
			Random generator = new Random(); 
			int direcao = generator.nextInt(direcoes.length);
			return direcoes[direcao];
		} else
			return 0;
	}
	
	public void setPosInicial (int ag){
		 setAgPos(ag, inicio.get(ag));
		 
	}
	
    public boolean move(int ag) {
    	Location location = getAgPos(ag);
    	int sentido = escolheSentido(ag);
        
    	switch(sentido){
    		case Direcao.ESQUERDA:
    			location.x--;
    			break;
    		case Direcao.DIREITA:
    			location.x++;
    			break;
    		case Direcao.BAIXO:
    			location.y++;
    			break;
    		case Direcao.CIMA:
    			location.y--;
    			break;
    		default:
    			return false;
    	}

        setAgPos(ag, location); // move the robot in the grid
        
        // repaint the fridge and owner lo cations
//        if (view != null) {
//            view.update(lsem.x,lFridge.y);
//            view.update(lOwner.x,lOwner.y);
//        }
        return true;
    }

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
    
    

}
