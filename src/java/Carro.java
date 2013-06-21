import jason.environment.grid.Location;

public class Carro {
	public GridCell cell;
	public GUI parentGUI;
	
	public Carro(GridCell cell, GUI parent){
		this.cell = cell;
		this.parentGUI = parent;
		this.cell.temCarro = true;
		System.out.println("[Carro] CRIOU CARRO: ["+cell.location.x+"]["+cell.location.y+"]");
		this.parentGUI.frame.refreshGrid();
		//andar();
	}
	
	// CONFIGURACAO DA DIRECAO:
	// 0  = NADA
	// 1  = dir
	// 2  = bai
	// 3  = esq
	// 4  = cim
	// 5  = dir bai
	// 6  = esq bai
	// 7  = dir cim
	// 8  = esq cim
	
	public void andar(){
		System.out.println("ANDAR");
		GridCell[][] grid = this.parentGUI.frame.grid;
		GridCell temp = this.cell;
		Location locAtual = this.cell.location;
		
		int direcao = this.cell.direcao;
		switch(direcao){
			case 1:
				break;
			case 2:
				break;
			case 3:
				temp = grid[locAtual.x-1][locAtual.y];
				if(!temp.temCarro){
					this.cell.temCarro = false;
					System.out.println("---");
					System.out.println("+CELL TEMP: "+temp.location.x+", "+temp.location.y+" / Tem carro: "+temp.temCarro);
					System.out.println("+CELL: "+cell.location.x+", "+cell.location.y+" / Tem carro: "+cell.temCarro);
					this.cell = temp;
					System.out.println("---");
					System.out.println("-CELL TEMP: "+temp.location.x+", "+temp.location.y+" / Tem carro: "+temp.temCarro);
					System.out.println("-CELL: "+cell.location.x+", "+cell.location.y+" / Tem carro: "+cell.temCarro);
					this.cell.temCarro = true;
					System.out.println("---");
					System.out.println("=CELL TEMP: "+temp.location.x+", "+temp.location.y+" / Tem carro: "+temp.temCarro);
					System.out.println("=CELL: "+cell.location.x+", "+cell.location.y+" / Tem carro: "+cell.temCarro);
				}
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			default:
				break;
		}
		System.out.println("---");
		System.out.println("CELL: "+cell.location.x+", "+cell.location.y+" / Tem carro: "+cell.temCarro);
		this.parentGUI.frame.refreshGrid();
	}
	
	// GETTERS E SETTERS
	
}
