
public class Rua {
	public GridCell[] rua;
	
	public Rua(GridCell[] rua){
		this.rua = rua;
	}
	public Rua(){
		this.rua = new GridCell[0];
	}
	
	public void addGridCell(GridCell cell){
		GridCell[] temp = this.rua;
		GridCell[] rua = new GridCell[temp.length + 1];
		
		for(int cont = 0; cont < temp.length; cont++){
			rua[cont] = temp[cont];
		}
		rua[temp.length] = cell;
		
		this.rua = rua;
	}
	
	public int getQtdCarros(){
		int qtdCarros = 0;
		for(int cont = 0; cont < rua.length; cont++){
			if(rua[cont].temCarro)
				qtdCarros++;
		}
		return qtdCarros;
	}
	
	public void printRua(){
		System.out.println("RUA:");
		for(int cont = 0; cont < rua.length; cont++){
			System.out.println(cont+": ("+rua[cont].location.x+", "+rua[cont].location.y+")");
		}
	}
	
//	public Location inicio;
//	public Location fim;
//	public Location[] rua;
//	
//	public Rua(Location inicio, Location fim){
//		this.inicio = inicio;
//		this.fim = fim;
//		this.rua = locationsRua(inicio,fim);
//	}
//	public Rua(Location inicio, Location[] meio, Location fim){
//		this.inicio = inicio;
//		this.fim = fim;
//		
//		Location[] temp = new Location[meio.length + 2];
//		System.out.println("TESTE: "+meio.length);
//		temp[0] = inicio;
//		temp[temp.length-1] = fim;
//		for(int cont = 0; cont < meio.length; cont++){
//			temp[cont+1] = meio[cont];
//		}
//		this.rua = temp;
//	}
//	
//	private Location[] locationsRua(Location inicio, Location fim){
//		int tamanho = 1;
//		Location[] rua = new Location[0];
//		
//		if(inicio.x == fim.x){
//			tamanho += fim.y - inicio.y;
//			rua = new Location[tamanho];
//			
//			for(int cont = 0; cont < tamanho; cont++){
//				rua[cont] = new Location(inicio.x,cont+inicio.y);
//			}
//		} else if (inicio.y == fim.y) {
//			tamanho += fim.x - inicio.x;
//			rua = new Location[tamanho];
//			
//			for(int cont = 0; cont < tamanho; cont++){
//				rua[cont] = new Location(cont+inicio.x,inicio.y);
//			}
//		}
//		
//		return rua;
//	}
}
