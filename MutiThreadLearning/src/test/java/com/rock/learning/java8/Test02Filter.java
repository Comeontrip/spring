package com.rock.learning.java8;

import java.util.List;

public class Test02Filter {

	public static void main(String[] args) {
		List<Person> pl = Person.createShortList();

		SearchCriteria search = SearchCriteria.getInstance();
		
		System.out.println("\n=== Western Pilot Phone List ===");
		
		pl.stream().filter(search.getCriteria("allPilot"))
		.forEach(Person::printWesternName);

		System.out.println("\n=== Eastern Draftee Phone List ===");
		pl.stream().filter(search.getCriteria("allDraftees"))
		.forEach(Person::printEasternName);
	}

}
