package day2b;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	private static int replaceValues(ArrayList<Integer> list) 
	{
		for(int i = 0; i < list.size(); i++) 
		{
	    	if(list.get(i) == 1) 
	    	{
	    		list.set(list.get(i + 3), list.get(list.get(i + 2)) + list.get(list.get(i + 1)));
	    	} else if(list.get(i) == 2) 
	    	{
	    		list.set(list.get(i + 3), list.get(list.get(i + 2)) * list.get(list.get(i + 1)));
	    	}else break;
	    	
	    	i += 3;
	    }
		return list.get(0);
	}

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> list = new ArrayList<Integer>();

		String data = readFileAsString("src/day2/code.txt");
		StringTokenizer strTkn = new StringTokenizer(data);

		while (strTkn.hasMoreTokens()) {
			list.add(Integer.parseInt(strTkn.nextToken(",")));
		}
		
		for (int i = 1; i < 100; ++i) 
		{
			for (int j = 1; j < 100; ++j) 
			{
				list.set(1, i);
				list.set(2, j);
				
				ArrayList<Integer> newList = new ArrayList<Integer>(list);
				
				if (replaceValues(newList) == 19690720) 
				{
					System.out.println((100 * i) + j);
				}
			}
		}

	}

	public static String readFileAsString(String fileName) throws Exception 
	{
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}
}
