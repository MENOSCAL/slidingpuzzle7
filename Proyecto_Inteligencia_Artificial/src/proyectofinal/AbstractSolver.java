/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author victor
 */
public abstract class AbstractSolver implements Solver{

    private Set<State> closed = new HashSet<State>();
    
    @Override
    public List<State> solve(State initialState) {
        // Reset closed set
        closed.clear();
        clearOpen();
        addState(initialState);
        while(hasElements()){
            State s = nextState();
            if(s.isSolution())
                return findPath(s);
            closed.add(s);
            Iterable<State> moves = s.getPossibleMoves();
            for (State move : moves)
                if(!closed.contains(move))
                    addState(move);
        }
        return null;
    }
    
    public int getVisitedStateCount(){
        return closed.size();
    }
    
    private List<State> findPath(State solution){
        LinkedList<State> path = new LinkedList<State>();
        while(solution != null){
            path.addFirst(solution);
            solution = solution.getParent();
        }
        return path;
    }
    
    protected abstract boolean hasElements();
    
    protected abstract State nextState();
    
    protected abstract void clearOpen();
    
    protected abstract void addState(State s);
}
