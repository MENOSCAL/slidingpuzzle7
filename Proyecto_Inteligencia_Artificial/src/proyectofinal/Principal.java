/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.util.List;

import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
/**
 *
 * @author victor
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    
    private static void testSolver(State initialState, Solver solver) {
        
        String hola = null;
        
		//System.out.println("Solving with "+solver);
		List<State> solution = solver.solve(initialState);
		System.out.println("  States visited: "+solver.getVisitedStateCount());
		System.out.println("  Solution:");
		if (solution == null) {
			System.out.println("    None found.");
		} else {
			for (State s : solution)
				System.out.println("    "+s);
			System.out.println("   "+solution.size()+" states(s)");
                        
                        for (State s1 : solution){
                            System.out.println(s1.getroot());
                            hola= s1.getroot();
                        }
              
                        
                         Tree frame = new Tree(solution);
                
                frame.setSize(800, 500);

                frame.setVisible(true);
                
		}
	}
	private static void testSolvers(State initialState) {
		testSolver(initialState, new BestFirst());
	}
    
    public static void main(String[] args) throws Exception {
        
        final  int valor_heuristica;
        
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("SlidingPuzzle");
        frame.setSize(750, 400);
        frame.setLocationByPlatform(true);
        JList imageList = crearListImage();     
        
        Label lsubt = new Label("Goal:");
        lsubt.setFont(new Font("Arial", Font.BOLD, 16));
        lsubt.setForeground(Color.BLUE);
        Label lgoal = new Label("Blue Chips Left");
        lgoal.setFont(new Font("Arial", Font.BOLD, 16));
        
       String[] listacombo = new String[] {"Menor número de saltos ", "Menor costo",};
        JComboBox<String> funcionescombo = new JComboBox<>(listacombo); 
        // add to the parent container (e.g. a JFrame):
     
        String selectedHeuristic = (String) funcionescombo.getSelectedItem();
        funcionescombo.setForeground(Color.BLUE);
        funcionescombo.setFont(new Font("Arial", Font.BOLD, 14));
        
        if(funcionescombo.getSelectedIndex()==0){
            valor_heuristica=1;
        }
        else{
            valor_heuristica=2;
        }
            
      
        
        JButton bcomenzar = new JButton( "Comenzar");
        bcomenzar.addActionListener(new ActionListener() {
            
            
            

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                    //imageList.disable();
                    
                    DefaultListModel model = (DefaultListModel)imageList.getModel();
                    
                    char chips[] = {model.get(0).toString().charAt(0),model.get(1).toString().charAt(0),model.get(2).toString().charAt(0),
                        model.get(3).toString().charAt(0),model.get(4).toString().charAt(0),model.get(5).toString().charAt(0),
                        model.get(6).toString().charAt(0)};
                    
                    System.out.println("Sliding Tile Puzzle");
                    System.out.println();
                    testSolvers(new SlidingPuzzle(chips, valor_heuristica));
                    
            }
        });
        
        
      /*  
        JButton barbol = new JButton( "Ver Árbol");
        barbol.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                char c[]= {'f','g'};
                try {
                Tree frame = new Tree(c);
                
                frame.setSize(800, 500);

                frame.setVisible(true);
           

                } finally{}
            }
        });
       */ 
        JPanel toolBar = new JPanel();
       
        
        toolBar.add(funcionescombo);
        toolBar.add( bcomenzar);
        //toolBar.add( barbol);
        
        toolBar.add( lsubt);
        toolBar.add( lgoal);
    
        frame.add( toolBar, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(imageList));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    
    
    
    private static JList crearListImage() {

        JList imageList = new JList(ModeloCrear());
        imageList.setCellRenderer(new CellImgRenderizador());
        imageList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        imageList.setVisibleRowCount(-1);
        imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        imageList.setFixedCellWidth(100);
        imageList.setFixedCellHeight(70);
        imageList.setDragEnabled(true);
        imageList.setDropMode(DropMode.INSERT);
        imageList.setTransferHandler(new ImageTransferHandler(imageList));
        
        return imageList;
}

    private static DefaultListModel ModeloCrear() {
        DefaultListModel model = new DefaultListModel();
        model.addElement(new IcoColor(Color.YELLOW,"y"));
        model.addElement(new IcoColor(Color.YELLOW,"y"));
        model.addElement(new IcoColor(Color.YELLOW,"y"));
        model.addElement(new IcoColor(Color.WHITE,"w"));
        model.addElement(new IcoColor(Color.BLUE,"b"));
        model.addElement(new IcoColor(Color.BLUE,"b"));
        model.addElement(new IcoColor(Color.BLUE,"b"));
        
        
        
        return model;
    }

    static class ImageTransferHandler extends TransferHandler {

        private static final DataFlavor DATA_FLAVOUR = new DataFlavor(IcoColor.class, "Images");

        private final JList previewList;
        private boolean inDrag;

        ImageTransferHandler(JList previewList) {
            this.previewList = previewList;
        }

        public int getSourceActions(JComponent c) {
            return TransferHandler.MOVE;
        }

        protected Transferable createTransferable(JComponent c) {
            inDrag = true;
            return new Transferable() {
                public DataFlavor[] getTransferDataFlavors() {
                    return new DataFlavor[] {DATA_FLAVOUR};
                }

                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return flavor.equals(DATA_FLAVOUR);
                }

                public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                    return previewList.getSelectedValue();
                }
            };
        }

        public boolean canImport(TransferSupport support) {
            if (!inDrag || !support.isDataFlavorSupported(DATA_FLAVOUR)) {
                return false;
            }

            JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();
            if (dl.getIndex() == -1) {
                return false;
            } else {
                return true;
            }
        }

        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            Transferable transferable = support.getTransferable();
            try {
                Object draggedImage = transferable.getTransferData(DATA_FLAVOUR);
                
                JList.DropLocation dl = (JList.DropLocation)support.getDropLocation();
                DefaultListModel model = (DefaultListModel)previewList.getModel();
                int dropIndex = dl.getIndex();
                
                if (model.indexOf(draggedImage) < dropIndex) {
                    dropIndex--;
                }
                model.removeElement(draggedImage);
                
                model.add(dropIndex, draggedImage);
                
                
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        protected void exportDone(JComponent source, Transferable data, int action) {
            super.exportDone(source, data, action);
            inDrag = false;
        }
    }

    static class CellImgRenderizador extends JPanel implements ListCellRenderer {

        DefaultListCellRenderer defaultListCellRenderer = new DefaultListCellRenderer();
        JLabel imageLabel = new JLabel();
        JLabel descriptionLabel = new JLabel();

        CellImgRenderizador() {
            setLayout(new BorderLayout());
            Border border = BorderFactory.createLineBorder(Color.black, 4);
            imageLabel.setBorder(border);
            add(imageLabel, BorderLayout.CENTER);
            add(descriptionLabel, BorderLayout.SOUTH);
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            defaultListCellRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setBorder(defaultListCellRenderer.getBorder());
            setBackground(defaultListCellRenderer.getBackground());
            imageLabel.setIcon((Icon)value);
            
            descriptionLabel.setText("");
            return this;
        }
    }

    static class IcoColor implements Icon, Serializable {
        private Color color;
        private String name;

        IcoColor(Color color,String name) {
            this.color = color;
            this.name = name;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, getIconWidth(), getIconHeight());
        }

        public int getIconWidth() {
            return 200;
        }

        public int getIconHeight() {
            return 100;
        }

        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return color.equals(((IcoColor)o).color);
        }

        @Override
        public String toString() {
            return name;
        }

    }
    
}
