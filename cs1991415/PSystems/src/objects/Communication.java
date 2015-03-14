package objects;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Flags;

public class Communication extends Rule{
	int label;
	public int type;
	int membrane;
	int source; //this will depend on membrane structure
	int destination; //this will depend on membrane structure
	public ArrayList<String> elements;
	ArrayList<String> energy;
	
	public Communication(int label, String rule, int membrane){
		this.elements = new ArrayList<String>();
		this.energy = new ArrayList<String>();
		this.label = label;
		
		String[] parts = rule.split("( *);( *)");
		for(String s : parts){
			String[] temp = s.split("( *),( *)");
			
			if(temp[1].equals("in")){
				this.type = Flags.IN;
				this.source = membrane - 1;
				this.destination = membrane;
				this.membrane = membrane - 1;
				
			}else{
				this.type = Flags.OUT;
				this.source = membrane;
				this.destination = membrane - 1;
				this.membrane = this.source;
			}
			
			for(String t: temp[0].split(" ")){
				if(s.matches("^e(\\^.)")){
					this.energy.add(t.split("\\^")[1]);
				}else if(t.equals("e")){
					this.energy.add("1");
				}
				else
					this.elements.add(t);
			}
		}
		
		if(parts.length > 1){
			this.type = Flags.INOUT;
			this.source = membrane - 1;
			this.destination = membrane;
			this.membrane = source;
		}
	}
	
	public void print(){
		System.out.println(label + " : " + type + " : " + Arrays.toString(this.elements.toArray()) + " : " + Arrays.toString(this.energy.toArray()));
	}
	
	public void applyRule(ArrayList<Membrane> membranes){
		if(this.type == Flags.INOUT){
			membranes.get(this.source).addElement(this.elements.get(1));
			membranes.get(this.destination).addElement(this.elements.get(0));
		}else
			membranes.get(this.destination).addElements(this.elements);
	}
}
