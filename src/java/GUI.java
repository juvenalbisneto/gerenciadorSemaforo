import java.awt.event.*;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import cartago.INTERNAL_OPERATION;
//import cartago.OPERATION;
import cartago.tools.GUIArtifact;

public class GUI extends GUIArtifact {

	public Frame frame;
	
	public void setup() {
		StreetModel model = new StreetModel(10);
		StreetView view = new StreetView(model);
		view.setVisible(true);
//		frame = new Frame();
//		frame.setVisible(true);
		
//		linkActionEventToOp(frame.okButton,"ok");
//		linkKeyStrokeToOp(frame.text,"ENTER","updateText");
//		linkWindowClosingEventToOp(frame, "closed");
//
//		defineObsProperty("value",getValue());
	}

//	@INTERNAL_OPERATION void ok(ActionEvent ev){
//		signal("ok");
//	}

	@INTERNAL_OPERATION void closed(WindowEvent ev){
		signal("closed");
	}

//	@INTERNAL_OPERATION void updateText(ActionEvent ev){
//		getObsProperty("value").updateValue(getValue());
//	}
//
//	@OPERATION void setValue(int value){
//		frame.setText(""+value);
//		getObsProperty("value").updateValue(getValue());
//	}
//
//	private int getValue(){
//		return Integer.parseInt(frame.getText());
//	}

	@SuppressWarnings("serial")
	class Frame extends JFrame {
//		private JTextField text;
		private static final int gridSize = 10;
		public GridCell[][] grid;
		private Rua[] ruas;
		
		public Frame(){
			settings();
	        
	        //GRID
	        createGrid();

	        setVisible(true);
		}
		
		private void settings(){
			setTitle("Controle de Tr‰nsito");
	        setSize(500, 500);
	        setResizable(true);
	        setLocationRelativeTo(null);
	        
	        grid = new GridCell[0][0];
	        ruas = new Rua[0];
		}
		
		private void createGrid(){
			System.out.println("[GUI] CRIANDO GRID");
			setLayout(new GridLayout(gridSize,gridSize));
	        this.grid = new GridCell[gridSize][gridSize];
	        Container container = getContentPane();
	        JPanel temp = null;
	        for(int y = 0; y < gridSize; y++){
	        	for(int x = 0; x < gridSize; x++){
	        		grid[x][y] = new GridCell(x,y);
	        		temp = grid[x][y];
		            container.add(temp);
	        	}
	        }
	        
	        loadMap();
		}
		
		public void refreshGrid(){
			System.out.println("[GUI] ATUALIZANDO");
			Container container = getContentPane();
			container.removeAll();
			
	        for(int contY = 0; contY < gridSize; contY++){
	        	for(int contX = 0; contX < gridSize; contX++){
	        		grid[contX][contY].paint();
	        		if(grid[contX][contY].temCarro)
	        			System.out.println("[GUI] Tem carro ["+grid[contX][contY].location.x+"]["+grid[contX][contY].location.y+"]");
		            container.add(grid[contX][contY]);
	        	}
	        }
	        System.out.println("[GUI] ->ATUALIZOU");
		}
		
		private void loadMap(){
			System.out.println("[GUI] CARREGANDO MAPA");
			Rua rua1 = new Rua();
			Rua rua2 = new Rua();
			grid[1][1] = new GridCell(1,1,true,2,false); rua1.addGridCell(grid[1][1]);
			grid[1][2] = new GridCell(1,2,true,2,false); rua1.addGridCell(grid[1][2]);
			grid[1][3] = new GridCell(1,3,true,2,false); rua1.addGridCell(grid[1][3]);
			grid[1][4] = new GridCell(1,4,true,2); //SEMAFORO
			grid[1][5] = new GridCell(1,5,true,2,false);
			
			grid[2][1] = new GridCell(2,1,true,3,false); rua1.addGridCell(grid[2][1]);
			grid[3][1] = new GridCell(3,1,true,6,false);
			grid[4][1] = new GridCell(4,1,true,3,false);
			grid[5][1] = new GridCell(5,1,true,3,false);
			
			grid[3][2] = new GridCell(3,2,true,2,false); rua2.addGridCell(grid[3][2]);
			grid[3][3] = new GridCell(3,3,true,2,false); rua2.addGridCell(grid[3][3]);
			grid[3][4] = new GridCell(3,4,true,3,false); rua2.addGridCell(grid[3][4]);
			
			grid[2][4] = new GridCell(2,4,true,3,false); rua2.addGridCell(grid[2][4]);
			
			ruas = new Rua[2];
			ruas[0] = rua1;
			ruas[1] = rua2;
			
			refreshGrid();
		}
		
//		public String getText(){
//			return text.getText();
//		}
//
//		public void setText(String s){
//			text.setText(s);
//		}
	}
}