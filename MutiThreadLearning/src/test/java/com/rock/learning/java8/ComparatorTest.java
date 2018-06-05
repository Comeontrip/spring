package com.rock.learning.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

	public static void main(String[] args) {
		List<Person> personList = Person.createShortList();
		
		Collections.sort(personList, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				return p1.getSurName().compareTo(p2.getSurName());
			}
		});
		
		System.out.println("=== Sorted Asc SurName ====");
		
		for(Person p: personList) {
			p.printName();
		}
		
		//User Lambda instead
		
		System.out.println("=== Sorted Asc SurName ====");
		
		Collections.sort(personList, (Person p1, Person p2) -> p1.getSurName().compareTo(p2.getSurName())); 
		personList.forEach(item -> {item.printName();});
		
		System.out.println("=== Sorted Asc SurName ====");
		personList.forEach(item -> item.printName());
	}

}
