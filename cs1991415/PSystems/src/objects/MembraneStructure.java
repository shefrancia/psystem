package objects;

import java.util.ArrayList;

public class MembraneStructure {
	public ArrayList<Membrane> membranes;
	private int index;
	
	public MembraneStructure(){
		this.membranes = new ArrayList<Membrane>();
		this.index = 0;
	}
	
	public void parse(String input){
		String[] temp = input.split(" ");
		int level = 0;
		for(String s : temp){
			if(s.equals("]"))
				level--;
			else{
				Membrane m = new Membrane(index, s.substring(3,s.length()-1), level);
				m.print();
				membranes.add(m);
				this.index++;
				level++;
			}
		}
	}
	
	public int length(){
		return this.membranes.size();
	}
	
	public void addOmega(Alphabet alpha, String input, int index){
		String[] temp = input.split(" ");
		ArrayList<String> elements = new ArrayList<String>();
		int energy = 0;
		for(String s : temp){
			if(s.equals("e")){
				energy++;
			}else{
				elements.add(s);
			}
		}
		this.membranes.get(index).addElements(elements);
		this.membranes.get(index).addEnergy(energy);
		
		this.membranes.get(index).print();
	}
	
	public void print(){
		
	}
	
}
