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
        fis.setVariable("qtd", 3);
        fis.setVariable("qtddir", 1);
        fis.setVariable("qtdindir", 2);

        // Evaluate
        fis.evaluate();

        // Show output variable's chart 
        fis.getVariable("ajuste").chartDefuzzifier(true);
//        fis.getVariable("ajusteSem2").chartDefuzzifier(true);
        
        double value = fis.getVariable("ajuste").defuzzify();
        System.out.println("DEFUZZIFY: " + value);
        value = fis.getVariable("ajuste").getValue();
        System.out.println("GET VALUE: "+value);
        System.out.println("VALOOOOOOOOOOR "+value);
        
        // Print ruleSet
        System.out.println(fis);
    }
	
}
