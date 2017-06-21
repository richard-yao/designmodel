package com.tvunetworks.richardyao.another;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Oct 21, 2016 4:06:09 PM
 */
public class TestResult {
	
	public static void main(String[] args) {
		PersonThinBuilder ptb = new PersonThinBuilder();
		PersonDirector pd = new PersonDirector(ptb);
		pd.createPerson();
		
		PersonFatBuilder pfb = new PersonFatBuilder();
		PersonDirector pdf = new PersonDirector(pfb);
		pdf.createPerson();
	}

}
