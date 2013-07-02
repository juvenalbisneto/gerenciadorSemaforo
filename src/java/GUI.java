import java.awt.event.*;
import cartago.INTERNAL_OPERATION;
//import cartago.OPERATION;
import cartago.tools.GUIArtifact;

public class GUI extends GUIArtifact {
	public void setup() {
		StreetModel model = new StreetModel(25);
		StreetView view = new StreetView(model);
		view.setVisible(true);
	}
	
	@INTERNAL_OPERATION void closed(WindowEvent ev){
		signal("closed");
	}
}