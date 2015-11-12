/**
 * @(#)BudgetProto.java
 *
 *
 * @author 
 * @version 1.00 2015/10/11
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class BudgetProto extends JFrame implements ActionListener {
	
	private JButton checkBudget,addEvent,masterOptions, printUsers, quitBtn;
	private ArrayList users = new ArrayList();
	private ArrayList events = new ArrayList();
	
	public BudgetProto() {
		
		checkBudget = new JButton("Show current budget");
		addEvent = new JButton("Add purchase event");
		masterOptions= new JButton("Add user");
		printUsers = new JButton("Userlist");
		quitBtn = new JButton("Quit");
		checkBudget.addActionListener(this);
		addEvent.addActionListener(this);
		masterOptions.addActionListener(this);
		printUsers.addActionListener(this);
		quitBtn.addActionListener(this);
		setLayout(new GridLayout(6, 1, 1, 1));
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
		add(printUsers);
		add(quitBtn);
		
	}

    public static void main(String[] args ) {
    	try {
    	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	    } catch(Exception e) {e.printStackTrace();}
    	BudgetProto window = new BudgetProto();
		window.setResizable(false);
    	window.setTitle("Budget application");
    	window.setSize(300,450);
    	window.setLocationRelativeTo(null);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == checkBudget){
    		Icon icon = new ImageIcon("history.png");
    		JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, icon);
		}
    	else if (e.getSource() == masterOptions){
    			testing();
		}
		else if (e.getSource() == printUsers){
    		System.out.println(users);
    		JOptionPane.showMessageDialog(null, users);
		}
		else if (e.getSource() == addEvent){
			testing2();
		}
		else if (e.getSource() == quitBtn){
			System.exit(0);
		}
    }
    public void testing() {
    	JPanel paneeli = new JPanel();
		JTextField userName = new JTextField(10);
		JRadioButton masterRdio, normalRdio;
		masterRdio = new JRadioButton("Master user", false);
		normalRdio = new JRadioButton("Normal user", true);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(masterRdio);
		btnGroup.add(normalRdio);
		paneeli.add(userName);
		paneeli.add(masterRdio);
		paneeli.add(normalRdio);
		//JButton pois = new JButton("Sulje");
		//JButton addUser = new JButton("Add user");
		//paneeli.add(addUser);
		//paneeli.add(pois);
		
		int result = JOptionPane.showConfirmDialog(null, paneeli, 
	       "Enter name and level of new user", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(masterRdio.isSelected()) {
				users.add(new User(userName.getText(), "master"));
				JOptionPane.showMessageDialog(null, "Master user added");
			}
			else {
				users.add(new User (userName.getText(), "normal"));
				JOptionPane.showMessageDialog(null, "Normal user added");
			}
		}
    }
    public void testing2() {

    	JPanel paneeli = new JPanel(new GridLayout(8,1,1,1));
    	JTextField purchaser = new JTextField(10);
    	JLabel label1 = new JLabel("Name of purchaser");
    	JTextField item = new JTextField(20);
    	JLabel label2 = new JLabel("Name of item");
    	JTextField cost = new JTextField(10);
    	JLabel label3 = new JLabel("Cost of item");
    	JTextField date = new JTextField(10);
    	JLabel label4 = new JLabel("Date of purchase");
    	
    	paneeli.add(label1);
    	paneeli.add(purchaser);
    	paneeli.add(label2);
    	paneeli.add(item);
    	paneeli.add(label3);
    	paneeli.add(cost);
    	paneeli.add(label4);
    	paneeli.add(date);
    	
    	int result = JOptionPane.showConfirmDialog(null, paneeli, 
	       "Add event", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			events.add(new Event(purchaser.getText(), item.getText(), cost.getText(), 
						date.getText()));
			JOptionPane.showMessageDialog(null, "Event added succesfully");
		}	
    }
}