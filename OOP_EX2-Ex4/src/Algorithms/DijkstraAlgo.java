package Algorithms;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Map;
import GIS.Node;
import Geom.Point3D;
import graph.Graph;
import graph.Graph_Algo;
/**
 * Dijakstra Algo class-
 * Iterate over the list of fruits using the Dijkstra algorithm:
 * 1. finds the closest fruit.
 * 2. updates the graph.
 * @author Maoz Grossman, Liron Arad, Michael Lemberger
 */
public class DijkstraAlgo{
	Game _game;public Fruit fruit;
	public ArrayList<String>path;
	double distance;
	public Cgraph C;public Graph graph;
	/**
	 * constructor
	 * @param game
	 * @param C
	 */
	public DijkstraAlgo(Game game,Cgraph C) {
		this._game = game;
		path=new ArrayList<String>();
		distance=Integer.MAX_VALUE;
		this.C=C;
		graph=new Graph();
		runAlgo();
	}
	
	/**
	 * main use of algorithm.
	 * iterates over the fruits. 
	 */
	public void runAlgo() {
		String source="a";String target="b";
		Iterator<GIS_element> fruits=_game.fruits.iterator();
		while(fruits.hasNext()) {
			Fruit f=(Fruit) fruits.next();
			int[]arr=C.map.gpsToPixel(f.get_p().x(),f.get_p().y());
			Node targeted=new Node(f.get_p(),new Point3D(arr[0],arr[1]),"b");
			CopyGraph();
			graph.Node node=new graph.Node("b");
			graph.add(node);
			C.IsConnected(targeted,C.nodes);
			for(Node n:targeted._neighbors) {
			graph.addEdge(n._name, targeted._name, n._point.distance2D(targeted._point));
			}
			Graph_Algo.dijkstra(graph,source);
			graph.Node d=graph.getNodeByName(target);
			double dis=d.getDist();
			if(dis<=distance) {
				distance=dis;
				this.path=d.getPath();
				this.fruit=f;
			}
		}
	}
	/**
	 * The graph + a fruit.
	 */
	public void CopyGraph(){
		this.graph=new Graph();
		for(Node n:C.nodes) {
			graph.Node d=new graph.Node(n._name);
			this.graph.add(d);
			for(Node n1:n._neighbors) {
				this.graph.addEdge(n._name, n1._name, n._point.distance2D(n1._point));
			}
		}
	}
}