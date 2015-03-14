package objects;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Flags;

public class Rules {
	private ArrayList<Rule> rules;
	private int counter = 0;
	
	public Rules(){
		this.rules = new ArrayList<Rule>();
	}
	
	public void parse(Alphabet alpha, int membrane, String input,int type){
		String[] temp;
		if(type == Flags.EVOL){
			temp = input.split("[,]");
//			System.out.println(Arrays.toString(temp));
			for(int i = 0; i < temp.length; i++){
				Evolution e = new Evolution(this.counter,temp[i],membrane);
				rules.add(e);
				alpha.addERule(membrane, e.left, e.label);
				this.counter++;
			}
		}else{
			temp = input.split("[)].*[(]");
			for(String s: temp){
				Communication c = new Communication(this.counter,s.replaceAll("[(]|[)]", ""), membrane);
				rules.add(c);
				boolean b = false;
				for(String e : c.elements){
					alpha.addCRule(c.membrane,e, c.label);
					if(b)
						alpha.addCRule(c.destination,e, c.label);
					b = true;
				}
				this.counter++;
			}
			
		}
	}
	
	public Evolution getEvolRule(int index){
		return (Evolution) rules.get(index);
	}
	
	public Communication getCommunicationRule(int index){
		return (Communication) rules.get(index);
	}
	
	public void print(){
		for(Rule r : rules){
			r.print();
		}
	}
}
