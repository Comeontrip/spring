package com.rock.learning.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class SearchCriteria {
	private static SearchCriteria criteria ;
	
	private final Map<String, Predicate<Person>> searchMap = new HashMap<>();

	private SearchCriteria() {
		super();
		initSearchMap();
	}

	private void initSearchMap() {
		Predicate<Person> allDrivers = p -> p.getAge() >= 16;
		Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
		Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;

		searchMap.put("allDrivers", allDrivers);
		searchMap.put("allDraftees", allDraftees);
		searchMap.put("allPilot", allPilots);
	}
	
	public Predicate<Person> getCriteria(String predicateName){
		Predicate<Person> target;
		target = searchMap.get(predicateName);
		if(target == null) {
			System.out.println("Search Criteria not found...");
			System.exit(1);
		}
		
		return target;
	}
	
	public static SearchCriteria getInstance() {
		synchronized(SearchCriteria.class){
			if(null == criteria) {
				criteria = new SearchCriteria();
			}
		}
		
		return criteria; 
	}
}
