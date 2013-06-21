import jason.asSyntax.*;
import jason.environment.Environment;
//import jason.environment.grid.Location;
import java.util.logging.Logger;
//import net.sourceforge.jFuzzyLogic.FIS;


public class StreetEnvironment extends Environment{
	static Logger logger = Logger.getLogger(StreetEnvironment.class.getName());
	
	//literals
	//public static final Literal qtdCarros = Literal.parseLiteral("");
	
	StreetModel model;
	
	@Override
	public void init(String[] args){
		model = new StreetModel(3,3,2);
		
		if(args.length == 1 && args[0].equals("gui")){
			StreetView view = new StreetView(model);
			model.setView(view);
		}
		
		updatePercepts();
	}
	
	@Override
	public boolean executeAction(String ag, Structure action){
		logger.info(ag + " doing: " + action);
		
		try{
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	void updatePercepts(){
//		Location lAg = model.getAgPos(0);
//		logger.info("entrou no for (" + lAg.x + ", " + lAg.y + ")");
	}
}
