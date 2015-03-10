package objects;

import java.util.ArrayList;

public class Membrane {
	public int energy;
	public int index;
	public int level;
	public String label;
	public ArrayList<Integer> rules;
	public ArrayList<String> elements;
	
	public Membrane(int index, String label, int level){
		this.index = index;
		this.label = label;
		this.energy = 0;
		this.level = level;
		rules = new ArrayList<Integer>();
		elements = new ArrayList<String>();
	}
	
	public void print(){
		System.out.println(this.index + " : " + this.label + " : " + this.level + " : " + this.energy );
		for (String s : this.elements) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
	

	public void addElements(ArrayList<String> elements){
		this.elements.addAll(elements);
	}
	
	public void addEnergy(int energy){
		this.energy += energy;
	}
	
	public void emptyElements(){
		this.elements.clear();
	}
	
}
