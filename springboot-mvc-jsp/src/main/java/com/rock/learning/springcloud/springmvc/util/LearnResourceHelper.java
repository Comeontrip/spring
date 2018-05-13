package com.rock.learning.springcloud.springmvc.util;

import java.util.ArrayList;
import java.util.List;

import com.rock.learning.springcloud.springmvc.model.LearnResource;

public class LearnResourceHelper {

	public static List<LearnResource> getLearningResources() {
		List<LearnResource> resoruces = new ArrayList<LearnResource>();
		resoruces.add(createLearnResource("Rock", "Rock's Blog", "https://www.google.com"));
		resoruces.add(createLearnResource("DBS", "DBS Bank", "http://www.dbs.com"));
		return resoruces;
	}
	
	public static LearnResource createLearnResource(String author, String title, String url) {
		LearnResource resource = new LearnResource(author,title,url);
		return resource;
	}
}
