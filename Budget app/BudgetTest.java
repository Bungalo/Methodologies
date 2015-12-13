import static org.junit.Assert.*;
import junit.framework.*;
import org.junit.Before;
import org.junit.Test;

public class BudgetTest {
	private Budget budget = new Budget("budget", 1000);
	@Before
	public void setUp() throws Exception {
		budget = new Budget("budget", 1000);
	}

	@Test
	//Check that user names and levels are saved into the Arraylist holding the added users
	public void testUser() {
		budget.addUser(new User("bob", "normal"));
		budget.addUser(new User("master", "master", "password"));
		assertEquals("bob", budget.getUsers().get(0).getName());
		assertEquals("normal", budget.getUsers().get(0).getLevel());
		assertEquals("master", budget.getUsers().get(1).getName());
		assertEquals("master", budget.getUsers().get(1).getLevel());
		assertTrue(budget.getUsers().get(1).comparePassword("password"));
	}
	@Test
	//Check that events are properly saved into the Arraylist holding the added event
	public void testEvent() {
		budget.addEvent(new Event("purchaser", "ItemName", "20", "12122015"));
		budget.addEvent(new Event("purchaser2", "ItemName2", "202", "01122015"));
		assertEquals("purchaser", budget.getEvents().get(0).getPurchaser());
		assertEquals("ItemName", budget.getEvents().get(0).getName());
		assertEquals("20", budget.getEvents().get(0).getPrice());
		assertEquals("12122015", budget.getEvents().get(0).getPurchDate());
		assertEquals("purchaser2", budget.getEvents().get(1).getPurchaser());
		assertEquals("ItemName2", budget.getEvents().get(1).getName());
		assertEquals("202", budget.getEvents().get(1).getPrice());
		assertEquals("01122015", budget.getEvents().get(1).getPurchDate());
	}
	@Test
	public void testGetName() {
		assertEquals("budget", budget.getName());
	}
	@Test
	//Smallest sum is one cent, so if the values differ below that, it does not matter
	public void testGetSum() {
		assertEquals(1000, budget.getSum(), 0.0099999);
	}
	@Test
	public void testRemainingAndDeduct() {
		assertEquals(1000, budget.getRemaining(), 0.0099999);
		budget.deductRemaining(500);
		assertEquals(500, budget.getRemaining(), 0.0099999);
		//assertEquals(400, budget.getRemaining(), 0.0099999); fails
	}

}
