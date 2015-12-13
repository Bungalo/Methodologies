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
import java.util.ArrayList;
import java.util.Calendar;
import java.io.*;

public class BudgetProto extends JFrame implements ActionListener {
	
	private JButton checkBudget,addEvent,masterOptions, printUsers, quitBtn, addUser, addBudget, closeMaster, chooseBudget;
	private JButton delUser, saveBudget, loadBudget, delEvent;
	private String password;
	private static ArrayList<Budget> budgets = new ArrayList<Budget>();
	private static Budget selectedBudget;
	private static BudgetProto window;
	
	public BudgetProto() {
	}
	public BudgetProto(int a) {
		
		checkBudget = new JButton("Show current budget");
		addEvent = new JButton("Add purchase event");
		chooseBudget = new JButton("Select budget");
		saveBudget = new JButton("Save budget");
		loadBudget = new JButton("Load Budget");
		masterOptions= new JButton("Master options");
		quitBtn = new JButton("Quit");
		checkBudget.addActionListener(this);
		addEvent.addActionListener(this);
		chooseBudget.addActionListener(this);
		saveBudget.addActionListener(this);
		loadBudget.addActionListener(this);
		masterOptions.addActionListener(this);
		quitBtn.addActionListener(this);
		setLayout(new GridLayout(8, 1, 1, 1));
		JPanel title = new JPanel(){
			public void paint(Graphics g){
				g.setFont(new java.awt.Font("Arial",Font.BOLD,30));
				g.setColor(java.awt.Color.LIGHT_GRAY);
				g.drawString("Budget Application",10,44);
				g.setColor(java.awt.Color.BLACK);
				g.drawString("Budget Application",14,40);
			}
		};
		add(title);
		add(checkBudget);
		add(addEvent);
		add(chooseBudget);
		add(saveBudget);
		add(loadBudget);
		add(masterOptions);
		add(quitBtn);
		
		setResizable(false);
    	setTitle("Budget application");
    	setSize(300,450);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
		
	}

