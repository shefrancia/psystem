package objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Evolution extends Rule{
	String left;
	ArrayList<String> right;
	int label;
	ArrayList<String> energy;
	String value;
	
	public Evolution(int label,String rule){
		String[] parts = rule.split("( *)\\\\rightarrow( *)");
		
		this.left = parts[0];
		this.right = new ArrayList<String>();
		this.energy = new ArrayList<String>();
		this.label = label;
		this.value = rule;
		
		String[] temp = parts[1].split(" +");
		for(String s: temp){
			if(s.matches("^e(\\^.)")){
				this.energy.add(s.split("\\^")[1]);
			}else if(s.equals("e")){
				this.energy.add("1");
			}
			else
				this.right.add(s);
		}
	}
	
	public void print(){
		System.out.println(this.label + " : " + this.left + " : " + Arrays.toString(this.right.toArray()) + " : " + Arrays.toString(this.energy.toArray()));
	}
}
