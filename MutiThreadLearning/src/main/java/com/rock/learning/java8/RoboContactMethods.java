package com.rock.learning.java8;

import java.util.List;

public class RoboContactMethods {

	public void callDrivers(List<Person> pl) {
		pl.forEach(p -> {
			if (p.getAge() >= 16) {
				roboCall(p);
			}
		});
	}

	public void emailDraftees(List<Person> pl) {
		if (null != pl) {
			pl.forEach(p -> {
				if (p.getAge() >= 18 && p.getAge() <= 25 & p.getGender() == Gender.MALE) {
					roboEmail(p);
				}
			});
		}
	}

	public void mailPilots(List<Person> pl) {
		if (null != pl) {
			pl.forEach(p -> {
				if (p.getAge() >= 23 && p.getAge() <= 65)
					roboMail(p);
			});
		}
	}

	public void roboCall(Person p) {
		System.out.println(
				"Calling " + p.getGivenName() + "" + p.getSurName() + " age " + p.getAge() + " at " + p.getPhone());
	}

	public void roboEmail(Person p) {
		System.out.println(
				"Emailing " + p.getGivenName() + "" + p.getSurName() + " age " + p.getAge() + " at " + p.getEmail());
	}

	public void roboMail(Person p) {
		System.out.println(
				"Mailing " + p.getGivenName() + "" + p.getSurName() + " age " + p.getAge() + " at " + p.getAddress());
	}
}
