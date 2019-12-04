package day3amm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		ArrayList<String> stringWire1 = new ArrayList<String>();
		ArrayList<String> stringWire2 = new ArrayList<String>();

		String data = readFileAsString("src/day3amm/code.txt");

		String[] str1 = data.split(System.getProperty("line.separator"));

		StringTokenizer strTkn1 = new StringTokenizer(str1[0]);
		StringTokenizer strTkn2 = new StringTokenizer(str1[1]);

		while (strTkn1.hasMoreTokens()) {
			stringWire1.add((strTkn1.nextToken(",")));
		}

		while (strTkn2.hasMoreTokens()) {
			stringWire2.add((strTkn2.nextToken(",")));
		}
		
		//PART1

		ArrayList<ArrayList<Integer>> wire1 = calcWirePoints(stringWire1);
		ArrayList<ArrayList<Integer>> wire2 = calcWirePoints(stringWire2);

		
		ArrayList<ArrayList<Integer>> intersections = new ArrayList<ArrayList<Integer>>();
		intersections = getIntersectionPoints(wire1, wire2);

		System.out.println("The min disctance is: " + getMinDistance(intersections));
		
		//PART2
		
		ArrayList<Integer> steps = new ArrayList<Integer>();
		
		for( int i = 0; i < intersections.size(); i++) 
		{
			steps.add(calcWirePointsdist(stringWire1, intersections.get(i).get(0), intersections.get(i).get(1)) + calcWirePointsdist(stringWire2, intersections.get(i).get(0), intersections.get(i).get(1)));
		}
		
		Collections.sort(steps);
		System.out.println("Steps" + steps.get(1));
		
	}
	
	
	public static String readFileAsString(String fileName) throws Exception 
	{
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}
	
	
	
	static ArrayList<ArrayList<Integer>> getIntersectionPoints(ArrayList<ArrayList<Integer>> wire1, ArrayList<ArrayList<Integer>> wire2)
	{
		
		ArrayList<ArrayList<Integer>> intersections = new ArrayList<ArrayList<Integer>>();

		for (ArrayList<Integer> point1 : wire1)
		{
			for (ArrayList<Integer> point2 : wire2) 
			{
				if (point1.equals(point2)) 
				{
					intersections.add(point1);
				}

			}
		}
		
		return intersections;
	}
	
	static int getMinDistance(ArrayList<ArrayList<Integer>> intersections) 
	{
		ArrayList<Integer> manhattanDistances = new ArrayList<Integer>();

		for (ArrayList<Integer> point : intersections) 
		{
			int mDistance = calcManhattanDistance(point);
			manhattanDistances.add(mDistance);
		}
		
		Collections.sort(manhattanDistances);
		
		return manhattanDistances.get(1);
	}
	
	
	static int calcManhattanDistance(ArrayList<Integer> point) 
	{
		int distance = 0;
		distance = Math.abs(point.get(0)) + Math.abs(point.get(1));
		return distance;
	}

	private static ArrayList<ArrayList<Integer>> calcWirePoints(ArrayList<String> wire) 
	{
		ArrayList<ArrayList<Integer>> Wirepoints = new ArrayList<>();
		ArrayList<Integer> centralPoint = new ArrayList<Integer>();
		centralPoint.add(0);
		centralPoint.add(0);
		Wirepoints.add(centralPoint);

		for (int i = 0; i < wire.size(); i++) 
		{
			int amount = 0;

			switch (wire.get(i).charAt(0)) 
			{
			case 'R':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++) 
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0) + j);
					nextPoint.set(1, centralPoint.get(1));
					Wirepoints.add(nextPoint);
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				break;
			case 'D':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++) 
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0));
					nextPoint.set(1, centralPoint.get(1) - j);
					Wirepoints.add(nextPoint);
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				
				break;
			case 'L':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++) 
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0) - j);
					nextPoint.set(1, centralPoint.get(1));
					Wirepoints.add(nextPoint);
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				
				break;
			case 'U':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++) 
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0));
					nextPoint.set(1, centralPoint.get(1) + j);
					Wirepoints.add(nextPoint);
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				
				break;
			}
		}

		return Wirepoints;

	}

	
	private static int calcWirePointsdist(ArrayList<String> wire, int firstValue, int secondValue) 
	{
		int count = 0;
		
		ArrayList<ArrayList<Integer>> Wirepoints = new ArrayList<>();
		ArrayList<Integer> centralPoint = new ArrayList<Integer>();
		centralPoint.add(0);
		centralPoint.add(0);
		Wirepoints.add(centralPoint);
		
		for (int i = 0; i < wire.size(); i++) 
		{
			int amount = 0;
			
			switch (wire.get(i).charAt(0)) 
			{
			case 'R':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++)
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0) + j);
					nextPoint.set(1, centralPoint.get(1));
					Wirepoints.add(nextPoint);
					if(nextPoint.get(0).equals(firstValue + 1) && nextPoint.get(1).equals(secondValue)) 
					{
						return count;
					}
					count++;
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				
				break;
			case 'D':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++) 
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0));
					nextPoint.set(1, centralPoint.get(1) - j);
					Wirepoints.add(nextPoint);
					if(nextPoint.get(0).equals(firstValue) && nextPoint.get(1).equals(secondValue - 1)) {
						return count;
					}
					count++;
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				
				break;
			case 'L':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++) 
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0) - j);
					nextPoint.set(1, centralPoint.get(1));
					Wirepoints.add(nextPoint);
					if(nextPoint.get(0).equals(firstValue - 1) && nextPoint.get(1).equals(secondValue)) 
					{
						return count;
					}
					count++;
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				
				break;
			case 'U':
				
				amount = Integer.parseInt(wire.get(i).substring(1));
				
				for (int j = 1; j <= amount; j++)
				{
					ArrayList<Integer> nextPoint = new ArrayList<Integer>(Collections.nCopies(2, 0));
					nextPoint.set(0, centralPoint.get(0));
					nextPoint.set(1, centralPoint.get(1) + j);
					Wirepoints.add(nextPoint);
					if(nextPoint.get(0).equals(firstValue) && nextPoint.get(1).equals(secondValue + 1)) 
					{
						return count;
					}
					count++;
				}
				centralPoint = Wirepoints.get(Wirepoints.size() - 1);
				
				break;
			}
		}
		
		return count;
		
	}

}
