package objects;

import java.util.ArrayList;

public class Element {
	public int label;
	private String value;
	private ArrayList<Integer> rules;
	
	public Element(int label, String value){
		this.label = label;
		this.value = value;
		this.rules = new ArrayList<Integer>();
	}
	
	public void addRule(int rule){
		this.rules.add(rule);
	}
	
	public String getValue(){
		return this.value;
	}
	
	public ArrayList<Integer> getRules(){
		return this.rules;
	}
	
	public void print(){
		System.out.println(this.label + " " + this.value);
		for(int i : this.rules){
			System.out.println(i);
		}
	}
	
}
