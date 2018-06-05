package com.rock.learning.java8;

import java.util.List;
import java.util.function.Function;

public class NameTestNew {

	public static void main(String[] args) {
		System.out.println("\n==== NameTestNew02 ====");

		List<Person> list1 = Person.createShortList();

		System.out.println("=== Custome List ====");
		list1.forEach(person -> {
			System.out.println(person.printCustom(p -> "Name: " + p.getGivenName() + " Email: " + p.getEmail()));
		});

		Function<Person, String> westernStyle = p -> {
			return "\nName: " + p.getGivenName() + " " + p.getSurName() + "\n" + "Age: " + p.getAge() + "  "
					+ "Gender: " + p.getGender() + "\n" + "EMail: " + p.getEmail() + "\n" + "Phone: " + p.getPhone()
					+ "\n" + "Address: " + p.getAddress();
		};

		Function<Person, String> easternStyle = p -> {
			return "\nName: " + p.getSurName() + " " + p.getGivenName() + "\n" + "Age: " + p.getAge() + "  "
					+ "Gender: " + p.getGender() + "\n" + "EMail: " + p.getEmail() + "\n" + "Phone: " + p.getPhone()
					+ "\n" + "Address: " + p.getAddress();
		};

		System.out.println("\n==== Western List ====");

		list1.forEach(person -> {
			System.out.println(person.printCustom(westernStyle));
		});
		
		System.out.println("\n===Eastern List===");
		list1.forEach(person -> {
			System.out.println(person.printCustom(easternStyle));
		});
	}

}
