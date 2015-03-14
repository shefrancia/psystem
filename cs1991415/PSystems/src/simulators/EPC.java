package simulators;

import java.util.ArrayList;
import java.util.Arrays;

import objects.*;
import constants.*;

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
		boolean isCApplicable = true;
		ArrayList<Communication> commRules = new ArrayList<Communication>();
		for (Membrane m : membranes) {
//			m.print();
			ArrayList<String> temp = (ArrayList<String>) m.elements.clone();
			m.emptyElements();
			for (String value : temp) {
				if(!value.isEmpty()){
					Element e = alpha.getElement(value);
					int eRuleIndex = e.chooseEvolRuleND(m.index);
					int cRuleIndex = e.chooseCommRuleND(m.index, ms.membranes, rules, alpha);
					if(eRuleIndex >= 0){
						Evolution rule = rules.getEvolRule(eRuleIndex);
						m.addElements(rule.getRight());
						m.addEnergy(rule.getEnergy());
						ruleApplied++;
						isCApplicable = false;
						commRules.clear();
					}else if(isCApplicable && cRuleIndex >= 0){
							commRules.add(rules.getCommunicationRule(cRuleIndex));
					}else{
						m.addElement(e.value);
					}
				}
			}
//			m.print();
		}
		if(isCApplicable){
			System.out.println(Arrays.toString(commRules.toArray()));
			for(Communication c : commRules){
				c.applyRule(membranes);
				ruleApplied++;
			}
		}
		
		for(Membrane m : membranes)
			m.print();
	}
}
