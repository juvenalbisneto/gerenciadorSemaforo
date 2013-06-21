import java.awt.*;
import jason.environment.grid.GridWorldView;
//import jason.environment.grid.Location;


@SuppressWarnings("serial")
public class StreetView extends GridWorldView{
	StreetModel smodel;
	
	public StreetView(StreetModel model) {
		super(model, "Controle de Tr‰nsito", 700);
        smodel = model;
        defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
        setVisible(true);
        repaint();
	}
	
	@Override
	public void draw(Graphics g, int x, int y, int object) {
		//Location lcar1 = smodel.getAgPos(0);
		//Location lcar2 = smodel.getAgPos(1);
		
		super.drawAgent(g, x, y, Color.LIGHT_GRAY, -1);
        switch (object) {
            case StreetModel.SEMAFORO:
            	super.drawAgent(g, x, y, Color.RED, -1);
                break;
            case StreetModel.CARRO:
            	super.drawAgent(g, x, y, Color.BLUE, -1);
            	g.setColor(Color.black);
            	super.drawString(g, x, y, defaultFont, "Carro");
                break;
        }
	}
	
//	@Override
//	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
//        Location lcar1 = smodel.getAgPos(0);
//        Location lcar2 = smodel.getAgPos(1);
//        if (!lcar1.equals(smodel.lsem1) && !lcar1.equals(smodel.lsem2) &&
//        	!lcar2.equals(smodel.lsem1) && !lcar2.equals(smodel.lsem2)) {
//            c = Color.blue;
//            //TODO PINTAR AGENTES NOS SINAIS
//            super.drawAgent(g, x, y, c, -1);
//            g.setColor(Color.black);
//            super.drawString(g, x, y, defaultFont, "Carro");
//        }
//	}

}
