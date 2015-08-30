
/*
 * All rights reserved. Not for use without explicit permission from author. 
 * Author is not responsible for loss in terms of data or otherwise and is not liable for any damage due to the usage of this software.
 * contact kausikp at github in case of any questions
 */
package com.kausik.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Kausik Class to demonstrate the Dijkstra's algorithm
 */
public class Dijkstra
{
	private Map<String, Integer> distMap = new HashMap<String, Integer>();
	Node startNode, endNode;
	List<Node> nodes;
	Set<Node> unvisitedNodes = new HashSet<Node>();
	List<String> visitedNodes = new ArrayList<String>();
	/**
	 * Method to find shortest path using dijkstra's algorithm
	 * 
	 * @param graph
	 *            . The graph with distances of all nodes
	 * @param startNode
	 *            . The starting node for path search
	 * @param end
	 *            Node. The ending node for path search
	 * @return An array of nodes for the shortest paths
	 */
	public int[] findShortestPath(int[][] graph, int startNodeIndex,
			int endNodeIndex)
	{
		int[] shortestPath =
		{};

		nodes = prepData(graph);
		startNode = nodes.get(startNodeIndex-1);
		endNode = nodes.get(endNodeIndex-1);
		// assign tentative distance values
		for (Node node : nodes)
		{
			if (node.equals(startNode))
			{
				distMap.put(node.name, 0);
			}
			else
			{
				distMap.put(node.name, Integer.MAX_VALUE);
			}
			unvisitedNodes.add(node);
		}
		processNode(startNode);
		System.out.println(visitedNodes);
		return shortestPath;
	}
	
	private void processNode(Node node)
	{
		Node currentNode = node;
		
		if (unvisitedNodes.contains(node))
		{
			unvisitedNodes.remove(node);
			visitedNodes.add(node.name);
			if (node.equals(endNode))
			{
				// we are done
				return;
			}
		}
		else
		{
			return;
		}
		String closestNeighbour = null;
		for (int index=0; index<currentNode.neighbours.size(); index++)
		{
			String neighbour = currentNode.neighbours.get(index);
			Node neighbourNode = nodes.get(Integer.parseInt(neighbour.substring(1)) - 1);
			int currentTentativeDistance = distMap.get(neighbour);
			int actual = distMap.get(node.name) + currentNode.distance.get(index);
			if (actual < currentTentativeDistance)
			{
				distMap.put(neighbour, actual);
				if (closestNeighbour == null || (distMap.get(neighbour) < distMap.get(closestNeighbour)))
				{
					closestNeighbour = neighbour;
				}
			}		
		}

		// process the next closest neighbour
		processNode(nodes.get(Integer.parseInt(closestNeighbour.substring(1)) - 1));
		
	}

	/**
	 * Method to take input two dimensional integer array and convert it into list of nodes
	 * @param graph integer array with nodes and distances
	 * @return List of Nodes
	 */
	private List<Node> prepData(int[][] graph)
	{
		List<Node> nodes = new ArrayList<Node>();
		// logic goes here
		for (int i = 0; i < graph.length; i++)
		{
			Node n = new Node();
			n.name = "n" + (i + 1);
			for (int j = 0; j < graph[i].length; j++)
			{
				if (graph[i][j] != 0)
				{
					// This is a neighbour node with valid distance
					n.neighbours.add("n" + (j + 1));
					n.distance.add(graph[i][j]);
				}
			}
			nodes.add(n);
		}
		return nodes;
	}

	class Node
	{
		String name;
		List<String> neighbours = new ArrayList<String>();
		List<Integer> distance = new ArrayList<Integer>();

		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("Node: ").append(name).append(" Neighbours:");
			for (int i = 0; i < neighbours.size(); i++)
			{
				builder.append(" ").append(neighbours.get(i));
				builder.append(" ").append(distance.get(i));
			}
			return builder.toString();
		}
		
		@Override
		public boolean equals(Object obj)
		{
			
			return this.name.equals(((Node)obj).name);
		}
	}

	public static void main(String[] args)
	{
		// get the arguments
		// Initialize the array
		int[][] graph =
		{
		{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
		{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
		{ 0, 8, 0, 7, 0, 4, 0, 0, 2 },
		{ 0, 0, 7, 0, 9, 14, 0, 0, 0 },
		{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
		{ 0, 0, 4, 0, 10, 0, 2, 0, 0 },
		{ 0, 0, 0, 14, 0, 2, 0, 1, 6 },
		{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
		{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		Dijkstra d = new Dijkstra();
		d.findShortestPath(graph, 1, 7);

	}
}
