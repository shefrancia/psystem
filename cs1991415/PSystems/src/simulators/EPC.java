package simulators;

import java.util.ArrayList;

import objects.*;

public class EPC {
	Alphabet alpha;
	Rules rules;
	
	public EPC(Alphabet alpha, Rules rules){
		this.alpha = alpha;
		this.rules = rules;
	}
	
	public void setAlphabet(Alphabet alpha){
		this.alpha = alpha;
	}
	
	public void setRules(Rules rules){
		this.rules = rules;
	}
	
	public void execute(MembraneStructure ms){
		ArrayList<Membrane> membranes = ms.membranes;
		int ruleApplied = 0;
		for (Membrane m : membranes) {
			m.print();
			ArrayList<String> temp = (ArrayList<String>) m.elements.clone();
			m.emptyElements();
			for (String value : temp) {
				if(!value.isEmpty()){
					Element e = alpha.getElement(value);
					int ruleIndex = e.chooseEvolRuleND(m.index);
					if(ruleIndex >= 0){
						Evolution rule = rules.getEvolRule(ruleIndex);
						m.addElements(rule.getRight());
						m.addEnergy(rule.getEnergy());
						ruleApplied++;
					}else{
						
					}
				}
			}
			m.print();
		}
	}
}
