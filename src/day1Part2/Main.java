package day1b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		String file = "src/day1b/module.txt";

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		
		while ((line = reader.readLine()) != null) {
			list.add(getSumOfModule(Integer.parseInt(line)));
		}

		System.out.println(list);
		int sum = 0;
		
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		System.out.println(sum);

		reader.close();
		
	}
	
	static int getSumOfModule(int a) {
		int sum = 0;
		while((a/3) -2  >=0) 
		{
			a = (a/3) -2;
			sum += a;
		}
		return sum;
	}

}
