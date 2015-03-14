package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import objects.Element;

public class Alphabet {
	private ArrayList<Element> elements;
	private Hashtable<String, Integer> table;
	
	public Alphabet(){
		this.elements = new ArrayList<Element>();
		this.table = new Hashtable<String, Integer>();
	}
	
	public void parse(String input){
		String[] temp = input.split("[,]");
		System.out.println(Arrays.toString(temp));
		for(int i = 0; i < temp.length; i++){
			Element e = new Element(i,temp[i]);
			this.elements.add(e);
			this.table.put(e.getValue(), i);
		}
	}
	
	public void addERule(int membrane, String entry, int label){
		elements.get(table.get(entry)).addERule(membrane, label);
	}
	
	public void addCRule(int membrane, String entry, int label){
		elements.get(table.get(entry)).addCRule(membrane, label);
	}
	
	public void print(){
		for (Element e : this.elements){
			e.print();
		}
	}
	
	public int getIndex(String entry){
		return table.get(entry);
	}
	
	public Element getElement(int index){
		return elements.get(index);
	}
	
	public Element getElement(String value){
		return elements.get(table.get(value));
	}
}
