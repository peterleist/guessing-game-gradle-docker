
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Controller {
	
	private Maze m;
	private List<Integer> solution;
	
	public Controller() throws IOException, InterruptedException {
		solution = new ArrayList<Integer>();
		
		int w,h;
		
		List<Integer> inputList = load();
		h = inputList.get(inputList.size()-1);
		inputList.remove(inputList.size()-1);
		w = inputList.get(inputList.size()-1);
		inputList.remove(inputList.size()-1);
		
		m = new Maze(w,h,inputList);
		

		solve();

	}
	

	
	public List<Integer> getSolution(){
		return solution;
	}
	
	public void solve() throws InterruptedException {
		ArrayList<Integer> targets = m.getTargets();
	   
		int s = 0;
		for(int i = 0; i < targets.size(); i++) {
			
			solution.addAll(m.Astar(s, targets.get(i)));

			s = targets.get(i);
		}
		solution.remove(solution.size()-1);
		
	}
	
	public List<Integer> load() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> inputList = new ArrayList<Integer>();
		String str;
		String[] splitStr;
		int h = 0;
		int w = 0;
		boolean run = true, width = true;
		
		while(run) {
			str = input.readLine();
			splitStr = str.split(" ");
			if(width) { w = splitStr.length; width = false;} 
			if(splitStr.length == 1) run = false;
			for(String s: splitStr) {
				inputList.add(Integer.parseInt(s));
			}
			h++;
		}
		inputList.remove(inputList.size()-1);
		h--;
		inputList.add(w);
		inputList.add(h);
		return inputList;
	}
	
	
	
	
	void printSolution() {
		for(int s: solution) {
			if(s == -1) System.out.println("felvesz");
			else System.out.println(s/ m.getMazeHeight() + " " + s% m.getMazeHeight());
		}
		System.out.print("\n");
	}
	
	
	
}
/*
10 13 5 5 3 9 5 5 1 7
12 5 5 3 12 6 9 3 12 3
13 5 3 12 3 9 6 28 5 2
9 5 4 7 12 2 9 5 7 10
8 5 5 5 5 0 4 3 9 6
10 9 5 1 3 8 3 12 6 11
12 6 9 0 2 10 12 3 9 2
11 9 2 8 0 4 3 12 6 10
10 10 10 10 8 3 10 9 3 10
12 6 12 4 6 12 6 14 12 2
1


12 3 9 5 7 9 5 5 5 3
9 2 12 5 5 2 9 3 11 10
12 0 3 9 3 12 2 12 2 10
9 6 12 6 28 5 0 3 10 10
10 9 1 5 3 9 0 0 6 10
8 6 10 13 6 10 10 8 7 10
10 11 12 3 9 6 10 10 9 2
12 2 11 10 12 3 12 6 10 10
9 6 10 12 3 10 9 5 6 10
12 5 4 7 12 4 6 13 5 2
1

10 9 5 5 3
10 10 29 5 6
12 0 1 5 7
9 6 12 1 3
12 5 7 12 2
1
*/