    public static void main(String[] args ) {
    	
    	try {
    	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	    } catch(Exception e) {e.printStackTrace();}
    	window = new BudgetProto(1);
		
    }
    
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == checkBudget){
    		//System.out.println(System.getProperty("user.dir"));
    		//ImageIcon icon = new ImageIcon("src\\history.png");
    		//JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, icon);
    		if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
    		else
    			checkBudget();
		}
    	else if (e.getSource() == masterOptions){
			if(budgets.size()==0 || selectedBudget.getUsers().size() == 0)
				masterOptions();
			else {
				JPanel paneeli = new JPanel();
				JTextField fieldi = new JTextField(10);
				paneeli.add(fieldi);
				int result = JOptionPane.showConfirmDialog(null, paneeli, 
			   "Enter master password", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if (selectedBudget.getUsers().get(0).comparePassword(fieldi.getText()))
						masterOptions();
					else
						JOptionPane.showMessageDialog(null, "Invalid password","Error", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
				
		}
    	else if (e.getSource() == chooseBudget){
    		if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
    		else
    			chooseBudget();
		}
		else if (e.getSource() == printUsers){
			if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
			else {
				String userString = "";
				if(selectedBudget.getUsers().size() == 0)
					userString = "No users";
				else {
					for(int i=0; i<selectedBudget.getUsers().size();i++) {
						userString +=(i+1) + ". "+ selectedBudget.getUsers().get(i);
					}
				}
		    	JOptionPane.showMessageDialog(null,userString,"Userlist", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (e.getSource() == addEvent){
			if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
			else
				addEvent();
		}
		else if (e.getSource() == quitBtn){
			System.exit(0);
		}
		else if (e.getSource() == addUser){
			if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
			else
				addUser();
		}
		else if (e.getSource() == addBudget){
			addBudget();
		}
		else if (e.getSource() == delEvent){
			if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
			else
				deleteEvent();
		}
		else if (e.getSource() == delUser){
			if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
			else if(selectedBudget.getUsers().size() == 0)
				JOptionPane.showMessageDialog(null, "No users","", JOptionPane.INFORMATION_MESSAGE);
			else
				deleteUser();
		}
		else if (e.getSource() == saveBudget){
			if(budgets.size() == 0)
				JOptionPane.showMessageDialog(null, "You must first create a budget","No budget selected", JOptionPane.INFORMATION_MESSAGE);
			else 
				saveBudget();
		}
		else if (e.getSource() == loadBudget){
				loadBudget();
		}
		else
			JOptionPane.showMessageDialog(null, "Something went horribly wrong, contact administrator","Error", JOptionPane.ERROR_MESSAGE);
    }
    public void checkBudget() {
    	final JFrame frame = new JFrame();
    	frame.setLayout(new BorderLayout(10,0));
    	String eventString = "";
    	ArrayList<Event> events = selectedBudget.getEvents();
    	for(int i=0; i<events.size();i++) {
    			eventString +="  " + (i+1) + ". "+ events.get(i);
		}
    	JPanel paneeli = new JPanel(new BorderLayout(10,0));
    	paneeli.setSize(2000,600);
    	JTextArea area = new JTextArea(eventString);
    	String info = selectedBudget.getName() + " Remaining: " + selectedBudget.getRemaining();
    	JLabel budgetInfo = new JLabel(info);
    	budgetInfo.setFont(new Font("Arial", Font.BOLD, 22));
    	JButton closeCheck = new JButton("Back");
    	area.setSize(2000,600);
    	area.setEditable(false);
    	area.setBackground(paneeli.getBackground());
		area.setFont(new Font("Arial", Font.BOLD, 16));
    	paneeli.add(budgetInfo, BorderLayout.NORTH);
    	paneeli.add(area, BorderLayout.CENTER);
    	frame.add(paneeli, BorderLayout.CENTER);
    	frame.add(closeCheck, BorderLayout.SOUTH);
    	
    	closeCheck.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			frame.dispose();
				repaint();
    			//window.setVisible(true);
    		}
    	});
    	
    	frame.setResizable(true);
    	frame.setTitle("Budget application");
    	frame.setSize(1000,700);
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	
    }
    public void masterOptions() {
    	
    	window.setVisible(false);
    	final JFrame frame = new JFrame();
    	frame.setLayout(new GridLayout(6,1));
    	addUser = new JButton("Add user");
    	printUsers = new JButton("Userlist");
    	delUser = new JButton("Delete user");
    	addBudget = new JButton("Add budget");
		delEvent = new JButton("Delete event");
    	closeMaster = new JButton("Back");
    	
    	addUser.addActionListener(this);
    	addBudget.addActionListener(this);
    	delUser.addActionListener(this);
    	printUsers.addActionListener(this);
		delEvent.addActionListener(this);
    	closeMaster.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			frame.dispose();
    			window.setVisible(true);
				repaint();
    		}
    	});
    	
    	frame.add(addUser);
    	frame.add(delUser);
    	frame.add(printUsers);
    	frame.add(addBudget);
		frame.add(delEvent);
    	frame.add(closeMaster);
    	
    	frame.setResizable(false);
    	frame.setTitle("Master options");
    	frame.setSize(300,450);
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	
    }
    public void addUser() {

    	JPanel paneeli = new JPanel();
		JTextField userName = new JTextField(10);
		JTextField password = new JTextField(10);
		JRadioButton masterRdio, normalRdio;
		masterRdio = new JRadioButton("Master user", false);
		normalRdio = new JRadioButton("Normal user", true);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(masterRdio);
		btnGroup.add(normalRdio);
		paneeli.add(userName);
		paneeli.add(password);
		paneeli.add(masterRdio);
		paneeli.add(normalRdio);
		//JButton pois = new JButton("Sulje");
		//JButton addUser = new JButton("Add user");
		//paneeli.add(addUser);
		//paneeli.add(pois);
		
		int result = JOptionPane.showConfirmDialog(null, paneeli, 
	       "Enter name, password and level of new user", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(userName.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Name cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
			else {
				if(masterRdio.isSelected()) {
					if(password.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Master password cannot be empty","Error", JOptionPane.ERROR_MESSAGE);
					selectedBudget.addUser(new User (userName.getText(), "master", password.getText()));
					System.out.println(password.getText());
					JOptionPane.showMessageDialog(null, "Master user added");
				}
				else {
					selectedBudget.addUser(new User (userName.getText(), "normal"));
					JOptionPane.showMessageDialog(null, "Normal user added");
				}
			}
		}
    }
    public void deleteUser() {
    	String userString = "";
    	ArrayList<User> users = selectedBudget.getUsers();
    	for(int i=0; i<users.size();i++) {
    			userString +="  " + (i+1) + ". "+ users.get(i);
		}
    	JPanel paneeli = new JPanel(new BorderLayout(10,0));
    	JPanel input = new JPanel(new GridLayout(2,1));
    	JTextField choice = new JTextField(2);
    	JLabel label1 = new JLabel("Input the number of the user you want to delete");
    	JTextArea text = new JTextArea(userString);
    	text.setEditable(false);
    	text.setBackground(paneeli.getBackground());
    	System.out.println(userString);
    	input.add(label1);
    	input.add(choice);
    	paneeli.add(text, BorderLayout.CENTER);
    	paneeli.add(input, BorderLayout.SOUTH);
    	
		int result =JOptionPane.showConfirmDialog(null,paneeli,"List of users", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			String choiceString = choice.getText();
			if(isNumeric(choiceString) == -1.0) {
				JOptionPane.showMessageDialog(null, "Only input a number","Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int choiceInt = Integer.parseInt(choiceString);
					if(choiceInt > users.size() || choiceInt < 1) {
						JOptionPane.showMessageDialog(null, "Invalid index","Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						users.remove(choiceInt -1);
						JOptionPane.showMessageDialog(null, "User deleted");
					}
			}
		}
    }
    
    public void addBudget() {
    	JPanel paneeli = new JPanel(new GridLayout(4,1));
    	JTextField budgetName = new JTextField(20);
    	JLabel label1 = new JLabel("Name of budget, cannot be empty");
    	JTextField budgetSum = new JTextField(10);
    	JLabel label2 = new JLabel("Amount, numbers only");
    	
    	paneeli.add(label1);
    	paneeli.add(budgetName);
    	paneeli.add(label2);
    	paneeli.add(budgetSum);
    	
    	int result = JOptionPane.showConfirmDialog(null, paneeli, 
    		       "Add budget", JOptionPane.OK_CANCEL_OPTION);
    	if (result == JOptionPane.OK_OPTION) {
    		String name = budgetName.getText();
    		String sumString = budgetSum.getText();
    		if(name.equals("") || isNumeric(sumString) == -1.0) {
    			JOptionPane.showMessageDialog(null, "Error creating budget. ","Error", JOptionPane.ERROR_MESSAGE);
    		}
    		else{
    			Double sumNumeric = Double.parseDouble(sumString);
    			Budget newBudget = new Budget(name,sumNumeric);
    			budgets.add(newBudget);
    			selectedBudget = newBudget;
    			JOptionPane.showMessageDialog(null, "Budget added succesfully");
    		}
    	}
    }
    public void chooseBudget() {
    	
    	String budgetString = "";
    	for(int i=0; i<budgets.size();i++) {
    		if(selectedBudget.getName().equals(budgets.get(i).getName()))
    			budgetString +="->" + (i+1) + ". "+ budgets.get(i);
    		else
    			budgetString +="  " + (i+1) + ". "+ budgets.get(i);
		}
    	JPanel paneeli = new JPanel(new BorderLayout(10,0));
    	JPanel input = new JPanel(new GridLayout(2,1));
    	JTextField choice = new JTextField(2);
    	JLabel label1 = new JLabel("Input the number of the budget you want to select");
    	JTextArea text = new JTextArea(budgetString);
    	text.setEditable(false);
    	text.setBackground(paneeli.getBackground());
    	System.out.println(budgetString);
    	input.add(label1);
    	input.add(choice);
    	paneeli.add(text, BorderLayout.CENTER);
    	paneeli.add(input, BorderLayout.SOUTH);
    	
		int result =JOptionPane.showConfirmDialog(null,paneeli,"List of budgets", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			String choiceString = choice.getText();
			if(isNumeric(choiceString) == -1.0) {
				JOptionPane.showMessageDialog(null, "Only input a number","Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				System.out.println("asd");
				int choiceInt = Integer.parseInt(choiceString);
					if(choiceInt > budgets.size() || choiceInt < 1) {
						JOptionPane.showMessageDialog(null, "Invalid index","Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						selectedBudget = budgets.get(choiceInt - 1);
						JOptionPane.showMessageDialog(null, "Budget number " + choiceInt + " selected");
					}
			}
		}
    }
    public void addEvent() {

    	JPanel paneeli = new JPanel(new GridLayout(8,1,1,1));
    	JTextField purchaser = new JTextField(10);
    	JLabel label1 = new JLabel("Username of purchaser");
    	JTextField item = new JTextField(20);
    	JLabel label2 = new JLabel("Name of item, max. 20 characters");
    	JTextField cost = new JTextField(10);
    	JLabel label3 = new JLabel("Cost of item, numbers only");
    	JTextField date = new JTextField(10);
    	JLabel label4 = new JLabel("Date of purchase, DD.MM.YYYY");
    	
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
    	boolean userFound = false;
		if (result == JOptionPane.OK_OPTION) {
			//correctDate(date.getText());
			if(item.getText().equals("") || item.getText().length() > 20|| isNumeric(cost.getText()) == -1.0  || !correctDate(date.getText())) {
				/*System.out.println(item.getText().equals(""));
				System.out.println(isNumeric(cost.getText()));
				System.out.println(!correctDate(date.getText()));*/
				JOptionPane.showMessageDialog(null, "Error creating purchase event."+
													"\nMake sure all fields are filled correctly","Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				ArrayList<User> users = selectedBudget.getUsers();
				ArrayList<Event> events = selectedBudget.getEvents();
				for(int i=0; i < users.size(); i++) {
					if(users.get(i).getName().equals(purchaser.getText())) {
						String itemName = item.getText();
						for(int j=itemName.length();j<21;j++) {
							itemName+= " ";
						}
						events.add(new Event(purchaser.getText(), itemName, cost.getText(), date.getText()));
						selectedBudget.deductRemaining(Double.parseDouble(cost.getText()));
						JOptionPane.showMessageDialog(null, "Event added succesfully");
						userFound = true;
						break;
					}		
				}
				if(!userFound) {
					JOptionPane.showMessageDialog(null, "User does not exist","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		repaint();
    }
	public void deleteEvent(){
		String userString = "";
    	ArrayList<Event> events = selectedBudget.getEvents();
    	for(int i=0; i<events.size();i++) {
    			userString +="  " + (i+1) + ". "+ events.get(i);
		}
    	JPanel paneeli = new JPanel(new BorderLayout(10,0));
    	JPanel input = new JPanel(new GridLayout(2,1));
    	JTextField choice = new JTextField(2);
    	JLabel label1 = new JLabel("Input the number of the event you want to delete");
    	JTextArea text = new JTextArea(userString);
    	text.setEditable(false);
    	text.setBackground(paneeli.getBackground());
    	System.out.println(userString);
    	input.add(label1);
    	input.add(choice);
    	paneeli.add(text, BorderLayout.CENTER);
    	paneeli.add(input, BorderLayout.SOUTH);
    	
		int result =JOptionPane.showConfirmDialog(null,paneeli,"List of events", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			String choiceString = choice.getText();
			if(isNumeric(choiceString) == -1.0) {
				JOptionPane.showMessageDialog(null, "Only input a number","Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int choiceInt = Integer.parseInt(choiceString);
					if(choiceInt > events.size() || choiceInt < 1) {
						JOptionPane.showMessageDialog(null, "Invalid index","Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						events.remove(choiceInt -1);
						JOptionPane.showMessageDialog(null, "Event deleted");
					}
			}
		}
	}
    public void saveBudget() {

    	try{
 		   
    		FileOutputStream fout = new FileOutputStream("d:\\budget.ser");
    		ObjectOutputStream oos = new ObjectOutputStream(fout);
    		oos.writeObject(selectedBudget);
    		fout.flush();
    		oos.flush();
    		oos.close();
    		System.out.println("Done");
    		   
    	   }catch(Exception ex){
    		   ex.printStackTrace();
    	   }

	}
    public void loadBudget() {
		
    	Budget budget;
   	 
 	   try{
 		    
 		   FileInputStream fin = new FileInputStream("d:\\budget.ser");
 		   ObjectInputStream ois = new ObjectInputStream(fin);
 		   budget = (Budget) ois.readObject();
 		   ois.close();
 		  budgets.add(budget);
 	 	  selectedBudget = budget;
 	 	  
 		  System.out.println(budget.getName());
 		  System.out.println(budget.getSum());
 		  System.out.println(budget.getUsers());
 		  System.out.println(budget.getEvents());
 		   
 	   }catch(Exception ex){
 		   ex.printStackTrace();
 	   }
 	   
	}
    public double isNumeric(String string) {
    	try {
    		double value = Double.parseDouble(string);
    		return value;
    	}catch (Exception e) {
    		System.err.println(e.getStackTrace());
    		return -1.0;
    	}
    }
    public boolean correctDate(String date) {
    	
    	try {//12.12.1212
    		
    		if(date.length() != 10)
    			return false;
    		
    		int day = Integer.parseInt(date.substring(0, 2));
    		int month = Integer.parseInt(date.substring(3, 5));
    		int year = Integer.parseInt(date.substring(6, 10));
    		if(day < 1 || day > 31 || (month == 2 && day > 28))
    			return false;
    		if (month < 1 || month > 12)
    			return false;
    		if (year < 1900)
    			return false;
    		Calendar now = Calendar.getInstance();
    		Calendar purchase = Calendar.getInstance();
    		purchase.clear();
    		purchase.set(year,month-1,day);
    		
    		if(now.before(purchase)){
    			return false;
    		}
    		/*System.out.println("Käynti");
    		System.out.println(now.get(Calendar.DAY_OF_MONTH));
    		System.out.println(purchase.get(Calendar.DAY_OF_MONTH));
    		System.out.println(now.get(Calendar.MONTH));
    		System.out.println(purchase.get(Calendar.MONTH));
    		System.out.println(now.get(Calendar.YEAR));
    		System.out.println(purchase.get(Calendar.YEAR));
    		System.out.println("Tulos: "+now.before(purchase));*/
    	}catch(Exception e) {
    		System.err.println(e.getStackTrace());
    		return false;
    	}
    	return true;
    }
}