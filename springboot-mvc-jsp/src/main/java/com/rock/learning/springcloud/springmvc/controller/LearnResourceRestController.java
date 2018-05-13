package com.rock.learning.springcloud.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rock.learning.springcloud.springmvc.model.LearnResource;
import com.rock.learning.springcloud.springmvc.util.LearnResourceHelper;

@RestController
public class LearnResourceRestController {
	
	@RequestMapping("/rest/learn")
	public LearnResource getLearnResource() {
		return LearnResourceHelper.createLearnResource("Rock", "Rock'Blog", "http://www.google.com");
	}
}
