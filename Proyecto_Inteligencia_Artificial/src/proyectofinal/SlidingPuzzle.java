/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author victor
 */
public class SlidingPuzzle extends AbstractState{

    private char chips[];
    
    public SlidingPuzzle(char chips[]) {
        this.chips= chips;
    }

    public SlidingPuzzle(SlidingPuzzle parent, int space, int tile) {
        super(parent);
        this.chips = parent.chips.clone();
        this.chips[space] = parent.chips[tile];
        this.chips[tile] = parent.chips[space];
    }

    
    @Override
    public Iterable<State> expand() {
        Set<State> moves = new HashSet<State>();
        int space = getSpace();
        for(int i=space-3; i<=space+3; i++)
            if(i>=0 && i<chips.length && i!=space)
                moves.add(new SlidingPuzzle(this,space,i));
        return moves;
    }
    
    private int getSpace(){
        for(int i=0; i<chips.length; i++)
            if(chips[i] == ' ')
                return i;
        return 0;
    }

    private int countWhiteTiles(){
        int sum = 0;
        for(int i=0; i<chips.length; i++)
            if(chips[i]=='y')
                for(int j=i+1; j<chips.length; j++)
                    if(chips[j] == 'b')
                        sum++;
        return sum;
    }
    
    @Override
    public boolean isSolution() {
        return countWhiteTiles() == 0;
    }

    @Override
    public double getHeuristic() {
        return countWhiteTiles();
    }
    
    public boolean equals(Object o){
        if(o==null || !(o instanceof SlidingPuzzle))
            return false;
        SlidingPuzzle sts = (SlidingPuzzle)o;
        return new String(chips).equals(new String(sts.chips));
    }
    
    public int hashCode(){
        return new String(chips).hashCode();
    }
    
    public String toString(){
        return "Sliding Tile State ["+new String(chips)+"] "+
                "(moves: "+ getDistance() +") (heurisctic: "+getHeuristic()+")";
    }
    
}
