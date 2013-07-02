import java.awt.*;

import jason.environment.grid.GridWorldView;


@SuppressWarnings("serial")
public class StreetView extends GridWorldView{
	StreetModel smodel;
	
	public StreetView(StreetModel model) {
		super(model, "Controle de Transito", model.gridSize*35);
        smodel = model;
        model.setView(this);
        defaultFont = new Font("Arial", Font.BOLD, 16);
        setVisible(true);
        repaint();
	}
	
	@Override
	public void draw(Graphics g, int x, int y, int object) {
		super.drawAgent(g, x, y, Color.BLACK, -1);
        switch (object) {
        	case StreetModel.RUA:
        		super.drawAgent(g, x, y, Color.LIGHT_GRAY, -1);
        		g.setColor(Color.DARK_GRAY);
            	super.drawString(g, x, y, defaultFont, smodel.direcao(x,y));
        		break;
            case StreetModel.CARRO:
            	super.drawAgent(g, x, y, Color.cyan, -1);
            	g.setColor(Color.BLUE);
            	super.drawString(g, x, y, defaultFont, "C");
                break;
        }
	}
	
	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		c = Color.blue;
		super.drawAgent(g, x, y, c, -1);
		g.setColor(Color.black);
		super.drawString(g, x, y, defaultFont, "S");
	}

}
