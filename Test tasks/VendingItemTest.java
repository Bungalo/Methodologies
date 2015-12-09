//Joonatan Heiskanen, Vending test problems 1 and 2

import junit.framework.TestCase;
import org.junit.Test;

public class VendingItemTest extends TestCase {
	/* Problem 1
	@Test
	public void test() {
		Vending test = new Vending(2,2);
		assertEquals(0.0, test.getBalance());
		test.addMoney(1.0);
		assertEquals(1.0, test.getBalance());
	}
	public void test2() {
		Vending test = new Vending(2,2);
		test.addMoney(1.25);
		assertEquals(1.25, test.getBalance());
		test.select("Candy");
		assertEquals(0.0, test.getBalance());
		
	}*/
	//Problem 2
	//-setUp() is run before every test. If the Vending-object is not static, a new one is created for every test.
	//-Question is a bit weird because the tests work fine with v as static and either test commented out. My answer, I would do nothing to 
	// make it work. If both tests need to be uncommented, then you just need to change the assertTrue() value of test3 to 1.25.
    static Vending v = new Vending(5,5);
    
    public void setUp() {
      v.addMoney(1.5);
    }
  
    public void test2() {
      v.select("Gum");//0.5
      assertTrue(v.getBalance()==1.0);
    }
  
    public void test3() {
      v.select("Candy");//1.25
      assertTrue(v.getBalance()== 0.25);
    }

}
