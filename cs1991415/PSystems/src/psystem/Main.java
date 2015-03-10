package psystem;

import objects.*;
import simulators.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import constants.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String filename = "sample.tex";
		String input;
		Alphabet alpha = new Alphabet();
		Rules rules = new Rules();
		MembraneStructure ms = new MembraneStructure();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			input = reader.readLine();
			alpha.parse(input);
			input = reader.readLine();
			ms.parse(input);
			int memCount =ms.length();
			for(int i = 0; i < memCount; i++){
				input = reader.readLine();
				if(!input.isEmpty())
					ms.addOmega(alpha,input, i);
			}
			for(int i = 0; i < memCount; i++){
				input = reader.readLine();
				rules.parse(alpha,i,input,Flags.EVOL);
				input = reader.readLine();
				rules.parse(alpha,i,input,Flags.COMM);
			}
			
			reader.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		alpha.print();
		rules.print();
		//sample execution
		EPC epc = new EPC(alpha, rules);
		epc.execute(ms);
		sc.close();
	}

}
