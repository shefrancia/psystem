package objects;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import constants.Flags;

public class Element {
	public int label;
	public String value;
	private ArrayList<Integer> evolRules;
	private ArrayList<Integer> commRules;
	private Hashtable<Integer, ArrayList<Integer>> evol;
	private Hashtable<Integer, ArrayList<Integer>> comm;
	
	public Element(int label, String value){
		this.label = label;
		this.value = value;
		this.evolRules = new ArrayList<Integer>();
		this.commRules = new ArrayList<Integer>();
		this.evol = new Hashtable<Integer, ArrayList<Integer>>();
		this.comm = new Hashtable<Integer, ArrayList<Integer>>();
	}
	
	public void addERule(int membrane, int rule){
		this.evolRules.add(rule);
		
		if(this.evol.containsKey(membrane)){
			this.evol.get(membrane).add(rule);
		}else{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(rule);
			this.evol.put(membrane, temp);
		}
	}
	
	public void addCRule(int membrane, int rule){
		this.commRules.add(rule);
		
		if(this.comm.containsKey(membrane)){
			this.comm.get(membrane).add(rule);
		}else{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(rule);
			this.comm.put(membrane, temp);
		}
	}
	
	public String getValue(){
		return this.value;
	}
	
	public ArrayList<Integer> getRules(){
		return this.evolRules;
	}
	
	public void print(){
		System.out.println(this.label + " " + this.value);
		System.out.println("Evolution");
		for(int i : this.evolRules){
			System.out.println(i);
		}
		System.out.println("Communication");
		for(int i : this.commRules){
			System.out.println(i);
		}
	}
	
	public int chooseEvolRuleND(int membrane){
		if(this.evolRules.isEmpty() || this.evol.get(membrane).isEmpty())
			return -1;
		Random random = new Random();
//		int index = random.nextInt(this.evolRules.size());
//		int rule = this.evolRules.get(index);
		int index = random.nextInt(this.evol.get(membrane).size());
		int rule = this.evol.get(membrane).get(index);
		return rule;
	}
	
	//TO DO: transfer this method to simulators
	public int chooseCommRuleND(int membrane, ArrayList<Membrane> membranes, Rules rules, Alphabet alpha){
		
		try{
			Random random = new Random();

			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int index : this.comm.get(membrane)){
				Communication rule = rules.getCommunicationRule(index);
				if(rule.type == Flags.INOUT){
					Membrane destination = membranes.get(rule.destination);
					if(destination.elements.contains(rule.elements.get(1)) && alpha.getElement(rule.elements.get(1)).evolRules.isEmpty()){
						temp.add(index);
						destination.elements.remove(rule.elements.get(1));
					}
				}else{
					temp.add(index);
				}
			}
			
			if(temp.isEmpty())
				return -1;
			int index = random.nextInt(temp.size());
			int rule = temp.get(index);
			return rule;
		}catch(Exception e){
//			e.printStackTrace();
			return -1;
		}
		
	}
	
}
