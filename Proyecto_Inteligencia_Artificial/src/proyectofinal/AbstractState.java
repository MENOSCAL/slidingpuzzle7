/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author victor
 */
public abstract class AbstractState implements State{

    private State parent = null;
    private double distance = 0;

    public AbstractState() {
    }
    
    public AbstractState(State parent) {
        this.parent = parent;
        this.distance = parent.getDistance() + 1;
    }
    
    /*
    @Override
    public Iterable<State> expand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSolution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getHeuristic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public State getParent() {
        return parent;
    }
    
    
    
}
