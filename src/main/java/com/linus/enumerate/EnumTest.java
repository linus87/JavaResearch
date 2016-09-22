package com.linus.enumerate;

public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Gender.MALE.name());
		System.out.println(Gender.MALE.getName());
		System.out.println(Gender.FEMALE.name());
		System.out.println(Gender.FEMALE.getName());
		System.out.println("MALE ordinal: " + Gender.MALE.ordinal());
		System.out.println("MALE id: " + Gender.MALE.getId());
		System.out.println("FEMALE ordinal: " + Gender.FEMALE.ordinal());
		System.out.println("FEMALE id: " + Gender.FEMALE.getId());
		
		 // use values()
	    Week allWeek[] = Week.values();
	    for (Week aday : allWeek) {
	      System.out.println(aday);
	    }
	}

}
