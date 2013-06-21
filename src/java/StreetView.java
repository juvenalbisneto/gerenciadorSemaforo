import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;


public class StreetView extends GridWorldView{
	StreetModel smodel;
	public StreetView(StreetModel model) {
		super(model, "Traffic Control", 700);
        smodel = model;
        defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
        setVisible(true);
        repaint();
	}
	
	@Override
	public void draw(Graphics g, int x, int y, int object) {
		//Location lcar1 = smodel.getAgPos(0);
		//Location lcar2 = smodel.getAgPos(1);
        super.drawAgent(g, x, y, Color.lightGray, -1);
        switch (object) {
            case StreetModel.SEMAFORO:
                //TODO PINTAR O GRID
            	super.drawAgent(g, x, y, Color.red, -1);
                break;
        }
	}
	
	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        Location lcar1 = smodel.getAgPos(0);
        Location lcar2 = smodel.getAgPos(1);
        if (!lcar1.equals(smodel.lsem1) && !lcar1.equals(smodel.lsem2) &&
        	!lcar2.equals(smodel.lsem1) && !lcar2.equals(smodel.lsem2)) {
            c = Color.blue;
            //TODO PINTAR AGENTES NOS SINAIS
            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.black);
            super.drawString(g, x, y, defaultFont, "Carro");
        }
	}

}
