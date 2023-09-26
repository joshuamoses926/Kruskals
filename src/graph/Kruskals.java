package graph;

import java.util.*;
import java.io.*;

public class Kruskals 
{	
	//making global variables to store vertexes and edges into an array
	static ArrayList<String> vlist = new ArrayList<>();
	static ArrayList<Edge> elist = new ArrayList<>();
	
	public void kruskal()
	{
		//provided by the pseudo-code provided from the textbook
	    int edgesAccepted = 0;
	    int sum = 0;
	    DisjSets ds = new DisjSets(vlist.size());
	    PriorityQueue<Edge> pq = new PriorityQueue<>();
	    
	    	    
	    for (Edge e: elist)
	    {
	    	pq.add(e);
	    }
	    System.out.print("minimum Spanning Tree:\n");
	    while (edgesAccepted < vlist.size()-1)
	    {
	        Edge e = pq.poll();  // get minimum edge = (u,v), poll Retrieves and removes the head of this queue, or returns null if this queue is empty.
	        int uset = ds.find(vlist.indexOf(new String(e.u))); // find set vertex u is in.
	        int vset = ds.find(vlist.indexOf(new String(e.v))); // find set vertex v is in.
	        if (uset != vset)    // if not same set (not yet connected)
	        {
	             // accept the edge
	             edgesAccepted++;
	             ds.union(uset, vset); // connect them
	             sum = sum + e.EdgeWeight;// get the sum of all the edges
	            
	             System.out.print(e.u + " & " + e.v );
	             System.out.print(": edge length: " + e.EdgeWeight);
	             System.out.println();

	        }
	     }
	    System.out.print("Total Weight:\n" + sum);
	 }
	

	@SuppressWarnings({"resource"})
	public static void main(String[] args)
	{
		//try and catch blocks are used to print errors if file is not found
		 try 
		 {
			 
			String line = "";
			//Use a buffered reader to open the file. it's faster and more
			//efficient compared to a scanner
			BufferedReader reader = new BufferedReader(new FileReader("assn9_data.csv"));
			
			//while loop is used if unable to find the end.
			while((line = reader.readLine()) != null)
			{
				//Store the values of the csv file into an array
				String[] values = line.split(",");// separates values when a comma is seen
				vlist.add(values[0]);//add to the vertex list
				for(int i = 1; i< values.length ; i++)
				{
					//adding to the edge list
					elist.add(new Edge(values[0], values[i], Integer.parseInt(values[i+1])));
					i++;
					
				}
				
				
			}
			
			
		 } 
		 catch(Exception error) 
		 {
			error.printStackTrace();
		 }
		 
		 //call the kruskal method
		 Kruskals k = new Kruskals();
		 k.kruskal();

	}
}

//Create an edge class to compare the weights of edges between 2 vertices
class Edge implements Comparable<Edge>
{
	//variables for the starting vertex, the second vertex,
	//and the edge length.
	String u, v;
	int EdgeWeight;

	//this is made in order to make the parse int function work
	Edge(String string, String string2, int parseInt)
	{
		this.u = string;
		this.v = string2;
		this.EdgeWeight = parseInt;
	}

	//compares the edge distances
	public int compareTo(Edge compareEdge) 
	{
		if(this.EdgeWeight < compareEdge.EdgeWeight)
		{
			return -1;
		}
		else if(this.EdgeWeight > compareEdge.EdgeWeight)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
};





