import java.awt.Color;

import jason.environment.grid.Location;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GridCell extends JPanel{
	public Location location;
	boolean ehRua;
	int direcao;
	boolean ehSemaforo;
	boolean temCarro;
	
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
	
	public GridCell(int x, int y, boolean ehRua, int direcao, boolean temCarro){
		this.location = new Location(x,y);
		this.ehRua = ehRua;
		this.direcao = direcao;
		this.ehSemaforo = false;
		this.temCarro = temCarro;
		
		conf();
	}
	
	public GridCell(int x, int y, boolean ehSemaforo, int direcao){
		this.location = new Location(x,y);
		this.ehRua = false;
		this.direcao = direcao;
		this.ehSemaforo = ehSemaforo;
		this.temCarro = false;
		
		conf();
	}
	
	public GridCell(int x, int y){
		this.location = new Location(x,y);
		this.ehRua = false;
		this.direcao = 0;
		this.ehSemaforo = false;
		this.temCarro = false;
		
		conf();
	}
	
	private void conf(){
		setSize(100, 100);
		JLabel label = new JLabel(""+this.direcao+"("+(this.temCarro?1:0)+")");
		add(label);
		paint();
	}
	
	//Metodo para pintar o grid
	public void paint(){
		if(ehRua) {
			if(temCarro){
				setBackground(Color.BLUE);
				setBorder(BorderFactory.createLineBorder(Color.BLACK));
			} else {
				setBackground(Color.LIGHT_GRAY);
				setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}
		} else if(ehSemaforo) {
			setBackground(Color.RED);
			setBorder(BorderFactory.createLineBorder(Color.RED));
		} else {
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}
	}
}
