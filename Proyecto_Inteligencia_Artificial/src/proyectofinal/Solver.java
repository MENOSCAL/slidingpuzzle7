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
public interface Solver {
    
    public List<State> solve(State initialState);
    
}
