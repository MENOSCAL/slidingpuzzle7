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
public class SlidingTileState extends AbstractState{

    private char tiles[] = {'b','b','b',' ','w','w','w'};
    
    public SlidingTileState() {
    }

    public SlidingTileState(SlidingTileState parent, int space, int tile) {
        super(parent);
        this.tiles = parent.tiles.clone();
        this.tiles[space] = parent.tiles[tile];
        this.tiles[tile] = parent.tiles[space];
    }

    
    @Override
    public Iterable<State> getPossibleMoves() {
        Set<State> moves = new HashSet<State>();
        int space = getSpace();
        for(int i=space-3; i<=space+3; i++)
            if(i>=0 && i<tiles.length && i!=space)
                moves.add(new SlidingTileState(this,space,i));
        return moves;
    }
    
    private int getSpace(){
        for(int i=0; i<tiles.length; i++)
            if(tiles[i] == ' ')
                return i;
        return 0;
    }

    private int countWhiteTiles(){
        int sum = 0;
        for(int i=0; i<tiles.length; i++)
            if(tiles[i]=='b')
                for(int j=i+1; j<tiles.length; j++)
                    if(tiles[j] == 'w')
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
        if(o==null || !(o instanceof SlidingTileState))
            return false;
        SlidingTileState sts = (SlidingTileState)o;
        return new String(tiles).equals(new String(sts.tiles));
    }
    
    public int hashCode(){
        return new String(tiles).hashCode();
    }
    
    public String toString(){
        return "Sliding Tile State ["+new String(tiles)+"] "+
                "(moves: "+ getDistance() +") (heurisctic: "+getHeuristic()+")";
    }
    
}
