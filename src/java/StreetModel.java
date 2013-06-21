import java.util.HashMap;
import jason.environment.grid.Area;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;


public class StreetModel extends GridWorldModel {
	
//	public static final int CARRO = 16;
	public static final int SEMAFORO = 16;

	public static final int sentido1 = 1; // esq -> dir
	public static final int sentido2 = 2; // dir -> esq
	public static final int sentido3 = 3; // baixo -> cima
	public static final int sentido4 = 4; // cima -> baixo
	
	public static final int emFrente = 1;
	public static final int direito = 2;
	public static final int esquerda = 3;
	public static final int frenteDireita = 4;
	public static final int frenteEsquerda = 5;
	
	public static final int GridSize = 3;
	
//	Location lcar1 = new Location(GridSize-1, GridSize-1);
//	Location lcar2 = new Location(GridSize-1, GridSize-2);
	Location lsem1 = new Location(GridSize-1, GridSize-3);
	Location lsem2 = new Location(GridSize-3, GridSize-1);
	
	private HashMap<Integer, Location> inicio;
	Area areaSem0;
	Area areaSem1;
	
	public StreetModel(int w, int h, int nbAgs) {
		super(GridSize,GridSize, 2);
		addWall(1, 1, 1, 1); // obstaculo em (1,1) 
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
		Location r1 = getAgPos(ag);
		
		if(r1.y == 0 && r1.x < GridSize -1) 		return sentido1;
		if(r1.y == GridSize-1 && r1.x > 0)  		return sentido2;
		if(r1.x == 0 && r1.y > 0) 					return sentido3;
		if(r1.x == GridSize-1 && r1.y < GridSize-1)	return sentido4;
		return 0;
	}
	
	public void setPosInicial (int ag){
		 setAgPos(ag, inicio.get(ag));
		 
	}
	
    public boolean move(int ag, int sentido) {
        Location r1 = getAgPos(ag);
       
        if(sentido == sentido1) {
        	if(r1.x < GridSize-1) r1.x++;
        } else if (sentido == sentido2){
        	if(r1.x > 0) r1.x--;
        } else if (sentido == sentido3){
        	if(r1.y > 0) r1.y--;
        } else if (sentido == sentido4){
        	if(r1.y < GridSize-1) r1.y++;
        }else{
        	setPosInicial(ag);
        	return false; //TODO False?
        }
        setAgPos(ag, r1); // move the robot in the grid
        
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
    
    

}
