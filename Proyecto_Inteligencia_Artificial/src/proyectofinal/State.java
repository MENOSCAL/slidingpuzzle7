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

public interface State {
    
    public Iterable<State> expand();
    
    public boolean isSolution();
    
    public double getHeuristic();
    
    public double getDistance();
    
    public State getParent();
    
    public String getroot();
}
