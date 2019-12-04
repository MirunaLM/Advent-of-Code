package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {

		ArrayList<Integer> list = new ArrayList<Integer>();
		String file = "src/day1/mass.txt";

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		while ((line = reader.readLine()) != null) {
			Integer newElement = Integer.parseInt(line);
			newElement /= 3;
			newElement -= 2;
			list.add(newElement);
		}

		System.out.println(list);
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		System.out.println(sum);

		reader.close();

	}

}
