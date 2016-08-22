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
    
    public Tree(List<State> solution ) {
        super("Tree");

        final mxGraph grafico = new mxGraph();
        Object parent = grafico.getDefaultParent();
        
        String ia = "ff";
        
        
	PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
        //utilizar add
	/*for (int x : ia) {
            pq1.offer(x);
	}*/
 
	
        
        grafico.getModel().beginUpdate();
        
        
        
        try {

            a = (mxICell) grafico.insertVertex(parent, null, ia, 0, 0, 80, 30);
            b = (mxICell) grafico.insertVertex(parent, null, "b", 0, 0, 80, 30);
            c = (mxICell) grafico.insertVertex(parent, null, "c", 0, 0, 80, 30);
            d = (mxICell) grafico.insertVertex(parent, null, "d", 0, 0, 80, 30);
            e = (mxICell) grafico.insertVertex(parent, null, "e", 0, 0, 80, 30);
            f = (mxICell) grafico.insertVertex(parent, null, "f", 0, 0, 80, 30);
            g = (mxICell) grafico.insertVertex(parent, null, "g", 0, 0, 80, 30);
            h = (mxICell) grafico.insertVertex(parent, null, "h", 0, 0, 80, 30);
            i = (mxICell) grafico.insertVertex(parent, null, pq1, 0, 0, 80, 30);
            j = (mxICell) grafico.insertVertex(parent, null, pq1, 0, 0, 80, 30);
            k = (mxICell) grafico.insertVertex(parent, null, pq1, 0, 0, 80, 30);
            l = (mxICell) grafico.insertVertex(parent, null, pq1, 0, 0, 80, 30);

            grafico.insertEdge(parent, null, "", a, b);
            grafico.insertEdge(parent, null, "", a, c);
            grafico.insertEdge(parent, null, "", b, d);
            grafico.insertEdge(parent, null, "", b, e);
            grafico.insertEdge(parent, null, "", c, f);
            grafico.insertEdge(parent, null, "", c, g);
            grafico.insertEdge(parent, null, "", e, h);
            grafico.insertEdge(parent, null, "", e, i);
            grafico.insertEdge(parent, null, "", e, j);
            grafico.insertEdge(parent, null, "", e, k);
            grafico.insertEdge(parent, null, "", e, l);

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
 
}