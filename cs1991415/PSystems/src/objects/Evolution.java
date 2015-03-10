package objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Evolution extends Rule{
	String left;
	ArrayList<String> right;
	int label;
	int membrane;
	int energy;
	String value;
	
	public Evolution(int label,String rule, int membrane){
		String[] parts = rule.split("( *)\\\\rightarrow( *)");
		
		this.left = parts[0];
		this.right = new ArrayList<String>();
		this.energy = 0;
		this.label = label;
		this.value = rule;
		this.membrane = membrane;
		
		
		String[] temp = parts[1].split(" +");
		for(String s: temp){
//			if(s.matches("^e(\\^.)")){
//				this.energy.add(s.split("\\^")[1]);
//			}else
			if(s.equals("e")){
				this.energy++;
			}
			else
				this.right.add(s);
		}
	}
	
	public ArrayList<String> getRight(){
		return right;
	}
	
	public int getEnergy(){
		return energy;
	}
	
	public void print(){
		System.out.println(this.label + " : " + this.left + " : " + Arrays.toString(this.right.toArray()) + " : " + this.energy);
	}
}
