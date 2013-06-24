package teste;

//import java.util.HashMap;

import net.sourceforge.jFuzzyLogic.FIS;
//import net.sourceforge.jFuzzyLogic.FunctionBlock;
//import net.sourceforge.jFuzzyLogic.rule.RuleBlock;

public class FuzzyTest {

	public static void main(String[] args) throws Exception {
        // Load from 'FCL' file
        String fileName = "fcl/fuzzyTraffic.fcl";
        FIS fis = FIS.load(fileName,true);
        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" 
                                   + fileName + "'");
            return;
        }

        // Show 
        fis.chart();

        // Set inputs
        fis.setVariable("qtdcarros1", 3);
        fis.setVariable("qtdcarros2", 0);

        // Evaluate
        fis.evaluate();

        // Show output variable's chart 
        fis.getVariable("ajusteSem").chartDefuzzifier(true);
//        fis.getVariable("ajusteSem2").chartDefuzzifier(true);
        
        double value = fis.getVariable("ajusteSem").defuzzify();
        System.out.println("DEFUZZIFY: " + value);
        value = fis.getVariable("ajusteSem").getValue();
        System.out.println("GET VALUE: "+value);
        System.out.println("VALOOOOOOOOOOR "+value);
        
        // Print ruleSet
        System.out.println(fis);
    }
	
}
