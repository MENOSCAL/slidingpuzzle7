/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.util.List;

/**
 *
 * @author victor
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    
    private static void trySolver(State initialState, AbstractSolver solver) {
		//System.out.println("Solving with "+solver);
		List<State> solution = solver.solve(initialState);
		System.out.println("  States visited: "+solver.getVisitedStateCount());
		System.out.println("  Solution:");
		if (solution == null) {
			System.out.println("    None found.");
		} else {
			for (State s : solution)
				System.out.println("    "+s);
			System.out.println("   "+solution.size()+" states(s)");
		}
	}
	private static void trySolvers(State initialState) {
		trySolver(initialState, new BestFirstSolver());
	}
    
    public static void main(String[] args) {
        System.out.println("Sliding Tile Puzzle");
	System.out.println();
	trySolvers(new SlidingTileState());
    }
    
}
