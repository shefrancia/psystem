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
	
	public void parse(Alphabet alpha, String input, int type){
		String[] temp;
		if(type == Flags.EVOL){
			temp = input.split("[,]");
			System.out.println(Arrays.toString(temp));
			for(int i = 0; i < temp.length; i++){
				Evolution e = new Evolution(this.counter,temp[i]);
				rules.add(e);
				alpha.addRule(e.left, e.label);
				this.counter++;
			}
		}else{
			temp = input.split("[)].*[(]");
			for(String s: temp){
				Communication c = new Communication(this.counter,s.replaceAll("[(]|[)]", ""));
				rules.add(c);
				for(String e : c.elements){
					alpha.addRule(e, c.label);
				}
				this.counter++;
			}
			
		}
	}
	
	public void print(){
		for(Rule r : rules){
			r.print();
		}
	}
}
