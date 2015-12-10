import java.io.Serializable;
import java.util.ArrayList;

public class Budget implements Serializable{
	private String name;
	private double sum;
	private double remaining;
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<User> users = new ArrayList<User>();
	
	public Budget(String name, double sum) {
		this.name = name;
		this.sum = sum;
		this.remaining = sum;
	}
	public double getSum() {
		return this.sum;
	}
	public String getName() {
		return this.name;
	}
	public double getRemaining() {
		return this.remaining;
	}
	public void deductRemaining(double amount) {
		remaining -= amount;
	}
	public ArrayList<Event> getEvents() {
		return events;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void addEvent(Event event) {
		events.add(event);
	}
	public void addUser(User user){
		users.add(user);
	}
	 public String toString() {
		    	
		 	return ("Name: " + name + ", Sum: " + sum+ "\n");
		    
	 }
}
