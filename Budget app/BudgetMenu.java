/**
 * @(#)BudgetMenu.java
 *
 *
 * @author 
 * @version 1.00 2015/10/11
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BudgetMenu extends JFrame implements ActionListener {
	
	private JButton checkBudget,addEvent,masterOptions;
	
	public BudgetMenu() {
		
		checkBudget = new JButton("Show current budget");
		addEvent = new JButton("Add purchase event");
		masterOptions= new JButton("Master user options");
		checkBudget.addActionListener(this);
		addEvent.addActionListener(this);
		masterOptions.addActionListener(this);
		setLayout(new GridLayout(4, 1, 1, 1));
		JPanel title = new JPanel(){
			public void paint(Graphics g){
				g.setFont(new java.awt.Font("Arial",Font.BOLD,35));
				g.setColor(java.awt.Color.LIGHT_GRAY);
				g.drawString("Budget",46,44);
				g.setColor(java.awt.Color.BLACK);
				g.drawString("Budget",50,40);
			}
		};
		add(title);
		add(checkBudget);
		add(addEvent);
		add(masterOptions);
		
	}

    public static void main(String[] args ) {
    	try {
    	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	    } catch(Exception e) {e.printStackTrace();}
    	BudgetMenu window = new BudgetMenu();
		window.setResizable(false);
    	window.setTitle("Budget application");
    	window.setSize(300,450);
    	window.setLocationRelativeTo(null);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    
}