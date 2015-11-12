/**
 * @(#)Event.java
 *
 *
 * @author 
 * @version 1.00 2015/10/14
 */


public class Event {
	
	private String price;
	private String name;
	private String purchDate;
	private String purchaser;

    public Event(String purchaser, String name, String price, String purchDate) {
    	
    	this.purchaser = purchaser;
    	this.name = name;
    	this.price = price;
    	this.purchDate = purchDate;
    }
    
    public String toString() {
    	System.out.println("Not yet implemented");
    	return "";
    }
}