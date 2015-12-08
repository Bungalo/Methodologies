//Joonatan Heiskanen, Person number test task

public class PersNumber {
	private String pNumber;
	
	
	public PersNumber(String testNumber) throws Exception {
		
		try {
			//Check length
			if(testNumber.length() != 11){
				throw new Exception("Length error");
			}
			//Check day
			int day = Integer.parseInt(testNumber.substring(4,6));
			if(day < 1 || day >31) {
				throw new Exception("Day error");
			}
			//Check month
			int month = Integer.parseInt(testNumber.substring(2,4));
			if(month < 1 || month > 12) {
				throw new Exception("Month error");
			}
			//Try to get numerical value of year. Exception is thrown if not possible
			Integer.parseInt(testNumber.substring(0, 2));
			//Check the minus/plus character
			if(testNumber.charAt(6) != '-' && testNumber.charAt(6) != '+') {
				throw new Exception("-/+ error");
			}
			//Try to get numerical value of gender digit. Exception is thrown if not possible
			Integer.parseInt(testNumber.substring(9,10));
			int checkSum = Character.getNumericValue(testNumber.charAt(10));
			boolean helper = true;
			int testSum = 0;
			//Iterate until second to last digit, because last one is the check sum
			for (int i=0; i<testNumber.length()-1;i++) {
				//Skip -/+ digit
				if( i == 6) {
					helper = true;
					continue;
				}
				//Alternate between multiplying by 2 and multiplying by 1
				if (helper) {
					
					int testOverTen = Character.getNumericValue(testNumber.charAt(i)) * 2;
					if(testOverTen > 10) {
						String helper2 = Integer.toString(testOverTen);
						for(int j = 0;j<helper2.length() -1;j++) {
							testSum += Character.getNumericValue(helper2.charAt(j));
						}
					}
					else
						testSum += testOverTen ;
					helper = false;
				}
				else {
					testSum += Character.getNumericValue(testNumber.charAt(i));
					helper = true;
				}
			}
			//Subtract the last digit from 10
			testSum = 10 - (testSum % 10);
			//Test the sum
			if(checkSum != testSum || (checkSum == 0 & testSum != 10) ) {
				System.out.println(testSum);
				throw new Exception("Checksum error");
			}
			
		} catch(Exception e) {
			throw new Exception("Invalid personal number: " + e.getMessage());
		}
		this.pNumber = testNumber;
	}
	public String getDate() {
		return this.pNumber.substring(0,6);
	}
	public String getYear() {
		return this.pNumber.substring(0, 2);
	}
	public String getMonth() {
		return this.pNumber.substring(2, 4);
	}
	public String getSex() {
		int test = Character.getNumericValue(this.pNumber.charAt(9));
		if (test % 2 == 0)
			return "Female";
		else if (test % 2 == 1)
			return "Male";
		else
			return "Error in determining sex";
	}
	public int getCheckSum() {
		return Character.getNumericValue(this.pNumber.charAt(10));
	}
	
	public static void main(String[] args){
		PersNumber user1;
		
		try{//						212121-212
			user1 = new PersNumber("001231+1221");

			System.out.println("YYMMDD: " + user1.getDate());

			System.out.println("Month: "+ user1.getMonth());

			System.out.println("Year: "+ user1.getYear());

			System.out.println(user1.getSex());

			System.out.println("Checksum: "+ user1.getCheckSum());


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
