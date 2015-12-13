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
	private static String masterPassword;
    
	public User(String name, String level) {
    	this.name = name;
    	this.level = level;
    }
    public User(String name, String level, String password) {
    	this.name = name;
    	this.level = level;
		this.masterPassword = password;
    }
    
    public String getName() {
    	return name;
    }
    public String getLevel() {
    	return level;
    }
	public boolean comparePassword(String password) {
		if (password.equals(masterPassword))
			return true;
		else
			return false;
	}
    public String toString() {
    	return ("Name: "+name + ", Level: "+level + "\n");
    }
}