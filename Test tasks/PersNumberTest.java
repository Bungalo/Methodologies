//Joonatan Heiskanen, Person number test task

import junit.framework.*;
import org.junit.Test;

public class PersNumberTest extends TestCase {
	private PersNumber person;
	@Test
	public void testPersNumber() throws Exception{
		person = new PersNumber("001231+1221");
		String expectedDate = "001231";
		assertEquals(expectedDate, person.getDate());
		String expectedYear = "00";
		assertEquals(expectedYear, person.getYear());
		String expectedMonth = "12";
		assertEquals(expectedMonth, person.getMonth());
		String expectedSex = "Female";
		assertEquals(expectedSex, person.getSex());
		int expectedSum = 1;
		assertEquals(expectedSum, person.getCheckSum());
	}
}
