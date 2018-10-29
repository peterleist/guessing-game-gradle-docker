

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Maze {
	private int mazeSize;
	private int mazeWidth;
	private int mazeHeight;
	
	int c = 0;

	
	int[] costs; 
	
	boolean[] visited;
	private LinkedHashSet<Integer> nodes[];
	private ArrayList<Integer> targetList = new ArrayList<Integer>();
	
	public void removeTarget() {
		targetList.remove(0);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Maze(int w, int h, List<Integer> inputList){
		mazeWidth = w;
		mazeHeight = h;
		mazeSize = w*h;
		
		nodes = new LinkedHashSet[mazeSize];
		for(int i = 0; i < mazeSize; ++i) nodes[i] = new LinkedHashSet();
		
		visited  = (boolean[]) Array.newInstance(boolean.class, mazeSize);
		for(int i = 0; i< mazeSize; i++) visited[i] = false;
		
		costs = (int[]) Array.newInstance(int.class, mazeSize);
		for(int i = 0; i< mazeSize; i++) costs[i] = 0;
		
		loadMaze(inputList);
	}
	
	public int getSize() {
		return mazeSize;
	}
	
	
	public int getMazeWidth() {
		return mazeWidth;
	}

	public int getMazeHeight() {
		return mazeHeight;
	}

	public ArrayList<Integer> getTargets(){
		return targetList;
	}
	
	
	void addConnection(int sourceNumber, int targetNumber) {
		nodes[sourceNumber].add(targetNumber);
	}
	

	
	void loadMaze(List<Integer> inputList) {
		for(int actualNodeLoad = 0; actualNodeLoad < mazeSize; actualNodeLoad++) {
			int data = inputList.get(actualNodeLoad);
			
			if(16 <= data) { targetList.add(actualNodeLoad); data-=16;}
		
			if(data < 8) { 
				if(actualNodeLoad % mazeWidth != 0) this.addConnection(actualNodeLoad, actualNodeLoad - 1); 
			}
			else data -= 8;
		
			if(data < 4) {
				if(actualNodeLoad + mazeHeight < mazeSize) this.addConnection(actualNodeLoad, actualNodeLoad + mazeHeight);
			}
			else data -= 4;
		
			if(data < 2) {
				if(actualNodeLoad % mazeWidth != mazeWidth - 1) this.addConnection(actualNodeLoad, actualNodeLoad + 1);
			}
			else data -= 2;
		
			if(data < 1) {
				if(actualNodeLoad - mazeHeight > 0)this.addConnection(actualNodeLoad, actualNodeLoad - mazeHeight); 
			}
			else data -= 1;
		}
		targetList.add(mazeSize - 1);
	}
    
    public void printgraph() {
    		for(int i = 0; i < mazeSize; i++) {
    			System.out.print(i + ": ");
    			for(int n: nodes[i]) {
    				System.out.print(n + " ");
    			}
    			System.out.print("\n");
    		}	
    		}
    		
    		
    public List<Integer> Astar(int s, Integer d) throws InterruptedException {
    		List<Integer> queue = new ArrayList<Integer>();
    		queue.add(s);
    		int actual = 0;
    		int cost;
    		
    		for(int i = 0; i< mazeSize; i++) costs[i] = 0;
    		cost(d);
    		
    		for(int i = 0; i< mazeSize; i++) visited[i] = false;
    		
    		while(!queue.isEmpty()) {
    			
    			if(s == d) {
    				queue.remove(0);
    				queue.add(-1);
    				return queue;
    			}
    			cost = mazeSize;
    			visited[s] = true;
    			for(int n: nodes[s]) {
    				
    				int new_cost = distance(n,d) + costs[n]; 
    				
    				if(new_cost < cost && !visited[n]) {
    					cost = new_cost;
    					actual = n;
    				}
    				if(new_cost == cost && !visited[n]) {
    					if(costs[n] < costs[actual]) {
    						cost = new_cost;
        					actual = n;
    					}
    				}
    			}
    			s = actual;
    			queue.add(s);
    		}
    		
		return queue;
    		
    }
    
    public int distance(int s, int d) {
    		int sx = s % mazeHeight;
    		int sy = s / mazeHeight;
    		int dx = d % mazeHeight;
    		int dy = d / mazeHeight;
    		
    		return Math.abs(dx - sx) + Math.abs(dy - sy);
    	
    }
    
    
    public void cost(int s) {
    		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    		queue.add(s);
    		int c = 0;
    		
    		for(int i = 0; i< mazeSize; i++) visited[i] = false;
    		visited[s] = true;
    			
    		while(!queue.isEmpty()) {
    			c++;
    			int next = queue.poll();
    			for(int n: nodes[next]) {
    				if(!visited[n]) {
    					queue.add(n);
    					visited[n] = true;
    					costs[n] = c;
    				}
            	}
    		}	
    }	
}
