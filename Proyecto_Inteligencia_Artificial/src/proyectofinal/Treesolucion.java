/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author Ronny
 */
import javax.swing.JLabel;
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
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import java.util.PriorityQueue;

import java.util.*;
import javafx.scene.text.Font;
import java.awt.Color;
import javax.swing.*;


public class Treesolucion extends JFrame {


    public Treesolucion() {
        super("Tree SOLUCIÓN");
       
        final mxGraph grafico = new mxGraph();
        Object parent = grafico.getDefaultParent();
      
        grafico.getModel().beginUpdate();
        
        try {
    
            Object[] raices = Solver.path.toArray();
            
            Object v = (mxICell) grafico.insertVertex(parent, null, Solver.path.size()+" Estados solución", 200, 0, 100, 30);                
            grafico.setCellStyles(mxConstants.STYLE_FILLCOLOR, "#E9BCFF", new Object[]{v});
            
            
            for (int i = 0; i < Solver.path.size(); i++) {
            //System.out.println("Raices:" + raices[i]); 
            raices[i] = (mxICell) grafico.insertVertex(parent, null, Solver.path.get(i), 0, 0, 280, 30,"shape=ellipse;perimeter=ellipsePerimeter");                
            
            }
            for (int i = 0; i < Solver.path.size(); i++) {
                
            if(i<Solver.path.size()-1){               
                
                grafico.insertEdge(parent, null, "", raices[i], raices[i+1]);               
                
            }   
            if(i>0&&i<Solver.path.size()-1)
            grafico.setCellStyles(mxConstants.STYLE_FILLCOLOR, "#FFFFCA", new Object[]{raices[i]});
            
            }
        } finally {
            grafico.getModel().endUpdate();
        }
        // define layout
        
        
        while (!Solver.path.isEmpty()) {
        Solver.path.removeFirst();
        }
        mxIGraphLayout layout = new mxHierarchicalLayout(grafico);
        layout.execute(grafico.getDefaultParent());


        mxGraphComponent graphComponent = new mxGraphComponent(grafico);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(graphComponent, BorderLayout.CENTER);
        

    }
    
    public static int[] addInt(int [] series, int newInt){

    int[] newSeries = new int[series.length + 1];
    for (int i = 0; i < series.length; i++){
        newSeries[i] = series[i];    }
       
    newSeries[newSeries.length - 1] = newInt;
    return newSeries;

     }
 
}