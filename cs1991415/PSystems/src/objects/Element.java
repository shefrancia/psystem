package objects;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Element {
	public int label;
	private String value;
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
		if(this.evolRules.isEmpty())
			return -1;
		Random random = new Random();
//		int index = random.nextInt(this.evolRules.size());
//		int rule = this.evolRules.get(index);
		int index = random.nextInt(this.evol.get(membrane).size());
		int rule = this.evol.get(membrane).get(index);
		return rule;
	}
	
}
