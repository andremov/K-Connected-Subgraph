/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subgraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class MatrixWindow extends JFrame {
	
    JLabel infoDisplay;
    JButton processBtn;

    JLabel cityNameDisplay;
    JTextField cityName;
    JButton addVertexBtn;
    JButton resetBtn;

    JTable adyacency;

    public MatrixWindow() {
		setLayout(null);
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Adyacencia");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		init();

		setVisible(true);
    }
	
    /**
     * inicializa la interfaz
     */
    private void init() {

		adyacency = new JTable();
		adyacency.setModel(new TableModel(new String[] {"Nombre"},0));
		adyacency.setRowHeight(30);
		adyacency.getColumnModel().getColumn(0).setPreferredWidth(140);
		adyacency.getColumnModel().getColumn(1).setPreferredWidth(120);
		adyacency.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		adyacency.getTableHeader().setReorderingAllowed(false);
		adyacency.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleValue();
			}

			@Override
			public void mousePressed(MouseEvent e) { }

			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }
			
		});

		JScrollPane scroll = new JScrollPane(adyacency);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setSize(600, 559);
		scroll.setLocation(1,1);
		add(scroll);

		addVertexBtn = new JButton("Agregar Vertice");
		addVertexBtn.setSize(175, 50);
		addVertexBtn.setLocation(605, 37);
		addVertexBtn.setFocusable(false);
		addVertexBtn.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				addVertex();
			}
		});
		add(addVertexBtn);

		resetBtn = new JButton("Eliminar Ciudad");
		resetBtn.setSize(175, 50);
		resetBtn.setLocation(605, 90);
		resetBtn.setFocusable(false);
		resetBtn.addActionListener(new ActionListener( ) {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				reset();
		    }
		});
		add(resetBtn);

		final String text = "Ingrese '0' para inhabilitar una conexion.";
		final String html1 = "<html><body style='width: ";
		final String html2 = "px'>";
		infoDisplay = new JLabel(html1+175+html2+text);
		infoDisplay.setSize(175, 40);
		infoDisplay.setLocation(605, 320);
		add(infoDisplay);

		processBtn = new JButton("Resolver");
		processBtn.setSize(175, 60);
		processBtn.setLocation(605, 400);
		processBtn.setFocusable(false);
		processBtn.addActionListener(new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				   sendGraph();
				} catch(Exception ex) { }
			}
		});
		add(processBtn);
    }
	
	private void reset() {
		adyacency = new JTable();
		adyacency.setModel(new TableModel(new String[] {"Nombre"},0));
		adyacency.setRowHeight(30);
		adyacency.getColumnModel().getColumn(0).setPreferredWidth(140);
		adyacency.getColumnModel().getColumn(1).setPreferredWidth(120);
		adyacency.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		adyacency.getTableHeader().setReorderingAllowed(false);
		adyacency.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleValue();
			}

			@Override
			public void mousePressed(MouseEvent e) { }

			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }
			
		});
	}
	
    /**
     * agrega una ciudad a la tabla
     */
    private void addVertex() {
		String name = generateName(adyacency.getRowCount()+1);
	    DefaultTableModel model = (DefaultTableModel) adyacency.getModel();

	    Object[] columnData = new Object[adyacency.getRowCount()];
	    model.addColumn(name,columnData);

	    Object[] rowData = new Object[adyacency.getColumnCount()];
	    for (int i = 1; i < rowData.length; i++) {
		rowData[i] = "1";
	    }
	    rowData[0] = name;
	    model.addRow(rowData);

	    cityName.setText("");
	    sendGraph();
    }
	
	private String generateName(int id) {
		String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
							"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		String result = "";
		ArrayList<Integer> digits = new ArrayList<>();
		digits.add(0);
		int disposable = id;
		
		while (disposable > 0) {
			disposable--;
			digits.set(0, digits.get(0)+1);
			int i = 0;
			while (digits.get(i) >= 26) {
				digits.set(i, digits.get(i)-26);
				if (digits.size() == i+1) {
					digits.add(0);
				}
				digits.set(i+1, digits.get(i+1)+1);
			}
		}
		
		for (int i = 0; i < digits.size(); i++) {
			result += letters[digits.get(i)];
		}
		
		return result;
	}
	
	private void toggleValue() {
		int x = adyacency.getSelectedColumn();
		int y = adyacency.getSelectedRow();
		int curValue = Integer.parseInt((String)adyacency.getValueAt(x,y));
		adyacency.setValueAt((curValue+1)%2,x,y);
		adyacency.setValueAt((curValue+1)%2,y,x);
	}

    /**
     * manda la informacion a la ventana principal
     */
    private void sendGraph() {
		DefaultTableModel model = (DefaultTableModel) adyacency.getModel();
		String[] names = new String[model.getRowCount()];
		for (int i = 0; i < model.getRowCount(); i++) {
			names[i] = (String)adyacency.getValueAt(i,0);
		}
		Vertex[] V = Vertex.createVertexArray(names);
		int[][] C = new int[model.getRowCount()][model.getRowCount()];
		
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 1; j < model.getRowCount()+1; j++) {
				C[i][j-1] = Integer.parseInt((String)adyacency.getValueAt(i,j));
			}
		}
		
		Handler.createGraph(V,C);
    }
	
    private class TableModel extends DefaultTableModel {

		public TableModel(Object[] columnNames, int rowCount) {
			super(columnNames, rowCount);
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
    }
}