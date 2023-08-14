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
	public static final int MAX = 25;
	public static int edges = 0, vtxs = 0;
	public static String[] names = new String[MAX];
    public static Scanner sc = new Scanner(System.in);
	public static boolean visited[] = new boolean[MAX];
	public static Integer[] node_values = new Integer[MAX];

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
			System.out.print((i+1)+" " + names[i] + " ("+ node_values[i] +")-->  "); 

			for (int m = 0; m < Head[i].size(); m++) {
				System.out.print("("+ (Head[i].get(m).vtx+1) +","+ Head[i].get(m).distance +") --> "); 
			}System.out.println("X"); 
		
		}
	}
	
	public void get_data_file(){
	
		try{
			int sv,ev,dis;
			File file = new File("input_astarAlgo.txt");
			Scanner sc = new Scanner(file);
			
			String s = sc.nextLine();
			String[] vtxEdge = s.split(" ");
			vtxs = Integer.parseInt(vtxEdge[0]);
			edges = Integer.parseInt(vtxEdge[1]);

			String[] name_node_val;
			for(int i=0;i<vtxs;i++){
				//names[i] = sc.nextLine();
                name_node_val = sc.nextLine().split(" ");
                names[i] = name_node_val[0];
                node_values[i] = Integer.parseInt(name_node_val[1]);
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

    void aStar_algo(int sv, int ev){
        int min=100, sum=0;
        int shortest_node=0,tempVtx=0, current_vtx = sv;
        while(true){
            System.out.println("current vertex : " + names[current_vtx]);

            for (int i = 0; i < Head[current_vtx].size(); i++) {
                
                tempVtx = Head[current_vtx].get(i).vtx;
                sum = Head[current_vtx].get(i).distance + node_values[tempVtx];
			
                if(min > sum){
                    shortest_node = tempVtx;
                    min = sum;
                }
				
            }

			System.out.println("Node with shortes f(n): "+names[shortest_node]+"("+(shortest_node+1)+")  f(n) --> "+min);
            current_vtx = shortest_node;

            if(current_vtx == ev){
                System.out.println("\nEnding Node : " + names[ev] + "  Node found");
                break;
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

	void aStar_algo_iter(int sv, int ev){
        int min=100, sum=0;
        int shortest_node=0,tempVtx=0, current_vtx = sv;
        while(true){
            System.out.println("current vertex : " + names[current_vtx]);
			
            Iterator<Edge> iter = Head[current_vtx].listIterator();
            while(iter.hasNext()){
                Edge curr_node = iter.next();
                
                tempVtx = curr_node.vtx;
                sum = curr_node.distance + node_values[tempVtx];
				
                if(min > sum){
                    shortest_node = tempVtx;
                    min = sum;
                }
				
            }

			System.out.println("Node with shortes f(n): "+names[shortest_node]+"("+(shortest_node+1)+")  f(n) --> "+min);
            current_vtx = shortest_node;

			if(current_vtx == ev){
                System.out.println("\nEnding Node : " + names[ev] + "  is found");
                break;
            }

        }
    }

	

	void user_choice(){
		int ch;
		while(true){
			System.out.println("\n\n1. DFS of graph.\n2. BFS of graph.\n3. A* (A Star) Algorithm.\n4. A* (A Star) Algorithm Manual Input.\n5. Exit.\n\tEnter choice : ");
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
                    System.out.println("For A* algo we are using 'A' as starting vertex and 'J' as Ending vertex.\n"); 
                    aStar_algo_optimized(0, 9); // (A,J)
                    break;
				case 4:
                    // System.out.println("Enter Starting vtx and Ending vtx index as above graph(1 4)");
					// int svxt = sc.nextInt();
					// int evtx = sc.nextInt();
                    // aStar_algo_iter(svxt , evtx);
                    break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Enter correct input "); 
					break;
			}

		}
	}
}

public class Astar_algo {
	public static void main(String[] args) throws Exception{

		graphc g = new graphc();
		g.get_data_file();
		g.PrintGraph();
		g.user_choice();
		

	}
}
