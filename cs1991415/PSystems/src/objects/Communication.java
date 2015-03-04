package objects;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Flags;

public class Communication extends Rule{
	int label;
	int type;
	ArrayList<String> elements;
	ArrayList<String> energy;
	
	public Communication(int label, String rule){
		this.elements = new ArrayList<String>();
		this.energy = new ArrayList<String>();
		this.label = label;
		
		String[] parts = rule.split("( *);( *)");
		for(String s : parts){
			String[] temp = s.split("( *),( *)");
			
			if(temp[1].equals("in")){
				this.type = Flags.IN;
			}else{
				this.type = Flags.OUT;
			}
			
			for(String e: temp[0].split(" ")){
				if(s.matches("^e(\\^.)")){
					this.energy.add(e.split("\\^")[1]);
				}else if(e.equals("e")){
					this.energy.add("1");
				}
				else
					this.elements.add(e);
			}
		}
		
		if(parts.length > 1)
			this.type = Flags.INOUT;
	}
	
	public void print(){
		System.out.println(label + " : " + type + " : " + Arrays.toString(this.elements.toArray()) + " : " + Arrays.toString(this.energy.toArray()));
	}
}
