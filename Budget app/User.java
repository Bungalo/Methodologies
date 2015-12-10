import java.io.Serializable;

/**
 * @(#)User.java
 *
 *
 * @author 
 * @version 1.00 2015/11/12
 */


public class User implements Serializable{
	private String name;
	private String level;
    
    public User(String name, String level) {
    	this.name = name;
    	this.level = level;
    }
    
    public String getName() {
    	return name;
    }
    public String getLevel() {
    	return level;
    }
    public String toString() {
    	return ("Name: "+name + ", Level: "+level + "\n");
    }
}