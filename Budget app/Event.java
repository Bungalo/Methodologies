import java.io.Serializable;

/**
 * @(#)Event.java
 *
 *
 * @author 
 * @version 1.00 2015/10/14
 */


public class Event implements Serializable{
	
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
    	return (purchDate + "\t\t" + name + "\t" + price + "\t\t" + purchaser + "\n");
    }
    
    public String getPrice() {
    	return this.price;
    }
    public String getName() {
    	return this.name;
	}
	public String getPurchDate() {
	 	return this.purchDate;
	}
	public String getPurchaser() {
		return this.purchaser;
	}
}