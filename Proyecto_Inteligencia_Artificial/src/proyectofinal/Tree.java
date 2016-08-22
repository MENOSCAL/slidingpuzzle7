/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author CltControl
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.util.List;
import java.util.PriorityQueue;

public class Tree extends JFrame {

    
    mxICell a,b,c,d,e,f,g,h,i,j,k,l;
    
    public Tree() {
        super("Tree");

        final mxGraph grafico = new mxGraph();
        Object parent = grafico.getDefaultParent();
        
        //String ia = "ff";
        
        
	//PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
        //utilizar add
	/*for (int x : ia) {
            pq1.offer(x);
	}*/
 
	
        
        grafico.getModel().beginUpdate();
        
        
        
        try {
    
            Object[] raices = Solver.path.toArray();
            Object[] estados = Solver.estados.toArray();
            
            for (int i = 0; i < Solver.path.size(); i++) {
            System.out.println("Raices:" + raices[i]);            
            }
            
            int j =0;
            int[] indices = new int[0];
            try {
                for (int i = 0; i < Solver.estados.size(); i++) {

                    System.out.println("Estados:" + estados[i]);                      
                                 if(raices[j].equals(estados[i])){                                     
                                     indices = addInt(indices, i);

                                     j++;
                          }            
                      }
                
                        System.out.println("Raices indice:");

                        for (int i = 0; i < indices.length; i++){
                          
                                System.out.println(indices[i]);
                            
                        }
                
                int r=0;
                System.out.println("TAMAÑO ESTADOS"+Solver.estados.size());
                for (int w = 0; w < Solver.estados.size(); w++) {
                        estados[w] = (mxICell) grafico.insertVertex(parent, null, Solver.estados.get(w), 0, 0, 80, 30);                
                        
                        System.out.println("r indice : "+r + "valor"+indices[r]);
                        System.out.println("i: "+w);
                        
                        
                        //Aqui en 
                        //estados[r] 
                        //creo que deberia de ser 
                        //estados[indices[r]
                        
                        grafico.insertEdge(parent, null, "", estados[r], estados[w]);          
                        
                        
                        if(w==indices[r])
                        {
                        System.out.println("Indices iguales");
                        r++;
                        }
                        

                }
                 
                


   
    
  
    
                
                    
            } finally {
                grafico.getModel().endUpdate();
            }
      
     
            
            //System.out.println(Arrays.toString(AbstractSolver.path.toArray()));


        } finally {
            grafico.getModel().endUpdate();
        }

        // define layout
        mxIGraphLayout layout = new mxHierarchicalLayout(grafico);
        layout.execute(grafico.getDefaultParent());



        mxGraphComponent graphComponent = new mxGraphComponent(grafico);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(graphComponent, BorderLayout.CENTER);
        

    }
    
        public static int[] addInt(int [] series, int newInt){
    //create a new array with extra index
    int[] newSeries = new int[series.length + 1];

    //copy the integers from series to newSeries    
    for (int i = 0; i < series.length; i++){
        newSeries[i] = series[i];
    }
//add the new integer to the last index     
    newSeries[newSeries.length - 1] = newInt;



    return newSeries;

     }
 
}