/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author victor
 */
public class BestFirst extends Solver{

    private PriorityQueue<State> queue = null;
    
    public BestFirst(){
        queue = new PriorityQueue<State>(1, new Comparator<State>() {

            @Override
            public int compare(State s1, State s2) {
                return Double.compare(s1.getDistance()+s1.getHeuristic(),
                                      s2.getDistance()+s2.getHeuristic());
            }
        });
    }
    
    
    @Override
    protected boolean hasElements() {
        return !queue.isEmpty();
    }

    @Override
    protected State nextState() {
        return queue.remove();
    }

    @Override
    protected void clearOpen() {
        queue.clear();
    }

    @Override
    protected void addState(State s) {
        if(!queue.contains(s))
            queue.offer(s);
    }
    
}
