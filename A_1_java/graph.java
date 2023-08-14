import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

class Edge{
	int vtx;
	int distance;

	public Edge(){ }
	public Edge(int v,int w){
		this.vtx=v;
		this.distance=w;
	}
}

class graphc {
	
	public static List<Edge> Head[];
	public static final int MAX = 20;
	public static int edges = 0, vtxs = 0;
	public static boolean visited[] = new boolean[MAX];
	public static String[] names = new String[MAX];
    public static Scanner sc = new Scanner(System.in);

	public graphc(){
		Head = new LinkedList[MAX];
		for(int i=0; i<Head.length; i++){
			Head[i] = new LinkedList<Edge>();
		}
	}

	public void addEdge(int sv,int ev,int distance){
		Head[sv].add(new Edge(ev,distance));
	}

	public void PrintGraph(){
		for(int i=0; i < vtxs; i++){
			System.out.print((i+1)+" " + names[i] + " -->  "); 

			for (int m = 0; m < Head[i].size(); m++) {
				System.out.print("("+ (Head[i].get(m).vtx+1) +","+ Head[i].get(m).distance +") --> "); 
			}System.out.println("X"); 
		
		}
	}
	
	public void get_data_file(){
	
		try{
			int sv,ev,dis;
			File file = new File("input.txt");
			Scanner sc = new Scanner(file);
			
			String s = sc.nextLine();
			String[] vtxEdge = s.split(" ");
			vtxs = Integer.parseInt(vtxEdge[0]);
			edges = Integer.parseInt(vtxEdge[1]);

			
			for(int i=0;i<vtxs;i++){
				names[i] = sc.nextLine();
			}
			
			String[] data;
			for(int i=0;i<edges;i++){
				s = sc.nextLine();
				data = s.split(" ");
				sv = Integer.parseInt(data[0]);
				ev = Integer.parseInt(data[1]);
				dis = Integer.parseInt(data[2]);
				sv--;ev--;
				
				addEdge(sv, ev, dis);
				addEdge(ev, sv, dis);
			}
			System.out.println("Graph data accepted by file :" + file + "\n"); 
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public void get_data_manual(){
		int sv,ev,dis;
		sv=ev=dis=0;
		System.out.print("Enter How many vertices are there in graph?? ");
		vtxs = sc.nextInt();
		
		System.out.print("Enter How many Edgeds are there in graph?? ");
		edges = sc.nextInt();

		System.out.println("Enter starting vertices and ending vtx with distance. EX= 2 1 150 :"); 
		for (int i = 0; i < edges; i++) {
			sv = sc.nextInt(); ev = sc.nextInt(); dis = sc.nextInt();
			sv--;ev--;
			addEdge(sv, ev, dis);
		}
	}

	void dfsr(int v){
		visited[v] = true;
		System.out.print(v+" ("+ names[v]+")  ");

		Iterator<Edge> iter = Head[v].listIterator();
		while(iter.hasNext()){
			Edge n = iter.next();
			if(!visited[n.vtx]){
				dfsr(n.vtx);
			}
		}
	}
	
	void BFS(int s)
	{
		boolean visited[] = new boolean[vtxs];
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			s = queue.poll();
			System.out.print(s+" ("+ names[s]+")  ");
			Iterator<Edge> iter = Head[s].listIterator();
			while (iter.hasNext()) {
				Edge n = iter.next();
				if (!visited[n.vtx]) {
					visited[n.vtx] = true;
					queue.add(n.vtx);
				}
			}
		}
	}
	
	void user_choice(){
		int ch;
		while(true){
			System.out.println("\n\n1. DFS of graph.\n2. BFS of graph.\n3. Exit.\n\tEnter choice : ");
			ch = sc.nextInt();
			switch (ch) {
				case 1:
					System.out.println("\nDFS : "); 
					dfsr(0);
					break;
				case 2:
					System.out.println("\nBFS : "); 
					BFS(0);
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Enter correct input "); 
					break;
			}

		}
	}
}

public class graph {
	public static void main(String[] args) throws Exception{

		graphc g = new graphc();
		g.get_data_file();
		g.PrintGraph();
		g.user_choice();
		

	}
}
