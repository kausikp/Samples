package com.kausik.algorithms;
/*
 * All rights reserved. Not for use without explicit permission from author. 
 * Author is not responsible for loss in terms of data or otherwise and is not liable for any damage due to the usage of this software.
 * contact kausikp at github in case of any questions
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFS
{
	public void performDepthFirstSearch(File inputFile)
	{
		try
		{
			Map<String, Node> nodeMap = prepGraphFromFile(inputFile);
			// Get the first node
			String currentNodeName = "D";
			Node node = nodeMap.get(currentNodeName);
			visitNeighboursInTree(node);
		}
		catch (IOException e)
		{
			System.out.println("No input file provided");
			e.printStackTrace();
		}

	}

	private void visitNeighboursInTree(Node node)
	{
		if (node != null && !node.isVisited)
		{
			System.out.println(node.name);
			node.isVisited=true;
			for (Node n : node.neighbours)
			{
				visitNeighboursInTree(n);
			}
		}
	}

	private Map<String, Node> prepGraphFromFile(File inputFile)
			throws IOException
	{
		Map<String, Node> graph = new HashMap<String, Node>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputFile)));
		String line;
		while ((line = reader.readLine()) != null)
		{
			String[] tokens = line.split(":");
			String nodeName = tokens[0];
			String connectedNodes = tokens[1];
			Node node = new Node();
			node = graph.get(nodeName);
			if (node == null)
			{
				node = new Node();
				node.name = nodeName;
			}
			// set neighbours
			for (String neighbour : connectedNodes.split(","))
			{
				Node neighbourNode = graph.get(neighbour);
				if (neighbourNode == null)
				{
					neighbourNode = new Node();
					neighbourNode.name = neighbour;
					graph.put(neighbour, neighbourNode);
				}
				node.neighbours.add(neighbourNode);
			}
			graph.put(nodeName, node);
		}
		reader.close();
		return graph;
	}

	private class Node
	{
		String name;
		List<Node> neighbours = new ArrayList<Node>();
		boolean isVisited = false;

		@Override
		public String toString()
		{
			StringBuilder ret = new StringBuilder(name);
			ret.append(" neighbours : ");
			for (Node neighbour : neighbours)
			{
				ret.append(neighbour.name).append(" ");
			}
			ret.append("\n");
			return ret.toString();
		}
	}

	public static void main(String[] args)
	{
		DFS dfs = new DFS();
		// output should be ABSCDEHGF for the sample
		dfs.performDepthFirstSearch(new File("resources/dfs.txt"));
	}

}
