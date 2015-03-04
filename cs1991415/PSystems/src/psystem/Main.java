package psystem;

import objects.*;

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
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			input = reader.readLine();
			alpha.parse(input);
			input = reader.readLine();
			rules.parse(alpha,input,Flags.EVOL);
			input = reader.readLine();
			rules.parse(alpha,input,Flags.COMM);
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		alpha.print();
		rules.print();
		sc.close();
	}

}
